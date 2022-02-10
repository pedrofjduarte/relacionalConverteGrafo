package com.executa;

import com.modeloGrafo.Aresta;
import com.modeloGrafo.Grafo;
import com.modeloGrafo.Graphml;
import com.modeloGrafo.Vertice;
import com.modeloRelacional.Banco;
import com.modeloRelacional.Coluna;
import com.modeloRelacional.Metadata;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Banco banco = new Banco();
        banco = lerModeloRelacional("mER.xml"); //passar o arquivo gerado na ferramenta DBDesigner.

        Graphml graphml = new Graphml();
        Grafo grafo = new Grafo();
        List<Vertice> vertices = new ArrayList<>();
        List<Aresta> arestas = new ArrayList<>();

        String xmlModeloGrafo;

        grafo = mapearRelacionalParaGrafo(banco, vertices, arestas);
        xmlModeloGrafo = gerarArquivoGraphmlGrafo(grafo);
        salvarArquivo(xmlModeloGrafo, "modelagemGrafo.graphml");
        graphml = lerModeloGrafo("modelagemGrafo.graphml");//passar o arquivo gerado na conversão para leitura.

        System.out.println(xmlModeloGrafo);
    }

    private static String gerarArquivoGraphmlGrafo(Grafo grafo){
        Graphml graphml = new Graphml();
        graphml.setGrafo(grafo);

        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{"com.modeloRelacional.**"});
        xstream.alias("graphml", Graphml.class);
        xstream.alias("graph", Grafo.class);
        xstream.alias("node", Vertice.class);
        xstream.alias("edge", Aresta.class);

        xstream.alias("data", String.class);

        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();

        String xml = xstream.toXML(graphml);
        return xml;
    }

    private static Grafo mapearRelacionalParaGrafo(Banco banco, List<Vertice> vertices, List<Aresta> arestas){

        Grafo grafo = new Grafo();

        int controlador;
        for(controlador = 0; controlador<banco.getMetadata().getTABLES().size(); controlador++){
            Vertice vertice = new Vertice();
            vertice.setId(banco.getMetadata().getTABLES().get(controlador).getTablename());
            int quantidadeColunas;
            List<String>colunas = new ArrayList<>();
            for(quantidadeColunas = 0; quantidadeColunas < banco.getMetadata().getTABLES().get(controlador).getColunas().size(); quantidadeColunas++){
                colunas.add(banco.getMetadata().getTABLES().get(controlador).getColunas().get(quantidadeColunas).getColName());
            }
            vertice.setColunas(colunas);
            vertices.add(vertice);
        }

        int controladorAresta;
        for(controladorAresta = 0; controladorAresta <banco.getMetadata().getRELATIONS().size(); controladorAresta++){
            Aresta aresta = new Aresta();
            aresta.setId(banco.getMetadata().getRELATIONS().get(controladorAresta).getRelationName());

            aresta.setOrigem(banco.getMetadata().getRELATIONS().get(controladorAresta).getFkFields());
            aresta.setDestino(banco.getMetadata().getRELATIONS().get(controladorAresta).getFkFields());
            arestas.add(aresta);
        }

        int controle;
        for(controle=0; controle<arestas.size(); controle++){
            String origemAntiga = arestas.get(controle).getOrigem();
            String origemNova = origemAntiga.replace("\\n", "");
            arestas.get(controle).setOrigem(origemNova);

            String destinoAntigo = arestas.get(controle).getDestino();
            String destinoNovo = destinoAntigo.replace("\\n", "");
            arestas.get(controle).setDestino(destinoNovo);

            int posicaoO = origemNova.indexOf('=');
            if (posicaoO >= 0) {
                origemNova = origemNova.substring(0, posicaoO);
            }
            arestas.get(controle).setOrigem(origemNova);

            int posicaoD = destinoNovo.indexOf('=') +1;
            if (posicaoD>=0) {
                destinoNovo = destinoNovo.substring(posicaoD, destinoNovo.length());
            }
            arestas.get(controle).setDestino(destinoNovo);
        }

        int v;
        for (v = 0; v<vertices.size(); v++ ){
            int j;
            for (j=0; j<arestas.size(); j++){
                int p;
                for(p=0; p<vertices.get(v).getColunas().size(); p++){
                    if(arestas.get(j).getOrigem().equals((vertices.get(v).getColunas().get(p)))){
                        arestas.get(j).setOrigem(vertices.get(v).getId());
                    }
                    if (arestas.get(j).getDestino().equals((vertices.get(v).getColunas().get(p)))){
                        arestas.get(j).setDestino(vertices.get(v).getId());
                    }
                }
            }
        }

        int k;
        for (k=0; k<arestas.size(); k++){
            arestas.get(k).setId("R"+(Integer.toString(k+1)));
        }

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);
        grafo.setId("Graph");
        grafo.setEdgedefault("undirected");

        return grafo;
    }

    private static Graphml lerModeloGrafo(String file) throws IOException {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{"com.modeloGrafo.**"});
        xstream.alias("graphml", Graphml.class);
        xstream.alias("graph", Grafo.class);
        xstream.alias("node", Vertice.class);
        xstream.alias("edge", Aresta.class);

        xstream.alias("data", String.class);

        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();

        File xmlFileLer = new File("src/main/resources/arquivos/modeloGrafo/" + file);
        Graphml graphml = (Graphml) xstream.fromXML(xmlFileLer);
        return graphml;
    }

    private static Banco lerModeloRelacional(String file) throws IOException {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{"com.modeloRelacional.**"});
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.alias("DBMODEL", Banco.class);
        xstream.alias("METADATA", Metadata.class);
        xstream.alias("TABLE", com.modeloRelacional.Tabela.class);
        xstream.alias("COLUMN", Coluna.class);

        File xmlFileLer = new File("src/main/resources/arquivos/modeloRelacional/" + file);
        Banco banco = (Banco) xstream.fromXML(xmlFileLer);
        return banco;
    }

    private static void salvarArquivo(String documento, String file) {
        File path = new File("src/main/resources/arquivos/modeloGrafo/" + file);
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(
                    ""
            );
            writer.println(documento);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
