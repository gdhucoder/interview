Robot Localization

Pose Array - particalecloud

![2020-02-02_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_001.jpg)

## MCL

Monte Carlo Localization, particle filter localization.

Guesses  are known as particles.



## AMCL

Adaptive Monte Carlo Localization. AMCL pacakge provides the amcl node which uses the MCL system in order to track the localization of a robot moving in a 2D space. 

**Adaptive** means we can configure some parameters used in this algorithm.

**Arrows** indicate the most probable position the robot was in, and its orientation.

![2020-02-02_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_005.jpg)

Map data:



Change Map:

Set the map file path, relaunch the amcl.launch and rviz

```xml
<launch>

  <!-- Run the map server -->
  <arg name="map_file" default="$(find husky_navigation)/maps/playpen_map.yaml"/>
  <node name="map_server" pkg="map_server" type="map_server" args="$(arg map_file)" />
```

Now the map has changed. (the maps directory is located int husky_navigation package)

![2020-02-02_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-02_007.jpg)



