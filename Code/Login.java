

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Sylfaen", Font.BOLD, 13));
		lblUsername.setBounds(83, 74, 89, 21);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(182, 73, 126, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Sylfaen", Font.BOLD, 13));
		lblPassword.setBounds(83, 122, 89, 21);
		contentPane.add(lblPassword);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogIn.addActionListener(new ActionListener() {
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
	    		
				try {
				
					Statement statement = con.createStatement();

					String qry = "SELECT * FROM login WHERE username = '" + textField.getText()
							+ "' AND password = '" + String.valueOf(passwordField.getPassword()) + "' ";
					
					ResultSet rs = statement.executeQuery(qry);
					int count = 0;

					while (rs.next()) {
						count++;
						//or count = count + 1;
					}
					statement.close();
				
					if (count > 0) {
						JOptionPane.showMessageDialog(null, "Welcome to SS Hospital!", "", JOptionPane.INFORMATION_MESSAGE);
						
						Mainframe Mainframe = new Mainframe();
						Mainframe.setVisible(true);
						
						dispose();

					} else if (count == 0) {
						JOptionPane.showMessageDialog(null, "Invalid keyword!", "Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnLogIn.setBounds(104, 189, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(219, 189, 89, 23);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 116, 126, 27);
		contentPane.add(passwordField);
	}
}
