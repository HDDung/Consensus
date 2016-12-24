package main;

import core.Network;
import core.PacketGenerator;
import Graph.model.Ring;
import Graph.model.Torus;
import Consensus.ConsensusProtocol;
import Consensus.Module;
import Consensus.Weight.*;
import input_BQM.*;
import math_BQM.*;
import output.Accountant;

public final class main {

	public static void main(String[] args) {
		try {	
			// TODO Auto-generated method stub
			
			System.out.println("Start filtering");
			Data.Initialize();
	
			System.out.println("Start training");
			PriorNaiveBayes.Default.buildClassifier(Data.Default.GetTrainingData());
	
			System.out.println("Start testing");
			
			Module.SetConsensusLimit(1e-8);
			PacketGenerator.SetAttackDataRatio(.6);
			
			// make a accountant
			
			
			Weight[] weights = new Weight[]	{
					new LocalDegreeWeight(),
					new MaxDegreeWeight(),
					new BestConstantEdgeWeight(), 
					new MetropolisHasting()};
			
			for (Weight weight : weights){
				Accountant AccNormal = new Accountant();
				Accountant AccRand = new Accountant();
				@SuppressWarnings("unused")
				int count = 0, count1 = 0;
				for (int i = 0; i< 1000; i++){
					Network e1 = new Network(new Ring(11*11), weight) ;
					//System.out.println(e1.GetState());
					ConsensusProtocol cons = new ConsensusProtocol(e1, 9.5);
					AccNormal.AddReport(cons.Evaluator()); 
					e1.ResetData();
					AccRand.AddReport(cons.EvaluatorRandMethod()); 
					
					/*
					if (AccNormal.IndexReport(i).GetConclusion() != AccRand.IndexReport(i).GetConclusion()){
						System.out.println(AccNormal.IndexReport(i).GetNetworkState() + " " + AccNormal.IndexReport(i).GetConclusion() + " " + AccRand.IndexReport(i).GetConclusion());
						count++;
					}*/
					if (AccNormal.IndexReport(i).GetConclusion() == AccRand.IndexReport(i).GetConclusion() && AccRand.IndexReport(i).GetConclusion() != AccNormal.IndexReport(i).GetNetworkState()){
						System.out.println(AccNormal.IndexReport(i).GetNetworkState() + " " + AccNormal.IndexReport(i).GetConclusion() + " " + AccRand.IndexReport(i).GetConclusion());
						count1++;
					}
				}
				
				System.out.println(AccNormal.CalAccuracy());
				System.out.println(AccRand.CalAccuracy());
				System.out.println(AccRand.AveIteration()*1.0 / AccNormal.AveIteration());
				System.out.println(AccNormal.CalFN() + " " + AccRand.CalFN());
				System.out.println(AccNormal.CalFP() + " " + AccRand.CalFP());

				//System.out.println(count*1.0/1000);
			}
			
			System.out.println("Hello");
			
			 Integer i = new Integer(4);
			 System.out.println(i);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
