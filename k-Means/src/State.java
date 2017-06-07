import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
	/* List<Attribute> attributes; */
	int[] k;
	int[] iter;

	/* List<Cluster> clusters; */
	List<Point> points;
	Map<Integer, Map<Integer, List<Cluster>>> clusters;
	Map<Integer, Map<Integer, List<Double>>> scores;
	Map<Integer, Map<Integer, List<Cluster>>> correctClusters;
	/* List<Map<Integer, Map<Integer, Double>>> scores; */
	/* List<Cluster> optClusters; */

	public State(int[] kL, int[] iterL) {
		/* attributes = new ArrayList<>(); */

		/* clusters = new ArrayList<>(); */
		points = new ArrayList<>();

		clusters = new HashMap<>();
		scores = new HashMap<>();
		correctClusters = new HashMap<>();
		k = new int[kL.length];
		iter = new int[iterL.length];
		for (int i = 0; i < kL.length; i++) {
			this.k[i] = kL[i];
		}

		for (int j = 0; j < iterL.length; j++) {
			this.iter[j] = iterL[j];
		}
	}

	public void setNumbers() {

	}

	public void addClusters(List<Cluster> c, int k, int iter) {
		/*
		 * clustersList.add(new ArrayList<Cluster>());
		 * clustersList.get(clustersList.size()-1).all(c);
		 * ssTotal[clustersList.size()-1] = total;
		 */

		/*
		 * if (lIndex >= clusters.size()) { clusters.add(new HashMap<>()); }
		 * 
		 * Map<Integer, Map<Integer, List<Cluster>>> tmp = clusters.get(lIndex);
		 */
		if (!clusters.containsKey(k)) {
			clusters.put(k, new HashMap<>());
		}
		/* double total = 0.0; */
		/*
		 * for (int i = 0; i < k; i++) { Map<Integer, List<Cluster>> t =
		 * tmp.get(k); if(!t.containsKey(iter)){ tmp.get(k).put(iter, new
		 * ArrayList<>()); } tmp.get(k).get(iter).add(c.get(i)); total+=
		 * KMeans.ssTotal(c.get(i).pointList, c.get(i).centroid); }
		 */
		clusters.get(k).put(iter, new ArrayList<>());
		for (int i = 0; i < c.size(); i++) {
			clusters.get(k).get(iter).add(c.get(i));
		}

		/*
		 * if(lIndex>=scores.size()){ scores.add(new HashMap<>()); }
		 * 
		 * Map<Integer, Map<Integer, Double>> temp = scores.get(lIndex);
		 * temp.put(k, new HashMap<>()); temp.get(k).put(iter, total);
		 */
	}

	public void addScores(int k, int iter, List<Double> totalScore) {

		if (!scores.containsKey(k)) {
			scores.put(k, new HashMap<>());
		}
		scores.get(k).put(iter, new ArrayList<>());
		for (int i = 0; i < totalScore.size(); i++) {
			scores.get(k).get(iter).add(totalScore.get(i));
		}

	}

	public void setCorrect(int k, int iter, List<Cluster> c) {
		List<Cluster> correctSet = new ArrayList<>();
		int iSetosa = 0, iVersicolor = 0, iVirginica = 0;
		double maxSetosa = 0.0, maxVersicolor = 0.0, maxVirginica = 0.0;

		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).setosa > maxSetosa) {
				maxSetosa = c.get(i).setosa;
				iSetosa = i;
			}
			if (c.get(i).versicolor > maxVersicolor) {
				maxVersicolor = c.get(i).versicolor;
				iVersicolor = i;
			}
			if (c.get(i).virginica > maxVirginica) {
				maxVirginica = c.get(i).virginica;
				iVirginica = i;
			}
		}

		correctSet.add(c.get(iSetosa));
		correctSet.add(c.get(iVersicolor));
		correctSet.add(c.get(iVirginica));

		if (!correctClusters.containsKey(k)) {
			correctClusters.put(k, new HashMap<>());
		}
		correctClusters.get(k).put(iter, new ArrayList<>());
		for (Cluster cl : correctSet) {
			correctClusters.get(k).get(iter).add(cl);
		}
	}

	public void setF1Score(List<Cluster> c) {

		int totalSetosa = 0; int totalVirginica = 0; int totalVersicolor = 0;
		for(Point p: points){
			if(p.label.equals("Iris-setosa")){
				totalSetosa++;
			}
			if(p.label.equals("Iris-versicolor")){
				totalVersicolor++;
			}
			if(p.label.equals("Iris-virginica")){
				totalVirginica++;
			}
		}
		
		for (int i = 0; i < c.size(); i++) {
			if (i == 0) {
				double number = c.get(i).setosa * c.get(i).pointList.size();
			
				c.get(i).f1Score.setF1((double) number / totalSetosa, (double) number / c.get(i).pointList.size());
			}
			if (i == 1) {
				double number = c.get(i).versicolor * c.get(i).pointList.size();
				
				c.get(i).f1Score.setF1((double) number / totalVersicolor, (double) number / c.get(i).pointList.size());
			}
			if (i == 2) {
				double number = c.get(i).virginica * c.get(i).pointList.size();
			
				c.get(i).f1Score.setF1((double) number / totalVirginica, (double) number / c.get(i).pointList.size());
			}
		}
	}

	

}
