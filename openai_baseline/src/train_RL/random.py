import gym
import time
env = gym.make('CartPole-v0')   #创造环境
observation = env.reset()       #初始化环境，observation为环境状态
count = 0
for t in range(100):
    action = env.action_space.sample()  #随机采样动作
    observation, reward, done, info = env.step(action)  #与环境交互，获得下一步的时刻
    if done:             
        break
    env.render()         #绘制场景
    count+=1
    time.sleep(0.2)      #每次等待0.2s
print(count)             #打印该次尝试的步数