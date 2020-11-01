

reference: https://towardsdatascience.com/running-jupyter-notebook-in-google-cloud-platform-in-15-min-61e16da34d52

jupyter notebook config

/home/gdong_hu_gmail_com/.jupyter/jupyter_notebook_config.py

vim /home/gdong_hu_gmail_com/.jupyter/jupyter_notebook_config.py

c = get_config()
c.NotebookApp.ip = '*'
c.NotebookApp.open_browser = False
c.NotebookApp.port = 5000

jupyter-notebook --no-browser --port=5000

http://35.236.46.183:5000/?token=/?token=38ca758a2fc4f28a1458db8bc28d2a1e7228825d76faf9a4


http://127.0.0.1:5000/?token=98d1b35d7116695df89632ecfeb55073dc4a45095f09cf5d