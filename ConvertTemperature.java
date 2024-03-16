package Projects;
import java.util.Scanner;

public class ConvertTemperature {

	public static void main(String[] args) {
		float celsius, fahrenheit;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter temperature in Fahrenheit: ");
		fahrenheit=sc.nextInt();
		celsius = (fahrenheit - 32)*5/9;
		System.out.println("Celsius = " + celsius);
		

	}

}
