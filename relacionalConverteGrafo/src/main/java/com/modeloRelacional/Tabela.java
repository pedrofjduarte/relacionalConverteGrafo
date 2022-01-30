package com.modeloRelacional;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

@XStreamAlias("TABLE")
public class Tabela {

    @XStreamAsAttribute
    private String Tablename;

    @XStreamAlias("COLUMNS")
    private List<Coluna> colunas;

    public String getTablename() {
        return Tablename;
    }

    public void setTablename(String tablename) {
        Tablename = tablename;
    }

    public List<Coluna> getColunas() {
        return colunas;
    }

    public void setColunas(List<Coluna> colunas) {
        this.colunas = colunas;
    }

    @Override
    public String toString() {
        return "Tabela{" +
                "Tablename='" + Tablename + '\'' +
                ", colunas=" + colunas +
                '}';
    }
}
