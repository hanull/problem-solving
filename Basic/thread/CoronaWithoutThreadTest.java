package Basic.thread;

public class CoronaWithoutThreadTest {
	
	public static void main(String[] args) {
		for( int t=0; t<1000; t++ ) {

			System.out.println("#" + t + " is Started");
			for( int i=0; i< 10000; i++ ) {
				int j = i*100;
			}
			System.out.println(t);

		}
	}
}