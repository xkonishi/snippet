package com.mycompany;

import java.util.Iterator;
import java.util.function.Consumer;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.model.IModel;

public class ExDefaultNestedTree extends DefaultNestedTree<ExDefaultNode> {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>コンストラクタ</p>
     *
     * @param id ID
     * @param provider
     */
    public ExDefaultNestedTree(String id, ExDefaultProvider provider) {
        super(id, provider);
    }

    /**
     * <p>ノードのコンポーネント作成</p>
     *
     * @param id ID
     * @param model モデル
     * @return コンポーネント
     */
    @Override
    protected Component newContentComponent(String id, IModel<ExDefaultNode> node) {
        return ((ExDefaultProvider)this.getProvider()).newContentComponent(id, this, node);
    }

    /**
     * <p>プロバイダーの取得</p>
     *
     * @return プロバイダー
     */
    @Override
    public ExDefaultProvider getProvider() {
        return (ExDefaultProvider)super.getProvider();
    }

    /**
     * <p>ノードを全て展開</p>
     *
     */
    public void expandAll() {
        Iterator<ExDefaultNode> it = ((ExDefaultProvider)this.getProvider()).getRoots();
        while(it.hasNext()) {
            this.setRecursive(it.next(), true);
        }
    }

    /**
     * <p>ノードを全て縮小</p>
     *
     */
    public void collapseAll() {
        Iterator<ExDefaultNode> it = ((ExDefaultProvider)this.getProvider()).getRoots();
        while(it.hasNext()) {
            this.setRecursive(it.next(), false);
        }
    }

    /**
     * <p>ノードの展開／縮小（再帰呼び出し）</p>
     *
     * @param node ノード
     * @param expand true:展開／false:縮小
     */
  	private void setRecursive(ExDefaultNode node, boolean expand) {
        Consumer<ExDefaultNode> c = (p) -> {
            if (expand) {
                this.expand(p);
            }else{
                this.collapse(p);
            }
        };
        c.accept(node);

        Iterator<ExDefaultNode> it = ((ExDefaultProvider)this.getProvider()).getChildren(node);
        while(it.hasNext()) {
            ExDefaultNode n = it.next();
            c.accept(n);
            if (((ExDefaultProvider)this.getProvider()).hasChildren(n)) {
                this.setRecursive(n, expand);
            }
        }
    }
}
