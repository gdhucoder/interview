ROS Service Client

launch a service.

```
<launch>

  <include file="$(find iri_wam_reproduce_trajectory)/launch/start_service.launch"/>

  <node pkg ="iri_wam_aff_demo"
        type="iri_wam_aff_demo_node"
        name="iri_wam_aff_demo"
        output="screen">
  </node>
```

```
rosservice list | grep execute_trjectory
rosservice info /execute_trajectory

Service message file:

user:~/catkin_ws$ rosservice info /execute_trajectory
Node: /iri_wam_reproduce_trajectory
URI: rosrpc://ip-172-31-1-3:57245
Type: iri_wam_reproduce_trajectory/ExecTraj
Args: file

rossrv show iri_wam_reproduce_trajectory/ExecTraj
file
---
no response


```

Request: variable called `file`

Response: empty

```python
#! /usr/bin/env python

import rospy
from iri_wam_reproduce_trajectory.srv import ExecTraj, ExecTrajRequest # Import the service message used by the service /execute_trajectory

rospy.init_node('service_execute_trajectory_client') # Initialise a ROS node with the name service_client
rospy.wait_for_service('/execute_trajectory') # Wait for the service client /execute_trajectory to be running
execute_trajectory_service_client = rospy.ServiceProxy('/execute_trajectory', ExecTraj) # Create the connection to the service
execute_trajectory_request_object = ExecTrajRequest() # Create an object of type ExecTrajRequest

"""
user:~/catkin_ws$ rossrv show iri_wam_reproduce_trajectory/ExecTraj
string file
---

"""

execute_trajectory_request_object.file = "file_path" # Fill the variable file of this object with the desired file path
result = execute_trajectory_service_client(execute_trajectory_request_object) # Send through the connection the path to the trajectory file to be executed
print result # Print the result type ExecTrajResponse
```

![2020-01-21_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-21_000.jpg)

```python
#! /usr/bin/env python
import rospkg
import rospy
from iri_wam_reproduce_trajectory.srv import ExecTraj, ExecTrajRequest # Import the service message used by the service /execute_trajectory

rospy.init_node('service_execute_trajectory_client') # Initialise a ROS node with the name service_client
rospy.wait_for_service('/execute_trajectory') # Wait for the service client /execute_trajectory to be running
execute_trajectory_service_client = rospy.ServiceProxy('/execute_trajectory', ExecTraj) # Create the connection to the service
execute_trajectory_request_object = ExecTrajRequest() # Create an object of type ExecTrajRequest

"""
user:~/catkin_ws$ rossrv show iri_wam_reproduce_trajectory/ExecTraj
string file
---

"""

rospack = rospkg.RosPack()
# This rospack.get_path() works in the same way as $(find name_of_package) in the launch files.
trajectory_file_path = rospack.get_path('iri_wam_reproduce_trajectory') + "/config/get_food.txt"


execute_trajectory_request_object.file = trajectory_file_path # Fill the variable file of this object with the desired file path
result = execute_trajectory_service_client(execute_trajectory_request_object) # Send through the connection the path to the trajectory file to be executed
print result # Print the result type ExecTrajResponse
```

```xml
<launch>

  <include file="$(find iri_wam_reproduce_trajectory)/launch/start_service.launch"/>

  <!-- Here will go our python script that calls the execute_trajectory service -->
    <node pkg ="unit_3_services"
        type="exercise_3_1.py"
        name="service_execute_trajectory_client"
        output="screen">
  </node>

</launch>
```

![2020-01-21_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-21_002.jpg)

![2020-01-21_003.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-21_003.jpg)

![2020-01-21_004.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-21_004.jpg)

```
from geometry_msgs.msg import Twist
move_circle = Twist()
move_circle.linear.x = 0.2
move_circle.angular.z = 0.2

my_pub = rospy.Publisher('/cmd_vel', Twist, queue_size=1)
rospy.loginfo("Service /move_bb8_in_circle Ready")
rsopy.spin()
```

 service .launch

```xml
<launch>
   <node pkg="unit_3_services"
        type="bb8_custom_service_server.py"
        name="service_move_bb8_in_circle_custom_server"
        output="screen"
        >
   </node>
</launch>
```

Service.py

```python
#! /usr/bin/env python
import rospy
from my_custom_srv_msg_pkg.srv import MyCustomServiceMessage, MyCustomServiceMessageResponse

# geometry message
from geometry_msgs.msg import Twist


def my_callback(request):
    rospy.loginfo("The Service move_bb8_in_circle_custom has been called")
    rospy.loginfo( "request data: duration="+str(request.duration))
    move_circle.linear.x = 0.2
    move_circle.angular.z = 0.2
    i = 0
    while i <= request.duration:
        my_pub.publish(move_circle)
        rate.sleep()
        i = i+1
    move_circle.linear.x = 0
    move_circle.angular.z = 0
    my_pub.publish(move_circle)
    rospy.loginfo("Finished service service_move_bb8_in_circle_custom")
    my_response = MyCustomServiceMessageResponse()
    my_response.success = True
    return my_response

rospy.init_node('service_move_bb8_in_circle_custom_serve_clientr')
my_service = rospy.Service("/service_move_bb8_in_circle_custom", MyCustomServiceMessage, my_callback)
my_pub = rospy.Publisher('/cmd_vel', Twist, queue_size=1)
move_circle = Twist()
rate = rospy.Rate(1)
rospy.loginfo("Service /move_bb8_in_circle_custom Ready")
rospy.spin()
```

Client.launch

```xml
<launch>

  <node pkg="unit_3_services"
        type="bb8_custom_service_client.py"
        name="bb8_move_in_circle_custom_client"
        output="screen"
        >
   </node>
</launch>
```

client.py

```python
#! /usr/bin/env python
import rospy
from my_custom_srv_msg_pkg.srv import MyCustomServiceMessage, MyCustomServiceMessageRequest # you import the service message python classes generated from Empty.srv.

rospy.init_node("bb8_move_in_circle_custom_client")
rospy.wait_for_service("/service_move_bb8_in_circle_custom")

bb8_move_in_circle_custom_client=rospy.ServiceProxy('/service_move_bb8_in_circle_custom', MyCustomServiceMessage)
request = MyCustomServiceMessageRequest()
request.duration = 10
# Send through the connection the name of the trajectory to be executed by the robot
result = bb8_move_in_circle_custom_client(request)
# Print the result given by the service called
print result



```

![2020-01-21_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-21_005.jpg)