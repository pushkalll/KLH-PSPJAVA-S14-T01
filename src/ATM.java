package PSPJProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMWithdrawal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Inheritance + polymorphism: Account reference holds a SavingsAccount object
        Account account = new SavingsAccount("Aaryan", "1234567890", 2806, 10000);

        System.out.println("== ATM MACHINE ==");

        // PIN verification with 3 attempts (for loop + exception handling)
        boolean authenticated = false;
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                System.out.print("Enter PIN: ");
                int enteredPin = sc.nextInt();

                if (account.verifyPin(enteredPin)) {
                    authenticated = true;
                    break;
                } else {
                    System.out.println("Incorrect PIN. Attempts left: " + (3 - attempt));
                }
            } catch (InputMismatchException e) {
                System.out.println("PIN must be numeric. Attempts left: " + (3 - attempt));
                sc.next(); 
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Card blocked.");
            sc.close();
            return;
        }

        account.showName();

        int choice = 0;

        do {
            System.out.println("\n ===== MENU ===== ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        account.checkBalance();
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        System.out.print("Add a remark? (yes/no): ");
                        String reply = sc.next();

                        if (reply.equalsIgnoreCase("yes")) {
                            System.out.print("Enter source (e.g. Salary): ");
                            String source = sc.next();
                            account.deposit(depositAmount, source); 
                        } else {
                            account.deposit(depositAmount);
                        }
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount); 
                        break;

                    case 4:
                        account.showMiniStatement();
                        break;

                    case 5:
                        System.out.println("Thank you for using the ATM!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                sc.next(); // clear invalid input
                choice = 0;
            } catch (InsufficientBalanceException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                if (choice != 5) {
                    System.out.println("--------------------");
                }
            }

        } while (choice != 5);

        sc.close();
    }
}

// Custom checked exception
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

// Parent class
class Account {
    private String name;
    private String accountNumber;
    private int pin;
    protected double balance;

    // Array to store the last 5 transactions
    private String[] history = new String[5];
    private int historyCount = 0;

    Account(String name, String accountNumber, int pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    boolean verifyPin(int enteredPin) {
        return pin == enteredPin;
    }

    // Store a transaction in the array (oldest one is dropped when full)
    protected void addHistory(String entry) {
        if (historyCount < history.length) {
            history[historyCount++] = entry;
        } else {
            for (int i = 1; i < history.length; i++) {
                history[i - 1] = history[i];
            }
            history[history.length - 1] = entry;
        }
    }

    // Check balance
    void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Method overloading: same name, different parameters
    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
        addHistory("Deposited " + amount);
        System.out.println(amount + " deposited successfully.");
        System.out.println("Current Balance: " + balance);
    }

    void deposit(double amount, String source) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
        addHistory("Deposited " + amount + " (" + source + ")");
        System.out.println(amount + " deposited successfully from " + source + ".");
        System.out.println("Current Balance: " + balance);
    }

    // Withdraw money
    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Current Balance: " + balance);
        }
        balance -= amount;
        addHistory("Withdrew " + amount);
        System.out.println(amount + " withdrawn successfully.");
        System.out.println("Current Balance: " + balance);
    }

    void showMiniStatement() {
        System.out.println("=== MINI STATEMENT (last 5) ===");
        if (historyCount == 0) {
            System.out.println("No transactions yet.");
            return;
        }
        for (int i = 0; i < historyCount; i++) {
            System.out.println((i + 1) + ". " + history[i]);
        }
    }

    // String methods: toUpperCase(), substring(), length(), String.format()
    void showName() {
        String masked = "XXXXXX" + accountNumber.substring(accountNumber.length() - 4);
        System.out.println("Welcome, " + name.toUpperCase() + "!");
        System.out.println(String.format("Account No: %s", masked));
    }
}

// Inheritance: SavingsAccount IS-A Account
class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500;

    SavingsAccount(String name, String accountNumber, int pin, double balance) {
        super(name, accountNumber, pin, balance);
    }

    // Method overriding: adds a minimum balance rule
    @Override
    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0 && (balance - amount) < MIN_BALANCE) {
            throw new InsufficientBalanceException(
                    "Savings account must maintain a minimum balance of " + MIN_BALANCE);
        }
        super.withdraw(amount); 
    }
}
