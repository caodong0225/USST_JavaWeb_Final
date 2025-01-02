FROM python:3.10
WORKDIR /shopping_backend

COPY requirements.txt ./
RUN pip install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple

COPY . .

CMD ["gunicorn", "start:app", "--preload", "-c", "./gunicorn.conf.py"]