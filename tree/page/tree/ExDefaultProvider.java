package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class ExDefaultProvider implements ITreeProvider<ExDefaultNode> {
    private static final long serialVersionUID = 1L;
    
    private List<ExDefaultNode> nodeList;
    
    private IModel<ExDefaultNode> selected;

    public ExDefaultProvider() {
        this(new ArrayList<>());
    }

    public ExDefaultProvider(List<ExDefaultNode> nodeList) {
        this.setNodeList(nodeList);
    }

    @Override
    public void detach() {
    }

    @Override
    public Iterator<ExDefaultNode> getRoots() {
        return this.nodeList.iterator();
    }

    @Override
    public boolean hasChildren(ExDefaultNode node) {
        return node.getParent() == null || !node.getNodes().isEmpty();
    }

    @Override
    public Iterator<ExDefaultNode> getChildren(ExDefaultNode node) {
        return node.getNodes().iterator();
    }

    @Override
    public IModel<ExDefaultNode> model(ExDefaultNode node) {
        return new DefaultNodeModel(node);
    }

    public void setNodeList(List<ExDefaultNode> nodeList) {
        this.nodeList = nodeList;
    }

    public ExDefaultNode getDefaultNode(String label)
    {
        return findDefaultNode(this.nodeList, label);
    }

    private ExDefaultNode findDefaultNode(List<ExDefaultNode> nodes, String id) {
        for (ExDefaultNode DefaultNode : nodes) {
            if (DefaultNode.getLabel().equals(id)) {
                return DefaultNode;
            }

            ExDefaultNode temp = findDefaultNode(DefaultNode.getNodes(), id);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }
    
    protected void onClick(ExDefaultNode node) {
    }

    public Component newContentComponent(String id, final ExDefaultNestedTree tree, IModel<ExDefaultNode> model) {
        return new Folder<ExDefaultNode>(id, tree, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean isClickable() {
                return true;
            }

            @Override
            protected void onClick(AjaxRequestTarget targetOptional) {
                ExDefaultProvider.this.select(getModelObject(), tree, targetOptional);
                ExDefaultProvider.this.onClick(getModelObject());
            }

            @Override
            protected boolean isSelected() {
                return ExDefaultProvider.this.isSelected(getModelObject());
            }
        };
    }

    private class DefaultNodeModel extends LoadableDetachableModel<ExDefaultNode> {
        private static final long serialVersionUID = 1L;

        private final String label;
        private final String fulllabel;

        public DefaultNodeModel(ExDefaultNode node) {
            super(node);

            label = node.getLabel();
            fulllabel = node.getFullLabel();
        }

        @Override
        protected ExDefaultNode load() {
            return ExDefaultProvider.this.getDefaultNode(label);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultNodeModel)
            {
                return ((DefaultNodeModel)obj).fulllabel.equals(fulllabel);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return label.hashCode();
        }
    }

    protected boolean isSelected(ExDefaultNode node) {
        IModel<ExDefaultNode> model = this.model(node);

        try {
            return selected != null && ((DefaultNodeModel)selected).equals(model);
        }
        finally {
            model.detach();
        }
    }

    protected void select(ExDefaultNode node, AbstractTree<ExDefaultNode> tree, final AjaxRequestTarget targetOptional) {
        if (selected != null) {
            tree.updateNode(selected.getObject(), targetOptional);

            selected.detach();
            selected = null;
        }

        selected = this.model(node);

        tree.updateNode(node, targetOptional);
    }
}
