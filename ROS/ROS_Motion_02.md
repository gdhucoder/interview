
ROS Manipulation

- MoveIt
- Motion Planning
- Perception
- Grasping

## MoveIt

MoveIt 是使用ROS执行“操作”的软件包和工具的集合。

MoveIt 提供了运动规划，操作，感知，动力学，碰撞检测、控制和导航等软件。

官网是：http://moveit.ros.org

RViz提供的操作界面：![2020-02-18_006.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_006.jpg)

RViz中的操作界面有很多选项，直接劝退！

## Motion Planning

什么是运动规划（Motion Planning）？简单的说，是规划一个运动轨迹，从点A到点B，不碰到其他东西。
换句话说，必须能控制机器人不同的关节和链接避免它们之间或者它们和环境之间发生碰撞。

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
scene = moveit_commander.PlanningSceneInterface()    
group = moveit_commander.MoveGroupCommander("arm")
display_trajectory_publisher = rospy.Publisher('/move_group/display_planned_path', moveit_msgs.msg.DisplayTrajectory, queue_size=1)

group_variable_values = group.get_current_joint_values()

group_variable_values[1] = 1.5
group.set_joint_value_target(group_variable_values)

plan2 = group.plan()

rospy.sleep(5)
group.go(wait=True)
rospy.sleep(5)

group_variable_values[2] = -1.5
group.set_joint_value_target(group_variable_values)

plan2 = group.plan()

rospy.sleep(5)
group.go(wait=True)
rospy.sleep(5)

moveit_commander.roscpp_shutdown()
```

![2020-02-18_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_007.jpg)


## Perception

感知的意思是说，要和环境交互，你必须能够看到环境。

感知通常使用RGBD相机来实现，例如Kinect相机。

```
rosrun rviz rviz
add to rviz PointCloud2, 来显示Kinect相机捕捉的PointCloud
# 执行命令来获取RGBD相关的frame
rostopic echo -n1 /camera/depth/points/header/frame_id
```

![2020-02-18_010.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_010.jpg)

选择 `camera_depth_optical_frame`

![2020-02-18_009.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_009.jpg)

## Grasping

抓取，看似很简单，但要考虑很多变量。

## Structure of a Manipulator Robot

操作机器人是由一系列严格的链接组成的（rigid links），它们通过关节（joints）连接起来，以抓取器结束（end-effector）。所以，任何操作器（Manipulator是由这三部分构成的）

- Links: Rigid pieces that connect the joints of the manipulator
- Joints: Connectors between the links of the manipulator, and which provide either translational or rotatinoal movement.
- End Effector:
    - Grippers/Tools
    - Sensors

![2020-02-18_011.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_011.jpg)

## Basic Terminology

### DoFs for Manipulation

自由度（**DoFs** is short for **Degrees of Freedom** of a robot.），他的意思是机械臂能够移动的方式有几种。系统中如果有n个DoFs，则需要设定n个参数。

在操作机器人中，机器人的配置决定了关节的数量，关节的数量决定了机器人的自由度。

3D场景中的物体在空间中有6个参数。

- 3: x,y,z 表示位置
- 3: roll, pitch, yaw angles 表示方向 orientation

备注：表示方向的参数我不是很理解，接下来要看看！

如果一个机械臂少于6DoFs，说明机械臂不能到达工作空间的任意位置

如果机械臂大于6DoFs，说明机器人**动力学冗余(kinematically redundant)**。同时，自由度越高，机器人就越难控制。

## 问题

图中的机械臂有几个自由度？


## Grippers

> A gripper is a device that enables the holding of an object to be manipulated. The easier way to describe a gripper is to think of the human hand. Just like a hand, a gripper enables holding, tightening, handling, and releasing of an object. A gripper can be attached to a robot or it can be part of a fixed automation system. Many styles and sizes of grippers exist so that the correct model can be selected for the application.


