package output;

import java.util.List;
import java.util.Vector;

import core.State;

public class Accountant {
	private List<Report> reports;
	
	public Accountant(){
		reports = new Vector<Report>();
	}
	
	public void AddReport(Report input){
		reports.add(input);
	}
	
	public List<Report> GetReports(){
		return reports;
	}
	
	public Report IndexReport(int index){
		return reports.get(index);
	}
	
	public double AveIteration(){
		double ave = 0;
		for (Report report : reports){
			ave = ave + report.GetIteration();
		}
		return (ave/reports.size());
	}
	
	public int CalTP(){
		int TP = 0;
		for (Report t: reports){
			if (t.GetNetworkState() == State.ATTACK && t.GetConclusion() == State.ATTACK){
				TP++;
			}
		}
		return TP;
	}
	
	public int CalTN(){
		int TN = 0;
		for (Report t: reports){
			if (t.GetNetworkState() == State.NORMAL && t.GetConclusion() == State.NORMAL){
				TN++;
			}
		}
		return TN;
	}
	
	public int CalFN(){
		int FN = 0;
		for (Report t: reports){
			if (t.GetNetworkState() == State.NORMAL && t.GetConclusion() == State.ATTACK){
				FN++;
			}
		}
		return FN;
	}
	public int CalFP(){
		int FP = 0;
		for (Report t: reports){
			if (t.GetNetworkState() == State.ATTACK && t.GetConclusion() == State.NORMAL){
				FP++;
			}
		}
		return FP;
	}
	
	public float CalAccuracy(){
		int TP, TN, FP, FN;
		TP = this.CalTP();
		TN = this.CalTN();
		FP = this.CalFP();
		FN = this.CalFN();
		return (float) ((TP+TN)*1.0/(TP+TN+FP+FN));
	}
	
	
	
	
	
}
