# KTunnel
A demo of encrypted p2p communication tool

## Features
1. P2P communication

    Pros:\
        Decenterlized connection between users, no server needed. \
        No phone number, ID card verfication needed. \
    Cons:\
        You have to share your IP address with ur friend face to face, or by other centerlized/verified communication tools.\
        There is no "offline message" so messages are only sent when both users are online.
2. Encryption

    Extreme privacy! (extreme only if users switch the key face to face)

## Tech stack & roadmap

1. Implement a demo on PC to verify the functionality of project, and improve the working flow of each feature. The demo is mostly based on Java, including a simple GUI(Java Swing), and network package(Java native websocket) and encrpytion package.

2. Adapt the PC demo to android platform. Most of backend code should be able to reuse.

3. Develop peripheral infra and ecosystem, if there are any users. 

## Requiements List
1. User can setup one's basic information. Such as alias, avatar, customized signature and a relatively unique ID to distinguish an identify user, with different IP address.
2. User can open a static port to listen all connect requests.
3. User can send a connection request to an IP address, and trying to get the basic information from static port. If info format is not correct, or the port is not responding, the connection fails. Otherwise, user will be client and the port listener will be server. Client will send its basic info at first connection, and server respond. Then, client will send a heartbeat pack to server each 1 sec, and server will respond. All msgs, images and connection infos will be transfered by this heartbeat datapack.
4. Users can distinguish the unique ID in the basic info, if it is known (talked to each other before), the interactions will be continued to an existing contacts/interaction, otherwise, it will be treated as a new contacts and start a new interaction to save messages.
5. A message contains
    
    header -> sender IP, basic info; reader IP, basic info; tempstamp\
    msg -> msg type (cmd, text, img etc.), msg length, content hash, msg content

6. When sending a message, a message data will be created and stored into buffer. And data will be transfered/read in next heartbeat datapack. Reader of message will send ack pack (everything of msg except msg content). If ack matchs the message, message will be removed from buffer, otherwise it keeps in there until next data transfer.

7. GUI can reorder displaying messages by timestamp to avoid msg resending.

8. If 10 consecutive datapack requests are failed, client/server closes the connection and marks offline in GUI. Messages remains in buffer will be dump into file.

9. User can manually close one/all connections, by sending a cmd msg.

10. User can broadcast connection requests to all exisiting contacts, to announce user is online. 

11. User can send a encrpytion method cmd msg to send request to one contact, and contact can accept or decline. If accepted, the encrpytion method and keys(switched face to face ideally) will be stored into both's contacts info. And following msgs content will be encrpyted/decrpyted using method and key.
