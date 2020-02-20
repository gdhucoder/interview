# Motion Planning using Graphical Interface

使用图形界面进行运动规划

启动MoveIt！助手

`roslaunch moveit_setup_assistant setup_assistant.launch`

![2020-02-18_015.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_015.jpg)

- start
- self-collisions
- virtual joints
- planning groups
- robot poses
- end effectors
- passive joints
- ros control
- simulation
- 3D perception
- author information
- configuration files

![2020-02-18_013.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_013.jpg)

##  move_group node

move_group 是MoveIt的核心，整合了各种组件和根据用户的需求交付动作或者服务。

move_group节点搜集机器人信息，例如点云（PointCloud），机器人关节状态（joint state of the robot)，机器人变化（TFs tansforms）。

move_group节点收集信息，正确的配置后，我们能使用C++或者python代码控制move_group来执行动作，例如抓取和放下，IK，FK或者其他的事情。

使用RViz运动控制插件，我们能通过RViz GUI来命令机器人。

> From the parameter server, it collects the robot kinematics data, such as robot description (URDF), SRDF (Semantic Robot Description Format), and the configuration files. The SRDF file and the configuration files are generated while we generate a MoveIt! package for our robot. The configuration files contain the parameter file for setting joint limits, perception, kinematics, end effector, and so on. These are the files that have been created in the config folder of your package.


## planning scene

"planning scene" 用来表示机器人周围的世界，和保存机器人的状态。

 "world scene monitor" 从 occupancy map monitor中读取数据，使用3D感知，建立环境的3D表示，这个叫做Octomap。Octomap可以由点云数据生成，由 PointCloud occupancy地图更新插件和depth images更新器处理。

 ## Kinematics Handling 运动学处理

MoveIt! 用插件的方式提供了极大的方便，可以切换inverse kinematics algorithms。

## Collision Checking 碰撞检测

Collision Checking 碰撞检测 用于发现planning scene中的碰撞。使用FCL（Fexible Collison Libaray）软件包。

碰撞检测是在运动规划中耗时的任务。为了减少计算量，MoveIt！提供了一个矩阵，叫做ACM（Allowed Collision Matrix）。它包含需要检测碰撞的pairs，1表示不需要检测。优化ACM可以减少碰撞检测的计算量。


