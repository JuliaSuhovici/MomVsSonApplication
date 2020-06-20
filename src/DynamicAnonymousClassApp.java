
public class DynamicAnonymousClassApp {

	public static void main(String[] args) {
		/*CanSpin p = */
		new CanSpin() {
			@Override
			public void rotate() {
				System.out.println("Turning!!!");
			}
		}.rotate();
	}

}

interface CanSpin{
	public void rotate();
}

