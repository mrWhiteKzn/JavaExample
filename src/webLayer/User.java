package webLayer;

public class User {
	public boolean isValid(String name, String password) {
		if(name.equals("dess") && password.equals("30208320")) {
			return true;
		}
		
		return false;
	}
}
