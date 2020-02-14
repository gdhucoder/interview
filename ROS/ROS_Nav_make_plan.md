ROS Navigation make plan without actual execution navigation.

```python
#! /usr/bin/env python

import rospy
from nav_msgs.srv import GetPlan, GetPlanRequest
import sys 

rospy.init_node('service_client')
rospy.wait_for_service('/move_base/make_plan')
make_plan_service = rospy.ServiceProxy('/move_base/make_plan', GetPlan)
msg = GetPlanRequest()
msg.start.header.frame_id = 'map'
msg.start.pose.position.x = 0
msg.start.pose.position.y = 0
msg.start.pose.position.z = 0
msg.start.pose.orientation.x = 0
msg.start.pose.orientation.y = 0
msg.start.pose.orientation.z = 0
msg.start.pose.orientation.w = 0
msg.goal.header.frame_id = 'map'
msg.goal.pose.position.x = 1
msg.goal.pose.position.y = 2
msg.goal.pose.position.z = 0
msg.goal.pose.orientation.x = 0
msg.goal.pose.orientation.y = 0
msg.goal.pose.orientation.z = 0
msg.goal.pose.orientation.w = 0
result = make_plan_service(msg)
print result
```

