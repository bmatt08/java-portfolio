package Projects;

import java.util.Scanner;

public class GradingSystem {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		byte score;
		String name;
		
		for(int i = 1; i <= 100; i++) {
			System.out.println("Enter your name: ");
			name = sc.next();
			
			System.out.println("Enter your score");
			score = sc.nextByte();
			
			if(score >= 90 && score <= 100) {
				System.out.println("Your Name is: " + name + " Your score is: A");
			}
			else if(score >= 80 && score <= 89) {
				System.out.println("Your name is: " + name + " Your score is: B");
			}
			else if(score >= 70 && score <= 79) {
				System.out.println("Your name is: " + name + " Your score is: C");
			}
			else if(score >= 60 && score <= 69) {
				System.out.println("Your name is: " + name + " Your score is D");
			}
			else {
				System.out.println("Your name is: " + name + " Your score is F");
			}
		}
		

	}

}
