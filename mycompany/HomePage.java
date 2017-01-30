package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
    private ExDefaultNestedTree tree;
    private DefaultProvider provider;
    private DefaultContent content;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		
        provider = new DefaultProvider(DefaultNodeList.getList()){
            private static final long serialVersionUID = 1L;
            
            @Override
            public void onClick(DefaultNode node) {
                String id = node.getId();
            }
        };
        //content = new DefaultContent(provider);
        tree = new ExDefaultNestedTree("tree", provider){
            private static final long serialVersionUID = 1L;
            
//            @Override
//            public void onClick(DefaultNode node) {
//                String id = node.getId();
//            }
        };
        tree.expandAll();
   		this.add(tree);
	}
}
