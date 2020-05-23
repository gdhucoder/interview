import gym
import time
import numpy
import random
import time
import qlearn
from gym import wrappers
from functools import reduce

if __name__ == '__main__':
    
    # Create the Gym environment
    env = gym.make('CartPole-v0')
    print ( "Gym environment done")
    last_time_steps = numpy.ndarray(0)
    # Set the logging system
    Alpha = 0.5
    Epsilon = 0.1
    Gamma = 0.9
    epsilon_discount = 0.99
    nepisodes = 10000
    nsteps = 100
    # Initialises the algorithm that we are going to use for learning
    qlearn = qlearn.QLearn(actions=range(env.action_space.n),
                    alpha=Alpha, gamma=Gamma, epsilon=Epsilon)
    initial_epsilon = qlearn.epsilon
    start_time = time.time()
    highest_reward = 0

    total_rewards = 0.0
    # Starts the main training loop: the one about the episodes to do
    for x in range(nepisodes):
        print("############### START EPISODE => " + str(x))
        
        cumulated_reward = 0  
        done = False
        if qlearn.epsilon > 0.05:
            qlearn.epsilon *= epsilon_discount
        
        # Initialize the environment and get first state of the robot
        
        observation = env.reset()
        # print (observation)
        # state = ''.join(map(str, observation))
        state = observation
        
        # Show on screen the actual situation of the robot
        # for each episode, we test the robot for nsteps
        for i in range(nsteps):
            
            print("############### Start Step => "+str(i))
            # Pick an action based on the current state
            action = qlearn.chooseAction(state)
            print ("Next action is: %d", action)
            # Execute the action in the environment and get feedback
            observation, reward, done, info = env.step(action)
            print(str(observation) + " " + str(reward))
            cumulated_reward += reward
            if highest_reward < cumulated_reward:
                highest_reward = cumulated_reward
            # nextState = ''.join(map(str, observation))
            nextState = observation
            qlearn.learn(state, action, reward, nextState)
            if not(done):
                state = nextState
            else:
                print ("DONE")
                last_time_steps = numpy.append(last_time_steps, [int(i + 1)])
                total_rewards = numpy.append(total_rewards, reward)
                break
            print("############### END Step =>" + str(i))
        m, s = divmod(int(time.time() - start_time), 60)
        h, m = divmod(m, 60)
        print ( ("EP: "+str(x+1)+" - [alpha: "+str(round(qlearn.alpha,2))+" - gamma: "+str(round(qlearn.gamma,2))+" - epsilon: "+str(round(qlearn.epsilon,2))+"] - Reward: "+str(cumulated_reward)+"     Time: %d:%02d:%02d" % (h, m, s)))
    
    print ( ("\n|"+str(nepisodes)+"|"+str(qlearn.alpha)+"|"+str(qlearn.gamma)+"|"+str(initial_epsilon)+"*"+str(epsilon_discount)+"|"+str(highest_reward)+"| PICTURE |"))
    l = total_rewards.tolist()
    l.sort()
    #print("Parameters: a="+str)
    print("Overall score: {:0.2f}".format(total_rewards.mean()))
    print("Best 100 score: {:0.2f}".format(reduce(lambda x, y: x + y, l[-100:]) / len(l[-100:])))
    # print("average score: {:0.2f}".format(total_rewards/nepisodes))
    env.close()