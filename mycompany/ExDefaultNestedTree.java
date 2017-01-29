package com.mycompany;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;

public class ExDefaultNestedTree<T> extends DefaultNestedTree<T> {
	private static final long serialVersionUID = 1L;
	
	DefaultContent<T> content;

	public ExDefaultNestedTree(String id, ITreeProvider<T> provider) {
		super(id, provider);
		// TODO 自動生成されたコンストラクター・スタブ
		content = new DefaultContent<T>(provider);
	}

	public ExDefaultNestedTree(String id, ITreeProvider<T> provider, IModel<? extends Set<T>> state) {
		super(id, provider, state);
		// TODO 自動生成されたコンストラクター・スタブ
		content = new DefaultContent<T>(provider);
	}
	
	@Override
	protected Component newContentComponent(String id, IModel<T> node) {
		return content.newContentComponent(id, this, node);
	}

}
