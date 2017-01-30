package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public final class DefaultNodeList extends Object{
    
    private static List<DefaultNode> nodes = new ArrayList<>();
    
    private DefaultNodeList() {
    }
    
    public static List<DefaultNode> getList() {
        if (nodes.size() == 0) {
        	//
            DefaultNode DefaultNode1 = new DefaultNode("ADMIN");
            {
                new DefaultNode(DefaultNode1, "10.127.0.0");
                new DefaultNode(DefaultNode1, "10.127.0.1");
            }
            nodes.add(DefaultNode1);
        	//
            DefaultNode DefaultNode2 = new DefaultNode("JAVA1");
            {
                new DefaultNode(DefaultNode2, "11.127.0.0");
            }
            nodes.add(DefaultNode2);
        }
        return nodes;
    }

    public static DefaultNode getDefaultNode(String id)
    {
        return findDefaultNode(nodes, id);
    }

    private static DefaultNode findDefaultNode(List<DefaultNode> nodes, String id)
    {
        for (DefaultNode DefaultNode : nodes)
        {
            if (DefaultNode.getId().equals(id))
            {
                return DefaultNode;
            }

            DefaultNode temp = findDefaultNode(DefaultNode.getNodes(), id);
            if (temp != null)
            {
                return temp;
            }
        }

        return null;
    }
}
