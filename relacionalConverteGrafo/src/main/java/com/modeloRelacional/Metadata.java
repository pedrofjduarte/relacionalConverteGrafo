package com.modeloRelacional;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

public class Metadata {

    @XStreamAlias("TABLES")
    private List<Tabela> TABLES;

    @XStreamAlias("RELATIONS")
    private List<Relacionamento> RELATIONS;

    public List<Tabela> getTABLES() {
        return TABLES;
    }

    public void setTABLES(List<Tabela> TABLES) {
        this.TABLES = TABLES;
    }

    public List<Relacionamento> getRELATIONS() {
        return RELATIONS;
    }

    public void setRELATIONS(List<Relacionamento> RELATIONS) {
        this.RELATIONS = RELATIONS;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "TABLES=" + TABLES +
                ", RELATIONS=" + RELATIONS +
                '}';
    }
}
