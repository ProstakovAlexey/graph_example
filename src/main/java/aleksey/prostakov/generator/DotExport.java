package aleksey.prostakov.generator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DotExport {
    private final Graph<String, DefaultEdge> directedGraph;
    private List<String>selectedVertex;
    private List<DefaultEdge>selectedEdge;

    public DotExport(Graph<String, DefaultEdge> directedGraph) {
        this.directedGraph = directedGraph;
        this.selectedVertex = new ArrayList<>();
        this.selectedEdge = new ArrayList<>();
    }

    public void setSelectedVertex(List<String> s) {
        this.selectedVertex = s;
    }

    public void setSelectedEdge(List<DefaultEdge> s) {
        this.selectedEdge = s;
    }
    /*
    Получить DOT экспортер.
    Он предназначен для экспорта в формате DOT, для программы GraphViz.dot
     */
    private DOTExporter<String, DefaultEdge> getExporter() {
        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>(v -> (v.toString()).replace('.', '_'));

        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            String label = v.toString();
            map.put("label", DefaultAttribute.createAttribute(label));
            if (selectedVertex.contains(label)) {
                map.put("style", DefaultAttribute.createAttribute("filled"));
                map.put("fillcolor", DefaultAttribute.createAttribute("red"));
            }
            return map;
        });

        exporter.setEdgeAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            if (selectedEdge.contains(v)) {
                map.put("color", DefaultAttribute.createAttribute("red"));
            }
            return map;
        });
        return exporter;
    }

    /*
    Сохранить результат в файл
     */
    public void saveToFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        DOTExporter<String, DefaultEdge> exporter = getExporter();
        exporter.exportGraph(directedGraph, writer);
        writer.close();
    }
}