# 🏦 Banking System - JDBC Project  

This is a **JDBC-based Banking Application** that allows users to **create accounts, deposit and withdraw cash, check account balances, and delete accounts.** The project follows a simple and efficient approach using **Java** and **MySQL** for backend operations.

## 🚀 Features  
- 📌 **Create Account** – Open a new account with a unique account number and initial balance.  
- 💸 **Deposit Money** – Add funds to an existing account.  
- 💳 **Withdraw Cash** – Securely withdraw money from an account, ensuring sufficient balance.  
- 🗑️ **Delete Account** – Remove an account from the system.  
- 📊 **Check Balance** – Retrieve the current account balance.  

## 📂 Database Schema  
```sql
CREATE TABLE bankingApk (
  acNo INT NOT NULL AUTO_INCREMENT,
  acName VARCHAR(25) DEFAULT NULL,
  acBal DOUBLE DEFAULT NULL,
  PRIMARY KEY (acNo)
);
