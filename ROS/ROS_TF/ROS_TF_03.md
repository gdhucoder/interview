
如何向gazebo中放置一个模型

```xml
<?xml version="1.0" encoding="UTF-8"?>
<launch>

    <arg name="x" default="0.0" />
    <arg name="y" default="0.0" />
    <arg name="z" default="0.0" />

    <arg name="urdf_robot_file" default="$(find your_pkg)/urdf/your_robot.urdf" />
    <arg name="robot_name" default="your_robot_model_name" />


    <!-- Load the URDF into the ROS Parameter Server -->
    <param name="robot_description" command="cat $(arg urdf_robot_file)" />

    <!-- Run a python script to the send a service call to gazebo_ros to spawn a URDF robot -->
    <node name="urdf_spawner" pkg="gazebo_ros" type="spawn_model" respawn="false" output="screen"
    args="-urdf -x $(arg x) -y $(arg y) -z $(arg z)  -model $(arg robot_name) -param robot_description"/>
</launch>
```

如何从gazebo中删除一个模型？

这个命令可以得到world中的所有模型名字。

`rosservice call /gazebo/get_world_properties "{}"`

下步，执行删除命令 删除模型

`rosservice call /gazebo/delete_model "model_name: 'your_robot_model_name'"`

