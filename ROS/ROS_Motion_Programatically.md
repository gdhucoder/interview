# Doing Motion Planning Programatically

启动MoveIt！助手

roslaunch moveit_setup_assistant setup_assistant.launch

将机器人描述文件拷贝至目录：

cp /home/simulations/public_sim_ws/src/all/rbkairos_tc/rbkairos_common/rbkairos_description/robots/rbkairos.urdf.xacro /home/user/catkin_ws/src/

self-collision matrix

KDLKinematicsPlugin

Planning Groups

arm_with_torso_controller

arm_with_torso_controller

`launch_planning_script.launch`

```xml
<launch>
    <node name="move_group_python_interface_tutorial" pkg="fetch_moveit_config" type="planning_script.py" output="screen"></node>
</launch>
```

`planning_script.py`

```python
#! /usr/bin/env python

import sys
import copy
import rospy
import moveit_commander
import moveit_msgs.msg
import geometry_msgs.msg

moveit_commander.roscpp_initialize(sys.argv)
rospy.init_node('move_group_python_interface_tutorial', anonymous=True)
# an interface to our robot
robot = moveit_commander.RobotCommander()
# planning scene
scene = moveit_commander.PlanningSceneInterface()  
# move group commander  
group = moveit_commander.MoveGroupCommander("arm")
# Topic Publisher, which will publish into the /move_group/display_planned_path topic
# By publishing into this topic, we will be able to visualize the planned motion through the MoveIt RViz interface.
display_trajectory_publisher = rospy.Publisher('/move_group/display_planned_path', moveit_msgs.msg.DisplayTrajectory, queue_size=1)

# pose
pose_target = geometry_msgs.msg.Pose()
pose_target.orientation.w = 1.0
pose_target.position.x = 0.7
pose_target.position.y = 0
pose_target.position.z = 1.1
group.set_pose_target(pose_target)

# Finally, we are telling the "manipulator" group that we created previously to calculate the plan. If the plan is successfully computed, it will be displayed through MoveIt RViz.
plan1 = group.plan()
## !!!! actually execute the plan!!!!

group.go(wait=True)

pose_target = geometry_msgs.msg.Pose()
pose_target.orientation.w = 1.0
pose_target.position.x = 0.8
pose_target.position.y = 0
pose_target.position.z = 1.1
group.set_pose_target(pose_target)

plan1 = group.plan()
group.go(wait=True)

# shut down moveit_commander module
moveit_commander.roscpp_shutdown()
```

![2020-02-18_026.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_026.jpg)

`get_data.py`

```python
#! /usr/bin/env python

import sys
import copy
import rospy
import moveit_commander
import moveit_msgs.msg
import geometry_msgs.msg

moveit_commander.roscpp_initialize(sys.argv)
rospy.init_node('move_group_python_interface_tutorial', anonymous=True)

robot = moveit_commander.RobotCommander()
group = moveit_commander.MoveGroupCommander("arm")
print "Reference frame: %s" % group.get_planning_frame()
print "End effector: %s" % group.get_end_effector_link()
print "Robot Groups:"
print robot.get_group_names()
print "Current Joint Values:"
print group.get_current_joint_values()
print "Current Pose:"
print group.get_current_pose()
print "Robot State:"
print robot.get_current_state()

```