# Python Baisc

## Tuples:

```python
t = (1, 2, 3, 'This is a tuple')
```

Tuples cannot be updated, they are read-only.

## Dictionaries

Dictionarites are similar to lists, they can be updated. 

```python
dict = {"Jhon":25}
print(dict["John"])
```

## IO

```python
a = 5

print ("Simple print")
print ("Now we print the variable a = " + str(a))
print ("Now we print the variable a = %d" % a)
print ("This is an example of a \n new line")
```

```python
from robot_control_class import RobotControl

rc = RobotControl()
l = rc.get_laser_full()
idx = int(input('Please input index: '))

print('The laser value received is %d \n'%l[idx])
```

注意%f

```python

from robot_control_class import RobotControl

rc = RobotControl()

distance = rc.get_laser(360)

while(distance>1):
    rc.move_straight()
    distance = rc.get_laser(360)
    print('distance is %f' %distance)

rc.stop_robot()

print('distance is %f' %distance)
```

Setting Python3 virtual env

```python
source ~/.py3venv/bin/activate
```

Global Variables

```python
def f(): 
    lv = "Local Variable"

f()
print(lv)
# error
```

Right usage:

```python
def f():
    global gv
    gv = "Global Variable"

gv = "Empty"
print (gv)
f()
print(gv)
```

## Class

```python
class Jedi:
    def __init__(self, name):
        self.jedi_name = name

    def say_hi(self):
        print('Hello, my name is ', self.jedi_name)

j1 = Jedi('ObiWan')
j1.say_hi()

j2 = Jedi('Anakin')
j2.say_hi()
```

How to load local class file in the same dir:

```
create an __init__.python
then you can like this from xx file name import Class
```

```java
      for (int i = 0; i < src.length; i++) 
        for (int j = 0; j < src[0].length; j++) 
          dis[i][j] = src[i][j];
      
      for (int j = 0; j < src.length; j++) 
        for (int i = 0; i < src[0].length; i++) 
          dis[i][j] = src[i][j];
    
```

