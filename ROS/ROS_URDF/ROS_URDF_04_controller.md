
控制器设置：

通过参数服务器加载参数，controller_spawner 中包含 ns 命名空间，通过args可以找到对应的controller.yaml设置。

```xml
<launch>
  <!-- Load joint controller configurations from YAML file to parameter server -->
  <rosparam file="$(find my_mira_description)/config/mira_onecontroller.yaml" command="load"/>

  <!-- load the controllers -->

  <node name="controller_spawner" pkg="controller_manager" type="spawner" respawn="false"
    output="screen" ns="/mira" args="roll_joint_position_controller joint_state_controller --shutdown-timeout 3"/>

  <!-- convert joint states to TF transforms for rviz, etc -->
  <node name="robot_state_publisher" pkg="robot_state_publisher" type="robot_state_publisher"
    respawn="false" output="screen">
    <remap from="/joint_states" to="/mira/joint_states" />
  </node>

</launch>
```
config/mira_onecontroller.yaml

```yaml
mira:
    # Publish all joint states -----------------------------------
    joint_state_controller:
      type: joint_state_controller/JointStateController
      publish_rate: 50

    # Position Controllers ---------------------------------------
    roll_joint_position_controller:
      type: effort_controllers/JointPositionController
      joint: roll_joint
      pid: {p: 1.0, i: 1.0, d: 0.0}
    # To add more just add them here as the first one
```

PID是啥？参考：https://www.flitetest.com/articles/p-i-and-sometimes-d-gains-in-a-nutshell