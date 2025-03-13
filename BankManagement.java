import java.util.ArrayList;
import java.util.List;

// BankAccount class (Represents an account linked to a bank and customer)
class BankAccount {
    private static int accountCounter = 1001;  // Unique account number generator
    private int accountNumber;
    private double balance;
    private Bank bank;

    public BankAccount(Bank bank, double initialDeposit) {
        this.accountNumber = accountCounter++;
        this.bank = bank;
        this.balance = initialDeposit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " into account " + accountNumber);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance in account " + accountNumber);
            return false;
        }
        balance -= amount;
        System.out.println("Withdrew ₹" + amount + " from account " + accountNumber);
        return true;
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber + " | Bank: " + bank.getBankName() + " | Balance: ₹" + balance);
    }
}

// Bank class (Association with Customers)
class Bank {
    private String bankName;
    private List<BankAccount> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public BankAccount openAccount(Customer customer, double initialDeposit) {
        BankAccount newAccount = new BankAccount(this, initialDeposit);
        accounts.add(newAccount);
        customer.addAccount(newAccount);
        System.out.println("Account " + newAccount.getAccountNumber() + " opened for " + customer.getName() + " in " + bankName);
        return newAccount;
    }
}

// Customer class (Associated with multiple bank accounts)
class Customer {
    private String name;
    private List<BankAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void viewBalance() {
        System.out.println("\n" + name + "'s Bank Accounts:");
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                accounts.get(i).displayAccountDetails();
            }
        }
    }
}

// Main class to test the implementation
public class BankManagement {
    public static void main(String[] args) {
        // Creating banks
        Bank sbi = new Bank("State Bank of India");
        Bank hdfc = new Bank("HDFC Bank");

        // Creating customers
        Customer customer1 = new Customer("Rahul Sharma");
        Customer customer2 = new Customer("Priya Verma");

        // Opening accounts
        BankAccount acc1 = sbi.openAccount(customer1, 5000);
        BankAccount acc2 = hdfc.openAccount(customer1, 10000);
        BankAccount acc3 = sbi.openAccount(customer2, 7000);

        // Viewing balances
        customer1.viewBalance();
        customer2.viewBalance();

        // Performing transactions
        acc1.deposit(2000);
        acc2.withdraw(3000);

        // Viewing updated balances
        customer1.viewBalance();
    }
}
