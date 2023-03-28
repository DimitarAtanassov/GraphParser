import guru.nidi.graphviz.model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static guru.nidi.graphviz.model.Factory.*;
class GraphParseTest {
    GraphParse testGraph;
    @BeforeEach
    public void setup() throws Exception
    {
         testGraph = new GraphParse( "C:\\Users\\Dimitar\\IdeaProjects\\CSE464P1\\input.dot");

    }
    @Test
    void testparseGraph()
    {
        Assert.assertEquals(4,this.testGraph.getNodeSize());
        Assert.assertEquals(3, this.testGraph.getEdgeSize());
        Assert.assertTrue(testGraph.containsEdge("a", "b"));
        Assert.assertTrue(testGraph.containsEdge("b", "c"));
        Assert.assertTrue(testGraph.containsEdge("b","d"));
        System.out.println(testGraph.toString());
    }

    @Test
    void testToString() {
        Assert.assertEquals("Nodes: 4" + "\n" + "Node Labels: [d, c, b, a]" + "\n" + "Edges: 3" + "\n" +
                "Graph Directions:\n" + "\"a\" -> \"b\"\n" + "\"b\" -> \"c\"\n" + "\"b\" -> \"d\"\n", testGraph.toString() );
    }
    @Test
   void outputGraph() throws IOException {
        String outputfile = "output1.dot";
        testGraph.outputGraph(outputfile);
        String output = Files.readString(Paths.get(outputfile));
        System.out.println("Output: " + output);
        String expected = testGraph.toString();
        Assert.assertEquals(expected,output);
   }

    @Test
    void testaddNode() {
        testGraph.addNode("x");
        System.out.println(testGraph.toString());
        Assert.assertEquals(5, testGraph.getNodeSize());
        Assert.assertTrue(testGraph.containsNode("x"));
    }

    @Test
    void removeNode() {
        testGraph.removeNode("a");
        System.out.println(testGraph.toString());
        Assert.assertEquals(3,testGraph.getNodeSize());
        Assert.assertFalse(testGraph.containsNode("a"));
    }

    @Test
    void addNodes() {
        String[] nodesToadd = {"x","y","z"};
        testGraph.addNodes(nodesToadd);
        System.out.println(testGraph.toString());
        Assert.assertEquals(7, testGraph.getNodeSize());
        Assert.assertTrue(testGraph.containsNode("x"));
        Assert.assertTrue(testGraph.containsNode("y"));
        Assert.assertTrue(testGraph.containsNode("z"));
    }

    @Test
    void removeNodes() {
        String[] nodesToremove = {"b","c"};
        testGraph.removeNodes(nodesToremove);
        System.out.println(testGraph.toString());
        Assert.assertEquals(2, testGraph.getNodeSize());
        Assert.assertFalse(testGraph.containsNode("b"));
        Assert.assertFalse(testGraph.containsNode("c"));
    }

    @Test
    void addEdge() throws IOException {
        testGraph.addEdge("e","a");
        System.out.println(testGraph.toString());
        Assert.assertEquals(5, testGraph.getNodeSize());
        Assert.assertTrue(testGraph.containsNode("e"));
        Assert.assertTrue(testGraph.containsEdge("e","a"));
    }

    @Test
    void removeEdge() throws IOException {
        testGraph.removeEdge("b","c");
        System.out.println(testGraph.toString());
        Assert.assertEquals(4,testGraph.getNodeSize());
        Assert.assertEquals(2, testGraph.getEdgeSize());
        Assert.assertTrue(testGraph.containsEdge("a", "b"));
        Assert.assertTrue(testGraph.containsEdge("b", "d"));
    }

    @Test
    void outputDOTGraph() throws IOException {
        testGraph.addEdge("e","f");
        testGraph.addEdge("a","e");
        String outputFile = "output2.dot";
        testGraph.outputDOTGraph(outputFile);
        String output = Files.readString(Paths.get(outputFile));
        System.out.println("output: " + output);
        String expected = Files.readString(Paths.get("expectedFile.dot"));
        Assert.assertEquals(expected, output);
    }

    @Test
    void outputGraphics() throws IOException {
        String outputFile = "output3.png";
        testGraph.outputGraphics(outputFile);
        Assert.assertNotNull(outputFile);

    }
}