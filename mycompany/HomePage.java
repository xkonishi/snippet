package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
    private ExDefaultNestedTree tree;
//    private DefaultProvider provider;
//    private DefaultContent content;
    
    TextField<String> text;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		
//        provider = new DefaultProvider(DefaultNodeList.getList()){
//            private static final long serialVersionUID = 1L;
//            
//            @Override
//            public void onClick(DefaultNode node) {
//                String id = node.getId();
//            }
//        };
//        //content = new DefaultContent(provider);
//        tree = new ExDefaultNestedTree("tree", provider){
//            private static final long serialVersionUID = 1L;
//            
////            @Override
////            public void onClick(DefaultNode node) {
////                String id = node.getId();
////            }
//        };
        tree = new ExDefaultNestedTree("tree", new DefaultProvider(DefaultNodeList.getList()){
            private static final long serialVersionUID = 1L;
            
            @Override
            public void onClick(DefaultNode node, AjaxRequestTarget targetOptional) {
                String id = node.getId();
                text.setModelObject(id);
                targetOptional.add(text);
            }
        });
        tree.expandAll();
   		this.add(tree);
   		
   		text = new TextField<String>("sampletext", new Model<String>("BBBBBBB"));
   		text.setOutputMarkupId(true);
   		this.add(text);
   		
   		text.setModelObject("KKKKKKK");
	}
}
