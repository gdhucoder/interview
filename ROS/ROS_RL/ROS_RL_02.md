

tf needs to be compiled in python3 env.

```
source ~/.catkin_ws_python3/openai_venv/bin/activate
source ~/.catkin_ws_python3/devel/setup.bash
cd /home/user/catkin_ws
rm -rf build devel
catkin build -DCATKIN_ENABLE_TESTING=0 -DCMAKE_BUILD_TYPE=Release -DPYTHON_VERSION=3.5
source devel/setup.bash
roslaunch my_moving_cube_pkg my_start_training_deepq_version.launch
```