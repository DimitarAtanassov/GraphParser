
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
        graph.DFS(src,dst);
        System.out.println(graph.dfsPath.substring(0, graph.dfsPath.length() - 2));
    }
}