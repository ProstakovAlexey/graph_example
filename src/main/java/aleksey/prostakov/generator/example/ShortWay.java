package aleksey.prostakov.generator.example;

import aleksey.prostakov.generator.DotExport;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;

public class ShortWay implements ExampleInterface {
    private static final String FILE_NAME = "short_way.dot";
    private static final String NAME = "Dijkstra shortest path";
    private Graph<String, DefaultEdge> directedGraph;
    private GraphPath<String, DefaultEdge> shortGraph;

    @Override
    public void init() {
        directedGraph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        directedGraph.addEdge("h", "e");
        directedGraph.addEdge("i", "h");
    }

    @Override
    public void make() {
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg =
                new DijkstraShortestPath<>(directedGraph);
        ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths("i");
        shortGraph = iPaths.getPath("a");
    }

    @Override
    public String print() throws IOException {
        DotExport exporter = new DotExport(directedGraph);
        exporter.setSelectedVertex(shortGraph.getVertexList());
        exporter.setSelectedEdge(shortGraph.getEdgeList());
        exporter.saveToFile(FILE_NAME);
        return FILE_NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
