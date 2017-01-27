package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;

public class ExNestedTreeProvider<T> implements ITreeProvider<T>{

    private static final long serialVersionUID = 1L;

    public ExNestedTreeProvider(){
        // TODO 自動生成されたコンストラクター・スタブ
    }

    @Override
    public void detach(){
        // TODO 自動生成されたメソッド・スタブ
        
    }

    @Override
    public Iterator<? extends T> getChildren(T arg0){
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public Iterator<? extends T> getRoots(){
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public boolean hasChildren(T arg0){
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public IModel<T> model(T arg0){
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }



}
