# spray-markdown
We will create a Markdown service that will read .md files from the classpath and loads an associated .css file to
style the generated HTML. Markdown is handy when documenting our REST APIs, and its much easier than writing HTML!

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
    
    http GET http://localhost:8080/doc/index.md
    
    http GET http://localhost:8080/css/markdown.css
    
Have fun!