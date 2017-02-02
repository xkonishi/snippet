package com.mycompany;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class ExDefaultProvider implements ITreeProvider<ExDefaultNode> {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ノードリスト
     */
    private List<ExDefaultNode> nodeList;

    /**
     * ノード選択判定用
     */
    private IModel<ExDefaultNode> selected;

    /**
     * <p>コンストラクタ</p>
     *
     */
    public ExDefaultProvider() {
        this(new ArrayList<>());
    }

    /**
     * <p>コンストラクタ</p>
     *
     * @param nodeList ノードリスト
     */
    public ExDefaultProvider(List<ExDefaultNode> nodeList) {
        this.setNodeList(nodeList);
    }

    /**
     * <p>デタッチ</p>
     *
     */
    @Override
    public void detach() {
    }

    /**
     * <p>第一階層ノードの取得</p>
     *
     * @return 第一階層ノード
     */
    @Override
    public Iterator<ExDefaultNode> getRoots() {
        return this.nodeList.iterator();
    }

    /**
     * <p>子ノードの有無判定</p>
     *
     * @param node 対象ノード
     * @return true:あり／false:なし
     */
    @Override
    public boolean hasChildren(ExDefaultNode node) {
        return node.getParent() == null || !node.getChildNodes().isEmpty();
    }

    /**
     * <p>子ノードの取得</p>
     *
     * @param node 対象ノード
     * @return 子ノード
     */
    @Override
    public Iterator<ExDefaultNode> getChildren(ExDefaultNode node) {
        return node.getChildNodes().iterator();
    }

    /**
     * <p>モデルの作成</p>
     *
     * @param node 対象ノード
     * @return モデル
     */
    @Override
    public IModel<ExDefaultNode> model(ExDefaultNode node) {
        return new DefaultNodeModel(node);
    }

    /**
     * <p>ノードリストの設定</p>
     *
     * @param nodeList ノードリスト
     */
    public void setNodeList(List<ExDefaultNode> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * <p>ノードの取得</p>
     *
     * @param id ID
     * @return ノード
     */
    public ExDefaultNode getNode(String id) {
        return findNode(this.nodeList, id);
    }

    /**
     * <p>ノードの検索</p>
     *
     * @param nodeList ノードリスト
     * @param id ID
     * @return ノード
     */
    private ExDefaultNode findNode(List<ExDefaultNode> nodeList, String id) {
        for(ExDefaultNode n : nodeList) {
            if (n.getId().equals(id)) {
                return n;
            }

            ExDefaultNode temp = findNode(n.getChildNodes(), id);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }

    /**
     * <p>ルートノードの判定</p>
     *
     * @param node ノード
     * @return true:Yes／false:No
     */
    public boolean isRoot(ExDefaultNode node) {
        return (node.getParent() == null) ? true : false;
    }

    /**
     * <p>選択ノードの取得</p>
     *
     * @return 選択ノード
     */
    public ExDefaultNode getSelectdNode() {
        return this.selected.getObject();
    }

    /**
     * <p>選択ノードの設定</p>
     *
     * @param node 選択ノード
     * @param tree ツリー
     */
    public void setSelectedNode(ExDefaultNode node, ExDefaultNestedTree tree) {
        this.select(node, tree, null);
    }

    /**
     * <p>ノードのクリック</p>
     *
     * @param node ノード
     * @param targetOptional ターゲット
     */
    protected void onClick(ExDefaultNode node, AjaxRequestTarget targetOptional) {
    }

    /**
     * <p>ノードのコンポーネント作成</p>
     *
     * @param id ID
     * @param tree ツリー
     * @param model モデル
     * @return コンポーネント
     */
    public Component newContentComponent(String id, final ExDefaultNestedTree tree, IModel<ExDefaultNode> model) {
        return new Folder<ExDefaultNode>(id, tree, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean isClickable() {
                return true;
            }

            @Override
            protected void onClick(AjaxRequestTarget targetOptional) {
                ExDefaultProvider.this.select(getModelObject(), tree, targetOptional);
                ExDefaultProvider.this.onClick(getModelObject(), targetOptional);
            }

            @Override
            protected boolean isSelected() {
                return ExDefaultProvider.this.isSelected(getModelObject());
            }
        };
    }

    /**
     * デフォルトノードモデルクラス
     * <p></p>
     *
     * @author Canon IT Solutions Inc. R&amp;D Center
     * @version 2.3
     */
    private class DefaultNodeModel extends LoadableDetachableModel<ExDefaultNode> {

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * ID
         */
        private final String id;

        /**
         * <p>コンストラクタ</p>
         *
         * @param node ノード
         */
        public DefaultNodeModel(ExDefaultNode node) {
            super(node);
            this.id = node.getId();
        }

        /**
         * <p>ロード</p>
         *
         * @return ノード
         */
        @Override
        protected ExDefaultNode load() {
            return ExDefaultProvider.this.getNode(this.id);
        }

        /**
         * <p>オブジェクト判定</p>
         *
         * @param obj オブジェクト
         * @return true:同じ／false:異なる
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultNodeModel) {
                DefaultNodeModel m = ((DefaultNodeModel)obj);
                return (m.id.equals(this.id));
            }
            return false;
        }

        /**
         * <p>ハッシュ値の取得</p>
         *
         * @return ハッシュ値
         */
        @Override
        public int hashCode() {
            return this.id.hashCode();
        }
    }

    /**
     * <p>選択有無の判定</p>
     *
     * @param node ノード
     * @return true:あり／false:なし
     */
    protected boolean isSelected(ExDefaultNode node) {
        IModel<ExDefaultNode> model = this.model(node);

        try {
            return selected != null && ((DefaultNodeModel)selected).equals(model);
        }
        finally {
            model.detach();
        }
    }

    /**
     * <p>選択の設定</p>
     *
     * @param node ノード
     * @param tree ツリー
     * @param targetOptional ターゲット
     */
    protected void select(ExDefaultNode node, AbstractTree<ExDefaultNode> tree, final AjaxRequestTarget targetOptional) {
        if (selected != null) {
            tree.updateNode(selected.getObject(), targetOptional);

            selected.detach();
            selected = null;
        }

        selected = this.model(node);

        tree.updateNode(node, targetOptional);
    }
}
