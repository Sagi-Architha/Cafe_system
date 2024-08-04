

public class CafeManagementApp extends JFrame {

    private Map<String, Double> menu;

    private JComboBox<String> menuDropdown;
    private JTextField quantityField;
    private JButton addToOrderButton;
    private JTextArea orderTextArea;
    private JLabel totalBillLabel;

    private double totalBill;

    public CafeManagementApp() {
        // Set up the main frame
        setTitle("Cafe Management App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize menu
        menu = new HashMap<>();
        menu.put("Coffee", 2.5);
        menu.put("Tea", 2.0);
        menu.put("Sandwich", 5.0);
        menu.put("Cake", 3.5);
        menu.put("Maggiee", 4.5);

        // Create components
        JLabel menuLabel = new JLabel("Menu:");
        menuDropdown = new JComboBox<>(menu.keySet().toArray(new String[0]));

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder();
            }
        });

        orderTextArea = new JTextArea(10, 30);
        orderTextArea.setEditable(false);

        totalBillLabel = new JLabel("Total Bill: INR0.0");

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(menuLabel);
        inputPanel.add(menuDropdown);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(addToOrderButton);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(totalBillLabel);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(orderTextArea), BorderLayout.CENTER);
        add(totalPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void addToOrder() {
        String selectedItem = (String) menuDropdown.getSelectedItem();
        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
            return;
        }

        double price = menu.get(selectedItem);
        double itemTotal = price * quantity;

        orderTextArea.append(selectedItem + " x " + quantity + " = INR" + itemTotal + "\n");

        totalBill += itemTotal;
        updateTotalBillLabel();

        // Clear input fields
        quantityField.setText("");
        menuDropdown.setSelectedIndex(0);
    }

    private void updateTotalBillLabel() {
        DecimalFormat df = new DecimalFormat("#.00");
        totalBillLabel.setText("Total Bill: INR" + df.format(totalBill));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CafeManagementApp();
            }
        });
    }
}