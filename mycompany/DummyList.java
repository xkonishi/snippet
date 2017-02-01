package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public final class DummyList extends Object{
    
    private static List<ExDefaultNode> DefaultNodes = new ArrayList<>();
    
    private DummyList() {
    }
    
    public static List<ExDefaultNode> getList() {
        if (DefaultNodes.size() == 0) {
            //
            ExDefaultNode DefaultNode1 = new ExDefaultNode("ADMIN");
            {
            	DefaultNode1.add(new ExDefaultNode("10.70.185.24"));
            	DefaultNode1.add(new ExDefaultNode("127.0.0.1"));
            }
            DefaultNodes.add(DefaultNode1);
            //
            ExDefaultNode DefaultNode2 = new ExDefaultNode("JAVA1");
            {
            	DefaultNode2.add(new ExDefaultNode("10.70.185.25"));
            }
            DefaultNodes.add(DefaultNode2);
        }
        return DefaultNodes;
    }

    public static ExDefaultNode getDefaultNode(String id)
    {
        return findDefaultNode(DefaultNodes, id);
    }

    private static ExDefaultNode findDefaultNode(List<ExDefaultNode> DefaultNodes, String id)
    {
        for (ExDefaultNode DefaultNode : DefaultNodes)
        {
            if (DefaultNode.getLabel().equals(id))
            {
                return DefaultNode;
            }

            ExDefaultNode temp = findDefaultNode(DefaultNode.getChildNodes(), id);
            if (temp != null)
            {
                return temp;
            }
        }

        return null;
    }
}
