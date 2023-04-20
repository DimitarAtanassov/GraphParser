import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
          GraphParse graph;
          graph = new GraphParse("input.dot");
        System.out.println(graph.toString());
        graph.outputGraph("outputgraphtest.txt");
        graph.addNode("x");
        graph.removeNode("x");
        String[] nodesToAdd = {"x","y","z"};
        graph.addNodes(nodesToAdd);
        String[] nodesToRemove = {"x","y","z"};
        graph.removeNodes(nodesToRemove);
        graph.addEdge("a","d");
        graph.removeEdge("a","d");
        graph.outputDOTGraph("output1.dot");
        graph.outputGraphics("output3.png");
        Path path;
        //Scanner myObj = new Scanner(System.in);
        //System.out.println("Enter (bfsAlgo) for BFS traversal and Enter (dfsAlgo) for DFS traversal ");
       // algoChoice userInput = algoChoice.valueOf(myObj.nextLine());
        path = new Path("input.dot");
        Path.GraphingAlgo algo = path.new dfsAlgo();
        path.GraphSearch("a","d",algo);



    }
}
