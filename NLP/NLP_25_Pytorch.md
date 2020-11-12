
How to use GCP

reference: https://towardsdatascience.com/running-jupyter-notebook-in-google-cloud-platform-in-15-min-61e16da34d52

jupyter notebook --generate-config

/home/gdong_hu_gmail_com/.jupyter/jupyter_notebook_config.py

vim /home/gdong_hu_gmail_com/.jupyter/jupyter_notebook_config.py

c = get_config()
c.NotebookApp.ip = '*'
c.NotebookApp.open_browser = False
c.NotebookApp.port = 5000

jupyter-notebook --no-browser --port=5000

http://35.236.46.183:5000/

http://34.94.36.239:5000/

c = get_config()
c.NotebookApp.ip = '*'
c.NotebookApp.open_browser = False
c.NotebookApp.port = 5000
c.NotebookApp.token = ''
c.NotebookApp.password = ''