package Graph.model;

import Graph.Graph;

public class Torus extends Graph {
	private Ring[] Torus;
	
	public Torus (int NumRing, int NumNodeRing){
		
		Torus = new Ring[NumRing];
		Torus[0] = new Ring(NumNodeRing);
		for (int i = 1; i < NumRing; i++ ){
			Torus[i] = new Ring(NumNodeRing);
		}
		
		// connect each Ring
		for (int i = 0; i < NumRing-1; i++ ){
			for (int j = 0; j < NumNodeRing; j++ ){
				Torus[i].MapAt(j).Add(Torus[i+1].MapAt(j));
			}
		}
		for (int i = 0; i < NumNodeRing; i++ ){
			Torus[NumRing-1].MapAt(i).Add(Torus[0].MapAt(i));
		}
		SetCentralNode(Torus[0].GetCentralNode());
		Invalidate();
		SetName("Torus graph " + NumRing + " " + NumNodeRing);
	}
	
}
