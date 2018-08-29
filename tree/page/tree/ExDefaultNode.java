package jp.co.mysample.component.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    private String label;

    /**
     * 親ノード
     */
    private ExDefaultNode parent;

    /**
     * 子ノード
     */
    private List<ExDefaultNode> nodes = new ArrayList<>();

    /**
     * ユーザーオブジェクト
     */
    private Object userObject;

    /**
     * <p>コンストラクタ</p>
     * 
     * @param label ラベル文字列
     */
    public ExDefaultNode(String label){
        this(label, null);
    }

    /**
     * <p>コンストラクタ</p>
     *
     * @param label ラベル文字列
     * @param userObject ユーザーオブジェクト
     */
    public ExDefaultNode(String label, Object userObject){
        this.id = UUID.randomUUID().toString();
        this.label = label;
        this.userObject = userObject;
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
     * <p>ラベル文字列の設定</p>
     *
     * @param label ラベル文字列
     */
    public void setLabel(String label) {
        this.label = label;
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
     * <p>ユーザーオブジェクトの取得</p>
     *
     * @return ユーザーオブジェクト
     */
    public Object getUserObject() {
        return this.userObject;
    }

    /**
     * <p>ユーザーオブジェクトの設定</p>
     *
     * @param userObject ユーザーオブジェクト
     */
    public void setUserObject(Object userObject) {
        this.userObject = userObject;
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
