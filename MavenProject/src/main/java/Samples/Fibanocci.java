package Samples;

public class Fibanocci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int firstnum=0,secondnum=1,r,n=8;
		System.out.println(firstnum);
		System.out.println(secondnum);
		for (int i = 0; i <n ; i++) {
	    r=firstnum+secondnum;
		firstnum=secondnum;
		secondnum=r;
		System.out.println(r);
			
		}


	}

}
