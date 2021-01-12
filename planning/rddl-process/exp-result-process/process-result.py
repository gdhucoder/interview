#!/usr/bin/env python3
# -*- coding: utf-8 -*

result_log = open("rddl-info.log", "r")

str = ''
for line in result_log:
    if line:
        if line.find("inst") is not -1:
            str += line[:-1]
        if line.find('Session finished successfully:') is not -1:
            str += ','+line.split(":")[1][1:-1]
            print(str)
        if line.find('Average reward:') is not -1:
            str += ','+line.split(":")[1][1:]
            print(str)

