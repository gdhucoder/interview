Robot Localization

Pose Array - particalecloud

![2020-02-02_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_001.jpg)

## MCL

Monte Carlo Localization, particle filter localization.

Guesses  are known as particles.



## AMCL

Adaptive Monte Carlo Localization. AMCL pacakge provides the amcl node which uses the MCL system in order to track the localization of a robot moving in a 2D space. 

**Adaptive** means we can configure some parameters used in this algorithm.

**Arrows** indicate the most probable position the robot was in, and its orientation.

![2020-02-02_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_005.jpg)

Map data:



Change Map:

Set the map file path, relaunch the amcl.launch and rviz

```xml
<launch>

  <!-- Run the map server -->
  <arg name="map_file" default="$(find husky_navigation)/maps/playpen_map.yaml"/>
  <node name="map_server" pkg="map_server" type="map_server" args="$(arg map_file)" />
```

Now the map has changed. (the maps directory is located int husky_navigation package)

![2020-02-02_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_007.jpg)



AMCL node launch file

```
odom_model_type
odom_frame_id
base_frame_id
global_frame_id
use_map_topic
```

Services provided by the amcl node

**global_localization (std_srvs/Empty)**: Initiate global localization, wherein all particles are dispersed randomly throughout the free space in the map.

This is used to restart the positions of the particles.

![2020-02-03_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-03_001.jpg)

Square_move.py

```python
#!/usr/bin/env python
import rospy
from geometry_msgs.msg import Twist, PoseWithCovarianceStamped
from std_srvs.srv import Empty, EmptyRequest
import time
import math

class MoveHusky():
    
    def __init__(self):
        
        # Init Publisher
        self.husky_vel_publisher = rospy.Publisher('/cmd_vel', Twist, queue_size=1)
        self.cmd = Twist()
        # Init Subscriber
        self.amcl_pose_sub = rospy.Subscriber('/amcl_pose', PoseWithCovarianceStamped, self.sub_callback)
        self.sub_msg = PoseWithCovarianceStamped()
        # Initialize Service Client
        rospy.wait_for_service('/global_localization')
        self.disperse_particles_service = rospy.ServiceProxy('/global_localization', Empty)
        self.srv_request = EmptyRequest()
        # Other stuff
        self.ctrl_c = False
        rospy.on_shutdown(self.shutdownhook)
        self.rate = rospy.Rate(10)
        
    def shutdownhook(self):
        
        # works better than the rospy.is_shut_down()
        self.stop_husky()
        self.ctrl_c = True

    def stop_husky(self):
        
        rospy.loginfo("Shutdown time! Stop the robot")
        self.cmd.linear.x = 0.0
        self.cmd.angular.z = 0.0
        i = 0
        
        while i < 20:
            self.husky_vel_publisher.publish(self.cmd)
            self.rate.sleep()
            i += 1

    def move_forward(self, linear_speed=0.5, angular_speed=0.0):
        
        self.cmd.linear.x = linear_speed
        self.cmd.angular.z = angular_speed
        i = 0
        
        while i < 50:
            self.husky_vel_publisher.publish(self.cmd)
            self.rate.sleep()
            i += 1
        
    def turn(self, linear_speed=0.0, angular_speed=0.8):
        
        self.cmd.linear.x = linear_speed
        self.cmd.angular.z = angular_speed
        i = 0
        
        while i < 25:
            self.husky_vel_publisher.publish(self.cmd)
            self.rate.sleep()
            i += 1
    
    def move_square(self):
        
        i = 0
        
        while not self.ctrl_c and i < 4:
            # Move Forwards
            rospy.loginfo("######## Going Forwards...")
            self.move_forward()
            self.stop_husky()
            # Turn
            rospy.loginfo("######## Turning...")
            self.turn()
            self.stop_husky()
            i += 1
            
        self.stop_husky()
        rospy.loginfo("######## Finished Moving in a Square")
        
    def call_service(self):
        
        rospy.loginfo("######## Calling Service...")
        result = self.disperse_particles_service(self.srv_request)
        
    def sub_callback(self, msg):
        
        self.sub_msg = msg

    def calculate_covariance(self):
        
        rospy.loginfo("######## Calculating Covariance...")
        cov_x = self.sub_msg.pose.covariance[0]
        cov_y = self.sub_msg.pose.covariance[7]
        cov_z = self.sub_msg.pose.covariance[35]
        rospy.loginfo("## Cov X: " + str(cov_x) + " ## Cov Y: " + str(cov_y) + " ## Cov Z: " + str(cov_z))
        cov = (cov_x+cov_y+cov_z)/3
        
        return cov
        
            
if __name__ == '__main__':
    rospy.init_node('move_husky_node', anonymous=True)
    MoveHusky_object = MoveHusky()
    
    cov = 1
    
    while cov > 0.65:
        MoveHusky_object.call_service()
        MoveHusky_object.move_square()
        cov = MoveHusky_object.calculate_covariance()
        rospy.loginfo("######## Total Covariance: " + str(cov))
        if cov > 0.65:
            rospy.loginfo("######## Total Covariance is greater than 0.65. Repeating the process...")
        else:
            rospy.loginfo("######## Total Covariance is lower than 0.65. Robot correctly localized!")
            rospy.loginfo("######## Exiting...")
        
    
    
```

launch file:

```xml
<launch>
    <node
    pkg="initialize_particles" type="square_move.py" name="move_husky_node" output="screen" />
</launch>
```

First disperse particles around the environment

![2020-02-04_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-04_002.jpg)

Second, move robot around in order to location according to **cov**

![2020-02-04_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-04_001.jpg)

```
1. launch amcl
roslaunch husky_navigation move_base_demo.launch
2. rosrun rviz rviz
3. roslaunch initialize_particles call_move_square.launch
```

Use Python code to send goals:

```
roslaunch husky_navigation move_base_demo.launch
roslaunch send_goals send_goals_client.launch
```

send_goal_client.py

```python
#! /usr/bin/env python
import rospy
import time
import actionlib
from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal, MoveBaseResult, MoveBaseFeedback

# definition of the feedback callback. This will be called when feedback
# is received from the action server
# it just prints a message indicating a new message has been received
def feedback_callback(feedback):

    print('[Feedback] Going to Goal Pose...')

# initializes the action client node
rospy.init_node('move_base_action_client')

# create the connection to the action server
client = actionlib.SimpleActionClient('/move_base', MoveBaseAction)
# waits until the action server is up and running
client.wait_for_server()

# creates a goal to send to the action server
goal = MoveBaseGoal()
goal.target_pose.header.frame_id = 'map'
goal.target_pose.pose.position.x = 1.16
goal.target_pose.pose.position.y = -4.76
goal.target_pose.pose.position.z = 0.0
goal.target_pose.pose.orientation.x = 0.0
goal.target_pose.pose.orientation.y = 0.0
goal.target_pose.pose.orientation.z = 0.75
goal.target_pose.pose.orientation.w = 0.66

# sends the goal to the action server, specifying which feedback function
# to call when feedback received
client.send_goal(goal, feedback_cb=feedback_callback)

# Uncomment these lines to test goal preemption:
#time.sleep(3.0)
#client.cancel_goal()  # would cancel the goal 3 seconds after starting

# wait until the result is obtained
# you can do other stuff here instead of waiting
# and check for status from time to time
# status = client.get_state()
# check the client API link below for more info

client.wait_for_result()

print('[Result] State: %d'%(client.get_state()))
```

