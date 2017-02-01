package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExDefaultNode implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String label;

    private ExDefaultNode parent;

    private List<ExDefaultNode> nodes = new ArrayList<>();

    public ExDefaultNode(String label){
        this(-1, label);
    }

    public ExDefaultNode(int id, String label){
        this(null, id, label);
    }

    public ExDefaultNode(ExDefaultNode parent, String label) {
        this(parent, -1, label);
    }

    public ExDefaultNode(ExDefaultNode parent, int id, String label) {
        this.id = id;
        this.label = label;
        this.parent = parent;
        if (this.parent != null) this.parent.nodes.add(this);
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public ExDefaultNode getParent() {
        return this.parent;
    }

    public List<ExDefaultNode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public String toString() {
        return this.getLabel();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExDefaultNode) {
            ExDefaultNode n = ((ExDefaultNode)obj);
            return (n.id == this.id && n.label == this.label);
        }
        return false;
    }
}
