package jp.co.canonits.sample.component;

import jp.co.canonits.common.component.tree.ExDefaultNode;

public class MyNode extends ExDefaultNode{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean checked;

    private String radioGroup;
    private String selectValue;
    private String textValue;

    public MyNode(String label){
        super(label);
    }

    public MyNode(String label, Object userObject){
        super(label, userObject);
    }

    public MyNode(String label, String radioGroup, String selectValue, String textValue){
        super(label);
        this.radioGroup = radioGroup;
        this.selectValue = selectValue;
        this.textValue = textValue;
    }

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public String getRadioGroup(){
        return radioGroup;
    }

    public void setRadioGroup(String radioGroup){
        this.radioGroup = radioGroup;
    }

    public String getSelectValue(){
        return selectValue;
    }

    public void setSelectValue(String selectValue){
        this.selectValue = selectValue;
    }

    public String getTextValue(){
        return textValue;
    }

    public void setTextValue(String textValue){
        this.textValue = textValue;
    }
}