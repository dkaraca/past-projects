import java.util.ArrayList;
import java.util.List;

public class Cluster {
		Point centroid;
		List<Point> pointList;
		double[] ssTotal;
		double score;
		F1 f1Score;
		double setosa;
		double versicolor;
		double virginica;
		
		
		public Cluster(Point p){
			centroid = p;
			pointList = new ArrayList<Point>();
			score = Double.MAX_VALUE;
			setosa = versicolor = virginica = 0.0;
			f1Score = new F1(0,0);
		}
		
		/*public void setClusters(){
			for(int i = 0; i<ssTotal.length; i++){
				if(ssTotal[i]<minScore){
					minScore = ssTotal[i];
				}
			}
			
		}*/
		public void setScore(double score){
			this.score = score;
		}
		
		
		
		public void setPercentages(){
			int nSetosa = 0, nVersicolor = 0, nVirginica = 0;
			for(Point p: pointList){
				if(p.label.equals("Iris-setosa")){
					nSetosa++;
				}
				if(p.label.equals("Iris-versicolor")){
					nVersicolor++;
				}
				if(p.label.equals("Iris-virginica")){
					nVirginica++;
				}
			}
			setosa = (double) nSetosa/pointList.size();
			versicolor = (double) nVersicolor/pointList.size();
			virginica = (double) nVirginica/pointList.size();
		}

		public static  void printPoints(List<Cluster> list) {
			// TODO Auto-generated method stub
			for(int i = 0; i<list.size(); i++){
				System.out.println(i);
				for(int j = 0; j<list.get(i).pointList.size(); j++){
					System.out.println(list.get(i).pointList.get(j).c1 + " " +list.get(i).pointList.get(j).c2 + " " +list.get(i).pointList.get(j).c3 + " " +list.get(i).pointList.get(j).c4 );
				}
			}
			
		}
		
		
		
}
