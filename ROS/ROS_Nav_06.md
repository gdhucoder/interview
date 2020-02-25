put the fetch robot on to the `
```xml
<?xml version="1.0"?>
<launch>
    <arg name="robot" default="fetch"/>
    <arg name="x" default="0.0" />
    <arg name="y" default="0.0" />
    <arg name="z" default="0.0" />
    <arg name="roll" default="0"/>
    <arg name="pitch" default="0"/>
    <arg name="yaw" default="0.0" />
    <include file="$(find fetch_gazebo)/launch/include/original_$(arg robot).launch.xml" >
        <arg name="x" value="$(arg x)" />
        <arg name="y" value="$(arg y)" />
        <arg name="z" value="$(arg z)" />
        <arg name="roll" default="$(arg roll)"/>
        <arg name="pitch" default="$(arg pitch)"/>
        <arg name="yaw" value="$(arg yaw)" />
    </include>
</launch>
```

fetch_tc -> fetch_gazebo -> launch -> main_multiple.launch
```xml
<launch>

  <arg name="robot" default="fetch"/>
  <arg name="debug" default="false"/>
  <arg name="gui" default="false"/>
  <arg name="headless" default="false"/>
  <arg name="pause" default="true"/>

  <!-- Start Gazebo with a blank world -->
  <include file="$(find gazebo_ros)/launch/empty_world.launch">
    <arg name="debug" value="$(arg debug)" />
    <arg name="gui" value="$(arg gui)" />
    <arg name="paused" value="$(arg pause)"/>
    <arg name="use_sim_time" value="true"/>
    <arg name="headless" value="$(arg headless)"/>
  </include>

  <!-- Oh, you wanted a robot? -->
  <include file="$(find fetch_gazebo)/launch/include/robots.launch.xml" />

</launch>

```

`/launch/include/robots.launch.xml`

```xml
<launch>

  <!-- URDF and TF support -->
  <param name="robot_description" command="$(find xacro)/xacro.py $(find fetch_description)/robots/fetch.gazebo.xacro" />
 
  <!-- BEGIN ROBOT 1-->
  <group ns="robot1">
    <param name="tf_prefix" value="robot1_tf" />
    <include file="$(find fetch_gazebo)/launch/include/fetch.launch.xml" >
      <arg name="init_pose" value="-x 1 -y 1 -z 0" />
      <arg name="robot_name"  value="Robot1" />
    </include>

    <include file="$(find fetch_gazebo)/launch/include/controller.launch.xml">
      <arg name="robot_ns" value="robot1" />
    </include>
  </group>

  <!-- BEGIN ROBOT 2-->
  <group ns="robot2">
    <param name="tf_prefix" value="robot2_tf" />
    <include file="$(find fetch_gazebo)/launch/include/fetch.launch.xml" >
      <arg name="init_pose" value="-x -1 -y 1 -z 0" />
      <arg name="robot_name"  value="Robot2" />
    </include>
    <include file="$(find fetch_gazebo)/launch/include/controller.launch.xml">
      <arg name="robot_ns" value="robot2" />
    </include>
  </group>
 

</launch>

```

cp -R /home/simulations/public_sim_ws/src/all/fetch_tc/ ~/pub_sim

rospack list | grep fetch