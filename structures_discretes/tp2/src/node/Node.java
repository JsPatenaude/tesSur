package node;

import transportObject.TransportObject;

import java.util.HashSet;

public class Node {
    private TransportObject object_;
    private boolean terminal_;
    private HashSet<Node> childrenSet_;

    public Node(TransportObject object, boolean terminal, HashSet<Node> childrenSet){
        object_ = object;
        terminal_ = terminal;
        childrenSet_ = childrenSet;
    }

    public TransportObject getObject(){
        return object_;
    }

    public void setObject_(TransportObject object_) {
        this.object_ = object_;
    }

    public boolean isTerminal_(){
        return object_ != null;
    }
    public void setTerminal_(boolean terminal_) {
        this.terminal_ = terminal_;
    }

    public HashSet<Node> getChildrenSet_(){
        return childrenSet_;
    }

    public void setChildrenSet_(HashSet<Node> childrenSet_) {
        this.childrenSet_ = childrenSet_;
    }






}
