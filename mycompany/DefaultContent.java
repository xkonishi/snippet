package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

public class DefaultContent<T> implements IDetachable {
	private static final long serialVersionUID = 1L;

	private ITreeProvider<T> provider;

	private IModel<T> selected;

	public DefaultContent(ITreeProvider<T> provider)
	{
		this.provider = provider;
	}

	@Override
	public void detach() {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected boolean isSelected(T foo)
	{
		IModel<T> model = provider.model(foo);

		try
		{
			return selected != null && selected.equals(model);
		}
		finally
		{
			model.detach();
		}
	}

    protected void select(T foo, AbstractTree<T> tree, final AjaxRequestTarget targetOptional)
    {
        if (selected != null)
        {
            tree.updateNode(selected.getObject(), targetOptional);

            selected.detach();
            selected = null;
        }

        selected = provider.model(foo);

        tree.updateNode(foo, targetOptional);
    }

	public Component newContentComponent(String id, final AbstractTree<T> tree, IModel<T> model)
	{
		return new Folder<T>(id, tree, model)
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
            }

			@Override
			protected boolean isSelected()
			{
				return DefaultContent.this.isSelected(getModelObject());
			}
		};
	}
}
