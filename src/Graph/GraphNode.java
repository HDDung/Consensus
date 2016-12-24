package Graph;

import java.util.List;
import java.util.Vector;


public class GraphNode {
	private Vector<GraphNode> neighbor;
	private Object  Node_value;
	private int Node_number;
	//private long  channelGain;
	
	public GraphNode(){
		this(null);
	}
	
	
	public GraphNode(Object data) {
		// TODO Auto-generated constructor stub
		neighbor = new Vector<GraphNode>();
		this.Node_value = data;
		
	} 
	public GraphNode GetNeighbor(int index) {
		return neighbor.get(index);
	}
	
	public List<GraphNode> GetNeighbors() {
		return neighbor;
	}
	
	public void Add(GraphNode node){
		if (!IsNeighbor(node)){
			neighbor.add(node);
			node.neighbor.add(this);
		}
	}
	
	public void SetData(Object data){
		this.Node_value = data;
	}
	
	public void Remove(GraphNode node) {
		neighbor.remove(node);
		node.neighbor.remove(this);
	}
	public void Remove() {
		for (GraphNode node : neighbor)
			node.neighbor.remove(this);
		neighbor.clear();
	}
	
	public Object GetData() {
		return Node_value;
	}
	
	public int GetDegree(){
		return neighbor.size();
	}
	
	public int NodeNum(){
		return Node_number;
	}
	public boolean IsNeighbor(GraphNode node) {
		return neighbor.contains(node);
	}
	
	public GraphEdge GetEdge(GraphNode node) {
		if (IsNeighbor(node))
			return new GraphEdge(this, node);
		return null;
	}
}
