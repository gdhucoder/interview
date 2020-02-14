Code: 

```xml
<launch>
    <node pkg="send_goals" type="make_plan_caller.py" name="make_plan_client" output="screen">
    </node>
</launch>
```



```python
#! /usr/bin/env python
from decimal import *

# getcontext().prec = 10
from math import pow,sqrt
import rospy
from nav_msgs.srv import GetPlan, GetPlanRequest
from nav_msgs.msg import Odometry
import sys
import time

class CalculateGoalPathLength():
    
    def __init__(self):
        self.odom_sub = rospy.Subscriber('/husky_velocity_controller/odom', Odometry, self.sub_odom_callback)
        self.odom_msg = Odometry()
        self.plan_req = GetPlanRequest()
        rospy.wait_for_service('/move_base/make_plan')
        self.make_plan_service = rospy.ServiceProxy('/move_base/make_plan', GetPlan)
        print('init success.')
    
    def wait_make_plan(self):
      # only one time 
        msg = rospy.wait_for_message('/husky_velocity_controller/odom', Odometry)
        self.odom_msg = msg
        self.gen_plan_request_msg()
        self.raw_plan = self.make_plan_service(self.plan_req)
        self.gen_plan_request_msg()
        self.get_plan_path_length()
        print(msg.pose.pose)
        print(self.path_lengh)
    
    def sub_odom_callback(self, msg):
        print('sub_odom_callback')


    def gen_plan_request_msg(self):
        self.plan_req.start.header.frame_id = 'map'
        self.plan_req.start.pose = self.odom_msg.pose.pose
        print (self.plan_req.start.pose)
        self.plan_req.goal.header.frame_id = 'map'
        self.plan_req.goal.pose.position.x = 0
        self.plan_req.goal.pose.position.y = 2
        self.plan_req.goal.pose.position.z = 0
        self.plan_req.goal.pose.orientation.x = 0
        self.plan_req.goal.pose.orientation.y = 0
        self.plan_req.goal.pose.orientation.z = 0
        self.plan_req.goal.pose.orientation.w = 0

    def get_plan_path_length(self):
        result = 0
        poses = self.raw_plan.plan.poses
        print(len(poses))
        for point in zip(poses[:-1], poses[1:]):
            x1 = point[1].pose.position.x
            x0 = point[0].pose.position.x
            y1 = point[1].pose.position.y
            y0 = point[0].pose.position.y
            print x1,x0,y1,y0
            result += sqrt(pow(x1-x0,2) + pow(y1-y0,2))
        self.path_lengh = result


if __name__ == '__main__':

    rospy.init_node('make_plan_client')
   
    calc = CalculateGoalPathLength()

    # all goals
    all_goals_requests = []
    calc.wait_make_plan()
    print ('path len', calc.path_lengh)

```

Send Goal:

```python
#! /usr/bin/env python
import rospy
import time
import actionlib
from nav_msgs.msg import Odometry
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

