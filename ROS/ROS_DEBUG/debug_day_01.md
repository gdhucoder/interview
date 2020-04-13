
Stop Robot

rostopic pub /cmd_vel geometry_msgs/Twist

Reset Gazebo world

rosservice call /gazebo/reset_world

```python
#! /usr/bin/env python
import rospy
from geometry_msgs.msg import Twist

class MoveRobot():
    def __init__(self):
        self.vel_publisher = rospy.Publisher('/cmd_vel', Twist, queue_size=1)
        self.move_msg = Twist()

    def publish_once_in_cmd_vel(self):
            """
            This is because publishing in topics sometimes fails the first time you publish.
            In continuous publishing systems, this is no big deal, but in systems that publish only
            once, it IS very important.
            """
            while not rospy.is_shutdown():
                connections = self.vel_publisher.get_num_connections()
                if connections > 0:
                    self.vel_publisher.publish(self.move_msg)
                    #rospy.loginfo("Cmd Published")
                    break
                else:
                    rospy.sleep(1)

    def send_cmd(self, linear=0, angular=0):
        self.move_msg.linear.x = linear
        self.move_msg.angular.z = angular
        self.publish_once_in_cmd_vel()

if __name__ == '__main__':
    rospy.init_node('debug_day1_node')
    moverobot_object = MoveRobot()
    moverobot_object.send_cmd(0.5, 0.5)
```

```python
#! /usr/bin/env python

import rospy
from move_robot import MoveRobot
from sensor_msgs.msg import LaserScan

class StopWall():
    def __init__(self):

        self.sub = rospy.Subscriber('/kobuki/laser/scan', LaserScan, self.callback)
        self.moverobot_object = MoveRobot()

    def callback(self, msg):
        print msg.ranges[360]
        if msg.ranges[360] > 1:
            linear_x = 0.5
            angular_z = 0.0
            self.moverobot_object.send_cmd(linear_x, angular_z)

        #If the distance to an obstacle in front of the robot is smaller than 1 meter, the robot will stop
        if msg.ranges[360] < 1:
            linear_x = 0.0
            angular_z = 0.0
            self.moverobot_object.send_cmd(linear_x, angular_z)


if __name__ == '__main__':
    rospy.init_node('debug_day1_node')
    stopwall_object = StopWall()
    rospy.spin()
```

`rospy.spin()` will go forever until it receives a shutdown signal

`rospy.sleep(period)` runs for a duration, then stops.

![2020-04-08_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-04-08_005.jpg)

![2020-04-08_006.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-04-08_006.jpg)

![2020-04-08_007.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-04-08_007.jpg)