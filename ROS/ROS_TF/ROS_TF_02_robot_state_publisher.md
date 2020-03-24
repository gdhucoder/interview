
robot state publisher, it takes a file describing the morphology of the robot (aka URDF file) as input and it automatically publishes the TF for you.

```xml
<launch>

  <param name="robot_description" command="cat $(find pi_robot_pkg)/urdf/pi_robot_v2.urdf" />  <!-- Load joint controller configurations from YAML file to parameter server -->
  <rosparam file="$(find pi_robot_pkg)/config/pirobot_control.yaml" command="load"/>

  <!-- load the controllers -->
  <node name="controller_spawner" pkg="controller_manager" type="spawner" respawn="false"
    output="screen" ns="/pi_robot" args="head_pan_joint_position_controller head_tilt_joint_position_controller torso_joint_position_controller
    left_shoulder_forward_joint_position_controller right_shoulder_forward_joint_position_controller left_shoulder_up_joint_position_controller
    right_shoulder_up_joint_position_controller left_elbow_joint_position_controller right_elbow_joint_position_controller left_wrist_joint_position_controller
    right_wrist_joint_position_controller joint_state_controller"/>
</launch>
```

使用了ns之后，会在对应的topic名字前方加上一个ns

```
user:~$ rostopic list
/clock
/gazebo/link_states
/gazebo/model_states
/gazebo/parameter_descriptions
/gazebo/parameter_updates
/gazebo/set_link_state
/gazebo/set_model_state
/pi_robot/head_pan_joint_position_controller/command
/pi_robot/head_pan_joint_position_controller/pid/parameter_descriptions
/pi_robot/head_pan_joint_position_controller/pid/parameter_updates
/pi_robot/head_pan_joint_position_controller/state
/pi_robot/head_tilt_joint_position_controller/command
/pi_robot/head_tilt_joint_position_controller/pid/parameter_descriptions
/pi_robot/head_tilt_joint_position_controller/pid/parameter_updates
/pi_robot/head_tilt_joint_position_controller/state
/pi_robot/joint_states
```

irobot_control.yaml 文件中已经有pi_robot这个参数了。 如果有多个机器人应该定义每个机器人的名字。

为什么会出现这个错误呢？

因为没有发布tf，所以RViz不能连接机器人不同的关节和链接，所以看不到机器人。

为啥没有发布机器人的tf data呢？ 因为没有启动 `robot_state_publisher`

![2020-03-16_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-16_001.jpg)

`pi_robot_control.launch`

```xml
<launch>

  <param name="robot_description" command="cat $(find pi_robot_pkg)/urdf/pi_robot_v2.urdf" />  <!-- Load joint controller configurations from YAML file to parameter server -->
  <rosparam file="$(find pi_robot_pkg)/config/pirobot_control.yaml" command="load"/>

  <!-- load the controllers -->
  <node name="controller_spawner" pkg="controller_manager" type="spawner" respawn="false"
    output="screen" ns="/pi_robot" args="head_pan_joint_position_controller head_tilt_joint_position_controller torso_joint_position_controller
    left_shoulder_forward_joint_position_controller right_shoulder_forward_joint_position_controller left_shoulder_up_joint_position_controller
    right_shoulder_up_joint_position_controller left_elbow_joint_position_controller right_elbow_joint_position_controller left_wrist_joint_position_controller
    right_wrist_joint_position_controller joint_state_controller"/>
    
  <!-- convert joint states to TF transforms for rviz, etc -->
  <node name="robot_state_publisher" pkg="robot_state_publisher" type="robot_state_publisher"
    respawn="false" output="screen">
    <remap from="/joint_states" to="/pi_robot/joint_states" />
  </node>

</launch>
```


![2020-03-16_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-16_002.jpg)

现在RViz中可以显示 tf 了


![2020-03-16_003.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-16_003.jpg)

如何定义机器人模型，对应定义了robot state publisher

- URDF文件可以在单个文件中定义所有要用的，也可以使用几个文件分开定义。
- URDF文件可以分成3部分：链接定义，关节定义，材料和颜色定义。

Links Definition, Joints Denifition, Materials/Colors Definition


![2020-03-24_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_000.jpg)

![2020-03-24_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_001.jpg)


```yaml
pi_robot:
  # Publish all joint states -----------------------------------
  joint_state_controller:
    type: joint_state_controller/JointStateController
    publish_rate: 50
   
  left_shoulder_forward_joint_position_controller:
    type: effort_controllers/JointPositionController
    joint: left_shoulder_forward_joint
    pid: {p: 100.0, i: 0.01, d: 10.0}
```

在文件开始要加一个机器人名字，例如pi_robots

使用rqt_gui查看controllers的状态

两个机器人tf树🌲

![2020-03-24_012.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_012.jpg)

静态变换 Static Transform

`rosrun tf static_transform_publisher x y z yaw pitch roll frame_id child_frame_id period_in_ms`

也可以创建文件：

```xml
<launch>
    <node pkg="tf" type="static_transform_publisher" name="name_of_node" 
          args="x y z yaw pitch roll frame_id child_frame_id period_in_ms">
    </node>
</launch>
```

从图上可以看出，这两个机器人的tf变换没有连到一起

![2020-03-24_014.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_014.jpg)

![2020-03-24_013.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_013.jpg)

This way, you can now connect various robots together, without defining them in the same model.urdf.