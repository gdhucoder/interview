
# ROS Manipulattion

![2020-02-18_000.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_000.jpg)


 ```python
from smart_grasping_sandbox.smart_grasper import SmartGrasper
from tf.transformations import quaternion_from_euler
from math import pi
import time

sgs = SmartGrasper()

sgs.pick()

sgs.reset_world()
 ```

 ```
 [ INFO] [1582033840.873647877, 1884.048000000]: Ready to take commands for planning group hand.
[INFO] [1582033841.282794, 1884.237000]: STARTING CONTROLLERS
[INFO] [1582033841.394167, 1884.239000]: Moving to Pregrasp
[INFO] [1582033844.150182, 1885.978000]: Grasping
[INFO] [1582033850.644981, 1890.060000]: Lifting
[INFO] [1582033856.722736, 1893.539000]: STARTING CONTROLLERS
 ```
![2020-02-18_001.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_001.jpg)

![2020-02-18_002.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_002.jpg)

Human intractable workspaces

Workspaces which are very hard for human to manage.

- too small workspaces
- too big workspaces
- workspaces where too much percison is needed.

RB-Kairos robot

![2020-02-18_003.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_003.jpg)

Fetch Robot

![2020-02-18_004.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_004.jpg)

RB-1 

![2020-02-18_005.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_005.jpg)