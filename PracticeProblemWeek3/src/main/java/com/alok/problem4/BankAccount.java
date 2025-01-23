package com.alok.problem4;

public class BankAccount {
    private String owner;
    private double balance;
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited $%.2f. New balance: $%.2f.%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f.%n", amount, balance);
        }
    }

    public double checkBalance() {
        System.out.printf("Current balance: $%.2f.%n", balance);
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("John Doe", 100);
        account.checkBalance();
        account.deposit(50);
        account.withdraw(30);
        account.withdraw(150);
    }
}
