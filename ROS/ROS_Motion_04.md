
modify `config folder -> ros_controllers.yaml`

```
controller_list:
  - name: rbkairos/arm_controller
    action_ns: follow_joint_trajectory
    default: True
    type: FollowJointTrajectory
    joints:
      - rbkairos_ur10_shoulder_pan_joint
      - rbkairos_ur10_shoulder_lift_joint
      - rbkairos_ur10_elbow_joint
      - rbkairos_ur10_wrist_1_joint
      - rbkairos_ur10_wrist_2_joint
      - rbkairos_ur10_wrist_3_joint
```

launch file:

```xml
<launch>

  <include file="$(find rkbairos_moveit_config)/launch/planning_context.launch" >
    <arg name="load_robot_description" value="true" />
  </include>

  <node name="joint_state_publisher" pkg="joint_state_publisher" type="joint_state_publisher">
    <param name="/use_gui" value="false"/>
    <rosparam param="/source_list">[/rbkairos/joint_states]</rosparam>
  </node>

  <include file="$(find rkbairos_moveit_config)/launch/move_group.launch">
    <arg name="publish_monitored_planning_scene" value="true" />
  </include>

  <include file="$(find rkbairos_moveit_config)/launch/moveit_rviz.launch">
    <arg name="config" value="true"/>
  </include>

</launch>
```

![2020-02-18_020.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_020.jpg)

![2020-02-18_023.jpg](https://gitee.com/gdhu/testtingop/raw/master/2020-02-18_023.jpg)


cp /home/simulations/public_sim_ws/src/all/rbkairos_tc/rbkairos_common/rbkairos_description/robots/rbkairos.urdf.xacro /home/user/catkin_ws/src/

cp /home/simulations/public_sim_ws/src/all/rbkairos_tc/ /home/user/catkin_ws/src/
cp -R /home/simulations/public_sim_ws/src/all/rbkairos_tc/ /home/user/catkin_ws/src/rbkairos


PointCloudUpdater  把从相机获取的数据传送给planning scene。

Summary

已知机器人的起始位置和目标位置，机器人的几何描述，环境的几何描述。运动规划是使机器人由起始位置到目标位置实现最优路径，同时避免碰撞的技术。

实现运动规划，我们要向运动规划器发送请求，指定我们的规划请求。规划请求可以通过指定终端effector的目标位置实现，例如抓取任务。

