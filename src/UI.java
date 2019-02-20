import DB.SQLManager;

public class UI {
	
	private SQLManager manager;
	
	
	
	public static void main(String args[]) {
		
		manager=new SQLManager();
		
		manager.connect();
		manager.createTables();
		
		
		
	}
}
