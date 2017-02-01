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

    public ExDefaultNode getDefaultNode(int id, String label)
    {
        return findDefaultNode(this.nodeList, id, label);
    }

    private ExDefaultNode findDefaultNode(List<ExDefaultNode> nodes, int id, String label) {
        for(ExDefaultNode n : nodes) {
            if (n.getId() == id && n.getLabel().equals(label)) {
                return n;
            }

            ExDefaultNode temp = findDefaultNode(n.getNodes(), id, label);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }
    
    protected void onClick(ExDefaultNode node, AjaxRequestTarget targetOptional) {
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
                ExDefaultProvider.this.onClick(getModelObject(), targetOptional);
            }

            @Override
            protected boolean isSelected() {
                return ExDefaultProvider.this.isSelected(getModelObject());
            }
        };
    }

    private class DefaultNodeModel extends LoadableDetachableModel<ExDefaultNode> {
        private static final long serialVersionUID = 1L;

        private final int id;
        private final String label;

        public DefaultNodeModel(ExDefaultNode node) {
            super(node);

            this.id = node.getId();
            this.label = node.getLabel();
        }

        @Override
        protected ExDefaultNode load() {
            return ExDefaultProvider.this.getDefaultNode(this.id, this.label);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultNodeModel) {
                DefaultNodeModel m = ((DefaultNodeModel)obj);
                return (m.id == this.id && m.label == this.label);
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
