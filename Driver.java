import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;


public class Driver {
public static void main(String [] args) {
		// test constructor
		Polynomial P1 = new Polynomial();
		System.out.println(P1.pc.length == 0);
		double f[] ={-1.0, -2.0, 3.0, 4.0, 5.0};
		int g[] = {5, 2, 4, 3, 1};
		Polynomial P2 = new Polynomial(f, g);
		double h[] ={-5.0, -4.0, -3.0, 2.0, 1.0}; 
		int i[] = {1, 3, 4, 2, 5};
		Polynomial P3 = new Polynomial(h, i);
		File fifi = new File("a.txt");
		Polynomial P10 = new Polynomial(fifi);
		System.out.println(P10.pc[0]);
		File fi = new File("empty.txt");
		Polynomial P7 = new Polynomial(fi);
		for(int k = 0; k < P7.pc.length; k++){
			System.out.println(P7.pc[k] + "x" + P7.pw[k]);
		}
		// test add
		Polynomial P5 = P2.add(P3);
		// test multiply
		Polynomial P4 = new Polynomial(h, g);
		Polynomial P6 = P4.multiply(P3);
		for(int j = 0; j < P6.pc.length; j++){
			System.out.println(P6.pc[j] + "x" + P6.pw[j]);
		}
		Polynomial P8 = new Polynomial();
		Polynomial P9 = new Polynomial();
		P8 = P8.add(P9);
		System.out.println(P8.pc.length == 0);
		// test evaluate
		System.out.println(P6.evaluate(1));
		// test hasRoot
		System.out.println(P6.hasRoot(0));
		// test saveToFile
		String s = "test.txt";
		P5.saveToFile(s);
	}
}
