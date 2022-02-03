package Week3.day2;

public class DeskTop implements HardWare, Software {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeskTop DT = new DeskTop();
		DT.hardwareResource();
		DT.softwareResource();
	}

	public void hardwareResource() {
		// TODO Auto-generated method stub
		System.out.println("HardwareResource");
	}

	public void softwareResource() {
		// TODO Auto-generated method stub
		System.out.println("SoftwareResource");
	}

}
