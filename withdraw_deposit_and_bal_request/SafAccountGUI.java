
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafAccountGUI extends JFrame {

    // USER INFORMATION
    private String USER_NAME = "Justine Muthuri";
    private String USER_PASSWORD = "1234";
    private String MOBILE_NUMBER = "0123456789";
    private String Id_NUMBER = "12345678";

    // AGENT INFORMATION
    private String AGENT_PASSWORD = "1111";
    private String STORE_NUMBER = "12345";
    private String AGENT_NUMBER = "54321";
    private String AGENT_NAME = "tuko pamoja";

    private JTextField mobileNumberField, idNumberField, depositAmountField, withdrawAmountField, agentNumberField, storeNumberField;
    private JPasswordField agentPasswordField, userPasswordField;
    private JButton depositButton, withdrawButton, checkBalanceButton;
    private JTextArea outputTextArea;

    private safAccount account;
    double accountBalance = safAccount.balance;

    //?==================================================================
    /*? IMAGE ICON=========================================================================
    private ImageIcon icon;

    public IconPanel(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the icon at a specific location (e.g., top-left corner)
        if (icon != null) {
            int x = 0;
            int y = 0;
            g.drawImage(icon.getImage(), x, y, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Return the size based on the icon size
        if (icon != null) {
            return new Dimension(icon.getIconWidth(), icon.getIconHeight());
        }
        return super.getPreferredSize();
    }
*///?===========================================================================================================

    public SafAccountGUI() {
        super("M-pesa Account");

        account = new safAccount();

        /*  SAFARICOM LOGO ICON
        ImageIcon icon = new ImageIcon("../images/safLogo.png");
        IconPanel iconPanel = new IconPanel(icon);
        */

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
        //?add(iconPanel);

        // Setup frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        // Event listeners
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
                withdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //outputTextArea.setText("");
                checkBalance();
            }
        });
    }

    //? CHECKING IF AMOUNT INPUT IS A NUMBER ====================================================
   
    public static boolean isNotNumber(String input) {
    try {
        Double.parseDouble(input); // Try parsing the input as a double
        return false; // Input is a valid number
    } catch (NumberFormatException e) {
        return true; // Input is not a number
    }
   }
 
    //?==========================================================================================
    private void deposit() {

        //double accountBalance = safAccount.balance;
        String mobileNumber = mobileNumberField.getText();
        String idNumber = idNumberField.getText();
        String depositAmountStr = depositAmountField.getText();
        if (isNotNumber(depositAmountStr)) {
            outputTextArea.setText("");
            outputTextArea.setText("Amount must be a number. Please input again.");
            return;
        }

        double amount = Double.parseDouble(depositAmountStr);
        String agentPassword = new String(agentPasswordField.getPassword());

        if (amount > 0 && amount < 399_999 && agentPassword.equals(AGENT_PASSWORD) && idNumber.equals(Id_NUMBER) && mobileNumber.equals(MOBILE_NUMBER)) {

            if(amount < 50) {
                outputTextArea.append("Amount must be Sh.50 and above, please try higher amount.");
            }
            else {
                int option = JOptionPane.showConfirmDialog(this, "Please confirm deposit of Sh." + amount + " to " + USER_NAME + ".", "Confirmation", JOptionPane.YES_NO_OPTION);
            

                if (option == JOptionPane.YES_OPTION) {
                    accountBalance += amount;
                    outputTextArea.setText("");
                    outputTextArea.append("Deposit of Sh." + amount + " successful. New M-pesa balance is Sh." + accountBalance + ". The amount you can transact within the day is 499,999.00.");
                }
                else {
                    outputTextArea.setText("");
                    outputTextArea.append("Dear customer, you have cancelled deposit of " + amount + " to " + USER_NAME + ". Cancelling too many transactions will make you unable to confirm other users names for a while.");
                }
            }
            
        } 
        else if (amount > 399_999) {
            outputTextArea.setText("");
            outputTextArea.append("Amount is too high to deposit.Please try a lower amount.");

        }
        else if (agentPassword != AGENT_PASSWORD) {
            outputTextArea.setText("");
            outputTextArea.append("Invalid password. You have 2 remaining trials to input correct password. Inputing more than three times you will be blocked.");
        }
        else {
            outputTextArea.setText("");
            outputTextArea.append("Invalid credentials, please input properly.");
        }

        
        //outputTextArea.append("Deposit operation performed.\n");
    }

    private void withdraw() {
        String withdrawAmountStr = withdrawAmountField.getText();
        if (isNotNumber(withdrawAmountStr)) {
            outputTextArea.setText("");
            outputTextArea.setText("Withdraw amount must be a number.");
            return;
        } 

        double withdrawAmount = Double.parseDouble(withdrawAmountStr);

        String agentNumber = agentNumberField.getText();
        String storeNumber = storeNumberField.getText();
        String userPassword = new String(userPasswordField.getPassword());

        

        if (accountBalance > 50 && accountBalance <= withdrawAmount && agentNumber.equals(AGENT_NUMBER) && storeNumber.equals(STORE_NUMBER) && userPassword.equals(USER_PASSWORD)) {


            accountBalance -= withdrawAmount;
            outputTextArea.setText("");
            outputTextArea.append("Withdrawal of Sh." + withdrawAmount + " from " + AGENT_NAME + " was successful, your remaining M-pesa balance is Sh." + accountBalance + ". The maximum amount you can transact during the day is 399,999.0.");
        } 
        else if (withdrawAmount > accountBalance) {
            outputTextArea.setText("");
            outputTextArea.append("You have insufficient funds to withdraw Sh." + withdrawAmount + ", your M-pesa balance is " + accountBalance + ".");
        }
        else if (accountBalance < 50.0) {
            outputTextArea.setText("");
            outputTextArea.append("Minimum amount to withdraw is Sh.50.0. Please try a higher amount");
        }

        else if (userPassword != USER_PASSWORD) {
            outputTextArea.setText("");
            outputTextArea.append("Invalid password. You have 2 remaining trials to input correct password. Inputing more than three times you will be blocked.");
        }else {
            outputTextArea.setText("");
            outputTextArea.append("Withdrawal amount must be positive.");
        }
        //outputTextArea.append("Withdraw operation performed.\n");
    }

    private void checkBalance() {
        // Get balance from account object
        double balance = account.getBalance();

        // Update outputTextArea with current balance
        outputTextArea.setText("");
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
