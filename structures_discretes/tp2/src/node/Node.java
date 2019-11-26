package node;

import transportObject.TransportObject;

import java.util.HashSet;

public class Node {
    private String name_;
    private HashSet<TransportObject> objects_;
    private boolean terminal_;
    private HashSet<Node> childrenSet_;

    public Node(String name, HashSet<Node> childrenSet, boolean terminal){
        name_ = name;
        childrenSet_ = childrenSet;
        objects_ = new HashSet<TransportObject>();
        terminal_ = terminal;
    }

    public String getName(){
        return name_;
    }
    public void setName(String name) {
        this.name_ = name;
    }

    public HashSet<Node> getChildrenSet(){ return childrenSet_; }
    public void setChildrenSet(HashSet<Node> childrenSet) {
        this.childrenSet_ = childrenSet;
    }
    public void addChild(Node child){ this.childrenSet_.add(child); }

    public HashSet<TransportObject> getObjects(){
        return objects_;
    }
    public void addObjects(TransportObject object) { objects_.add(object); }

    public boolean isTerminal(){
        return objects_ != null;
    }
    public void setTerminal(boolean terminal) {
        this.terminal_ = terminal;
    }


    public HashSet<String> getAllChildrenName(Node node, HashSet<String> childrenName){
        if (childrenName == null){
            childrenName = new HashSet<String>();
        }
        for (var child : node.childrenSet_) {
            childrenName.add(child.getName());
            if (child.childrenSet_ != null){
                getAllChildrenName(child, childrenName);
            }
        }
        return childrenName;
    }

    public Node getNodeByName(Node node, String name){
        if (node.getName().equals(name)){
            return node;
        }
        for (var child : node.childrenSet_) {
            if (child.childrenSet_ != null){
                return getNodeByName(child, name);
            }
        }
        return null;
    }


}
