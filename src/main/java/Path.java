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
    Path(String filePath, algoChoice algo)
    {
        graph = new GraphParse(filePath);
        this.userChoice = algo;
    }
    public void GraphSearch(String src, String dst)
    {
        switch(userChoice)
        {
          case bfsAlgo:
              GraphParse.GraphAlgo BFS = graph.new BFS();
              BFS.algo(src,dst);
            System.out.println(graph.bfsPath.substring(0, graph.bfsPath.length() - 2));
            break;
           case dfsAlgo:
               GraphParse.GraphAlgo DFS = graph.new DFS();
               DFS.algo(src,dst);
             System.out.println(graph.dfsPath.substring(0, graph.dfsPath.length() - 2));
             break;
        }
    }
}
