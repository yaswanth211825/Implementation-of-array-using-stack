import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayGuiApp extends JFrame {

    private int[] array = new int[10]; // Assuming a fixed-size array of length 10
    private int size = 0; // Current number of elements in the array

    private JTextArea outputTextArea;

    public ArrayGuiApp() {
        super("Array-Based Data Structure GUI Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Create components
        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");

        // Set layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertElement();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteElement();
            }
        });
    }

    private void insertElement() {
        if (size == array.length) {
            JOptionPane.showMessageDialog(this, "Array is full.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String inputValue = JOptionPane.showInputDialog("Enter an integer to insert at the end:");
        try {
            int value = Integer.parseInt(inputValue);
            array[size] = value;
            size++;
            updateOutput();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteElement() {
        if (size == 0) {
            JOptionPane.showMessageDialog(this, "Array is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String inputValue = JOptionPane.showInputDialog("Enter the index of the element to delete:");
        try {
            int index = Integer.parseInt(inputValue);
            if (index < 0 || index >= size) {
                JOptionPane.showMessageDialog(this, "Invalid index. Please enter an index between 0 and " + (size - 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }

            size--;
            updateOutput();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOutput() {
        StringBuilder output = new StringBuilder("Array: ");
        for (int i = 0; i < size; i++) {
            output.append(array[i]).append(" ");
        }
        outputTextArea.setText(output.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArrayGuiApp().setVisible(true);
            }
        });
    }
}
