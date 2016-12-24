package Consensus.Weight;

import Graph.*;


public final class LocalDegreeWeight implements Weight {

	@Override
	public void SetWeight(Graph graph) {
		for (GraphEdge edge: graph.GetEdges()) {
			GraphNode[] ends = edge.GetEndPoints();
			edge.SetData((double)1 / Math.max(ends[0].GetDegree(), ends[1].GetDegree()));
		}
	}

	@Override
	public String GetName() {
		return "Local-Degree Weight";
	}

}
