import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;
enum algoChoice
{
    bfsAlgo,
    dfsAlgo
}
public class Path {

    algoChoice userChoice = null;
    GraphParse graph;
    GraphingAlgo algorithm;
    Path(String filePath)
    {
        graph = new GraphParse(filePath);
    }
    public void GraphSearch(String src, String dst,GraphingAlgo algorithm)
    {
        algorithm.algo(src,dst);
    }

    public interface GraphingAlgo
    {
        void algo(String src, String dst);
    }
    public class bfsAlgo implements GraphingAlgo
    {
        public void algo(String src, String dst)
        {
            GraphParse.GraphAlgo BFS = graph.new BFS();
            BFS.algo(src,dst);
            System.out.println(graph.bfsPath.substring(0, graph.bfsPath.length() - 2));

        }
    }
    public class dfsAlgo implements GraphingAlgo
    {
        public void algo(String src, String dst)
        {
            GraphParse.GraphAlgo DFS = graph.new DFS();
            DFS.algo(src,dst);
            System.out.println(graph.dfsPath.substring(0, graph.dfsPath.length() - 2));
        }
    }
    public class randWalkAlgo implements GraphingAlgo
    {
        @Override
        public void algo(String src, String dst) {
            GraphParse.GraphAlgo randWalk = graph.new RandomWalk();
            randWalk.algo(src,dst);
            System.out.println(graph.randPath.substring(0, graph.randPath.length() - 2));
        }
    }
}
