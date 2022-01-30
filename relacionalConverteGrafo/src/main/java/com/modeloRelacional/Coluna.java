package com.modeloRelacional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("COLUMN")
public class Coluna {

    @XStreamAsAttribute
    private String ColName;

    public String getColName() {
        return ColName;
    }

    public void setColName(String colName) {
        ColName = colName;
    }

    @Override
    public String toString() {
        return "Coluna{" +
                "ColName='" + ColName + '\'' +
                '}';
    }
}
