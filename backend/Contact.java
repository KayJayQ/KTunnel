package backend;

import java.util.Queue;

public class Contact {

    public enum Status {
        OFFLINE,
        ONLINE
    }

    public String alias;
    public UniqueID id;
    public String ip;
    public Status status;
    public Queue<Message> msgQueue;
    
    public Contact(UniqueID id) {
        this.id = id;
    } 

    public void MessageEnqueue(Message msg) {
        msgQueue.add(msg);
    }

}
