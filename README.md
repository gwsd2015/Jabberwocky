

<pre>
 _____________________
 Project Jabberwocky 
 ---------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
</pre>
This is the source repository for Project Jabberwocky, the evolving IDE for new students.


<H1>Prereqs</H1>
<ul>
<li>apt-get Mercurial</li>
<li>apt-get java</li>
<li>cloud9 pre-reqs</li>
<li>apt-get python-mysqldb</li>
<li>sudo npm install python-shell</li>
<li>sudo npm install util</li>
</ul>

<H1>Installation</H1>
 <ol>
 <li>Install the latest node </li>
 <li>Install the latest npm</li>
 <li> Clone Jabberwocky</li>
 <li>npm install</li>
 <li>npm install sm</li>
 <li>Add Sm to path</li>
 <li> sm install . <- Do this in /src/ </li>
 <li> cd bin</li>
 <li>sudo  ./cloud9.sh -l 0.0.0.0 -w /path/to/workspace</li>
 </ol>
 
 
 
 <h1> Debugging Cloud9</h1>
At the command line just run <code><pre>node-inspector</pre></code>. This will present you with a debugger address that you can use to debug the applicaiton.
<br>
To set a breakpoint at the first location just run the command:
<code><pre>sudo node --debug-brk server.js -l 0.0.0.0 -w wksp -a x-www-browser</pre></code>
