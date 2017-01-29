package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
    private class FooExpansionModel extends AbstractReadOnlyModel<Set<Foo>>{
        private static final long serialVersionUID = 1L;

        @Override
	    public Set<Foo> getObject(){
	        return FooExpansion.get();
	    }
    }
	
//	NestedTree<Foo> tree;
    ExDefaultNestedTree<Foo> tree;
	SelectableFolderContent content;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		
		Foo f = new Foo("");
		Field[] fds = f.getClass().getDeclaredFields();

		// TODO Add your page's components here
		
		FooProvider provider = new FooProvider();
		
		content = new SelectableFolderContent(provider){
			private static final long serialVersionUID = 1L;
			
		};

		//_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
//		tree = new ExDefaultNestedTree<Foo>("tree", provider, new FooExpansionModel()){
		tree = new ExDefaultNestedTree<Foo>("tree", provider){
			private static final long serialVersionUID = 1L;
			
//			@Override
//			protected Component newContentComponent(String _id, IModel<Foo> _model) {
//				return content.newContentComponent(_id, tree, _model);
////				return HomePage.this.newContentComponent(_id, _model);
//			}
		};
		tree.expandAll();
		
		//this.expandAll();
		
		//_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
//        tree = new NestedTree<Foo>("tree", provider, new FooExpansionModel()){
////		tree = new NestedTree<Foo>("tree", provider){
//		      private static final long serialVersionUID = 1L;
//
//		      @Override
//		      protected Component newContentComponent(String _id, IModel<Foo> _model){
//		    	  return HomePage.this.newContentComponent(_id, _model);
//		      }
//		   };	
		//FooExpansion.get().expandAll();
   		this.add(tree);
	}

	protected Component newContentComponent(String id, IModel<Foo> model){
		return content.newContentComponent(id, tree, model);
     }
	
//	private void expandAll() {
//		Iterator<Foo> it = (Iterator<Foo>) this.tree.getProvider().getRoots();
//		while(it.hasNext()) {
//			tree.expand(it.next());
//		}
//	}
}
