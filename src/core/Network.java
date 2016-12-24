package core;

import Graph.*;
import Consensus.Module;
import Consensus.Weight.*;
import input_BQM.Data;

import java.util.Random;

public class Network {
	
	private Graph m_graph;
	private PacketGenerator generator;
	private Packet m_packet;
	private State state;
	
	public Network(Graph Input_Graph, Weight weight){
		// fill up data
		Random rnd = new Random();
		m_graph = Input_Graph;
		
		for (GraphNode node: m_graph.GetNodes())
			node.SetData(new Module());
		
		//System.out.println(m_graph.GetSize());
		State input;
		
		if (rnd.nextBoolean()) input = State.ATTACK;
		else input = State.NORMAL;
		
		state = input;
		//input = State.ATTACK;
		//input = State.NORMAL;
		
		//System.out.println(input);
		
		generator = new PacketGenerator(Input_Graph.GetSize(),
				Data.Default.GetNormalData(), Data.Default.GetAttackData());
		
		m_packet = generator.GetPacket(input);
		
		for (GraphNode node: m_graph.GetNodes())			
			((Module)node.GetData()).Audit(m_packet.PickRandomlyAndRemove());
		
		weight.SetWeight(m_graph);
	}
	
	public State GetState(){
		return state;
	}
	
	public Graph GetGraph(){
		return m_graph;
	}
	
	public void ResetData(){
		for (GraphNode node: m_graph.GetNodes()){
			((Module)node.GetData()).ResetPrior();
		}
	}
	
	public static int IntegerRandomWithRange(int min, int max)
	{
		   int range = Math.abs(max - min) + 1;     
		   return (int)(Math.random() * range) + (min <= max ? min : max);
	}
	public static double DoubleRandomWithRange(double min, double max)
	{
		   double range = Math.abs(max - min);     
		   return (Math.random() * range) + (min <= max ? min : max);
	}
	
	

	
}
