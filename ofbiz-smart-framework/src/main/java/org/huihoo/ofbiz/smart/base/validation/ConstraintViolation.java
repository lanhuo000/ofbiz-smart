package org.huihoo.ofbiz.smart.base.validation;


import java.io.Serializable;

public class ConstraintViolation implements Serializable{
    private String fieldName;
    private String filedMessage;
    private Object filedOriginalValue;

    public ConstraintViolation(String fieldName, String filedMessage, Object filedOriginalValue) {
        this.fieldName = fieldName;
        this.filedMessage = filedMessage;
        this.filedOriginalValue = filedOriginalValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFiledMessage() {
        return filedMessage;
    }

    public void setFiledMessage(String filedMessage) {
        this.filedMessage = filedMessage;
    }

    public Object getFiledOriginalValue() {
        return filedOriginalValue;
    }

    public void setFiledOriginalValue(Object filedOriginalValue) {
        this.filedOriginalValue = filedOriginalValue;
    }
}
