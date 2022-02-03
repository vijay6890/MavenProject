package Week3.Day1;

import org.apache.commons.math3.analysis.function.Divide;

import net.bytebuddy.implementation.bytecode.Multiplication;

public class Calculator {

	
	public void Multiplication(int a,int b)
	
	{
		int mul=a*b;
		System.out.println(mul);
	}
public void Multiplication(int c,double d)
	
	{
		double mul1=c*d;
		System.out.println(mul1);
	}

public void sub(int e,int f) {
	// TODO Auto-generated method stub
	int sub=e-f;
	System.out.println(sub);

}
public void sub(double x,double y) {
	// TODO Auto-generated method stub
	double sub=x-y;
	System.out.println(sub);

}

public void Divide(int m,int n) {
	// TODO Auto-generated method stub
	
	int div=m/n;
	System.out.println(div);
	
	
}

private void Divide(double j,double k) {
	// TODO Auto-generated method stub
	double div=j/k;
	System.out.println(div);

}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculator calc=new Calculator();
		calc.Divide(10, 5);
		calc.Divide(50, 5);
		calc.sub(20, 5);
		calc.sub(25, 10);
		calc.Multiplication(2, 10);
		calc.Multiplication(5, 5);

	}

}
