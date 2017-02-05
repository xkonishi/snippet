package com.mycompany;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class MyChiceRenderer<T> implements IChoiceRenderer<T> {
	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(T object) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getIdValue(T object, int index) {
		return object.toString();
	}

	@Override
	public T getObject(String id, IModel<? extends List<? extends T>> choices) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
