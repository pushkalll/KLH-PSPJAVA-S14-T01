package org.example;

import java.util.Scanner;

class Account {
    private String name;
    private int pin;
    private double balance;

    Account(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    boolean verifyPin(int enteredPin) {
        return pin == enteredPin;
    }

    void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("₹" + amount + " deposited successfully.");
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            System.out.println("Current Balance: " + balance);
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            System.out.println("Current Balance: " + balance);
        }
    }

    void showName() {
        System.out.println("Welcome, " + name + "!");
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account account = new Account("Pushkal", 1234, 10000);

        System.out.println("== ATM MACHINE ==");

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (!account.verifyPin(enteredPin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        account.showName();

        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}
