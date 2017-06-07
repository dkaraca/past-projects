import java.util.List;

public class F1 {
	double recall;
	double precision;

	public F1(double r, double p) {
		recall = r;
		precision = p;
	}

	public void setF1(double r, double p) {
		recall = r;
		precision = p;
	}

	public double getF1() {
		return (2 * recall * precision) / (recall + precision);
	}

	public double getWeighted(List<Point> p, int i) {
		// TODO Auto-generated method stub
		double percentSetosa = 0.0;
		double percentVirginica = 0.0;
		double percentVersicolor = 0.0;
		int totalSetosa = 0;
		int totalVirginica = 0;
		int totalVersicolor = 0;
		for (Point po : p) {
			if (po.label.equals("Iris-setosa")) {
				totalSetosa++;
			}
			if (po.label.equals("Iris-versicolor")) {
				totalVersicolor++;
			}
			if (po.label.equals("Iris-virginica")) {
				totalVirginica++;
			}
		}
		percentSetosa = (double) totalSetosa/p.size();
		percentVirginica = (double) totalVirginica/p.size();
		percentVersicolor = (double) totalVersicolor/p.size();
		
		double f1 = this.getF1();
		
		if (i == 0) {
			return f1*percentSetosa;
		}
		else if(i == 1){
			return f1*percentVersicolor;
		}
		else if(i == 2){
			return f1*percentVirginica;
		}
		else
			return 0;
		
	}

}
