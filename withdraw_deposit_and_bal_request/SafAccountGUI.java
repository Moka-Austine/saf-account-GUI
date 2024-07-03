
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafAccountGUI extends JFrame {

    private JTextField mobileNumberField, idNumberField, depositAmountField, withdrawAmountField, agentNumberField, storeNumberField;
    private JPasswordField agentPasswordField, userPasswordField;
    private JButton depositButton, withdrawButton, checkBalanceButton;
    private JTextArea outputTextArea;

    private safAccount account;

    public SafAccountGUI() {
        super("M-pesa Account");

        account = new safAccount();

        // Initialize components
        mobileNumberField = new JTextField(20);
        idNumberField = new JTextField(20);
        depositAmountField = new JTextField(20);
        withdrawAmountField = new JTextField(20);
        agentPasswordField = new JPasswordField(20);
        userPasswordField = new JPasswordField(20);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Layout setup
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Adding components to the panel with gridbag layout
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Mobile Number:"), constraints);

        constraints.gridx = 1;
        panel.add(mobileNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("ID Number:"), constraints);

        constraints.gridx = 1;
        panel.add(idNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Deposit Amount:"), constraints);

        constraints.gridx = 1;
        panel.add(depositAmountField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Agent Password:"), constraints);

        constraints.gridx = 1;
        panel.add(agentPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(depositButton, constraints);

        constraints.gridx = 1;
        panel.add(new JLabel("Withdraw Amount:"), constraints);

        constraints.gridx = 2;
        panel.add(withdrawAmountField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("User Password:"), constraints);

        constraints.gridx = 1;
        panel.add(userPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(withdrawButton, constraints);

        constraints.gridx = 1;
        panel.add(checkBalanceButton, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(scrollPane, constraints);

        // Add panel to frame
        add(panel);

        // Setup frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        // Event listeners
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
    }

    private void deposit() {
        String mobileNumber = mobileNumberField.getText();
        String idNumber = idNumberField.getText();
        String depositAmountStr = depositAmountField.getText();
        String agentPassword = new String(agentPasswordField.getPassword());

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

        // Perform deposit operation and update outputTextArea
        // Example usage:
        // account.deposit(mobileNumber, idNumber, depositAmountStr, agentPassword);

        // Update outputTextArea with appropriate messages
        outputTextArea.append("Deposit operation performed.\n");
    }

    private void withdraw() {
        String withdrawAmountStr = withdrawAmountField.getText();
        String agentNumber = agentNumberField.getText();
        String storeNumber = storeNumberField.getText();
        String userPassword = new String(userPasswordField.getPassword());

        

        // Perform withdraw operation and update outputTextArea
        // Example usage:
        // account.withdraw(withdrawAmountStr, agentNumber, storeNumber, userPassword);

        // Update outputTextArea with appropriate messages
        outputTextArea.append("Withdraw operation performed.\n");
    }

    private void checkBalance() {
        // Get balance from account object
        double balance = account.getBalance();

        // Update outputTextArea with current balance
        outputTextArea.append("Current balance: " + balance + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SafAccountGUI();
            }
        });
    }
}
