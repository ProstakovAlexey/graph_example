package aleksey.prostakov.generator;

import aleksey.prostakov.generator.example.ExampleInterface;
import aleksey.prostakov.generator.example.ShortWay;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Generator {

    public static void main(String[] args) {
        List<ExampleInterface> examples = new ArrayList<>();
        examples.add(new ShortWay());
        for (ExampleInterface example : examples) {
            example.init();
            example.make();
            try {
                System.out.println(String.format("Example %s result was print in file %s",
                        example.getName(), example.print()));
            } catch (IOException e) {
                System.out.println("Error printing result for example " + example.getName());
                e.printStackTrace();
            }
        }
    }
}


