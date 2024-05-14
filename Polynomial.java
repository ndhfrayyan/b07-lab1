public class Polynomial{
	double[] pc;
	public Polynomial(){
		pc = new double[1];
		pc[0] = 0;
	}
	public Polynomial(double[] dd){
		pc = new double[dd.length];
		for(int i = 0; i < dd.length; i++){
			pc[i] = dd[i];
		}
	}
	Polynomial add(Polynomial p){
		int len = pc.length;
		if(p.pc.length > len) len = p.pc.length;
		double [] pp = new double[len];
		for(int i = 0; i < len; i++){
			pp[i] = 0;
			if(i < p.pc.length) 
				pp[i] += p.pc[i];
			if(i < pc.length) 
				pp[i] += pc[i];
		}
		Polynomial ret = new Polynomial(pp);
		return ret;
	}
	double evaluate(double d){
		double ans = 0, sub = 1;
		for(int i = 0; i < pc.length; i++){
			ans += pc[i] * sub;
			sub *= d;
		}
		return ans;
	}
	boolean hasRoot(double d){
		if(evaluate(d) == 0) return true;
		return false;
	}
}