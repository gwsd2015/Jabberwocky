# Docker-Version 0.3.4

FROM ubuntu:14.04

RUN sudo apt-get -y update
RUN sudo apt-get -y install build-essential
RUN sudo apt-get -y install git wget python
RUN sudo apt-get -y install g++ curl libssl-dev apache2-utils git libxml2-dev
RUN sudo apt-get -y remove nodejs
RUN wget http://nodejs.org/dist/v0.10.33/node-v0.10.33.tar.gz
RUN tar -xzvf node-v0.10.33.tar.gz
RUN cd node-v0.10.33; ./configure
RUN cd node-v0.10.33; make
RUN cd node-v0.10.33; make install

COPY src /src

RUN cd /src; npm install

EXPOSE 3131

#CMD["node", "/src/bin/cloud9.sh","-l","0.0.0.0"]


#CMD node /src/bin/./cloud9.sh -l 0.0.0.0
