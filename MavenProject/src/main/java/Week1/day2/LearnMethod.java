package Week1.day2;

public class LearnMethod {

	public int Addition() {
		int a = 10;
		int b = 5;
		int c = a + b;
		return (c);

	}

	public void getResult(int a, String s) {
		System.out.println("Integer Value is:" + a);
		System.out.println("String Value is:" + s);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LearnMethod learn = new LearnMethod();
		int Result = learn.Addition();
		learn.getResult(Result, "Text");
		learn.getResult(50, "Hai");

	}

}
