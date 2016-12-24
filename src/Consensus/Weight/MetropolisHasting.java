package Consensus.Weight;

import Graph.*;

public class MetropolisHasting implements Weight {
	
	private String Name;
	
	@Override
	public void SetWeight(Graph graph) {
		// TODO Auto-generated method stub
		for (GraphEdge edge : graph.GetEdges()) edge.SetData(0); 
		for (GraphEdge edge : graph.GetEdges()){
			GraphNode end1 = edge.GetEndPoints()[0]
					, end2 = edge.GetEndPoints()[1];
			
			if (end1 == end2 ){
				for (GraphNode node : end1.GetNeighbors()){
					edge.SetData((double)edge.GetData() + (double)graph.EdgeBetween(end1, node).GetData());
				}
				edge.SetData(1 - (double)edge.GetData());
						
			} else {
				if (end1.IsNeighbor(end2)){
					edge.SetData((double)(1.0 / (1 + Math.max(end1.GetDegree(), end2.GetDegree()))));
					
				}
			}
		}
		
		Name = "Metropolis Hasting ";
	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return Name;
	}

}
