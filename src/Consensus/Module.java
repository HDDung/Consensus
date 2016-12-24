package Consensus;

import math_BQM.PreciseNumber;
import math_BQM.PriorNaiveBayes;
import weka.core.Instance;

public class Module {
	private PreciseNumber[] origin, data ;
	private Belief previous, exchange;
	private static double consensusLimit;
	
	public Module(){
		origin = new PreciseNumber[2];
		data = new PreciseNumber[2];
	}
	
	
	public void MakeBelief(){
		previous = new Belief(data[0].Log10(), data[1].Log10());
		exchange = new Belief(previous) ;
		
	}
	
	public void ResetPrior(){
		data[0] = origin[0];
		data[1] = origin[1];
	}
	
	public Belief GetData(){
		return previous;
	}
	
	public PreciseNumber[] GetPrior(){
		return data;
	}
	
	public void ExchangeBelief(Module module, double weight) {
		
		
		exchange = exchange.add(
				module.exchange.substract(previous).mul(weight));
	
	}
	
	public boolean UpdateBelief() {
		
		boolean strong = exchange.substract(previous).IsBoundedBy(previous.mul(consensusLimit));

		previous = new Belief(exchange);

		return strong;
	}
	public void SetBelief() {
		data[0] = PreciseNumber.Exp10(previous.GetNormal());
		data[1] = PreciseNumber.Exp10(previous.GetAttack());
	}
	// Get data and add to nodes
	public void Audit(Instance instance) {
		origin = PriorNaiveBayes.Default.GetPriorDistribution(instance);
		data[0] = origin[0];
		data[1] = origin[1];
	}
	
	public static void SetConsensusLimit(double value) {
		consensusLimit = value;
	}

}
