
# Chatty: a spring-boot chat project 

## Project Description

Chatty is a chat application built using Spring Boot and Websocket. The application allows multiple users to chat simultaneously while keeping all the messages in a message broker. There is no upper limit on how many users can be in the chat room, but it is recommended that at least 2 users be present at any given time. Any user can log in and log out of the chat room at any time. The project can further be extended by adding additional features like a message timer restricting how much time a user should wait before sending their next message, emojis, storing messages based on sessions, etc. This was a simple but effective implementation to understand how cheating applications work in real-time. We have used Java, javascript, jQuery, bootstrap, sockjs, WebSockets, and stomp protocol. 

![Image Alt](https://github.com/sauravnrai/Chatty/blob/master/login%20page.png?raw=true)
![Image Alt](https://github.com/sauravnrai/Chatty/blob/master/Chat%20room.png?raw=true)


## Why WebSockets?

Unlike HTTP, web sockets are full-duplex, bidirectional protocols that don't get disconnected after making a single request and response. The connections only get disconnected when either the server or client closes the connection from their side. Web sockets are more suitable for chatting applications since the client waits, pushes messages, and needs a connection to read all the messages. 




## Project setup

We will use Visual Studio Code with VMware's spring-boot extension pack and Microsoft's Java extension pack. 

In Visual Studio code, 

Open view -> command palette -> spring initializer (Create a maven project)

Use the latest version of Spring Boot, java as a programming language, and then set the group ID (com.chatty in my case) and then artifact ID (springboot_chatapp)

Select packaging as a jar with the Java version

Use WebSocket using messaging dependency and include spring web dependency

After all the above settings, save the project in a folder (chatty in my case)


### Resolving Dependency  

Important dependencies like spring boot web sockets and spring boot web are installed while setting up the project. However, some important dependencies for the front end still need to be added. We need to add the following dependencies: web-jar locator core, socket js client (sockJS-client), stomp web socket, bootstrap, and jQuery

Stomp will provide us with methods to send, connect, create endpoints, etc. We will add these dependencies in our pom.xml file from the Maven repository. 



## Server-side and Client-side Information

### Server-side work

We want an entity (a message broker) to hold the messages. We will create a new folder named "model," within which a class Message will keep the user's name and message. Using the object of the message class, we will store the messages

Now, we will create a controller to handle the data from the model, the MessageController class. We will store the data in JSON format using the RestController. The MessageController class will accept messages from the message model and return them. We will map two paths via annotations: SendTo and MessageMapping. MessageMapping will be used to send messages, and SendTo will be used to view messages. 

We need additional configurations to configure the web socket message broker and will make a class named config. We will implement the "web socket message broker configure" interface. We need configuration to make endpoints for connecting to the server and to set prefixes for paths for sending and receiving.

/topic is where the message broker gets enabled and /topic/return-to will be used to broadcast messages
/server1 is where the server receives configured for js clients

The following important URLs are  

To connect with the server: /server1

To send a message: /chatty/message

To receive or subscribe messages: /chatty/topic/return-to


Tip: Take care of package paths. Both controllers and models are supposed to be in the com.chatty folder



### Client-side work

We will use a static folder within the resources to create HTML, CSS, and Javascript files. We will configure CSS and JavaScript in our HTML file and add or configure all the relevant web-jar files in the HTML file. 

In the index.html file, we have made a page where users can enter their names and hit the enter button. Upon hitting the enter button, the data is handled at script.js 

When the user hits enter, we must accept the data by connecting and bringing it to the next page. We will show the messages and move around the paths defined earlier. 








