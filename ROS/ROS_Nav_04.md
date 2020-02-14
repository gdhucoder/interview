# Local Planner

## config planner:

```xml
  <arg name="base_global_planner" default="navfn/NavfnROS"/>
  <arg name="base_local_planner" default="dwa_local_planner/DWAPlannerROS"/>
```

## Steps

1. Sample
2. Forward simulation
3. Evaluate each trajectory
4. Pick highest-score trajectory

## Summary

the move_base node is the node that coordinates all of the Path Planning System.

- Global planner
- local planner
- costmaps
- recovery behaviors

### Overall

Summarizing, this is how the whole path planning method goes:

After getting the current position of the robot, we can send a goal position to the **move_base** node. This node will then send this goal position to a **global planner** which will plan a path from the current robot position to the goal position. This plan is in respect to the **global costmap**, which is feeding from the **map server**.

The **global planner** will then send this path to the **local planner**, which executes each segment of the global plan. The **local planner** gets the odometry and the laser data values and finds a collision-free local plan for the robot. The **local planner** is associated with the **local costmap**, which can monitor the obstacle(s) around the robot. The **local planner** generates the velocity commands and sends them to the base controller. The robot base controller will then convert these commands into real robot movement.

If the robot is stuck somewhere, the recovery behavior nodes, such as the **clear costmap recovery** or **rotate recovery**, will be called.

Dynamic Configure:

`rosrun rqt_reconfigure rqt_reconfigure`





