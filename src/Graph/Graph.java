package Graph;

import java.util.List;
import java.util.Vector;


import Jama.Matrix;
public class Graph {
	
	private GraphNode CentralNode;
	private List<GraphNode> nodes;
	private List<GraphEdge> edges;
	private double AveValue = 0;
	private String name;
	
	public Graph(){
		this(new GraphNode());
	}
	
	public Graph(GraphNode central) {
		name = "Graph";
		nodes = new Vector<GraphNode>();
		edges = new Vector<GraphEdge>();
		nodes.add(central);
	}
	public List<GraphNode> GetNodes() {
		return nodes;
	}
	
	public List<GraphEdge> GetEdges() {
		return this.edges;
	}
	public void SetCentralNode(GraphNode node){
		this.CentralNode = node;
	}
	
	public int GetSize() {
		return nodes.size();
	}
	
	public GraphNode NodeAt(int index) {
		return nodes.get(index);
	}
	
	public GraphEdge EdgeBetween(GraphNode e1, GraphNode e2){
		GraphEdge t_edge = new GraphEdge(e1,e2);
		for (GraphEdge edge : edges){
			if (t_edge.equals(edge)){
				return edge;
			}
		}
		return null;
	}
	public int IndexOf(GraphNode node) {
		return nodes.indexOf(node);
	}
	
	public double AverageValue(){
		double ave = 0;
		for (GraphNode node : nodes ){
			ave = ave + (double)node.GetData(); 
		}
		AveValue = ave/GetSize();
		return AveValue;
	}
	
	public Matrix GetLaplacian() {
		double[][] laplacian = new double[GetSize()][GetSize()];
		for (int i = 0; i < GetSize(); i++) {
			for (int j = 0; j < GetSize(); j++) {				
				if (i == j) 
					laplacian[i][j] = NodeAt(i).GetDegree();
				else if (NodeAt(i).IsNeighbor(NodeAt(j)))
					laplacian[i][j] = -1;
				else
					laplacian[i][j] = 0;
			}
		}
		
		return new Matrix(laplacian);
	}
	
	public void Invalidate() {
		Graph graph = GetConnectedGraph(CentralNode);
		nodes = graph.nodes;
		edges = graph.edges;
	}
	
	public static Graph GetConnectedGraph(GraphNode centralNode) {
		Graph graph = new Graph(centralNode);

		for (int i = 0; i < graph.nodes.size(); i++) {
			
			GraphNode node = graph.nodes.get(i);
			
			for (GraphNode neighbor: node.GetNeighbors()) {
				if (!graph.nodes.contains(neighbor))
					graph.nodes.add(neighbor);
				
				GraphEdge edge = node.GetEdge(neighbor);
				if (!graph.edges.contains(edge))
					graph.edges.add(edge);
					
			}
		}
		
		return graph;
	}
	
	public String GetName() {
		return name;
	}
	public void SetName(String name) {

		this.name = name;
	}
	
	public GraphNode GetCentralNode(){
		return this.CentralNode;
	}
}
