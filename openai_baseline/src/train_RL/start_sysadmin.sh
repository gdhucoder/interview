#!/bin/sh

echo 'Start Expriments'
# then run test
python3 q_learning.py --inst sysadmin_inst_mdp__1 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-1.log

python3 q_learning.py --inst sysadmin_inst_mdp__2 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-2.log

python3 q_learning.py --inst sysadmin_inst_mdp__3 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-3.log

python3 q_learning.py --inst sysadmin_inst_mdp__4 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-4.log

python3 q_learning.py --inst sysadmin_inst_mdp__5 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-5.log

python3 q_learning.py --inst sysadmin_inst_mdp__6 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-6.log

python3 q_learning.py --inst sysadmin_inst_mdp__7 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-7.log

python3 q_learning.py --inst sysadmin_inst_mdp__8 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-8.log

python3 q_learning.py --inst sysadmin_inst_mdp__9 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-9.log

python3 q_learning.py --inst sysadmin_inst_mdp__10 --eps 0.3 --norm_reward --path_suffix "normalized" --train_episodes 10000 --scratch > ./log/sys-10.log