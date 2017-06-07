import java.nio.file.Paths;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] kList = {3,9,18};
		int[] iterList = {5,10,20};
		
		State s = new State(kList,iterList);
		DataParser.readinFile(Paths.get("data.txt"), s);
		
		for(int i = 0; i<s.k.length; i++){
			for(int j = 0; j<s.iter.length; j++){
				KMeans.kMeans(s, s.k[i], s.iter[j]);
			}
		}
		
		for(Integer k: s.clusters.keySet()){
			for(Integer iter: s.clusters.get(k).keySet()){
				for(Cluster c: s.clusters.get(k).get(iter)){
					c.setPercentages();
				}
				s.setCorrect(k, iter, s.clusters.get(k).get(iter));
			}
		}
		System.out.println("scores:");
		for(Integer k: s.scores.keySet()){
			System.out.println("k: " + k + " ");
			for(Integer iter: s.scores.get(k).keySet()){
				System.out.println("iter: " + iter + " ");
				for(Double score: s.scores.get(k).get(iter)){
					System.out.print(score + " ");
				}System.out.println();
			}
		}
		
		
		System.out.println("F1 scores:");
		for(Integer k: s.correctClusters.keySet()){
			for(Integer iter: s.correctClusters.get(k).keySet()){
			//	System.out.println(s.correctClusters.get(k).get(iter).size());
				s.setF1Score(s.correctClusters.get(k).get(iter));
				System.out.print("k: " + k + " iter: " + iter + " ");
				for(int i = 0; i<s.correctClusters.get(k).get(iter).size(); i++){
					System.out.print(s.correctClusters.get(k).get(iter).get(i).f1Score.getF1() + " ");
				}System.out.println();
				for(int i = 0; i<s.correctClusters.get(k).get(iter).size(); i++){
					System.out.println(s.correctClusters.get(k).get(iter).get(i).f1Score.getWeighted(s.points,i) + " ");
				}
			}
		}
		
		Cluster.printPoints(s.correctClusters.get(3).get(20));
		
		for(Cluster c: s.correctClusters.get(3).get(10)){
			System.out.println(c.centroid.c1+" "+c.centroid.c2+" "+c.centroid.c3+" "+c.centroid.c4);
		}
		
		
		
		

	}

	

}
