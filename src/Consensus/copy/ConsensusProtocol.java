package Consensus.copy;

import Graph.*;
import java.util.List;

import core.Network;
import core.State;
import math_BQM.PreciseNumber;

public class ConsensusProtocol {
	
	private Network i_network;
	private double threshold = 1e-3;
	private static final long maxLOOP = (long) 1e+18;
	private double adjustment;
	
	
	public ConsensusProtocol(Network network, double adjustment){
		this.i_network = network;
		this.adjustment = adjustment;
	}
	
	public void Evaluator(){
		long loop = 0;
		boolean stop = false;
		
		Network Test_graph = i_network;
		
		for (GraphNode node: i_network.GetGraph().GetNodes())
			((Module)node.GetData()).MakeBelief();
		
		do {
			loop++;
			for (GraphEdge edge: Test_graph.GetGraph().GetEdges()){
				
				GraphNode node1 = edge.GetEndPoints()[0];
				GraphNode node2 = edge.GetEndPoints()[1];
				((Module)node1.GetData()).ExchangeBelief(((Module)node2.GetData()), (double)edge.GetData());
				((Module)node2.GetData()).ExchangeBelief(((Module)node1.GetData()), (double)edge.GetData());
			}
			
			stop = true;
			for (GraphNode node: i_network.GetGraph().GetNodes())
				if (!((Module)node.GetData()).UpdateBelief())
					stop = false;
			
			
		} while (!stop && loop <= maxLOOP);
		
		// Reclaim the priors from the belief
		for (GraphNode node: i_network.GetGraph().GetNodes())
			((Module)node.GetData()).SetBelief();
	
		// Decision phase --------------------------------------------
		PreciseNumber[] result = ((Module)i_network.GetGraph().GetCentralNode().GetData()).GetPrior();
		
		State state;
		if (result[0].Log10() - result[1].Log10() > adjustment)
			state = State.NORMAL;
		else
			state = State.ATTACK;
		
		
		System.out.println(loop + " " + state + " " + result[0].Log10() + " " + result[1].Log10() );
	}
	
	public void EvaluatorRandMethod(){
		long loop = 0;
		boolean stop = false;
		Network Test_graph = i_network;
		
		for (GraphNode node: i_network.GetGraph().GetNodes())
			((Module)node.GetData()).MakeBelief();

		do {
			loop++;
			for (GraphNode node: Test_graph.GetGraph().GetNodes()){
				List<GraphNode> nodes = node.GetNeighbors();
				int i = Network.IntegerRandomWithRange(0, nodes.size()-1);
				GraphNode node2 = nodes.get(i);
				GraphEdge edge = Test_graph.GetGraph().EdgeBetween(node, node2);
				if (edge != null){
					((Module)node.GetData()).ExchangeBelief(((Module)node2.GetData()), (double)edge.GetData());

				}

			}
			stop = true;
			for (GraphNode node: i_network.GetGraph().GetNodes())
				if (!((Module)node.GetData()).UpdateBelief())
					stop = false;
			
		} while (!stop && loop <= maxLOOP);
		
		for (GraphNode node: i_network.GetGraph().GetNodes())
			((Module)node.GetData()).SetBelief();
	
		// Decision phase --------------------------------------------
		PreciseNumber[] result = ((Module)i_network.GetGraph().GetCentralNode().GetData()).GetPrior();
		
		State state;
		if (result[0].Log10() - result[1].Log10() > adjustment)
			state = State.NORMAL;
		else
			state = State.ATTACK;
		
		
		System.out.println(loop + " " + state + " " + result[0].Log10() + " " + result[1].Log10() );
	}
	
	
	
}
