package com.modeloGrafo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("node")
public class Vertice {

    @XStreamAsAttribute
    @XStreamAlias("id")
    private String id;

    @XStreamImplicit
    @XStreamAlias("data")
    private List<String> colunas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getColunas() {
        return colunas;
    }

    public void setColunas(List<String> colunas) {
        this.colunas = colunas;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "id='" + id + '\'' +
                ", colunas=" + colunas +
                '}';
    }
}
