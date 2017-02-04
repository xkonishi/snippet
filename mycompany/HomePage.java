package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
    private ExDefaultNestedTree tree;  
    TextField<String> text;
    Html5TextField<String> text2;
    Datalist<String> mylist;

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
   		
		
		Form<Object> submitForm = new Form<Object>("submitForm");
		add(submitForm);

        this.tree.getProvider().setNodeList(DummyList.getList());
   		this.tree.expandAll();
   		
   		
   
   		this.text = new TextField<String>("sampletext", new Model<String>());
   		this.text.setOutputMarkupId(true);
   		submitForm.add(text);
   		
   		//-------------------------------------------------------------------------------------------
   		this.text2 = new Html5TextField<String>("sampletext2", new Model<String>());
   		submitForm.add(text2);
   		
   		List<String> ll = new ArrayList<String>();
   		ll.add("XX");
   		ll.add("YY");
   		ll.add("ZZ");
   		this.mylist = new Datalist<String>("mylist", new Model<String>(), ll, new MyChiceRenderer<String>());
   		submitForm.add(mylist);
   		//-------------------------------------------------------------------------------------------
   		
   		Button button = new Button("mysubmit", new Model<String>("MMM")) {
			private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
            	Html5TextField<String> t = HomePage.this.text2;
            	String s = t.getConvertedInput();
            }   			
   		};
   		submitForm.add(button);

	}
	
	private void setPrinterSetting(ExDefaultNode node, AjaxRequestTarget targetOptional) throws LogicalException{
		targetOptional.add(this.text);
		this.text.setModelObject(node.getId());
	}
}
