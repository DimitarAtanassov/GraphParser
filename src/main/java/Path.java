import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;
public class Path {
    GraphParse graph;
    Path(String filePath)
    {
        graph = new GraphParse(filePath);
    }
    public void GraphSearch(String src, String dst)
    {
        graph.BFS(src,dst);
        System.out.println(graph.bfsPath.substring(0, graph.bfsPath.length() - 2));
    }
}
