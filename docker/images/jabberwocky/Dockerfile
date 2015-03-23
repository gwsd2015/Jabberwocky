FROM ubuntu:14.04
MAINTAINER Lucas Chaufournier <lucasch@gwu.edu>
RUN apt-get update && apt-get install -y git mercurial openjdk-7-jdk python-mysqldb nodejs npm vim

RUN mkdir /root/.ssh/


## Adds private key to system
RUN echo "-----BEGIN RSA PRIVATE KEY-----" >> /root/.ssh/id_rsa && echo "MIIEpAIBAAKCAQEAt1gbKy3qGJa8NeNcErKDZj7Z20hg7rk09OaBeYIytYrQvHlS">> /root/.ssh/id_rsa && echo "eJX15e3wIU9+S/4cdlGv5N8OBTKhhtqUDvT8XvGdHTaPyo4ag19nZvQwTyZzOsoc">> /root/.ssh/id_rsa && echo "ZcsUdH2a7UZttbWW0RRoMCYCWnIkT5Y6bBPmNQflRvzfNTeH0aY7JRriEHYvwaET">> /root/.ssh/id_rsa && echo "zVuim9cjqykSqVKuOPjSBXwLlI50VmU1awsuhYpdkL6pgr6jPVofo2Q6/sT+aQSD">> /root/.ssh/id_rsa && echo "XOKS/jdbeetEV9prRzgvB+oDIBRTAIkWATdmDFbJ0wVPTwJm1UM9q1i40I20I9en">> /root/.ssh/id_rsa && echo "BPjBShSbNdX61ZOyLwWd+wmjvi/gQKmXV7RE8wIDAQABAoIBAQCCw44tD8RE4U0/">> /root/.ssh/id_rsa && echo "SfdjRnq9M9qBsW+hE+sXOEfj995e6LDTm9wcMSh+Y2O+/klfvc9i/APtwsDnKCNY">> /root/.ssh/id_rsa && echo "YRD0UfqlZMHgEgggOZXjWOoQ3dyOtye8KGZ0m5DtPKCA1phs3yS/3kQpAxLumzYW">> /root/.ssh/id_rsa && echo "pVjpuI+aBxWZucUg2Yg/GMZaQoA1qEBlb5C3ZvQFQN854nDwO3mY8glH+WX2idYJ">> /root/.ssh/id_rsa && echo "x9HGSVR+wemGmAxNBZHg0A49AA+2mxyvynPOUHOD7myvyoBe4bI4Q7BkTOb6vHDA">> /root/.ssh/id_rsa && echo "PldYBsqzU3ZIEFd0TrUNvW0u0xhvnfo9tJNG83ELgqHM6zSBznbrf9UTEPX4MVHb">> /root/.ssh/id_rsa && echo "Rqm4VVBxAoGBAN6IDzRw42fUj0T+iTHSIHbLRunc3tAlNymPGXj+P6rXkM1KjDy5">> /root/.ssh/id_rsa && echo "fyo/sJNqL33ikgrRvr9eB3Ta2SpKjvv6qY7Y44AKl6RBYT0640FcM7pVfQMvi7dG">> /root/.ssh/id_rsa && echo "1iDotwDNZ/vYMR8YuuWSRdUA0BbJa7NVrLRojWgX3xsw7X6Y+gI6htxtAoGBANLr">> /root/.ssh/id_rsa && echo "QDCIvEyhxS2Uuat7MYFll1fBXxS5iCSE5+2NTLWjnw/qWiY3Ww9n1lqQeQ2mPpw1">> /root/.ssh/id_rsa && echo "OvINRQfKWOBNJe3mgbRiYXlTwvqiWPt6tQwzMXbiOgSHo1opX3PZwm6NQOmlhvO6">> /root/.ssh/id_rsa && echo "tRWCerVZE18g4jFu6GDijQT8I/doiQhpXf5XVgrfAoGAV/6fXJJDKhygZpzsLVo0">> /root/.ssh/id_rsa && echo "4iSxrupa5R3COVnPrcuD64+AP915pM2JHnC+5xv3GaVNMKAN+Gg7NJcRhqqi7bsJ">> /root/.ssh/id_rsa && echo "72WOWGRX4GjGBctSb9cA0W7alzbm3ZtSMwMy0ktnEexESFKkHQGBQaAnqMsPHoQD">> /root/.ssh/id_rsa && echo "Xfd16zJ9U6g+UXA2dEkmaOUCgYEAvryjpcBhmGNKrveqGFsl38CBZoimdVV6upu+">> /root/.ssh/id_rsa && echo "KWL4leJLu9bQg3LX1Eyw2eGAt0k5oYW5sp8xZ+QjD2R1AnjLntbBEzkeQA2Erpa/">> /root/.ssh/id_rsa && echo "FzNMbhgTI3mNzcFQYrHwVI1EwIZCTjLwA22ljk3paMOSCeYkG0mFAZcK53gPRB4V">> /root/.ssh/id_rsa && echo "NCbC1r0CgYArMq60CkWawFRNoHOdzLFJ8IdzQO22i4NpcqzQMnj1ESW9dz/K7avY">> /root/.ssh/id_rsa && echo "dPgqrFhDrd/wqYRzDwrXdZsDVm/dOQ2ukGdV2lTMj7doaF6kRhHUcnNe1+sK9eiN">> /root/.ssh/id_rsa && echo "QMUXjznSEF9EfCMBh4eCSsQ4c2pn5RfKvO1FxBBgyjcapQdInBUJ1w==">> /root/.ssh/id_rsa && echo "-----END RSA PRIVATE KEY-----" >> /root/.ssh/id_rsa


RUN chmod 600 /root/.ssh/id_rsa

# Adds public key to system
RUN echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC3WBsrLeoYlrw141wSsoNmPtnbSGDuuTT05oF5gjK1itC8eVJ4lfXl7fAhT35L/hx2Ua/k3w4FMqGG2pQO9Pxe8Z0dNo/KjhqDX2dm9DBPJnM6yhxlyxR0fZrtRm21tZbRFGgwJgJaciRPljpsE+Y1B+VG/N81N4fRpjslGuIQdi/BoRPNW6Kb1yOrKRKpUq44+NIFfAuUjnRWZTVrCy6Fil2QvqmCvqM9Wh+jZDr+xP5pBINc4pL+N1t560RX2mtHOC8H6gMgFFMAiRYBN2YMVsnTBU9PAmbVQz2rWLjQjbQj16cE+MFKFJs11frVk7IvBZ37CaO+L+BAqZdXtETz lucasch@gwu.edu" >> /root/.ssh/id_rsa.pub

RUN echo "StrictHostKeyChecking no" >> /etc/ssh/ssh_config

#Clones repo 
RUN cd /root/ && git clone git@github.com:gwsd2015/Jabberwocky.git




#Fixes node location
RUN sudo ln -s /usr/bin/nodejs /bin/node

#Fixes npm
RUN sudo ln -s /usr/bin/npm /bin/npm

# installs utilities/prereqs for cloud9
RUN cd /root/Jabberwocky/cloud9 && npm install python-shell util sm

# Sets path for sm installer
RUN echo "export PATH='/lib/node_modules/sm/bin/sm':\$PATH" >> /root/.bashrc

#source /root/.bashrc

RUN mkdir /root/Jabberwocky/cloud9/wksp/

# installs cloud9
#RUN cd /root/Jabberwocky/cloud9/ && /lib/node_modules/sm/bin/sm install .

RUN cd /root/Jabberwocky/cloud9/ && /usr/bin/sm install .

EXPOSE 3131



