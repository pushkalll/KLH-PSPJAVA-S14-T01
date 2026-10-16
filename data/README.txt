DATA FOLDER

No external data files are needed. The program does not read or write any files.
The account details are fixed inside main() of src/ATM.java:

  Name: Pushkal
  Account number: 1234567890 (shown as XXXXXX7890)
  PIN: 1234
  Opening balance: 10000.0
  Minimum balance: 500

Files here:
  sample_input_*.txt  Ready-made inputs for 5 test scenarios
  test_cases.csv      18 test cases with expected and actual results

To replay a scenario:
  java -cp out PSPJProject.ATM < data/sample_input_01_normal_transactions.txt
