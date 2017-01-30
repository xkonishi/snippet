package com.mycompany;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class DefaultProvider implements ITreeProvider<DefaultNode> {
	private static final long serialVersionUID = 1L;
	
	List<DefaultNode> nodeList;
	
	private IModel<DefaultNode> selected;

	public DefaultProvider(List<DefaultNode> nodeList) {
		this.nodeList = nodeList;
	}

	@Override
	public void detach() {
	}

    @Override
    public Iterator<DefaultNode> getRoots() {
        return this.nodeList.iterator();
    }

    @Override
    public boolean hasChildren(DefaultNode node) {
        return node.getParent() == null || !node.getNodes().isEmpty();
    }

	@Override
	public Iterator<DefaultNode> getChildren(DefaultNode node) {
	    return node.getNodes().iterator();
	}

	@Override
	public IModel<DefaultNode> model(DefaultNode node) {
	    return new DefaultNodeModel(node);
	}

    public DefaultNode getDefaultNode(String id)
    {
        return findDefaultNode(this.nodeList, id);
    }

    private DefaultNode findDefaultNode(List<DefaultNode> nodes, String id)
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

    private class DefaultNodeModel extends LoadableDetachableModel<DefaultNode>
    {
        private static final long serialVersionUID = 1L;

        private final String id;

        public DefaultNodeModel(DefaultNode node)
        {
            super(node);

            id = node.getId();
        }

        @Override
        protected DefaultNode load()
        {
            return DefaultProvider.this.getDefaultNode(id);
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof DefaultNodeModel)
            {
                return ((DefaultNodeModel)obj).id.equals(id);
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            return id.hashCode();
        }
    }

    
    public void onClick(DefaultNode node) {
        
    }

	protected boolean isSelected(DefaultNode node)
	{
		IModel<DefaultNode> model = this.model(node);

		try
		{
			return selected != null && selected.equals(model);
		}
		finally
		{
			model.detach();
		}
	}

    protected void select(DefaultNode node, AbstractTree<DefaultNode> tree, final AjaxRequestTarget targetOptional)
    {
        if (selected != null)
        {
            tree.updateNode(selected.getObject(), targetOptional);

            selected.detach();
            selected = null;
        }

        selected = this.model(node);

        tree.updateNode(node, targetOptional);
    }

	public Component newContentComponent(String id, final ExDefaultNestedTree tree, IModel<DefaultNode> model)
	{
		return new Folder<DefaultNode>(id, tree, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean isClickable()
			{
				return true;
			}

            @Override
            protected void onClick(AjaxRequestTarget targetOptional)
            {
            	DefaultProvider.this.select(getModelObject(), tree, targetOptional);
            	DefaultProvider.this.onClick(getModelObject());
            }

			@Override
			protected boolean isSelected()
			{
				return DefaultProvider.this.isSelected(getModelObject());
			}
		};
	}
}

