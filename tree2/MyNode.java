package jp.co.canonits.sample.component;

import jp.co.canonits.common.component.tree.ExDefaultNode;

public class MyNode extends ExDefaultNode{

    private String radioId;
    private String selectValue;
    private String textValue;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MyNode(String label){
        super(label);
    }

    public MyNode(String label, Object userObject){
        super(label, userObject);
    }

    public MyNode(String label, String radioId, String selectValue, String textValue){
        super(label);
        this.radioId = radioId;
        this.selectValue = selectValue;
        this.textValue = textValue;
    }

    public String getRadioId(){
        return radioId;
    }

    public void setRadioId(String radioId){
        this.radioId = radioId;
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
