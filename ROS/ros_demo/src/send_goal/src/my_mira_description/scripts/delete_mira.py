#! /usr/bin/env python

import rospy
from gazebo_msgs.srv import DeleteModel, DeleteModelRequest

import sys

rospy.init_node('remove_model_service_client')
print "waiting for service..."
rospy.wait_for_service('/gazebo/delete_model')
delete_model_service = rospy.ServiceProxy('/gazebo/delete_model', DeleteModel)
kk = DeleteModelRequest()
kk.model_name = 'mira'
print "Deleting model = " + str(kk)
status_msg = delete_model_service(kk)
print status_msg