
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Billing extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField totalpayment;
	private JTextField cash;
	private JTextField roomnumber;
	private JTextField roommprice;
	private JTextField mede;
	private JTextField medecost;
	private JTextField name;
	private JTextField quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing frame = new Billing();
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
	public Billing() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 495);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBilling = new JLabel("BILLING");
		lblBilling.setFont(new Font("GungsuhChe", Font.BOLD, 15));
		lblBilling.setBounds(39, 11, 130, 33);
		contentPane.add(lblBilling);

		JLabel lblCash = new JLabel("Cash:");
		lblCash.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblCash.setBounds(115, 356, 46, 14);
		contentPane.add(lblCash);

		JLabel lblTotalPayment = new JLabel("Total Payment:");
		lblTotalPayment.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblTotalPayment.setBounds(82, 292, 122, 14);
		contentPane.add(lblTotalPayment);

		textField = new JTextField();
		textField.setBounds(217, 51, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		totalpayment = new JTextField();
		totalpayment.setEditable(false);
		totalpayment.setBounds(217, 292, 182, 20);
		contentPane.add(totalpayment);
		totalpayment.setColumns(10);

		cash = new JTextField();
		cash.setBounds(217, 352, 182, 20);
		contentPane.add(cash);
		cash.setColumns(10);

		JLabel lblSearchPatientId = new JLabel("Search Patient ID:");
		lblSearchPatientId.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblSearchPatientId.setBounds(82, 55, 97, 20);
		contentPane.add(lblSearchPatientId);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Connection conr = null;
	try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		conr = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    		e.printStackTrace();
	    	}

				Statement statement;
				try {
					statement = conr.createStatement();
					String qry = "SELECT * FROM info where patientid ='" + textField.getText() + "' ";

					ResultSet rs = statement.executeQuery(qry);

					int count = 0;
					while (rs.next()) {
						count++;
						name.setText(rs.getString("fname"));
					}

					if (count == 0) {
						JOptionPane.showMessageDialog(null, "patient not found Not Found", "Title",
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						
						// 0-
						qry = "SELECT * FROM confine";

						rs = statement.executeQuery(qry);

						while (rs.next()) {
							count++;
							roomnumber.setText(rs.getString("room"));
							quantity.setText(rs.getString("quantity"));
						}
						
						
						qry = "SELECT * FROM mede";

						rs = statement.executeQuery(qry);

						while (rs.next()) {
							count++;
							mede.setText(rs.getString("mede"));
							medecost.setText(rs.getString("price"));
						}
					}

					
					
					//	room
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(217, 81, 182, 23);
		contentPane.add(btnSearch);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
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
	    		e.printStackTrace();
	    	}

				String query = "INSERT into billing (fname, roomnumber, roommprice, mede, medecost,totalpayment) VALUES (?,?,?,?,?,?)";

				Statement statement2;

				PreparedStatement preparedStmt;
				try {
					preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, name.getText());
					preparedStmt.setString(2, roomnumber.getText());
					preparedStmt.setString(3, roommprice.getText());
					preparedStmt.setString(4, mede.getText());
					preparedStmt.setString(5, medecost.getText());
					preparedStmt.setString(6, totalpayment.getText());
					preparedStmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "bill saved", "Title",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(115, 381, 89, 23);
		contentPane.add(btnSave);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(217, 383, 89, 23);
		contentPane.add(btnExit);

		JLabel lblRoomPrice = new JLabel("Room:");
		lblRoomPrice.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblRoomPrice.setBounds(92, 147, 87, 14);
		contentPane.add(lblRoomPrice);

		roomnumber = new JTextField();
		roomnumber.setEditable(false);
		roomnumber.setBounds(217, 144, 182, 20);
		contentPane.add(roomnumber);
		roomnumber.setColumns(10);

		roommprice = new JTextField();
		roommprice.setColumns(10);
		roommprice.setBounds(217, 172, 182, 20);
		contentPane.add(roommprice);

		JLabel label = new JLabel("Room Price:");
		label.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		label.setBounds(92, 175, 87, 14);
		contentPane.add(label);

		JLabel lblMedicine = new JLabel("medicine:");
		lblMedicine.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblMedicine.setBounds(92, 206, 87, 14);
		contentPane.add(lblMedicine);

		mede = new JTextField();
		mede.setEditable(false);
		mede.setColumns(10);
		mede.setBounds(217, 203, 182, 20);
		contentPane.add(mede);

		JLabel lblMedicineCost = new JLabel("medicine cost:");
		lblMedicineCost.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblMedicineCost.setBounds(92, 239, 87, 14);
		contentPane.add(lblMedicineCost);

		medecost = new JTextField();
		medecost.setEditable(false);
		medecost.setColumns(10);
		medecost.setBounds(217, 236, 182, 20);
		contentPane.add(medecost);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(217, 115, 182, 20);
		contentPane.add(name);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblName.setBounds(92, 118, 87, 14);
		contentPane.add(lblName);
		
		JButton btnUpdatePayment = new JButton("Update Payment");
		btnUpdatePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!roommprice.getText().equals("")){
					
				
				int answer = Integer.parseInt(roommprice.getText());
				int answe2r = Integer.parseInt(quantity.getText()) * Integer.parseInt(medecost.getText());
				int answer3 = answer +answe2r;
				System.out.println(answer3);
				totalpayment.setText(Integer.toString(answer3));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "room price should not be empty", "Title",
						JOptionPane.INFORMATION_MESSAGE);
			}
			}
		});
		btnUpdatePayment.setBounds(217, 323, 182, 23);
		contentPane.add(btnUpdatePayment);
		
		quantity = new JTextField();
		quantity.setEditable(false);
		quantity.setColumns(10);
		quantity.setBounds(217, 261, 182, 20);
		contentPane.add(quantity);
		
		JLabel lblMedicineQuantity = new JLabel("medicine quantity");
		lblMedicineQuantity.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblMedicineQuantity.setBounds(92, 264, 122, 14);
		contentPane.add(lblMedicineQuantity);
	}
}
