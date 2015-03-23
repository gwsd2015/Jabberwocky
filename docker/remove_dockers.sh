#!/bin/bash


echo "Removing the containers"
sudo docker rm $(sudo docker ps -a -q)


echo "Removing the images"
sudo docker rmi $(sudo docker images -q)
