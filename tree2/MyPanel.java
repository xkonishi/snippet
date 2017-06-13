package jp.co.canonits.sample.component;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import jp.co.canonits.common.component.tree.ExDefaultNode;

/**
 * 
 */
public class MyPanel extends Panel
{
    private static final long serialVersionUID = 1L;
	
	private static final List<String> SEARCH_ENGINES = Arrays.asList(new String[]{"Google","Bing","Baidu"});

	private MyNode node;

    public MyPanel(String id, IModel<ExDefaultNode> foo)
    {
        super(id, foo);

        node = (MyNode)foo.getObject();

        add(new Radio<Object>("radio"){
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag){
                super.onComponentTag(tag);
                tag.getAttributes().put("name", node.getRadioGroup());
            }
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(new AjaxEventBehavior("change"){
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onEvent(AjaxRequestTarget arg0){
                        // イベント送信
                        MyPanel.this.send(getPage(), Broadcast.BREADTH, node);
                    }
                });
            }
        });

        add(new DropDownChoice<>("select", new PropertyModel<>(foo, "selectValue"), SEARCH_ENGINES));

        add(new TextField<ExDefaultNode>("text", new PropertyModel<>(foo, "textValue")));
    }

    @Override
    public void onEvent(IEvent<?> event){
        Object payload = event.getPayload();
        // ラジオボタンがクリックされた場合
        if (payload instanceof MyNode){
            if (payload.equals(this.node)){
                // 自分自身の場合は、チェックON
                this.node.setChecked(true);
            }
            else{
                // 自分以外で同じラジオグループの場合は、チェックOFF
                if (((MyNode)payload).getRadioGroup() == this.node.getRadioGroup()){
                    this.node.setChecked(false);
                }
            }
        }
    }
}
