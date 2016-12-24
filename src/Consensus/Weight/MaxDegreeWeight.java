package Consensus.Weight;
import Graph.*;

public final class MaxDegreeWeight implements Weight {

	@Override
	public void SetWeight(Graph graph) {
		int maxDeg = 0;
		for (GraphNode node: graph.GetNodes())
			if (node.GetDegree() > maxDeg)
				maxDeg = node.GetDegree();
		
		for (GraphEdge edge: graph.GetEdges())
			edge.SetData((double)1 / maxDeg);
	}

	@Override
	public String GetName() {
		return "Max-Degree Weight";
	}

}
