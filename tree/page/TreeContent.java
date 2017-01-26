package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
/**
 * TreeContent
 */
public abstract class TreeContent<T> implements IDetachable{

   /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public abstract Component newContentComponent(String id, AbstractTree<T> tree, IModel<T> model);

   @Override
   public void detach(){
   }
}