public class ApplicationInterface{
	public static void main(String[] args) {
		try {
		Thread client = new Thread(new Client());
		Thread server = new Thread(new Server());
		server.setPriority(6);
		server.start();
		client.start();
		
		//make main thread wait for client to finish executing. 
		client.join();
		//check if client is alive after main thread continues executing. It should not be if execution state is correct.
		if(!client.isAlive()) {
			System.exit(0);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}