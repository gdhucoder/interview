
frame之间的位置和方向的转换叫做Transform （变换）

IRobot 跟着 Turble：

roslaunch turtle_tf_3d irobot_follow_turtle.launch

移动3D turtle：

roslaunch turtle_tf_3d turtle_keyboard_move.launch

![2020-03-10_003.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_003.jpg)

上面例子中的`irobot_follow_turtle.launch`做了两件事：

1. tf broadcaster 发布 turtle 的TF
2. tf listener 读取TF，用于iRobot计算跟这Turtle的方向

## View Frames

使用`view_frames`可以获取系统的TF树🌲

```shell
roscd
cd ../src
rosrun tf view_frames
```

![2020-03-10_004.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_004.jpg)

- Broadcaster: the name of the broadcaster
- avergae rate of publication: 5.2Hz
- Most recent transform
- TFF buffer: 4.6 sec

## rqt_tf_tree

查看可以实时更新的变换树：

rosrun rqt_tf_tree rqt_tf_tree

更新时，在Gui中点击refresh

当停止发布tf数据时，Most recent transform 将不会再更新，这是一个小坑，需要注意。

![刚启动gui，没有发布数据](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_005.jpg)

![发布TF](https://gitee.com/gdhu/testtingop/raw/master/2020-03-10_006.jpg)


显示最新的一条tf发布的消息：

rostopic echo -n1 /tf

![2020-03-12_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-12_000.jpg)

查看frame之间的相对关系：`rosrun tf tf_echo turtle1 turtle2`

![2020-03-12_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-12_001.jpg)

可以在rivz中查看TF：

![2020-03-12_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-12_002.jpg)

## 到底啥是TF？

TF 是空间上每个frame和它的父parent的相对关系。 数据通过tf话题发布。

我们可以使用这些消息计算同一个tf树中，不同frame之间的变换。

##  如何写一个TF publisher？

如何知道一个机器人的位置呢？

这要依赖于你机器人的定位系统：

- 可以通过 odometry 的`/odom`话题获取
- 可以从navigation system中获取
- 可以通过GPS数据计算

在仿真的Gazebo环境中， Gazebo Simulator 可精确的知道机器人的位置和方向。

可以通过GazeboModel这个类获取机器人的位置和方向

```python
#! /usr/bin/env python

import rospy
import time
import tf
from turtle_tf_3d.get_model_gazebo_pose import GazeboModel

def handle_turtle_pose(pose_msg, robot_name):
    # print str(robot_name) + ": " + str(pose_msg)
    br = tf.TransformBroadcaster()
    br.sendTransform((pose_msg.position.x, pose_msg.position.y, pose_msg.position.z),
    (pose_msg.orientation.x, pose_msg.orientation.y, pose_msg.orientation.z, pose_msg.orientation.w),
    rospy.Time.now(),
    robot_name,
    "/world")

def publisher_of_tf():
    rospy.init_node('publisher_of_tf_node', anonymous=True)
    robot_name_list = ['turtle1', 'turtle2']
    gazebo_model_object = GazeboModel(robot_name_list)

    for robot_name in robot_name_list:
        pose_now = gazebo_model_object.get_model_pose(robot_name)

    time.sleep(1)
    rospy.loginfo("Ready.. Starting to Publish TF data now...")

    rate = rospy.Rate(5)

    while not rospy.is_shutdown():
        for robot_name in robot_name_list:
            pose_now = gazebo_model_object.get_model_pose(robot_name)
            if not pose_now:
                print "The" + str(robot_name) + "'s Pose is not yet available... Please Try later"
            else:
                handle_turtle_pose(pose_now, robot_name)
        rate.sleep()

if __name__ =='__main__':
    try:
        publisher_of_tf()
    except rospy.ROSInterruptException:
        pass
```

获得两个frame之间不同的变换：

`(trans,rot) = listener.lookupTransform(follower_modelz_frame, model_to_be_followed_frame, rospy.Time(0))`

这段代码大概的意思

```python
angular = 4 * math.atan2(trans[1], trans[0])
linear = 0.5 * math.sqrt(trans[0] ** 2 + trans[1] ** 2)
cmd = geometry_msgs.msg.Twist()
cmd.linear.x = linear
cmd.angular.z = angular
turtle_vel.publish(cmd)
```

如何计算：示意图

![2020-03-13_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-13_000.jpg)

## 如何新增更多的Frames？

如果你想相对于`/turtle1`新增Frame，例如每个眼睛👀对应一个新的frame，或者传感器呢？
或者进一步，你想生成一个完全抽象的frame，使得IRobot转圈呢？

### 固定的frame

固定的frame不随着时间改变。 例如fixed_carrot这个frame就是固定不变的，它的父frame为turtle1
。


```python
#!/usr/bin/env python  

import rospy
import tf

if __name__="__main__":
    rospy.init_node('my_fixed_carrot_tf_broadcaster')
    br = tf.TransformBroadcaster()
    rate = rospy.Rate(3.0)
    while not rospy.is_shutdown():
        br.sendTransform((0.0, 2.0, 0.0),
        (0.0, 0.0, 0.0, 1.0),
        rospy.Time.now(),
        "fixed_carrot",
        "turtle1")
        rate.sleep()
```

### 移动的frame

移动的frame可以随时间变化。

moving_carrot 是一个移动的frame，它相对的父frame为turtle2

![2020-03-13_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-03-13_001.jpg)

```python
#!/usr/bin/env python  
import rospy
import tf
import math

if __name__ == '__main__':
    rospy.init_node('my_moving_carrot_tf_broadcaster')
    br = tf.TransformBroadcaster()
    rate = rospy.Rate(5.0)
    turning_speed_rate = 0.1
    while not rospy.is_shutdown():
        t = (rospy.Time.now().to_sec() * math.pi) * turning_speed_rate
        rad_var = t % (2*math.pi)
        br.sendTransform((1.0*math.sin(rad_var), 1.0*math.cos(rad_var), 0.0),
                        (0.0, 0.0, 0.0, 1.0),
                        rospy.Time.now(),
                        "moving_carrot", # 子frame
                        "turtle2") #  相对父frame
        rate.sleep()
```

注意：⚠️

1. 必须发布要使用frame的父frame
2. 使用四元数（Quaternions），要知道如何转换成欧拉的表示形式（Euler）


### 欧拉到四元数

```python
import tf
quaternion = tf.transformations.quaternion_from_euler(roll,pitch,yaw)
```

```python
import tf
quaternion_x = my_quaternion[0]
quaternion_y = my_quaternion[1]
quaternion_z = my_quaternion[2]
quaternion_w = my_quaternion[3]
explicit_quat = [quaternion_x, quaternion_y, quaternion_z, quaternion_w]
your_euler = tf.transformations.euler_from_quaternion(explicit_quat)
```