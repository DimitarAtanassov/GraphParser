import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;
Enum algoChoice
{
  0
  1
}
public class Path {
    algoChoice userChoice;
    GraphParse graph;
    Path(String filePath, userChoice)
    {
        graph = new GraphParse(filePath);
        this.userChoice = userChoice;
    }
    public void GraphSearch(String src, String dst)
    {
        Switch(userChoice)
        {
          case 0:
            graph.BFS(src,dst);
            System.out.println(graph.bfsPath.substring(0, graph.bfsPath.length() - 2));
            break;
           case 1:
             graph.DFS(src,dst);
             System.out.println(graph.dfsPath.substring(0, graph.dfsPath.length() - 2));
             break;
            
        }
    }
}
