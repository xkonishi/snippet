package com.mycompany;
 
 
import java.util.List; 
 
import org.apache.wicket.markup.ComponentTag; 
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice; 
import org.apache.wicket.markup.html.form.IChoiceRenderer; 
import org.apache.wicket.markup.html.form.IOnChangeListener; 
import org.apache.wicket.model.IModel; 
 
/**
 * A choice implemented as a dropdown menu/list. 
 * <p> 
 * Java: 
 *  
 * <pre> 
 * List SITES = Arrays.asList(new String[] { "The Server Side", "Java Lobby", "Java.Net" }); 
 *  
 * // Add a dropdown choice component that uses Input's 'site' property to designate the 
 * // current selection, and that uses the SITES list for the available options. 
 * // Note that when the selection is null, Wicket will lookup a localized string to 
 * // represent this null with key: "id + '.null'". In this case, this is 'site.null' 
 * // which can be found in DropDownChoicePage.properties 
 * form.add(new DropDownChoice("site", SITES)); 
 * </pre> 
 *  
 * HTML: 
 *  
 * <pre> 
 *    <select wicket:id="site"> 
 *     <option>site 1</option> 
 *     <option>site 2</option> 
 *    </select> 
 * </pre> 
 *  
 * </p> 
 *  
 * <p> 
 * You can can extend this class and override method wantOnSelectionChangedNotifications() to force 
 * server roundtrips on each selection change. 
 * </p> 
 *  
 * @author Jonathan Locke 
 * @author Eelco Hillenius 
 * @author Johan Compagner 
 *  
 * @param <T> 
 *            The model object type 
 */ 
public class Datalist<T> extends AbstractSingleSelectChoice<T> implements IOnChangeListener 
{ 
 private static final long serialVersionUID = 1L; 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String) 
  */ 
 public Datalist(final String id) 
 { 
  super(id); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, List) 
  */ 
 public Datalist(final String id, final List<? extends T> choices) 
 { 
  super(id, choices); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, 
  *      List,IChoiceRenderer) 
  */ 
 public Datalist(final String id, final List<? extends T> data, 
  final IChoiceRenderer<? super T> renderer) 
 { 
  super(id, data, renderer); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, List) 
  */ 
 public Datalist(final String id, IModel<T> model, final List<? extends T> choices) 
 { 
  super(id, model, choices); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, List, 
  *      IChoiceRenderer) 
  */ 
 public Datalist(final String id, IModel<T> model, final List<? extends T> data, 
  final IChoiceRenderer<? super T> renderer) 
 { 
  super(id, model, data, renderer); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel) 
  */ 
 public Datalist(String id, IModel<? extends List<? extends T>> choices) 
 { 
  super(id, choices); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,IModel) 
  */ 
 public Datalist(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) 
 { 
  super(id, model, choices); 
 } 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, 
  *      IModel,IChoiceRenderer) 
  */ 
 public Datalist(String id, IModel<? extends List<? extends T>> choices, 
  IChoiceRenderer<? super T> renderer) 
 { 
  super(id, choices, renderer); 
 } 
 
 
 /**
  * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, 
  *      IModel,IChoiceRenderer) 
  */ 
 public Datalist(String id, IModel<T> model, IModel<? extends List<? extends T>> choices, 
  IChoiceRenderer<? super T> renderer) 
 { 
  super(id, model, choices, renderer); 
 } 
 
 /* (non-Javadoc)
  * @see org.apache.wicket.Component#onInitialize() 
  */ 
 @Override 
 protected void onInitialize() { 
  super.onInitialize(); 
   
  setOutputMarkupId(true); 
 } 
 
 /**
  * Processes the component tag. 
  *  
  * @param tag 
  *            Tag to modify 
  * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag) 
  */ 
 @Override 
 protected void onComponentTag(final ComponentTag tag) 
 { 
  checkComponentTag(tag, "datalist"); 
  super.onComponentTag(tag); 
 } 
 
 public void onSelectionChanged() {   
 } 
 
}