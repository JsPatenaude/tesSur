package file;

import transportObject.TransportObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Automate {
    private String name_;
    private HashSet<TransportObject> objects_;
    private boolean terminal_;
    private HashSet<Automate> childrenSet_;

    public Automate(String name, HashSet<Automate> childrenSet, boolean terminal)
    {
        name_ = name;
        childrenSet_ = childrenSet;
        objects_ = new HashSet<>();
        terminal_ = terminal;
    }

    public String getName(){ return name_; }

    public void addChild(Automate child){ childrenSet_.add(child); }

    public HashSet<TransportObject> getObjects(){
        return objects_;
    }

    public void addObjects(TransportObject object) { objects_.add(object); }

    public void setTerminal(boolean terminal) {
        terminal_ = terminal;
    }

    private HashSet<String> getAllChildrenName(Automate node)
    {
        HashSet<String> childrenName = new HashSet<>();
        if (node.childrenSet_.isEmpty())
            return childrenName;
        for (Automate child : node.childrenSet_)
        {
            childrenName.add(child.getName());
            if (child.childrenSet_ != null)
                childrenName.addAll(getAllChildrenName(child));
        }
        return childrenName;
    }

    public HashSet<String> getAllChildrenName() { return getAllChildrenName(this); }

    public Automate getNodeByName(Automate node, String name)
    {
        if (node.getName().equals(name))
            return node;
        for (Automate child : node.childrenSet_)
            if(child.getName().substring(0, Math.min(child.getName().length(), name.length())).equals(name.substring(0, Math.min(child.getName().length(), name.length()))) )
                if (!child.childrenSet_.isEmpty() || child.terminal_)
                    return getNodeByName(child, name);
        return null;
    }



    public Automate getNodeByName(String name) { return getNodeByName(this, name); }
}
