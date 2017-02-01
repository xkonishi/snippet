package com.mycompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>デフォルトツリーノードクラス</p>
 * 
 * @author Canon IT Solutions Inc. R&amp;D Center
 * @version 2.5
 */
public class ExDefaultNode implements Serializable{

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private final String id;

    /**
     * ラベル文字列
     */
    private final String label;

    /**
     * 親ノード
     */
    private ExDefaultNode parent;

    /**
     * 子ノード
     */
    private List<ExDefaultNode> nodes = new ArrayList<>();

    /**
     * <p>コンストラクタ</p>
     * 
     * @param label ラベル文字列
     */
    public ExDefaultNode(String label){
        this.id = UUID.randomUUID().toString();
        this.label = label;
    }

    /**
     * <p>IDの取得</p>
     *
     * @return ID
     */
    public String getId(){
        return this.id;
    }

    /**
     * <p>ラベル文字列の取得</p>
     *
     * @return ラベル文字列
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * <p>親ノードの取得</p>
     *
     * @return 親ノード
     */
    public ExDefaultNode getParent() {
        return this.parent;
    }

    /**
     * <p>親ノードの設定</p>
     *
     * @param node 親ノード
     */
    private void setParent(ExDefaultNode node){
        this.parent = node;
    }

    /**
     * <p>子ノードの取得</p>
     *
     * @return 子ノード
     */
    public List<ExDefaultNode> getChildNodes() {
        return Collections.unmodifiableList(nodes);
    }

    /**
     * <p>子ノードの追加</p>
     *
     * @param node 子ノード
     */
    public void add(ExDefaultNode node){
        node.setParent(this);
        this.nodes.add(node);
    }

    /**
     * <p>ラベル文字列の取得</p>
     *
     * @return ラベル文字列
     */
    @Override
    public String toString() {
        return this.getLabel();
    }

    /**
     * <p>オブジェクト判定</p>
     *
     * @param obj オブジェクト
     * @return true:同じ／false:異なる
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExDefaultNode) {
            ExDefaultNode n = ((ExDefaultNode)obj);
            return n.id.equals(this.id);
        }
        return false;
    }
}
