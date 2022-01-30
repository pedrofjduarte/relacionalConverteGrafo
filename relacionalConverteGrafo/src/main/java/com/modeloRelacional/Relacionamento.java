package com.modeloRelacional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("RELATION")
public class Relacionamento {

    @XStreamAsAttribute
    @XStreamAlias("RelationName")
    private String relationName;

    @XStreamAsAttribute
    @XStreamAlias("FKFields")
    private String fkFields;

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getFkFields() {
        return fkFields;
    }

    public void setFkFields(String fkFields) {
        this.fkFields = fkFields;
    }

    @Override
    public String toString() {
        return "Relacionamento{" +
                "relationName='" + relationName + '\'' +
                ", fkFields='" + fkFields + '\'' +
                '}';
    }
}
