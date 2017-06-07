import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class KMeans {

	public static void kMeans(State s, int k, int iter) {

		List<Double> tmpScores = new ArrayList<>();
		Map<Double, List<Cluster>> save = new HashMap<>();
		List<Point> tmpPoints = new ArrayList<>();
		Queue<Integer> usedRand = new LinkedList<>(); // to keep track that the
														// same random number is
														// not generated

		for (int i = 0; i < s.points.size(); i++) {
			tmpPoints.add(s.points.get(i));
		}

		int counter = 0;

		while (counter < 4) {
			// for (int m = 0; m < kList.length; m++) {

			int c = 0;
			// int k = kList[m];
			List<Cluster> clusters = new ArrayList<>();
			while (c < k) {
				// System.out.println(c);
				int rand = (int) (Math.random() * tmpPoints.size());
				/* System.out.println(rand); */
				if (!usedRand.contains(rand)) {
					/* System.out.println(rand); */
					usedRand.add(rand);
					clusters.add(new Cluster(tmpPoints.get(rand)));
					c++;
				}
			}
			// System.out.println(clusterList.size());

			for (int i = 0; i < iter; i++) {
				// System.out.println(clusters);
				update(s, clusters, iter);
			}
			tmpScores.add(totalSS(clusters));

			save.put(totalSS(clusters), new ArrayList<>());
			for(Cluster tmp: clusters){
				save.get(totalSS(clusters)).add(tmp);
			}
			// s.addClusters(clusters, k, iter, counter);
			/*for (Cluster tmp : clusters) {
				tmp.setPercentages();
			}*/

			/*
			 * System.out.println("k: " + k + " iter: " + iter + " counter: " +
			 * counter + " total: " + totalSS(clusters)); for(int o = 0;
			 * o<clusters.size(); o++){ System.out.println("i: " + o +
			 * " setosa: " + clusters.get(o).setosa + " versicolor: " +
			 * clusters.get(o).versicolor + " virginica: " +
			 * clusters.get(o).virginica ); }
			 */

			// }
			counter++;

		}
		while (!usedRand.isEmpty()) {
			usedRand.remove();
		}
		double minKey = getMinKey(save);
		s.addClusters(save.get(minKey), k, iter);
		s.addScores(k, iter, tmpScores);

		/*
		 * for(Double d: s.scores.get(k).get(iter)){ System.out.print(k + " " +
		 * iter + " " + d + " "); }System.out.println();
		 */
	}

	private static double getMinKey(Map<Double, List<Cluster>> save) {
		// TODO Auto-generated method stub
		double min = Double.MAX_VALUE;
		for (Double d : save.keySet()) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static void update(State s, List<Cluster> clusters, int iter) {
		for (int i = 0; i < iter; i++) {

			// step 3
			int currIndex = 0, pickedIndex = 0;
			while (currIndex < s.points.size()) {
				pickedIndex = minIndex(clusters, s.points.get(currIndex));
				clusters.get(pickedIndex).pointList.add(s.points.get(currIndex));
				pickedIndex = 0;
				currIndex++;
			}

			// step 4
			// List<Point> tmpCenters = new ArrayList<>();
			for (Cluster cl : clusters) {
				cl.centroid = meanC(cl.pointList);
				if (i != iter - 1)
					cl.pointList.clear();
			}

		}
		// double total = 0.0;
		for (Cluster cl : clusters) {
			cl.setScore(ssTotal(cl.pointList, cl.centroid));
			// total+=ssTotal(cl.pointList, cl.centroid);
		}

		/* s.printClusters(); */
	}

	public static Point meanC(List<Point> p) {

		double m1 = 0.0, m2 = 0.0, m3 = 0.0, m4 = 0.0;
		for (Point i : p) {
			m1 += i.c1;
			m2 += i.c2;
			m3 += i.c3;
			m4 += i.c4;
		}
		m1 /= p.size();
		m2 /= p.size();
		m3 /= p.size();
		m4 /= p.size();

		return new Point(m1, m2, m3, m4);
	}

	public static int minIndex(List<Cluster> c, Point p) {
		int mIndex = 0;
		double minDistance = euclidean(c.get(0).centroid, p);
		for (Cluster i : c) {
			if (euclidean(i.centroid, p) < minDistance) {
				minDistance = euclidean(i.centroid, p);
				mIndex = c.indexOf(i);
			}
		}
		return mIndex;
	}

	public static double euclidean(Point p1, Point p2) {

		return Math.sqrt(Math.pow((p1.c1 - p2.c1), 2) + Math.pow((p1.c2 - p2.c2), 2) + Math.pow((p1.c3 - p2.c3), 2)
				+ Math.pow((p1.c4 - p2.c4), 2));

	}

	public static double totalSS(List<Cluster> c) {
		double val = 0.0;
		for (Cluster cl : c) {
			val += ssTotal(cl.pointList, cl.centroid);
		}
		return val;
	}

	public static double ssTotal(List<Point> pList, Point c) {
		double sum = 0.0;
		for (int i = 0; i < pList.size(); i++) {
			sum += euclidean(c, pList.get(i));
		}
		return sum;
	}
}
