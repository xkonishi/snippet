package com.mycompany;

import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;

public class ExDefaultNestedTree extends DefaultNestedTree<DefaultNode> {
	private static final long serialVersionUID = 1L;
	
	private DefaultProvider provider;
	public DefaultContent content;

	public ExDefaultNestedTree(String id, DefaultProvider provider) {
		super(id, provider);
		// TODO 自動生成されたコンストラクター・スタブ
		this.provider = provider;
		this.content = new DefaultContent(provider);
	}

//	public ExDefaultNestedTree(String id, DefaultProvider provider, IModel<? extends Set<T>> state) {
//		super(id, provider, state);
//		// TODO 自動生成されたコンストラクター・スタブ
//		this.provider = provider;
////		this.content = new DefaultContent<T>(provider);
//	}
	
	@Override
	protected Component newContentComponent(String id, IModel<DefaultNode> node) {
//		return this.content.newContentComponent(id, this, node);
		return this.provider.newContentComponent(id, this, node);
	}
    
    public void onClick(DefaultNode node, AjaxRequestTarget targetOptional) {
        
    }

	public void expandAll() {
		Iterator<DefaultNode> it = this.provider.getRoots();
		while(it.hasNext()) {
		    DefaultNode n = it.next();
			this.expand(n);
			if (this.provider.hasChildren(n)) {
				this.expandChild(n);
			}
		}
	}
	
	private void expandChild(DefaultNode node) {
		Iterator<DefaultNode> it = this.provider.getChildren(node);
		while(it.hasNext()) {
		    DefaultNode n = it.next();
			this.expand(n);
			if (this.provider.hasChildren(n)) {
				this.expandChild(n);
			}
		}
	}
}
