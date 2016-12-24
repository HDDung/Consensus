package output;

import core.State;
import math_BQM.PreciseNumber;

public class Report {
	private long iteration; 
	private State conclusion;
	private State NetworkState;
	private PreciseNumber[] result;
	
	
	public Report(long iter, State state, State state_network, PreciseNumber[] result ){
		this.iteration = iter;
		this.conclusion = state;
		this.result = result;
		this.NetworkState = state_network;
		
	}
	
	public long GetIteration(){
		return iteration;
	}
	public State GetConclusion(){
		return conclusion;
	}
	public State GetNetworkState(){
		return NetworkState;
	}
	public PreciseNumber[] GetResult(){
		return result;
	}
	
}
