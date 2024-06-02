import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Polynomial{
	double[] pc;
	int[] pw;
	public Polynomial(){
		pc = new double[0];
		pw = new int[0];
	}
	public Polynomial(double[] dd, int[] bb){
		int len = dd.length;
		pc = new double[len];
		pw = new int[len];
		for(int i = 0; i < len; i++){
			pc[i] = dd[i];
			pw[i] = bb[i];
		}
	}
	public Polynomial(File f){
		String p = f.getAbsolutePath();
		String s = "";
		try{
			s = new String(Files.readAllBytes(Paths.get(p)));
		}
		catch(IOException e){
            e.printStackTrace();
        }
		String sp[] = s.split("[+-]");
		int len = sp.length;
		pc = new double[len];
		pw = new int[len];
		for(int i = 0; i < len; i++){
			int idx = sp[i].indexOf('x');
			if(idx == -1){
				pc[i] = Double.parseDouble(sp[i]);
				pw[i] = 0;
			}
			else{
				pc[i] = Double.parseDouble(sp[i].substring(0, idx - 1));
				pw[i] = Integer.parseInt(sp[i].substring(idx + 1));
			}
		}
		if(s.charAt(0) == '-') pc[0] *= -1;
		int j = 1;
		for(int i = 1; i < s.length; i++){
			if(s.charAt(i) == '-'){
				pc[j] *= -1;
				j++;
			}
			else if(s.charAt(i) == '+'){
				j++;
			}
		}
	}
	Polynomial add(Polynomial p){
		int len1 = pc.length, len2 = p.pc.length;
		double[] pp = new double[len1 + len2];
		int[] ww = new int[len1 + len2];
		for(int i = 0; i < len1 + len2; i++) pp[i] = ww[i] = -1;
		for(int i = 0; i < len1 + len2; i++){
			if(i < len1){
				for(int j = 0; j < len1 + len2; j++){
					if(ww[j] == -1){
						ww[j] = pw[i];
						pp[j] = pc[i];
						break;
					}
					else if(ww[j] == pw[i]){
						pp[j] += pc[i];
						break;
					}
				}
			}
			else{
				int k = i - len1;
				for(int j = 0; j < len1 + len2; j++){
					if(ww[j] == -1){
						ww[j] = p.pw[k];
						pp[j] = p.pc[k];
						break;
					}
					else if(ww[j] == p.pw[k]){
						pp[j] += p.pc[k];
						break;
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < len1 + len2; i++){
			if(ww[i] == -1) break;
			if(pp[i] != 0) cnt++;
		}
		double[] rc = new double[cnt];
		int[] cw = new int[cnt];
		int idx = 0;
		for(int i = 0; i < len1 + len2; i++){
			if(ww[i] == -1) break;
			if(pp[i] != 0){
				rc[idx] = pp[i];
				cw[idx] = ww[i];
				idx++;
			}
		}
		Polynomial ret = new Polynomial(rc, cw);
		return ret;
	}
	double evaluate(double d){
		double ans = 0, sub;
		for(int i = 0; i < pw.length; i++){
			sub = 1;
			for(int j = 1; j < pw[i]; j++){
				sub *= d;
			}
			ans += pc[i] * sub;
		}
		return ans;
	}
	boolean hasRoot(double d){
		return evaluate(d) == 0;
	}
	Polynomial multiply(Polynomial p){
		int len1 = pc.length, len2 = p.pc.length;
		Polynomial ret = new Polynomial();
		for(int i = 0; i < len1; i++){
			double[] pp = new double[len2];
			int[] ww =  new int[len2];
			for(int j = 0; j < len2; j++){
				pp[j] = pc[i] * p.pc[j];
				ww[j] = pw[i] + p.pw[j];
			}
			Polynomial added = new Polynomial(pp, ww);
			ret = ret.add(added);
		}
		return ret;
	}
	void saveToFile(String s){
		String o = "";
		for(int i = 0; i < pc.length; i++){
			if(i != 0 && pc[i] > 0){
				o += "+";
			}
			o += Double.toString(pc[i]);
			if(pw[i] != 0){
				o += "x";
				o += Integer.toString(pw[i]);
			}
		}
		Path cur = Paths.get(s);
		String ss = cur.toAbsolutePath().toString();
		Path p = Paths.get(ss);
		try{
			Files.writeString(p, o, StandardCharsets.UTF_8);
		}
		catch(IOException e){
            e.printStackTrace();
        }
	}
}