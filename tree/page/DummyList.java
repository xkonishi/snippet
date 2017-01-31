package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import java.util.ArrayList;
import java.util.List;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree.ExDefaultNode;

public final class DummyList extends Object{
    
    private static List<ExDefaultNode> DefaultNodes = new ArrayList<>();
    
    private DummyList() {
    }
    
    public static List<ExDefaultNode> getList() {
        if (DefaultNodes.size() == 0) {
            //
            ExDefaultNode DefaultNode1 = new ExDefaultNode("ADMIN");
            {
                new ExDefaultNode(DefaultNode1, "10.70.185.24");
                new ExDefaultNode(DefaultNode1, "127.0.0.1");
            }
            DefaultNodes.add(DefaultNode1);
            //
            ExDefaultNode DefaultNode2 = new ExDefaultNode("JAVA1");
            {
                new ExDefaultNode(DefaultNode2, "10.70.185.25");
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

            ExDefaultNode temp = findDefaultNode(DefaultNode.getNodes(), id);
            if (temp != null)
            {
                return temp;
            }
        }

        return null;
    }
}
