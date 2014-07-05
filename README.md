# spray-markdown
We will create a Markdown service that will read .md files from the classpath and loads an associated .css file to
style the generated HTML. Markdown is handy when documenting our REST APIs, and its much easier than writing HTML!

# Starting
This project uses the Typesafe Activator Launcher, only a Java 6 or higher must be installed on your computer and 
the activator-laucher will do the rest:

    $ ./activator 'run-main com.example.Main'

or

    $ ./launch.sh

# Docker
## Run the image
When you have Docker installed, you can launch a [containerized version](https://registry.hub.docker.com/u/dnvriend/spray-markdown/) using the following command:

    $ sudo docker run -d -P dnvriend/spray-markdown
    
Then check which local port has been mapped to the VM
    
    $ sudo docker ps
    
And note the entries in the PORTS column eg:

    CONTAINER ID        IMAGE                     COMMAND                CREATED             STATUS              PORTS                     NAMES
    ade95dac9e4e        dnvriend/spray-markdown:latest   /bin/sh -c java -jar   5 minutes ago       Up 5 minutes        0.0.0.0:49154->8080/tcp   sick_darwin

In this example, the local port of my Vagrant VM has been mapped to port 49154 to the port, and that is 8080. 
Point the browser to the following url (change the port to your mapped port):

    http://192.168.99.99:49154/doc/index.md

## Creating the image
Inside Vagrant navigate to
 
    $ cd /spray-markdown

Then type

	$ sudo docker build --rm -t dnvriend/spray-markdown .

## Pusing the image to [docker hub](https://hub.docker.com/)
This is just an example:
	
	$ sudo docker push dnvriend/spray-markdown

# Creating one jar
For distribution of our Spray applications, we can use the one-jar plugin, just type:

    $ ./activator 'one-jar'
    
or

    $ ./create-one-jar.sh
    
The resulting jar will be placed in:
     
    $ target/scala-2.10
    
# Spray MarkdownService inspiration
This markdown service has been inspired by the MarkdownService from a deprecated example I found on the Internet.

# Implementation
The MarkdownService has been implemented as a trait. The service registers two
paths /css and /doc. The route can be used in the Main object, just mix in the trait.

# Usage
Just use your favorite browser and nagivate to:

    http://localhost:8080/doc/index.md

# Httpie
Alternatively, you can install [httpie](https://github.com/jakubroztocil/httpie),
a great tool for testing your REST services, like spray! 
    
    $ http GET http://localhost:8080/doc/index.md
    
    $ http GET http://localhost:8080/css/markdown.css
    
Have fun!