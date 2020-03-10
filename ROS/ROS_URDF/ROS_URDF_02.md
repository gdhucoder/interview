
```xml
<material name="grey">
    <color rgba="0.75 0.75 0.75 1"/>
</material>

<link name="base_link">
    <inertial> 惯性
        <origin xyz="0 0 0" rpy="0 0 0"/>
        <mass value="0.18" />
        <inertia ixx="0.0002835" ixy="0.0" ixz="0.0" iyy="0.0002835" iyz="0.0" izz="0.000324"/>
    </inertial>
    
    <collision> 碰撞
        <origin xyz="0 0 0" rpy="0 0 0"/>
        <geometry>
            <cylinder radius="0.06" length="0.09"/>
        </geometry>
    </collision>
    
    <visual> 视觉效果
        <origin rpy="0.0 0 0" xyz="0 0 0"/>
        <geometry>
            <cylinder radius="0.06" length="0.09"/>
        </geometry>
        
        <material name="grey"/>
        
    </visual>
</link>
```

Gazebo的RGB颜色如何表示？

三原色 /255 ，最后一项是透明程度， 1为完全不透明

```xml
    <material name="grey">
        <color rgba="0.75 0.75 0.75 1"/>
    </material>
    <material name="red">
        <color rgba="0.8 0 0 1"/>
    </material>
```

物理性质

```xml
<gazebo reference="base_link">
    <kp>100000.0</kp> 静态硬度系数
    <kd>100000.0</kd> 动态硬度系数
    <mu1>10.0</mu1> 静摩擦系数
    <mu2>10.0</mu2> 动摩擦系数
    <material>Gazebo/Grey</material>
</gazebo>
```

启动Rviz

roslaunch my_mira_description urdf_visualize.launch model:='$(find my_mira_description)/urdf/mira_simple_collisions_inertias.urdf'

在gazebo中放置机器人

`spawn_mira_simple_collisions_intertias.launch`

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<launch>
   <include file="$(find my_mira_description)/launch/spawn_urdf.launch">
   <arg name="x" value="0.0" /> 
   <arg name="y" value="0.0" /> 
   <arg name="z" value="0.2" /> 
   <arg name="urdf_robot_file" value="$(find my_mira_description)/urdf/mira_simple_collisions_inertias.urdf" />
   <arg name="robot_name" value="BigFuckingRobot" />
   </include>
</launch>
```

如何在Gazebo中删除机器人

`rosservice call /gazebo/get_world_properties "{}"`

```
user urdf $ rosservice call /gazebo/get_world_properties "{}"                                                                                         
sim_time: 17173.946                                                                                                                                   
model_names: ['ground_plane', 'mira']                                                                                                                 
rendering_enabled: True                                                                                                                               
success: True                                                                                                                                         
status_message: GetWorldProperties: got properties
```

See the model name `mira`?


Execute the following command:

`rosservice call /gazebo/delete_model "model_name: 'mira'"`


How to delete model using python code?

```
roscd my_mira_description
touch scripts/delete_mira.py
chmod +x scripts/delete_mira.py
```

```python
#! /usr/bin/env python

import rospy
from gazebo_msgs.srv import DeleteModel, DeleteModelRequest

import sys

rospy.init_node('remove_model_service_client')
print "waiting for service..."
rospy.wait_for_service('/gazebo/delete_model')
delete_model_service = rospy.ServiceProxy('/gazebo/delete_model', DeleteModel)
kk = DeleteModelRequest()
kk.model_name = 'BigFuckingRobot'
print "Deleting model = " + str(kk)
status_msg = delete_model_service(kk)
print status_msg
```

now, let's execute it in shell:

```
rosrun my_mira_description delete_mira.py

# output
user:~/catkin_ws/src/my_mira_description$ rosrun my_mira_description delete_mira.py
waiting for service...
Deleting model = model_name: "BigFuckingRobot"
success: True
status_message: "DeleteModel: successfully deleted model"
```

current :

![2020-03-02_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-02_005.jpg)


ALWAYS WHEN YOU SIGN IN AGAIN IN ROBOT IGNITE ACADEMY, you will have to copy this folder AGAIN. (this is a limitation of our learning platform, not necessary on any other ROS system)

```
rm -rf /usr/share/gazebo/models/my_mira_description
cp -r /home/user/catkin_ws/src/my_mira_description /usr/share/gazebo/models
```

Basically, this class publishes in the controller topics ( /mira/roll_joint_position_controller/command, /mira/pitch_joint_position_controller/command /mira/yaw_joint_position_controller/command ) and checks the /mira/joint_states topic to make sure that the robot has arrived at the given position.

It also deals with the fact that joint angles sometimes have negative or complete turn versions of the same angle, and you have to make a system similar to the one in the function "assertAlmostEqualAngles" to be sure that the angles are the same, even though they might be different versions of the same angle.


## Adding sensors

camera

![2020-03-10_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_001.jpg)

![2020-03-10_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_000.jpg)

the most important is to set `reference="carema_link"` and the `pose` tag

`<pose>0 0 0 0 0 1.57</pose>` 90 degree.

```xml
<!-- camera -->
  <gazebo reference="camera_link">
    <sensor type="camera" name="camera1">
      <update_rate>15.0</update_rate>
      <camera name="head">

          <pose>0 0 0 0 0 1.57</pose>

        <horizontal_fov>1.3962634</horizontal_fov>
        <image>
          <width>400</width>
          <height>400</height>
          <format>R8G8B8</format>
        </image>
        <clip>
          <near>0.01</near>
          <far>100</far>
        </clip>
        <noise>
          <type>gaussian</type>
          <stddev>0.007</stddev>
        </noise>
      </camera>
      <plugin name="camera_controller" filename="libgazebo_ros_camera.so">
        <alwaysOn>true</alwaysOn>
        <updateRate>0.0</updateRate>
        <cameraName>mira/camera1</cameraName>
        <imageTopicName>image_raw</imageTopicName>
        <cameraInfoTopicName>camera_info</cameraInfoTopicName>
        <frameName>camera_link</frameName>
        <hackBaseline>0.07</hackBaseline>
        <distortionK1>0.0</distortionK1>
        <distortionK2>0.0</distortionK2>
        <distortionK3>0.0</distortionK3>
        <distortionT1>0.0</distortionT1>
        <distortionT2>0.0</distortionT2>
      </plugin>
    </sensor>
  </gazebo>
```

Warning:

In this case, **removing the robot with sensors will crash Gazebo in any system**. This is a known issue and they are working on solving it. For you as user, when you delete a robot model with sensors, just restart your Gazebo or, in RobotIgniteCase, just go to Unit 0 and come back again, restarting all of the systems.

