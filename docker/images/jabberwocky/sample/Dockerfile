FROM ubuntu:14.04
MAINTAINER Lucas Chaufournier <lucasch@gwu.edu>
RUN apt-get update && apt-get install -y git mercurial openjdk-7-jdk python-mysqldb nodejs npm vim

#Fixes node location
RUN ln -s /usr/bin/nodejs /bin/node

#Fixes npm
RUN ln -s /usr/bin/npm /bin/npm


RUN echo "StrictHostKeyChecking no" >> /etc/ssh/ssh_config

#RUN useradd alice && mkdir /home/alice && chown -R alice:alice /home/alice
RUN useradd -d /home/alice/ -m -G sudo alice
RUN echo alice:iamadmin | chpasswd 
#RUN echo "alice:x:1000:1000::/home/alice:" >> /etc/passwd
#RUN echo "alice:x:1000:" >> /etc/group
#RUN echo "alice:\$6\$G8/rcEVm$pZDCCMtx..PvOQ3BDJkqdlmRdjSGwuWa92eHSnO05hga0sUEXuRhWWlTjwClNd37SCq29vkovUUtACfGhLHUk/:16508:0:99999:7:::" >> /etc/shadow


#RUN mkdir /home/alice && chown -R alice:alice /home/alice

# installs utilities/prereqs for cloud9
RUN sudo npm install -g python-shell util sm


USER alice

RUN mkdir /home/alice/.ssh/


## Adds private key to system
RUN echo "-----BEGIN RSA PRIVATE KEY-----" >> /home/alice/.ssh/id_rsa && echo "MIIEpAIBAAKCAQEAt1gbKy3qGJa8NeNcErKDZj7Z20hg7rk09OaBeYIytYrQvHlS">> /home/alice/.ssh/id_rsa && echo "eJX15e3wIU9+S/4cdlGv5N8OBTKhhtqUDvT8XvGdHTaPyo4ag19nZvQwTyZzOsoc">> /home/alice/.ssh/id_rsa && echo "ZcsUdH2a7UZttbWW0RRoMCYCWnIkT5Y6bBPmNQflRvzfNTeH0aY7JRriEHYvwaET">> /home/alice/.ssh/id_rsa && echo "zVuim9cjqykSqVKuOPjSBXwLlI50VmU1awsuhYpdkL6pgr6jPVofo2Q6/sT+aQSD">> /home/alice/.ssh/id_rsa && echo "XOKS/jdbeetEV9prRzgvB+oDIBRTAIkWATdmDFbJ0wVPTwJm1UM9q1i40I20I9en">> /home/alice/.ssh/id_rsa && echo "BPjBShSbNdX61ZOyLwWd+wmjvi/gQKmXV7RE8wIDAQABAoIBAQCCw44tD8RE4U0/">> /home/alice/.ssh/id_rsa && echo "SfdjRnq9M9qBsW+hE+sXOEfj995e6LDTm9wcMSh+Y2O+/klfvc9i/APtwsDnKCNY">> /home/alice/.ssh/id_rsa && echo "YRD0UfqlZMHgEgggOZXjWOoQ3dyOtye8KGZ0m5DtPKCA1phs3yS/3kQpAxLumzYW">> /home/alice/.ssh/id_rsa && echo "pVjpuI+aBxWZucUg2Yg/GMZaQoA1qEBlb5C3ZvQFQN854nDwO3mY8glH+WX2idYJ">> /home/alice/.ssh/id_rsa && echo "x9HGSVR+wemGmAxNBZHg0A49AA+2mxyvynPOUHOD7myvyoBe4bI4Q7BkTOb6vHDA">> /home/alice/.ssh/id_rsa && echo "PldYBsqzU3ZIEFd0TrUNvW0u0xhvnfo9tJNG83ELgqHM6zSBznbrf9UTEPX4MVHb">> /home/alice/.ssh/id_rsa && echo "Rqm4VVBxAoGBAN6IDzRw42fUj0T+iTHSIHbLRunc3tAlNymPGXj+P6rXkM1KjDy5">> /home/alice/.ssh/id_rsa && echo "fyo/sJNqL33ikgrRvr9eB3Ta2SpKjvv6qY7Y44AKl6RBYT0640FcM7pVfQMvi7dG">> /home/alice/.ssh/id_rsa && echo "1iDotwDNZ/vYMR8YuuWSRdUA0BbJa7NVrLRojWgX3xsw7X6Y+gI6htxtAoGBANLr">> /home/alice/.ssh/id_rsa && echo "QDCIvEyhxS2Uuat7MYFll1fBXxS5iCSE5+2NTLWjnw/qWiY3Ww9n1lqQeQ2mPpw1">> /home/alice/.ssh/id_rsa && echo "OvINRQfKWOBNJe3mgbRiYXlTwvqiWPt6tQwzMXbiOgSHo1opX3PZwm6NQOmlhvO6">> /home/alice/.ssh/id_rsa && echo "tRWCerVZE18g4jFu6GDijQT8I/doiQhpXf5XVgrfAoGAV/6fXJJDKhygZpzsLVo0">> /home/alice/.ssh/id_rsa && echo "4iSxrupa5R3COVnPrcuD64+AP915pM2JHnC+5xv3GaVNMKAN+Gg7NJcRhqqi7bsJ">> /home/alice/.ssh/id_rsa && echo "72WOWGRX4GjGBctSb9cA0W7alzbm3ZtSMwMy0ktnEexESFKkHQGBQaAnqMsPHoQD">> /home/alice/.ssh/id_rsa && echo "Xfd16zJ9U6g+UXA2dEkmaOUCgYEAvryjpcBhmGNKrveqGFsl38CBZoimdVV6upu+">> /home/alice/.ssh/id_rsa && echo "KWL4leJLu9bQg3LX1Eyw2eGAt0k5oYW5sp8xZ+QjD2R1AnjLntbBEzkeQA2Erpa/">> /home/alice/.ssh/id_rsa && echo "FzNMbhgTI3mNzcFQYrHwVI1EwIZCTjLwA22ljk3paMOSCeYkG0mFAZcK53gPRB4V">> /home/alice/.ssh/id_rsa && echo "NCbC1r0CgYArMq60CkWawFRNoHOdzLFJ8IdzQO22i4NpcqzQMnj1ESW9dz/K7avY">> /home/alice/.ssh/id_rsa && echo "dPgqrFhDrd/wqYRzDwrXdZsDVm/dOQ2ukGdV2lTMj7doaF6kRhHUcnNe1+sK9eiN">> /home/alice/.ssh/id_rsa && echo "QMUXjznSEF9EfCMBh4eCSsQ4c2pn5RfKvO1FxBBgyjcapQdInBUJ1w==">> /home/alice/.ssh/id_rsa && echo "-----END RSA PRIVATE KEY-----" >> /home/alice/.ssh/id_rsa


