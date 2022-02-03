package Week1.day2;

import java.util.Scanner;

public class Calculator {
	// int n1,n2,n3;
	public int addition(int num1, int num2, int num3) {

		int add = num1 + num2 + num3;
		return (add);

	}

	public int Subtraction(int num1, int num2) {

		int sub = num1 - num2;
		return (sub);

	}

	public double Multiply(double num1, double num2) {

		double mul = num1 * num2;
		return (mul);

	}

	public float Division(float num1, float num2) {

		float div = num1 / num2;
		return (div);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int a1,a2,a3;
		Calculator calc = new Calculator();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the first number");
		int n1 = sc.nextInt();
		System.out.println("Enter the second number");
		int n2 = sc.nextInt();
		System.out.println("Enter the second number");
		int n3 = sc.nextInt();
		System.out.println("Enter the double number1");
		double n4 = sc.nextFloat();
		System.out.println("Enter the double number2");
		double n5 = sc.nextDouble();
		System.out.println("Enter the float number1");
		float n6 = sc.nextFloat();
		System.out.println("Enter the float number2");
		float n7 = sc.nextFloat();
		int Addition = calc.addition(n1, n2, n3);
		System.out.println("Addition:" + Addition);
		int subtraction = calc.Subtraction(n1, n2);
		System.out.println("Subtraction:" + subtraction);
		double multiplication = calc.Multiply(n4, n5);
		System.out.println("Multiplication:" + multiplication);
		float division = calc.Division(n6, n7);
		System.out.println("Division:" + division);

	}

}
