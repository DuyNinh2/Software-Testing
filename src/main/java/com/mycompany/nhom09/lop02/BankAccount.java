/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nhom09.lop02;

/**
 *
 * @author duy
 */
public class BankAccount {

    private double balance;
    private String accountNumber;
    private String accountHolder;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Số dư ban đầu không thể là số âm.");
        } else {
            this.balance = initialBalance;
        }
        if (accountNumber == null || accountNumber == "") {
            throw new IllegalArgumentException("Số tài khoản không được để trống.");
        } else if (accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Số tài khoản không được chỉ chứa khoảng trắng.");
        } else if (!accountNumber.matches("\\d{12}")) {
            throw new IllegalArgumentException("Số tài khoản phải có đúng 12 chữ số.");
        } else {
            this.accountNumber = accountNumber;
        }
        if (accountHolder == null || accountHolder == "") {
            throw new IllegalArgumentException("Tên chủ tài khoản không được để trống.");
        } else if (accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chủ tài khoản không được chỉ chứa khoảng trắng.");
        } else if (!accountHolder.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Tên chủ tài khoản không hợp lệ. Nó chỉ được phép chứa chữ cái và khoảng trắng.");
        } else {
            this.accountHolder = accountHolder.trim();
        }

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber == "") {
            throw new IllegalArgumentException("Số tài khoản không được để trống.");
        } else if (accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Số tài khoản không được chỉ chứa khoảng trắng.");
        } else if (!accountNumber.matches("\\d{12}")) {
            throw new IllegalArgumentException("Số tài khoản phải có đúng 12 chữ số.");
        } else {
            this.accountNumber = accountNumber;
        }
    }

    public String getAccountHolder() {
        return accountHolder.trim();
    }

    public void setAccountHolder(String accountHolder) {
        if (accountHolder == null || accountHolder == "") {
            throw new IllegalArgumentException("Tên chủ tài khoản không được để trống.");
        } else if (accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chủ tài khoản không được chỉ chứa khoảng trắng.");
        } else if (!accountHolder.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Tên chủ tài khoản không hợp lệ. Nó chỉ được phép chứa chữ cái và khoảng trắng.");
        } else {
            this.accountHolder = accountHolder.trim();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Số dư không thể là số âm.");
        }
        this.balance = balance;
    }

    public double deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Số tiền gửi phải là số dương.");
        } else if (amount == 0) {
            throw new IllegalArgumentException("Số tiền gửi phải khác 0.");
        }
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền rút phải là số dương.");
        } else if (amount > balance) {
            throw new Exception("Số dư không đủ.");
        }
        balance -= amount;
        return balance;
    }

    public void printAccountSummary() {
        System.out.println("Số tài khoản: " + accountNumber);
        System.out.println("Chủ tài khoản: " + accountHolder);
        System.out.println("Số dư: " + balance);
    }

    public boolean transfer(BankAccount recipient, double amount) {

        if (recipient != null && amount > 0 && amount <= balance) {
            this.balance -= amount;
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}
