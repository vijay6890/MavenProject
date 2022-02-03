package Week3.Day1;

public class Students {

	public void getStudentInfo(int id) {

		System.out.println(id);

	}

	public void getStudentInfo(int id, String Name) {

		System.out.println(id);
		System.out.println(Name);

	}

	public void getStudentInfo(String Email, long PhoneNumber) {

		System.out.println(Email);
		System.out.println(PhoneNumber);

	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Students stud=new Students(); 
		stud.getStudentInfo(3347);
		stud.getStudentInfo(3347, "Veeravel");
		stud.getStudentInfo("veeravelvl@gmail.com", 9789725);

	}

}
