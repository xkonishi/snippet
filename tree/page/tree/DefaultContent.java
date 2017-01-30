package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

public class DefaultContent implements IDetachable {
	private static final long serialVersionUID = 1L;

	private ITreeProvider<DefaultNode> provider;

	private IModel<DefaultNode> selected;

	public DefaultContent(ITreeProvider<DefaultNode> provider)
	{
		this.provider = provider;
	}

	@Override
	public void detach() {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected boolean isSelected(DefaultNode node)
	{
		IModel<DefaultNode> model = provider.model(node);

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

        selected = provider.model(node);

        tree.updateNode(node, targetOptional);
    }
    
    public void onClick(DefaultNode node) {
        
    }

	public Component newContentComponent(String id, final AbstractTree<DefaultNode> tree, IModel<DefaultNode> model)
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
            	DefaultContent.this.select(getModelObject(), tree, targetOptional);
            	DefaultContent.this.onClick(getModelObject());
            }

			@Override
			protected boolean isSelected()
			{
				return DefaultContent.this.isSelected(getModelObject());
			}
		};
	}
}
