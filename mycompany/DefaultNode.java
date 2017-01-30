package com.mycompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultNode implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private boolean loaded;

    private DefaultNode parent;

    private List<DefaultNode> nodes = new ArrayList<>();

    public DefaultNode(String id){
        this.id = id;
    }

    public DefaultNode(DefaultNode parent, String name)
    {
        this(name);

        this.parent = parent;
        this.parent.nodes.add(this);
    }

    public DefaultNode getParent()
    {
        return parent;
    }

    public String getId()
    {
        return id;
    }

    public List<DefaultNode> getNodes()
    {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public String toString()
    {
        return id;
    }

//    public boolean isLoaded()
//    {
//        return loaded;
//    }
//
//    public void setLoaded(boolean loaded)
//    {
//        this.loaded = loaded;
//    }
}
