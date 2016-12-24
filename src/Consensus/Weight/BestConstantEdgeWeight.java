package Consensus.Weight;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import Graph.*;

public final class BestConstantEdgeWeight implements Weight {

	@Override
	public void SetWeight(Graph graph) {
		
		Matrix laplacian = graph.GetLaplacian();
		
		EigenvalueDecomposition decompositor = new EigenvalueDecomposition(laplacian);
		double[] eigenvalues = decompositor.getRealEigenvalues();
		
		double constantWeight = 2 / (eigenvalues[1] + eigenvalues[graph.GetSize() - 1]); 
		
		for (GraphEdge edge: graph.GetEdges())
			edge.SetData(constantWeight);
	}

	@Override
	public String GetName() {
		return "Best-constant Edge Weight";
	}

}
