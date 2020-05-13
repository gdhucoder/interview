
OpenAI+ROS


DeepQ:

first we need to load param into server

```xml
<launch>
    <rosparam command="load" file="$(find cartpole_v0_training)/config/cartpole_qlearn_params.yaml" />
</launch>
```

then, launch train.scripts

