package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import java.util.ArrayList;
import java.util.List;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree.DefaultNode;

public final class DummyList extends Object{
    
    private static List<DefaultNode> DefaultNodes = new ArrayList<>();
    
    private DummyList() {
    }
    
    public static List<DefaultNode> getList() {
        if (DefaultNodes.size() == 0) {
            //
            DefaultNode DefaultNode1 = new DefaultNode("ADMIN");
            {
                new DefaultNode(DefaultNode1, "10.70.185.24");
                new DefaultNode(DefaultNode1, "127.0.0.1");
            }
            DefaultNodes.add(DefaultNode1);
            //
            DefaultNode DefaultNode2 = new DefaultNode("JAVA1");
            {
                new DefaultNode(DefaultNode2, "10.70.185.25");
            }
            DefaultNodes.add(DefaultNode2);
        }
        return DefaultNodes;
    }

    public static DefaultNode getDefaultNode(String id)
    {
        return findDefaultNode(DefaultNodes, id);
    }

    private static DefaultNode findDefaultNode(List<DefaultNode> DefaultNodes, String id)
    {
        for (DefaultNode DefaultNode : DefaultNodes)
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
