

# Build a Map

```
roslaunch turtlebot_teleop keyboard_teleop.launch
```

```
cat gmapping.launch.xml >> ~/catkin_ws/src/my_gmapping_launcher/launch/my_gmapping_launch.launch
```

maxUrange: set maximum usable range of the laser. The laser beams will be cropped to this value.

Gmapping_params.yaml

```yaml
base_frame: base_footprint
odom_frame: odom
map_update_interval: 5.0
maxUrange: 6.0
maxRange: 8.0

minimumScore: 200

linearUpdate: 0.5
angularUpdate: 0.436
temporalUpdate: -1.0
resampleThreshold: 0.5
particles: 80
xmin: -1.0
ymin: -1.0
xmax: 1.0
ymax: 1.0

delta: 0.05
llsamplerange: 0.01
llsamplestep: 0.01
lasamplerange: 0.005
lasamplestep: 0.005
```

my_gmapping_launch.launch

```xml
<launch>
     <arg name="scan_topic" default="/kobuki/laser/scan" />
    
   <!-- Defining parameters for slam_gmapping node -->

     <node pkg="gmapping" type="slam_gmapping" name="slam_gmapping"
     output="screen">
        
       <rosparam file="$(find my_mapping_launcher)/params/gmapping_params.yaml" command="load" />
    
       <remap from="scan" to="$(arg scan_topic)"/>
        
     </node>
    
</launch>
```

![2020-01-30_018.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-30_018.jpg)

## SLAM

Slam_gmapping node -> laser/scan and tranform topic (tf) -> map (nav_msgs/OccupancyGrid)

### How to save a map

```
rosrun map_server map_saver -f map_name
```

Yaml: metadata of a map, information and so on.

Ppm file: portable gray map

![2020-01-30_019.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-30_019.jpg)

### Providing the map

launch a map server

```
rosrun map_server map_server map_file.yaml
rostopic echo /map_metadata
rostopic echo /map topic
```

Static_map(nav_msgs/GetMap): provides the map occupancy data through this service. 

Map is **Static** stays always as it was when you created it. Map is **2D** map.

#### Hardware Requirements

If you want to have a good map, you need two things:

1. good laser data
2. good odometry data

## Transfroms

![2020-01-30_016.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-30_016.jpg)

where the laser is placed in the robot. (where position and orientation)

this is waht is called a tranform between frames.

A transform specifies how data expressed in a frame can be transformed into a different frame.

This information is very important.

![2020-01-30_020.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-30_020.jpg)

Slam_gmapping node needs: transform tree 

1. the frame attached to laser -> base_link: robot_state_publisher or tf static_transform_publisher
2. base_link -> odom: provided by Odometry System

`rosrun tf view_frames`

![2020-01-30_021.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-01-30_021.jpg)

## Build a Map using logged data

Crate a bag file. Recording laser data and transforms

```
# move the robot around
roslaunch pkg_name keyboard_teleop_launch_file.launch
# record data
rosbag record -O mylaserdata /laser_topic /tf_topic
```

Build map

```
rosrun gmapping slam_gmapping scan:=kobuki/laser/scan
rosbag play name_of_bag_file_created_in_step_1
rosrun map_server map_saver -f map_name
```

