package Graph.model;

import Graph.Graph;
import Graph.GraphNode;

public class Ring extends Graph {
	private GraphNode[] Rings;
	
	public Ring(int NumbNodes){
		Rings = new GraphNode[NumbNodes];
		GraphNode node = Rings[0] = new GraphNode();
		
		for (int i = 1; i < NumbNodes; i++) {
			GraphNode next = new GraphNode();
			node.Add(next);
			Rings[i] = node = next;
		}		
		node.Add(Rings[0]);
		SetCentralNode(Rings[0]);
		Invalidate();
		SetName("Ring graph " + NumbNodes);
	}
	
	public GraphNode MapAt(int index) {
		return Rings[index];
	}
}
