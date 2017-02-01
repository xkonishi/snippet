package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExDefaultNode implements Serializable{
    private static final long serialVersionUID = 1L;

    private String label;
    private String fulllabel;

    private ExDefaultNode parent;

    private List<ExDefaultNode> nodes = new ArrayList<>();

    public ExDefaultNode(String label){
        this.label = label;
        this.fulllabel = label;
    }

    public ExDefaultNode(ExDefaultNode parent, String label) {
        this(label);

        this.parent = parent;
        this.parent.nodes.add(this);
        
        StringBuffer buf = new StringBuffer();
        this.setFullLabel(this, buf);
        this.fulllabel = buf.toString();
    }

    private void setFullLabel(ExDefaultNode node, StringBuffer buf) {
        if (buf.length() != 0) buf.insert(0, "_");
        buf.insert(0, node.getLabel());
        ExDefaultNode p = node.getParent();
        if (p != null) {
            setFullLabel(p, buf);
        }
    }

    public ExDefaultNode getParent() {
        return parent;
    }

    public String getLabel() {
        return label;
    }

    public String getFullLabel() {
        return this.fulllabel;
    }

    public List<String> splitFullLabel() {
        String[] arr = this.fulllabel.split("_",0);
        return Arrays.asList(arr);
    }

    public String getParentLabel(ExDefaultNode node) {
        return this.getParent().getLabel();
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
        StringBuffer buf1 = new StringBuffer();
        StringBuffer buf2 = new StringBuffer();

        this.setFullLabel(this, buf1);

        ExDefaultNode n = ((ExDefaultNode)obj);
        n.setFullLabel(n, buf2);

        return (buf1.toString().compareTo(buf2.toString()) == 0);
    }
}
