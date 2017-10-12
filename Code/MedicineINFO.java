

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MedicineINFO extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineINFO frame = new MedicineINFO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MedicineINFO() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medicine Information");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblNewLabel.setBounds(41, 11, 167, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblMedicineItems = new JLabel("Medicine Items:");
		lblMedicineItems.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblMedicineItems.setBounds(72, 77, 113, 26);
		contentPane.add(lblMedicineItems);
		
		textField = new JTextField();
		textField.setBounds(203, 80, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblMedicineName = new JLabel("Medicine Name:");
		lblMedicineName.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblMedicineName.setBounds(72, 116, 113, 14);
		contentPane.add(lblMedicineName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 113, 118, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblPrice.setBounds(119, 161, 46, 14);
		contentPane.add(lblPrice);
		
		textField_2 = new JTextField();
		textField_2.setBounds(203, 158, 118, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAddMedecine = new JButton("Add Medicine");
		btnAddMedecine.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddMedecine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					Connection con = null;
					try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		con = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    		e.printStackTrace();}

				String query = "INSERT into mede (item, mede, price) VALUES (?,?,?)";

				Statement statement2;

				PreparedStatement preparedStmt;
				try {
					preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, textField.getText());
					preparedStmt.setString(2, textField_1.getText());
					preparedStmt.setString(3, textField_2.getText());
					preparedStmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Medicine Saved", "Title",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAddMedecine.setBounds(129, 195, 118, 23);
		contentPane.add(btnAddMedecine);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
				Mainframe Mainframe = new Mainframe();
				Mainframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(281, 195, 89, 23);
		contentPane.add(btnNewButton);
	}

}
