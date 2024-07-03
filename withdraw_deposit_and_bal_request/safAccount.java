import java.util.Scanner;

public class safAccount {

    // USER INFORMATION
    private double balance;
    private String USER_NAME = "Justine Muthuri";
    private String USER_PASSWORD = "1234";
    private String MOBILE_NUMBER = "0123456789";
    private String Id_NUMBER = "12345678";

    // AGENT INFORMATION
    private String AGENT_PASSWORD = "1111";
    private String STORE_NUMBER = "12345";
    private String AGENT_NUMBER = "54321";
    private String AGENT_NAME = "tuko pamoja";


    private Scanner prompt_user = new Scanner(System.in);
    
    public safAccount() {
        balance = 100.0;
    }

    // METHOD FOR DIPOSITING TO ACCOUNT========================================================================================
    public void deposit(String[] args) {

        System.out.print("Enter mobile number: ");
        String mobile_number = prompt_user.nextLine();

        System.out.print("Enter id number: ");
        String id_number = prompt_user.nextLine();


        System.out.print("Enter amount to deposit: ");
        String deposit = prompt_user.nextLine();
        double AMOUNT = Double.parseDouble(deposit);
        double amount = AMOUNT;


        System.out.print("Enter pin: ");
        String agent_password = prompt_user.nextLine();

        
        if (amount > 0 && amount < 399_999 && agent_password.equals(AGENT_PASSWORD) && id_number.equals(Id_NUMBER) && mobile_number.equals(MOBILE_NUMBER)) {

            if(amount < 50) {
                System.out.print("Amount must be Sh.50 and above, please try higher amount.");
            }
            else {
                System.out.println("Please confirm deposit of Sh." + amount + " to " + USER_NAME + ", type 1 to confirm or any other key to decline:");
                String agent_confirm_answer = prompt_user.nextLine();
                String yes_answer = "1";

                if (agent_confirm_answer.equals(yes_answer)) {
                    balance += amount;
                    System.out.println("Deposit of Sh." + amount + " successful. New M-pesa balance is Sh." + balance + ". The amount you can transact within the day is 499,999.00.");
                }
                else {
                    System.out.print("Dear customer, you have cancelled deposit of " + amount + " to " + USER_NAME + ". Cancelling too many transactions will make you unable to confirm other users names for a while.");
                }
            }
            
        } 
        else if (amount > 399_999) {
            System.out.println("Amount is too high to deposit.Please try a lower amount.");

        }
        else if (agent_password != AGENT_PASSWORD) {
            System.out.println("Invalid password. You have 2 remaining trials to input correct password. Inputing more than three times you will be blocked.");
        }
        else {
            System.out.println("Invalid credentials, please input properly.");
        }
        
    }

    // METHOD FOR WITHDRAWING FROM ACCOUNT====================================================================================
    public void withdraw(String[] args) {
        
        System.out.print("Enter agent number: ");
        String agent_number = prompt_user.nextLine();

        System.out.print("Enter store number: ");
        String store_number = prompt_user.nextLine();


        System.out.print("Enter amount to withdraw: ");
        String withdraw = prompt_user.nextLine();
        double AMOUNT = Double.parseDouble(withdraw);
        double amount = AMOUNT;

        System.out.print("Enter pin: ");
        String user_password = prompt_user.nextLine();




        if (amount > 50 && amount <= balance && agent_number.equals(AGENT_NUMBER) && store_number.equals(STORE_NUMBER) && user_password.equals(USER_PASSWORD)) {


            balance -= amount;
            System.out.println("Withdrawal of Sh." + amount + " from " + AGENT_NAME + " was successful, your remaining M-pesa balance is Sh." + balance + ". The maximum amount you can transact during the day is 399,999.0.");
        } 
        else if (amount > balance) {
            System.out.println("You have insufficient funds to withdraw Sh." + amount + ", your M-pesa balance is " + balance + ".");
        }
        else if (amount < 50.0) {
            System.out.println("Minimum amount to withdraw is Sh.50.0. Please try a higher amount");
        }

        else if (user_password != USER_PASSWORD) {
            System.out.println("Invalid password. You have 2 remaining trials to input correct password. Inputing more than three times you will be blocked.");
        }else {
            System.out.println("Withdrawal amount must be positive.");
        }
        
    }

    // METHOD FOR BALANCE ENQUIRIES==============================================================================================
    public double getBalance() {
        return balance;
    }

    // MAIN METHOD OF PROGRAM====================================================================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        safAccount account = new safAccount();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.deposit(args);
                    break;
                case 2:
                    
                    account.withdraw(args);
                    break;
                case 3:
                    System.out.println("Your M-pesa account balance is Sh." + account.getBalance() + ".");
                    break;
                case 4:
                    System.out.println("Thank you for using M-pesa services.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }
}
