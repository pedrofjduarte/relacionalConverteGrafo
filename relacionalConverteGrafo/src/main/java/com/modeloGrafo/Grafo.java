package com.modeloGrafo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("graph")
public class Grafo {

    @XStreamAsAttribute
    @XStreamAlias("id")
    private String id;

    @XStreamAsAttribute
    @XStreamAlias("edgedefault")
    private String edgedefault;

    @XStreamImplicit
    private List<Vertice> vertices;

    @XStreamImplicit
    private List<Aresta> arestas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdgedefault() {
        return edgedefault;
    }

    public void setEdgedefault(String edgedefault) {
        this.edgedefault = edgedefault;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "id='" + id + '\'' +
                ", edgedefault='" + edgedefault + '\'' +
                ", vertices=" + vertices +
                ", arestas=" + arestas +
                '}';
    }
}
