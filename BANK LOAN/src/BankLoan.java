
public class BankLoan {

	public static void main(String[] args) {
		int accountNumber = 1001;
		int accountBalance = 250000;
		int salary = 40000;
		String loanType = "Car";
		int eligibleLoanAmount = 0;
		int loanAmountExpected = 30000;
		int bankEMI = 0;
		int customerEmiExpected = 30;
		
		if(accountNumber > 999 && accountNumber < 2000) {
			if(accountBalance >= 100000) 
				if(salary > 25000 && loanType == "Car") {
					eligibleLoanAmount = 50000;
					bankEMI = 36;
					
		if(eligibleLoanAmount <= bankEMI && customerEmiExpected <= bankEMI)
				System.out.println(accountNumber);
				System.out.println(eligibleLoanAmount);
				System.out.println(bankEMI);
				System.out.println(loanAmountExpected);
				System.out.println(customerEmiExpected);
		}
	}
		else if (salary > 50000 && (loanType == "Car" || loanType == "House")){
			eligibleLoanAmount = 6000000;
			bankEMI = 60;
	}
		if(loanAmountExpected <= eligibleLoanAmount && customerEmiExpected <= bankEMI) {
			System.out.println(accountNumber);
			System.out.println(eligibleLoanAmount);
			System.out.println(bankEMI);
			System.out.println(loanAmountExpected);
			System.out.println(customerEmiExpected);
	}
	

		else if(salary > 75000 && (loanType == "Car" || loanType == "House" || loanType == "Business")) {
			eligibleLoanAmount = 7500000;
			bankEMI = 84;
			System.out.println(accountNumber);
			System.out.println(eligibleLoanAmount);
			System.out.println(bankEMI);
			System.out.println(loanAmountExpected);
			System.out.println(customerEmiExpected);
		}
	}
}

	


	
	
	
