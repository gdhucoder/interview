
# 如何创建gazebo world？

- 下载3D模型
- 根据特定的目录结构创建需要的文件夹
- 为3D模型创建model.config和model_name.sdf文件
- 创建launch文件启动我们的模型

## 下载3D模型

[3DWarehouse](https://3dwarehouse.sketchup.com/search/?q=chair)

![2020-03-24_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_005.jpg)

文件结构：我们使用URDF文件定义机器人模型，使用SDF文件定义世界模型

PACKAGE_NAME_description: 和机器人定义相关的一些东西放在这。

PACKAGE_NAME_gazebo：所有的world文件 模型等等


```java
package_name_gazebo
    launch(folder)
        my_chair.launch
    models(folder)
        ...
        my_chair(folder)
            materials
                scripts
                textures
            meshes(folder)
                model(folder)
                model.dae
            chair.sdf
            model.config
        ...
    worlds(foler)
        my_world.world
```

- meshes: To hold the COLLADA file
- materials: All the material scripts and textures for the model will be placed here.

![2020-03-24_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_002.jpg)

![2020-03-24_003.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_003.jpg)

## 步骤：

### 创建model.config

```xml
<?xml version="1.0"?>
<model>
  <name>my_chair</name>
  <version>1.0</version>
  <sdf version="1.5">chair.sdf</sdf>
  <author>
    <name>Your name</name>
    <email>Your email address</email>
  </author>
  <description>
    A chair
  </description>
</model>
```

注意：sdf标签定义了模型

### 创建chair.sdf

```xml
<?xml version="1.0" ?>
<sdf version="1.5">
  <model name="my_chair">
    <static>true</static>
    <link name="chair_link">
        <collision name="chair_collision">  
         <geometry>
            <box>
                <size>1.70 0.51 0.55</size>
            </box>
        </geometry>
      </collision>
      
      <visual name="chair_mesh">
        <cast_shadows>true</cast_shadows>
        <geometry>
            <mesh>
                <uri>model://my_chair/meshes/model.dae</uri>
            </mesh>
        </geometry>
      </visual>
    </link>
  </model>
</sdf>
```

注意：static为true表示模型是静止不动的（不受碰撞等影响）

### 定义my_world.world文件

```xml
<?xml version="1.0" ?>
<sdf version="1.5">
  <world name="my_world">
    <include> <!-- Add in a light source -->
      <uri>model://sun</uri>
    </include>
    <include> <!-- Add your custom model -->
      <uri>model://my_chair</uri>
      <name>simple_chair</name>
      <pose>0 0 1.5 0 0 0</pose>
    </include>
  </world>
</sdf>
```

注意：name标签中的名字必须唯一（即使很多个物体都一样，也要定义不同的名字）

### 定义启动文件，

```xml
<launch>
    <include file="$(find gazebo_ros)/launch/empty_world.launch">
        <arg name="world_name" value="$(find two_wheels_gazebo)/worlds/my_world.world"/>
    </include>
</launch>
```

一个更大的world model

```xml
<?xml version="1.0" ?>
<sdf version="1.6">
  <world name="room">
    <include>
      <uri>model://sun</uri>
    </include>
    
    <include>
      <uri>model://lc_floor</uri>
    </include>
    
    <include>
      <uri>model://lc_bookshelf</uri>
      <pose>2.05631 1.69254 0.680848 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_cabinet</uri>
      <pose>-1.38001 1.70508 0.822405 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_can</uri>
      <pose>-1.19744 1.39146 0.38114 0.010228 -0.003607 0.004857</pose>
    </include>
    
    <include>
      <uri>model://lc_shelf</uri>
      <pose>0.50964 1.65593 1.01491 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_sofa</uri>
      <pose>0.542039 -0.822537 0.200018 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_table</uri>
      <pose>0.530494 0.266919 -0.197504 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_tv</uri>
      <pose>0.500186 1.70152 0.676686 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://lc_tv_stand</uri>
      <pose>0.570066 1.54442 0.167182 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://wallEast</uri>
      <pose>2.57702 0.000715 -0.01458 0 -0 0</pose>
    </include>
    
    <include>
      <uri>model://wallNorth</uri>
      <pose>0.243646 1.7884 0.702708 0 -0 0</pose>
    </include>
    
  </world>
</sdf>
```

### 启动gazebo

`roslaunch two_wheels_description my_chair.launch`

![2020-03-24_006.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_006.jpg)

### 组合起来

```xml
<?xml version="1.0" encoding="UTF-8"?>
<launch>
    <param name="robot_description" command="cat '$(find two_wheels_description)/urdf/two_wheels.urdf'" />
    
    <arg name="x" default="-2"/>
    <arg name="y" default="0"/>
    <arg name="z" default="0.1"/>
    
    <!-- Start Gazebo with our custom world [room] -->
    <include file="$(find gazebo_ros)/launch/empty_world.launch">
        <arg name="world_name" value="$(find two_wheels_gazebo)/worlds/room.world"/>
    </include>
    
    <!-- Make an instance of the robot  -->
    <node name="mybot_spawn" pkg="gazebo_ros" type="spawn_model" output="screen"
          args="-urdf -param robot_description -model mybot -x $(arg x) -y $(arg y) -z $(arg z)" />
          
     <!-- Publish joint values -->
    <node name="joint_state_publisher" pkg="joint_state_publisher" type="joint_state_publisher">
      <param name="use_gui" value="False"/>
    </node>

    <!-- Combine joint values -->
    <node name="robot_state_publisher" pkg="robot_state_publisher" type="state_publisher"/>
            
</launch>
```

![2020-03-24_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-24_007.jpg)


其他3D模型资源：

[3DGEMS](http://data.nvision2.eecs.yorku.ca/3DGEMS/) 10大类的270多个模型。

[IGNITION_FUEL](https://app.ignitionrobotics.org/fuel/models) 有待深入看一下

[ignitionrobotics](https://ignitionrobotics.org/) IGNITION_FUEL model开源官网

> Ignition Robotics brings a fresh approach to simulation with a complete toolbox of development libraries and cloud services to make simulation easy. Iterate fast on your new physical designs in realistic environments with high fidelity sensors streams. Test control strategies in safety, and take advantage of simulation in continuous integration tests.

