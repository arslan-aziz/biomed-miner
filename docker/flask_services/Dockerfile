FROM python:3.8-slim-buster

WORKDIR /app

RUN apt-get -y update
RUN apt-get -y install git

COPY resources/requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
RUN pip3 install git+git://github.com/titipata/pubmed_parser.git

COPY resources/app.py .

CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]