DOCS FOLDER

Problem statement, design, algorithm, CO mapping, user manual
Classes: Account, SavingsAccount, InsufficientBalanceException, 
ATM
Flow of the PIN check and the menu loop

Short design summary:
  Account                       parent class (name, PIN, balance, last-5 history)
  SavingsAccount                child class, adds the minimum balance of 500
  InsufficientBalanceException  custom checked exception
  ATM                           main(): PIN loop, menu, error handling
