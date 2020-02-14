## How to get Robot current pose?

This is a demo for checking robot's pose

The idea is getting Pose info by subscribing the **odom** topic

## Step 1 check `odom` topic

```
rostopic list
```

![2020-02-13_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-13_007.jpg)

## Step 2 build a subscriber to odom topic

```
catkin_create_pkg check_odom rospy
create check_odom.py
create check_odomety.launch
```

Python code:

```python
#!/usr/bin/env python

import rospy
from nav_msgs.msg import Odometry

def callback(msg):
    print(msg.pose.pose)

rospy.init_node('check_odometry')

odom_sub = rospy.Subscriber('/odom', Odometry, callback)
rospy.spin()
```

launch file:

```xml
<launch>
    <node pkg="check_odom" type="check_odom.py" name="check_odometry" output="screen" />
</launch>
```

## Step 3: Usage

launch pkg, then check console

![2020-02-13_008.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-13_008.jpg)

Demo Address：

http://www.rosject.io/l/1099503f/