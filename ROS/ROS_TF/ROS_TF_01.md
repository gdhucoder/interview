
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





