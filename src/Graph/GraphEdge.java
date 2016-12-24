package Graph;

public class GraphEdge {
	private GraphNode[] endpoints;
	private Object Value;
	
	public GraphEdge(GraphNode e1, GraphNode e2) {
		this(e1, e2, null);
	}
	
	public GraphEdge(GraphNode e1, GraphNode e2, Object data  ){
		endpoints = new GraphNode[2];
		
		endpoints[0] = e1;
		endpoints[1] = e2;
		this.Value = data;
	}
	
	@Override
	public boolean equals(Object obj) {
		GraphEdge edge = (GraphEdge)obj;
		return ((endpoints[0] == edge.endpoints[0] && endpoints[1] == edge.endpoints[1]) ||
				(endpoints[0] == edge.endpoints[1] && endpoints[1] == edge.endpoints[0]));
	}
	
	public void SetData(Object data){
		this.Value = data;
	}
	
	public Object GetData(){
		return this.Value;
	}
	
	public GraphNode[] GetEndPoints(){
		return this.endpoints;
	}
}
