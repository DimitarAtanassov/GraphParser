import com.kitfox.svg.A;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.parse.Parser;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphParse {
    private MutableGraph g;
    private ArrayList<String> nodeList;
    Map<String, LinkedList<String>> adj;
    String bfsPath = "";
    String dfsPath = "";
    String graphDir;

    //Constructor
    public GraphParse(String filePath)
    {
        parseGraph(filePath);
        adj = new HashMap<String, LinkedList<String>>();
    }
    //Takes a filePath as an argument and creates a directed graph object
    public void parseGraph(String filePath) throws RuntimeException {
        try (InputStream dot = new FileInputStream(filePath)) {
            this.g = new Parser().read(dot).setDirected(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphviz viz = Graphviz.fromGraph(g);
        String renderedString = viz.render(Format.DOT).toString();
        graphDir = renderedString.substring(renderedString.indexOf("{") + 2, renderedString.indexOf("}")+1-1);
        String[] nodeLabelsFromString = null;
        String[] testing = null;
        nodeList =  new ArrayList<String>();
        nodeLabelsFromString = graphDir.split("\n");
        nodeLabelsFromString.toString();
        for(int i = 0; i < nodeLabelsFromString.length; i++) {
            testing = nodeLabelsFromString[i].split("->");
            if(testing.length == 2)
            {
                nodeList.add(testing[0].trim());
                nodeList.add(testing[1].trim());
            }else{
                nodeList.add(testing[0].trim());
            }
        }
        Set<String> set = new HashSet<>(nodeList);
        nodeList.clear();
        nodeList.addAll(set);
        for(int i = 0; i < nodeList.size();i++)
        {
            String newNode = nodeList.get(i).replace("\"", "");
            nodeList.set(i,newNode);
        }
    }
    //Output the number of nodes, the label of the nodes, the number of edges, the
    //nodes and the edge direction of edges (e.g., a -> b)
    public String toString()
    {
        Graphviz viz = Graphviz.fromGraph(g);
        String renderedString = viz.render(Format.DOT).toString();
        graphDir = renderedString.substring(renderedString.indexOf("{") + 2, renderedString.indexOf("}")+1-1);
        String edgeCount = Integer.toString(g.edges().size());
        String nodeCount = Integer.toString(g.nodes().size());
        String[] nodeLabelsFromString = null;
        String[] testing = null;
        nodeList =  new ArrayList<String>();
        nodeLabelsFromString = graphDir.split("\n");
        nodeLabelsFromString.toString();
        for(int i = 0; i < nodeLabelsFromString.length; i++) {
            testing = nodeLabelsFromString[i].split("->");
            if(testing.length == 2)
            {
                nodeList.add(testing[0].trim());
                nodeList.add(testing[1].trim());
            }else{
                nodeList.add(testing[0].trim());
            }
        }
        Set<String> set = new HashSet<>(nodeList);
        nodeList.clear();
        nodeList.addAll(set);
        for(int i = 0; i < nodeList.size();i++)
        {
            String newNode = nodeList.get(i).replace("\"", "");
            nodeList.set(i,newNode);
        }



        return "Nodes: " + nodeCount + "\n" + "Node Labels: " + nodeList.toString() + "\n" + "Edges: " + edgeCount + "\n" +
                "Graph Directions:\n" + graphDir ;

    }
    //Output the graphs statistics to a file, given the filePath
    public void outputGraph(String filePath){
        String outputString = toString();
        File outputFile = new File(filePath);
        try{
            if(outputFile.createNewFile())
                System.out.println("File Created " + outputFile.getName());
            else {
                System.out.println("File already Exists");
            }

        }catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        try{
            FileWriter out = new FileWriter(outputFile);
            out.write(outputString);
            System.out.println("String Written to file");
            out.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }


    public void addNode(String label) {
        if(nodeList.contains(label) == false){
            g.add(
                   mutNode(label)
            );
            nodeList.add(label);
            System.out.println("The node with label " + label + " has been added");
        }else{
            System.out.println("Error: Node with label " + label + " already exists in the graph");
        }
    }
    public void removeNode(String label){
        Graphviz viz = Graphviz.fromGraph(g);
        String dot = viz.render(Format.DOT).toString();
        String formatParamRight = "\"" + label + "\"" + " -> ";
        String formatParamLeft = " -> " + "\"" + label + "\"";
        String formatParamSingle = "\"" + label + "\"";
        dot = dot.replace(formatParamRight, "");
        dot = dot.replace(formatParamLeft, "");
        dot = dot.replace(formatParamSingle,"");

        try {
            g = new Parser().read(dot);
            nodeList.remove(label);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The node with label: " + label + " has been removed.");
    }

    public void addNodes(String[] labels){
//        for(int i = 0; i < labels.length; i++)
//        {
//            if(nodeList.contains(labels[i]) == false)
//            {
//                g.add(
//                        mutNode(labels[i])
//                );
//                System.out.println("Node with label " + labels[i] + " has been added");
//            }
//            else
//            {
//                System.out.println("Error: Node with label " + labels[i] + " already exists in the graph");
//            }
//        }
        for(int i = 0; i < labels.length; i++)
        {
            addNode(labels[i]);
        }
    }
    public void removeNodes(String[] labels){
        for(int i = 0; i < labels.length; i++)
        {
            removeNode(labels[i]);
        }
    }

    public void addEdge(String srcLabel, String dstLabel) throws IOException {
        Graphviz viz = Graphviz.fromGraph(g);
        String dot = viz.render(Format.DOT).toString();
        String edgeToAdd = "\"" + srcLabel + "\"" + " -> " + "\"" + dstLabel + "\"";
        int indexTwo = dot.indexOf("}");
        String newDot = "";
        if(dot.contains(edgeToAdd) == false)
        {
            newDot = dot.substring(0,indexTwo);
            newDot = newDot + edgeToAdd;
            newDot = newDot+ "\n" + dot.substring(indexTwo);
            g = new Parser().read(newDot).setDirected(true);
            System.out.println("The edge from " + srcLabel + " to " + dstLabel + " has been added");
        }
        else {
            System.out.println("Error: The edge already exists");
        }
    }
    public void removeEdge(String srcLabel, String dstLabel) throws IOException {
        Graphviz viz = Graphviz.fromGraph(g);
        String dot = viz.render(Format.DOT).toString();
        String edgeToRemove = "\"" + srcLabel + "\"" + " -> " + "\"" + dstLabel + "\"" +"\n";
        String edgeToRemoveEdit = edgeToRemove.replace("->", "");
        dot = dot.replace(edgeToRemove, edgeToRemoveEdit);
        g = new Parser().read(dot).setDirected(true);
        System.out.println("The edge from " + srcLabel + " to " + dstLabel + " has been removed");
    }
    public void outputDOTGraph(String path){
        Graphviz viz = Graphviz.fromGraph(g);
        String dot = viz.render(Format.DOT).toString();
        File outputFile = new File(path);
        try{
            if(outputFile.createNewFile())
                System.out.println("File Created " + outputFile.getName());
            else {
                System.out.println("File already Exists");
            }

        }catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        try{
            FileWriter out = new FileWriter(outputFile);
            out.write(dot.trim());
            System.out.println("Imported Graph has been written to DOT file");
            out.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    public void outputGraphics(String path)
    {
        try {
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getNodeSize()
    {
        // return g.nodes().size();
        return nodeList.size();
    }
    public int getEdgeSize()
    {
        return g.edges().size();
    }
    public boolean containsEdge(String nodeA, String nodeB)
    {
        Graphviz viz = Graphviz.fromGraph(g);
        String dot = viz.render(Format.DOT).toString();
        String edgeToAdd = "\"" + nodeA + "\"" + " -> " + "\"" + nodeB + "\"";
        if(dot.contains(edgeToAdd) == true) return true;
        else {
            return false;
        }
    }
    public boolean containsNode(String node){
        if(nodeList.contains(node) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void adjListSetup()
    {
        for(int i = 0; i < nodeList.size(); i++)
        {
            adj.putIfAbsent(nodeList.get(i),new LinkedList<String>());
        }
        for(int i = 0; i < nodeList.size(); i++)
        {
            for(int j = 0; j < nodeList.size(); j++)
            {
                if(containsEdge(nodeList.get(i), nodeList.get(j)))
                {
                    addNeighbor(nodeList.get(i), nodeList.get(j));
                }
            }
        }

    }
    public void addNeighbor(String v1,String v2)
    {
        adj.get(v1).add(v2);
    }
    public void BFS(String src, String dst)
    {
        adjListSetup();
        Set<String> visited = new HashSet<String>();
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(src);
        visited.add(src);
        while(queue.isEmpty() == false)
        {
            int size = queue.size();
            for(int i = 0 ; i < size; ++i)
            {
                String curr = queue.getFirst();
                bfsPath = bfsPath+ curr + "->";
                if(curr.equals(dst))
                {
                    return;
                }
                for(Map.Entry<String,LinkedList<String>> entry : adj.entrySet())
                {
                    if(entry.getKey().equals(curr))
                    {
                        for(int it = 0; it < entry.getValue().size(); it++)
                        {
                            if(visited.contains(entry.getValue().get(it)) == false)
                            {
                                queue.add(entry.getValue().get(it));
                                visited.add(entry.getValue().get(it));
                            }
                        }
                    }
                }
                queue.removeFirst();
            }
        }
    }
public boolean DFS(String src, String dst)
    {
        adjListSetup();
        Set<String> visited = new HashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.add(src);
        while(stack.isEmpty() == false)
        {
            String curr = stack.pop();
            dfsPath = dfsPath + curr + "->";
            if(curr.equals(src))
            {
                return true;
            }
            for(Map.Entry<String,LinkedList<String>> entry : adj.entrySet())
            {
                if(entry.getKey().equals(curr)){
                    for(int it = 0; it < entry.getValue().size(); it++){
                        if(visited.contains(entry.getValue().get(it)) == false)
                        {
                            visited.add(entry.getValue().get(it));
                            stack.add(entry.getValue().get(it));
                        }
                    }
                }
            }
        }
        return false;
    }



}
