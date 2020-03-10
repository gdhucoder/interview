
XACROS 是 XML Macros的缩写，可以帮助我们压缩urdf文件的大小，增加文件的可读性和可维护性。

通过XACRO文件生成URDF文件

`rosrun xacro xacro model.xacro > model.urdf`

`<param name="robot_description" command="$(find xacro)/xacro '$(find my_robot_description)/robots/myrobot.urdf.xacro'" />`

put the XACRO file inside `robots` folder

