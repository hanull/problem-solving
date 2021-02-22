package Basic.thread;

public class CoronaThreadTest {

	public static void main(String[] args) {
		for( int t=0; t<1000; t++ ) {
			
//			CoronaRunnable cr = new CoronaRunnable(t);
//			Thread thread = new Thread(cr);
//			thread.start();

			CoronaThread thread = new CoronaThread(t);
			thread.run();

		}
	}
}
	