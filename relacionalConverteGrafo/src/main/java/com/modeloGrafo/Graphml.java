package com.modeloGrafo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("graphml")
public class Graphml {

    @XStreamAlias("graph")
    private Grafo grafo;

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    @Override
    public String toString() {
        return "Graphml{" +
                "grafo=" + grafo +
                '}';
    }
}
