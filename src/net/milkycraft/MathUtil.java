package net.milkycraft;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.toDegrees;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {

	public static double getAtleastProbability(int x, int y, double z) {
		double d = 0D;
		int a = x - y;
		for (int i = 0; i <= a; i++)
			d += (f(x) / (f(y + i) * f(x - (y + i))) * pow(z, y + i) * pow(1 - z, (a) - i));
		return r(d * 100, 1);
	}

	public static int f(double y) {
		int r = 1;
		for (int i = 1; i <= y; ++i)
			r *= i;
		return r;
	}
	
	public static List<int[]> findProducts(int x){
		List<int[]> results = new ArrayList<int[]>();
		for(int a = -x; a < x+1; a++) {
			for(int b = -x; b < x+1; b++) {
				if(a * b == x) {
					int[] zi = new int[2];
					zi[0] = a;
					zi[1] = b;
					results.add(zi);
				}
			}	
		}
		return results;
	}

	public static void pascal(int N) {
	        int[][] pascal  = new int[N+1][];
	        pascal[1] = new int[1 + 2];
	        pascal[1][1] = 1;
	        for (int i = 2; i <= N; i++) {
	            pascal[i] = new int[i + 2];
	            for (int j = 1; j < pascal[i].length - 1; j++)
	                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
	        }
	        for (int i = 1; i <= N; i++) {
	        	System.out.print(s(N-i));
	            for (int j = 1; j < pascal[i].length - 1; j++) {
	                System.out.print(pascal[i][j] + " ");
	            }
	            System.out.println();
	        }
	}
	
	private static String s(int x) {
		String s = "";
		for(int i = 0; i < x; i++){		
			s+=" ";
		}
		return s;
	}

	public static double getArea(double a, double b, double c) {
		return (a + b + c) / 2;
	}

	public static double[] getAngles(double x, double y, double z) {
		return new double[] { ang(y, z, x), ang(z, x, y), ang(x, y, z) };
	}

	public static int fib(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

	public static double[] getAnglesByRatio(double a, double b, double c) {
		double d = 180D / (a + b + c);
		return new double[] { a * d, b * d, c * d };
	}

	public static double ang(double a, double b, double c) {
		double t = (pow(a, 2) + pow(b, 2) - pow(c, 2)) / (2 * a * b);
		if (t >= -1 && t <= 1)
			return r(toDegrees(acos(t)), 2);
		return Double.NaN;
	}

	public static double r(double v, int p) {
		long f = (long) pow(10, p);
		return (double) round(v * f) / f;
	}
}