RUN chmod 600 /home/alice/.ssh/id_rsa

# Adds public key to system
RUN echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC3WBsrLeoYlrw141wSsoNmPtnbSGDuuTT05oF5gjK1itC8eVJ4lfXl7fAhT35L/hx2Ua/k3w4FMqGG2pQO9Pxe8Z0dNo/KjhqDX2dm9DBPJnM6yhxlyxR0fZrtRm21tZbRFGgwJgJaciRPljpsE+Y1B+VG/N81N4fRpjslGuIQdi/BoRPNW6Kb1yOrKRKpUq44+NIFfAuUjnRWZTVrCy6Fil2QvqmCvqM9Wh+jZDr+xP5pBINc4pL+N1t560RX2mtHOC8H6gMgFFMAiRYBN2YMVsnTBU9PAmbVQz2rWLjQjbQj16cE+MFKFJs11frVk7IvBZ37CaO+L+BAqZdXtETz lucasch@gwu.edu" >> /home/alice/.ssh/id_rsa.pub


#Clones repo 
RUN cd  /home/alice/ && git clone git@github.com:gwsd2015/Jabberwocky.git

#USER root


WORKDIR /home/alice/Jabberwocky/cloud9
USER root
# installs utilities/prereqs for cloud9
RUN sudo npm install util 
RUN sudo npm install sm
RUN sudo npm install python-shell 


USER alice

# Sets path for sm installer
RUN echo "export PATH='/lib/node_modules/sm/bin/sm':\$PATH" >> /home/alice/.bashrc

#RUN source /home/alice/.bashrc

RUN mkdir wksp/
#USER root
# installs cloud9
#RUN cd /home/alice/Jabberwocky/cloud9/ && /lib/node_modules/sm/bin/sm install .
USER root
#RUN cd /home/alice/Jabberwocky/cloud9/ && /home/alice/Jabberwocky/cloud9/node_modules/sm/bin/sm install .
RUN /home/alice/Jabberwocky/cloud9/node_modules/sm/bin/sm install .
#RUN sudo sm install .
RUN make ace 
RUN chown -R alice:alice /home/alice/Jabberwocky/cloud9/
#USER alice
#EXPOSE 3131



