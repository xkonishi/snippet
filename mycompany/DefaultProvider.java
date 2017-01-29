package com.mycompany;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class DefaultProvider<T> implements ITreeProvider<T> {
	private static final long serialVersionUID = 1L;
	
	List<T> list;

	public DefaultProvider(List<T> list) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.list = list;
	}

	@Override
	public void detach() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public Iterator<? extends T> getChildren(T arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Iterator<? extends T> getRoots() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean hasChildren(T arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public IModel<T> model(T arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

//	@Override
//	public Iterator<T> getRoots()
//	{
//	    return this.list.iterator();
//	}
//
//	@Override
//	public boolean hasChildren(T foo)
//	{
//		return foo.getParent() == null || !foo.getFoos().isEmpty();
//	}
//
//	@Override
//	public Iterator<T> getChildren(final T foo)
//	{
//		return foo.getFoos().iterator();
//	}
//
//	/**
//	 * Creates a {@link FooModel}.
//	 */
//	@Override
//	public IModel<T> model(T foo)
//	{
//		return new FooModel(foo);
//	}
//
//	/**
//	 * A {@link Model} which uses an id to load its {@link Foo}.
//	 * 
//	 * If {@link Foo}s were {@link Serializable} you could just use a standard {@link Model}.
//	 * 
//	 * @see #equals(Object)
//	 * @see #hashCode()
//	 */
//	private static class FooModel<T> extends LoadableDetachableModel<T>
//	{
//		private static final long serialVersionUID = 1L;
//
//		private final String id;
//
//		public FooModel(T foo)
//		{
//			super(foo);
//
//			id = foo.getId();
//		}
//
//		@Override
//		protected Foo load()
//		{
////			return TreeApplication.get().getFoo(id);
//	        //---------------------------------------------
//		    return FooList.getFoo(id);
//	        //---------------------------------------------
//		}
//
//		/**
//		 * Important! Models must be identifyable by their contained object.
//		 */
//		@Override
//		public boolean equals(Object obj)
//		{
//			if (obj instanceof FooModel)
//			{
//				return ((FooModel)obj).id.equals(id);
//			}
//			return false;
//		}
//
//		/**
//		 * Important! Models must be identifyable by their contained object.
//		 */
//		@Override
//		public int hashCode()
//		{
//			return id.hashCode();
//		}
//	}
}
