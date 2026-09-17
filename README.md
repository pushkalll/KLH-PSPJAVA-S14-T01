# KLH-PSPJAVA-S14-T01
**Project Title:** ATM-Simulator

**Team No:** KLH/PSPJAVA/S14/T18

**Team Member Names with ID Numbers:**

1. 2620030009 - Aaryan Dash
2. 2620030176 - Pushkal Sushanth

**Supervisor's Name:** Dr. Madhavi Karumudi

**Abstract:** The ATM Simulator is a console-based Java application developed to simulate basic banking operations through an Automated Teller Machine. The system authenticates the user using a PIN and, after successful verification, provides options to check the account balance, deposit money, withdraw money, or exit. The program validates transaction amounts and prevents withdrawals when the requested amount exceeds the available balance. It displays appropriate messages for successful and invalid transactions. The project uses core Java concepts including classes, objects, constructors, methods, encapsulation, conditional statements, loops, switch-case statements, arithmetic operators, and console input using the Scanner class. An Account class stores and manages the customer's name, PIN, and balance, while separate methods perform the banking operations. The project demonstrates how fundamental Java programming concepts can be combined to build a simple interactive, menu-driven ATM application.

**Project Structure:**

ATM-Simulator (under this folder)
|— README.md
|— src
|   └— ATM.java
|— docs
|— data
|— results
|— reports

**Execution Instructions:**

> javac -d out src/ATM.java
> java -cp out org.example.ATM
