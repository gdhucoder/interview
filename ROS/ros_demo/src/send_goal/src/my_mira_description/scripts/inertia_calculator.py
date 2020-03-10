#!/usr/bin/env python

import math


print 'in'
class InertialCalculator(object):

    def __init__(self):
        print "InertailCalculator Initialized..."
    
    def start_ask_loop(self):
        selection = "START"

        while selection != 'Q':
            print "Select Geometyr to Calculate:"
            print "[1]Box width(w)*depth(d)*height(h)"
            print "[2]Sphere radius(r)"
            print "[3]Cylinder raduis(r) * height(h)"
            print "[Q]END program"
            selection = raw_input(">>")
            self.select_action(selection)
        
        print "InertialCalculator Quit..."

    def select_action(self, selection):
        if selection =='1':
            mass = float(raw_input("mass>>"))
            width = float(raw_input('width>>'))
            depth = float(raw_input('depth>>'))
            height = float(raw_input("hiehgt>>"))
            self.calculate_box_inertia(m=mass, w=width, d=depth, h=height)
    
    def calculate_box_inertia(self, m, w, d, h):
        Iw = (m/12.0)*(pow(d,2)+pow(h,2))
        Id = (m / 12.0) * (pow(w, 2) + pow(h, 2))
        Ih = (m / 12.0) * (pow(w, 2) + pow(d, 2))
        print "BOX w*d*h, Iw = "+str(Iw)+",Id = "+str(Id)+",Ih = "+str(Ih)
    
if __name__=="__main__":
    print 'in'
    inertial_obj = InertialCalculator()  
    inertial_obj.start_ask_loop() 