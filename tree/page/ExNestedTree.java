package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class ExNestedTree<T> extends NestedTree<T>{

    private static final long serialVersionUID = 1L;

    public ExNestedTree(String id, ITreeProvider<T> provider){
        super(id, provider);
        // TODO 自動生成されたコンストラクター・スタブ
    }

//    public ExNestedTree(String id){
//        super(id, new DefaultProvider(), new DefaultExpansionModel());
//        // TODO 自動生成されたコンストラクター・スタブ
//    }

//    public ExNestedTree(String id, ITreeProvider<T> provider){
//        this(id, provider, new DefaultExpansionModel());
//        //super(id, provider, new DefaultExpansionModel());
//        // TODO 自動生成されたコンストラクター・スタブ
//    }

//    public ExNestedTree(String id, ITreeProvider<T> provider, IModel<? extends Set<T>> state){
//        super(id, provider, state);
//        // TODO 自動生成されたコンストラクター・スタブ
//    }
 
    @Override
    protected Component newContentComponent(String id, IModel<T> model){
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public class DefaultProvider implements ITreeProvider<T> {

        private static final long serialVersionUID = 1L;

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

    private class DefaultExpansionModel extends AbstractReadOnlyModel<Set<T>>{

        private static final long serialVersionUID = 1L;

        @Override
        public Set<T> getObject(){
            // TODO 自動生成されたメソッド・スタブ
            return null;
        }

//       @Override
//       public Set<T> getObject(){
//          return DefaultExpansionModel.get();
//       }
    }

//    public class DefaultExpansion implements Set<T>, Serializable
//    {
//        private static final long serialVersionUID = 1L;
//
//        private static MetaDataKey<DefaultExpansion> KEY = new MetaDataKey<DefaultExpansion>()
//        {
//            private static final long serialVersionUID = 1L;
//        };
//
//        private Set<String> ids = new HashSet<>();
//
//        private boolean inverse;
//
//        public void expandAll()
//        {
//            ids.clear();
//
//            inverse = true;
//        }
//
//        public void collapseAll()
//        {
//            ids.clear();
//
//            inverse = false;
//        }
//
//        @Override
//        public boolean add(T foo)
//        {
//            if (inverse)
//            {
//                return ids.remove(foo.getId());
//            }
//            else
//            {
//                return ids.add(foo.getId());
//            }
//        }
//
//        @Override
//        public boolean remove(Object o)
//        {
//            Foo foo = (Foo)o;
//
//            if (inverse)
//            {
//                return ids.add(foo.getId());
//            }
//            else
//            {
//                return ids.remove(foo.getId());
//            }
//        }
//
//        @Override
//        public boolean contains(Object o)
//        {
//            Foo foo = (Foo)o;
//
//            if (inverse)
//            {
//                return !ids.contains(foo.getId());
//            }
//            else
//            {
//                return ids.contains(foo.getId());
//            }
//        }
//
//        @Override
//        public void clear()
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public int size()
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean isEmpty()
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public <A> A[] toArray(A[] a)
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public Iterator<Foo> iterator()
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public Object[] toArray()
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean containsAll(Collection<?> c)
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean addAll(Collection<? extends T> c)
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean retainAll(Collection<?> c)
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean removeAll(Collection<?> c)
//        {
//            throw new UnsupportedOperationException();
//        }
//
//        /**
//         * Get the expansion for the session.
//         * 
//         * @return expansion
//         */
//        public static DefaultExpansion get()
//        {
//            DefaultExpansion expansion = Session.get().getMetaData(KEY);
//            if (expansion == null)
//            {
//                expansion = new DefaultExpansion();
//
//                Session.get().setMetaData(KEY, expansion);
//            }
//            return expansion;
//        }
//    }
}
