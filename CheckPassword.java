import java.util.Scanner;

import static java.lang.System.*;

public class CheckPassword {

	public static void main(String[] args) {
		
		System.out.print("What's the password?");
		
		var keyboard = new Scanner(in);
		String password = keyboard.next();
		
		System.out.println("You typed >>" + password + "<<");
		System.out.println();
		
		if (password == "swordfish") {
			System.out.println("The word you typed is stored in the same place as the real password. You must be a hacker.");
		}
		else {
			System.out.println("The word you typed is not stored in the same place as the real password, but no big deal.");
			
		}
		System.out.println();
		
		if (password.equals("swordfish")) {
			System.out.println("The word you typed has the same characters as the real password. You can use your system.");
		}
		else {
			System.out.println("The word you typed doesn't have the same characters as the real password. You cannot use your system.");
		}
		keyboard.close();
	}

}
