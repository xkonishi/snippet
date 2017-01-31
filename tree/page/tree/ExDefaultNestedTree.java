package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree;

import java.util.Iterator;
import java.util.function.Consumer;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.model.IModel;

public class ExDefaultNestedTree extends DefaultNestedTree<ExDefaultNode> {
    private static final long serialVersionUID = 1L;
    
    private ExDefaultProvider provider;

    public ExDefaultNestedTree(String id, ExDefaultProvider provider) {
        super(id, provider);
        this.provider = provider;
    }

    @Override
    protected Component newContentComponent(String id, IModel<ExDefaultNode> node) {
        return this.provider.newContentComponent(id, this, node);
    }

    public void expandAll() {
        Iterator<ExDefaultNode> it = this.provider.getRoots();
        while(it.hasNext()) {
            this.setRecursive(it.next(), true);
        }
    }

    public void collapseAll() {
        Iterator<ExDefaultNode> it = this.provider.getRoots();
        while(it.hasNext()) {
            this.setRecursive(it.next(), false);
        }
    }

	private void setRecursive(ExDefaultNode node, boolean expand) {
        Consumer<ExDefaultNode> c = p -> {
            if (expand) {
                this.expand(p);
            }else{
                this.collapse(p);
            }
        };
        c.accept(node);

        Iterator<ExDefaultNode> it = this.provider.getChildren(node);
        while(it.hasNext()) {
            ExDefaultNode n = it.next();
            c.accept(n);
            if (this.provider.hasChildren(n)) {
                this.setRecursive(n, expand);
            }
        }
	}
}
