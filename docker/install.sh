#!/bin/bash

echo "-----BEGIN RSA PRIVATE KEY-----
MIIEpAIBAAKCAQEAt1gbKy3qGJa8NeNcErKDZj7Z20hg7rk09OaBeYIytYrQvHlS
eJX15e3wIU9+S/4cdlGv5N8OBTKhhtqUDvT8XvGdHTaPyo4ag19nZvQwTyZzOsoc
ZcsUdH2a7UZttbWW0RRoMCYCWnIkT5Y6bBPmNQflRvzfNTeH0aY7JRriEHYvwaET
zVuim9cjqykSqVKuOPjSBXwLlI50VmU1awsuhYpdkL6pgr6jPVofo2Q6/sT+aQSD
XOKS/jdbeetEV9prRzgvB+oDIBRTAIkWATdmDFbJ0wVPTwJm1UM9q1i40I20I9en
BPjBShSbNdX61ZOyLwWd+wmjvi/gQKmXV7RE8wIDAQABAoIBAQCCw44tD8RE4U0/
SfdjRnq9M9qBsW+hE+sXOEfj995e6LDTm9wcMSh+Y2O+/klfvc9i/APtwsDnKCNY
YRD0UfqlZMHgEgggOZXjWOoQ3dyOtye8KGZ0m5DtPKCA1phs3yS/3kQpAxLumzYW
pVjpuI+aBxWZucUg2Yg/GMZaQoA1qEBlb5C3ZvQFQN854nDwO3mY8glH+WX2idYJ
x9HGSVR+wemGmAxNBZHg0A49AA+2mxyvynPOUHOD7myvyoBe4bI4Q7BkTOb6vHDA
PldYBsqzU3ZIEFd0TrUNvW0u0xhvnfo9tJNG83ELgqHM6zSBznbrf9UTEPX4MVHb
Rqm4VVBxAoGBAN6IDzRw42fUj0T+iTHSIHbLRunc3tAlNymPGXj+P6rXkM1KjDy5
fyo/sJNqL33ikgrRvr9eB3Ta2SpKjvv6qY7Y44AKl6RBYT0640FcM7pVfQMvi7dG
1iDotwDNZ/vYMR8YuuWSRdUA0BbJa7NVrLRojWgX3xsw7X6Y+gI6htxtAoGBANLr
QDCIvEyhxS2Uuat7MYFll1fBXxS5iCSE5+2NTLWjnw/qWiY3Ww9n1lqQeQ2mPpw1
OvINRQfKWOBNJe3mgbRiYXlTwvqiWPt6tQwzMXbiOgSHo1opX3PZwm6NQOmlhvO6
tRWCerVZE18g4jFu6GDijQT8I/doiQhpXf5XVgrfAoGAV/6fXJJDKhygZpzsLVo0
4iSxrupa5R3COVnPrcuD64+AP915pM2JHnC+5xv3GaVNMKAN+Gg7NJcRhqqi7bsJ
72WOWGRX4GjGBctSb9cA0W7alzbm3ZtSMwMy0ktnEexESFKkHQGBQaAnqMsPHoQD
Xfd16zJ9U6g+UXA2dEkmaOUCgYEAvryjpcBhmGNKrveqGFsl38CBZoimdVV6upu+
KWL4leJLu9bQg3LX1Eyw2eGAt0k5oYW5sp8xZ+QjD2R1AnjLntbBEzkeQA2Erpa/
FzNMbhgTI3mNzcFQYrHwVI1EwIZCTjLwA22ljk3paMOSCeYkG0mFAZcK53gPRB4V
NCbC1r0CgYArMq60CkWawFRNoHOdzLFJ8IdzQO22i4NpcqzQMnj1ESW9dz/K7avY
dPgqrFhDrd/wqYRzDwrXdZsDVm/dOQ2ukGdV2lTMj7doaF6kRhHUcnNe1+sK9eiN
QMUXjznSEF9EfCMBh4eCSsQ4c2pn5RfKvO1FxBBgyjcapQdInBUJ1w==
-----END RSA PRIVATE KEY-----" >> /root/.ssh/id_rsa

sudo chmod 600 /root/.ssh/id_rsa

echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC3WBsrLeoYlrw141wSsoNmPtnbSGDuuTT05oF5gjK1itC8eVJ4lfXl7fAhT35L/hx2Ua/k3w4FMqGG2pQO9Pxe8Z0dNo/KjhqDX2dm9DBPJnM6yhxlyxR0fZrtRm21tZbRFGgwJgJaciRPljpsE+Y1B+VG/N81N4fRpjslGuIQdi/BoRPNW6Kb1yOrKRKpUq44+NIFfAuUjnRWZTVrCy6Fil2QvqmCvqM9Wh+jZDr+xP5pBINc4pL+N1t560RX2mtHOC8H6gMgFFMAiRYBN2YMVsnTBU9PAmbVQz2rWLjQjbQj16cE+MFKFJs11frVk7IvBZ37CaO+L+BAqZdXtETz lucasch@gwu.edu" >> /root/.ssh/id_rsa.pub


sudo apt-get update

sudo apt-get install git mercurial openjdk-7-jdk python-mysqldb nodejs npm sm vim



cd /root/

git clone git@github.com:gwsd2015/Jabberwocky.git

sudo ln -s /usr/bin/nodejs /bin/node

sudo ln -s /usr/bin/npm /bin/npm

cd /root/Jabberwocky/cloud9

npm install python-shell
npm install util
npm install sm

echo "export PATH='/lib/node_modules/sm/bin/sm':$PATH" >> /root/.bashrc

source /root/.bashrc

sm install . 
