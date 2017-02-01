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
 		
        this.tree = new ExDefaultNestedTree("tree", new ExDefaultProvider() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(ExDefaultNode node, AjaxRequestTarget targetOptional) {
                try {
                	HomePage.this.setPrinterSetting(node, targetOptional);
                    
                } catch (LogicalException ex) {

                }
            }
        });
   		this.add(tree);
   		
   		this.tree.getProvider().setNodeList(DummyList.getList());
   		this.tree.expandAll();
   		
   		
		
   		this.text = new TextField<String>("sampletext", new Model<String>());
   		this.text.setOutputMarkupId(true);
   		this.add(text);
	}
	
	private void setPrinterSetting(ExDefaultNode node, AjaxRequestTarget targetOptional) throws LogicalException{
		targetOptional.add(this.text);
		this.text.setModelObject(node.getId());
	}
}
