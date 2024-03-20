# GraphParse README

## Overview
GraphParse is a Java project that provides functionality for parsing, manipulating, and analyzing directed graphs represented in the DOT language. It includes features to add and remove nodes, add and remove edges, output graph statistics, generate DOT files, and visualize graphs. Additionally, it offers algorithms for traversing graphs such as Breadth-First Search (BFS), Depth-First Search (DFS), and Random Walk.

## Features
- **Parsing Graphs**: Parse DOT files to create directed graph objects.
- **Graph Manipulation**: Add and remove nodes, add and remove edges.
- **Graph Statistics**: Output the number of nodes, node labels, number of edges, and graph directions.
- **Output Graphs**: Output graphs and graph statistics to files.
- **Visualize Graphs**: Generate graphical representations of graphs in various formats (e.g., PNG).
- **Graph Traversal Algorithms**:
  - Breadth-First Search (BFS)
  - Depth-First Search (DFS)
  - Random Walk

## Usage
1. **Parsing and Manipulating Graphs**:
   - Create an instance of `GraphParse` by providing the path to the DOT file.
   - Use methods like `addNode`, `removeNode`, `addEdge`, and `removeEdge` to manipulate the graph.
   - Access graph statistics and information using methods like `toString`.

2. **Output Graphs**:
   - Use `outputGraph` to output graph statistics to a file.
   - Use `outputDOTGraph` to output the graph in the DOT file format.

3. **Visualize Graphs**:
   - Use `outputGraphics` to generate graphical representations of the graph.

4. **Graph Traversal Algorithms**:
   - Choose the desired traversal algorithm (BFS, DFS, Random Walk) by instantiating the appropriate class in the `Path` class.
   - Invoke the `GraphSearch` method, passing the source and destination nodes along with the chosen algorithm.

## Example
```java
public class Main {
    public static void main(String[] args) {
        // Initialize GraphParse with input DOT file
        GraphParse graph = new GraphParse("input.dot");

        // Manipulate the graph
        graph.addNode("x");
        graph.addEdge("a", "d");

        // Output graph statistics to a file
        graph.outputGraph("outputgraphtest.txt");

        // Generate graphical representation of the graph
        graph.outputGraphics("output3.png");

        // Use BFS algorithm for graph traversal
        Path path = new Path("input2.dot");
        Path.GraphingAlgo algo = path.new bfsAlgo();
        path.GraphSearch("a", "h", algo);
    }
}
