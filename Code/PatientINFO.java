

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class PatientINFO extends JFrame {

	private JPanel contentPane;
	private JTextField age;
	private JTextField address;
	private JTextField fname;
	private JTextField mname;
	private JTextField lname;
	private JTextField patientid;
	private JComboBox combogender;
	private JTextField textField;
	long idglobal;
	String wherestring="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientINFO frame = new PatientINFO();
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
	public PatientINFO() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 491);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPatientsInformation = new JLabel("Patient's Information");
		lblPatientsInformation.setFont(new Font("Baskerville Old Face", Font.BOLD, 15));
		lblPatientsInformation.setBounds(34, 11, 250, 36);
		contentPane.add(lblPatientsInformation);

		JLabel lblName = new JLabel("First Name:");
		lblName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblName.setBounds(34, 182, 72, 14);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblAge.setBounds(34, 271, 46, 14);
		contentPane.add(lblAge);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblAddress.setBounds(34, 298, 46, 14);
		contentPane.add(lblAddress);

		JLabel lblSex = new JLabel("Gender:");
		lblSex.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblSex.setBounds(65, 182, 46, -10);
		contentPane.add(lblSex);

		age = new JTextField();
		age.setBounds(141, 267, 142, 20);
		contentPane.add(age);
		age.setColumns(10);

		address = new JTextField();
		address.setBounds(141, 295, 142, 20);
		contentPane.add(address);
		address.setColumns(10);

		fname = new JTextField();
		fname.setBounds(141, 181, 142, 20);
		contentPane.add(fname);
		fname.setColumns(10);

		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblMiddleName.setBounds(34, 213, 86, 14);
		contentPane.add(lblMiddleName);

		mname = new JTextField();
		mname.setBounds(141, 209, 142, 20);
		contentPane.add(mname);
		mname.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblLastName.setBounds(34, 244, 79, 14);
		contentPane.add(lblLastName);

		lname = new JTextField();
		lname.setBounds(142, 240, 142, 20);
		contentPane.add(lname);
		lname.setColumns(10);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblStatus.setBounds(34, 353, 46, 14);
		contentPane.add(lblStatus);

		final JComboBox combostatus = new JComboBox();
		combostatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		combostatus.setModel(
				new DefaultComboBoxModel(new String[] {"Single", "Married", "Widow", "Divorced"}));
		combostatus.setSelectedIndex(2);
		combostatus.setBounds(141, 349, 142, 20);
		contentPane.add(combostatus);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			private Connection con;

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
	    		
				String query = "INSERT into info (fname, mname, lname, age, address,patientid,status,gender) VALUES (?,?,?,?,?,?,?,?)";

				Statement statement2;

				try {
					statement2 = con.createStatement();
					String qry2 = "SELECT * FROM info WHERE patientid = '" + patientid.getText() + "' ";
					ResultSet rs = statement2.executeQuery(qry2);
					int count = 0;
					while (rs.next()) {
						count++;
					
					}
					if (count == 0) {

						if (!fname.getText().equals("") && !age.getText().equals("") && !address.getText().equals("")
								&& !mname.getText().equals("") && !lname.getText().equals("")
								&& !patientid.getText().equals("")) {
							try {
								Statement statement = con.createStatement();

								PreparedStatement preparedStmt = con.prepareStatement(query);
								preparedStmt.setString(1, fname.getText());
								preparedStmt.setString(2, mname.getText());
								preparedStmt.setString(3, lname.getText());
								preparedStmt.setString(4, age.getText());
								preparedStmt.setString(5, address.getText());
								preparedStmt.setString(6, patientid.getText());
								preparedStmt.setString(7, combostatus.getSelectedItem().toString());
								preparedStmt.setString(8, combogender.getSelectedItem().toString());
								preparedStmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "Patient Added", "Title",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						else {

							JOptionPane.showMessageDialog(null, "Fields should not be empty.", "Title",
									JOptionPane.ERROR_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Patient ID already exist!", "Title",
								JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(44, 404, 89, 23);
		contentPane.add(btnSave);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.addActionListener(new ActionListener() {
			private Connection con;
			private String query;

			public void actionPerformed(ActionEvent arg0) {

					try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		con = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    		e.printStackTrace();}
				query = "UPDATE info SET fname = ?, mname = ?, lname = ?,age = ?, address = ?, patientid =?,  status =?, gender=? WHERE patientid = '"+ wherestring +"'";
				
				if ( !fname.getText().equals("") && !mname.getText().equals("") && !lname.getText().equals("") && !age.getText().equals("") && !address.getText().equals("") && !patientid.getText().equals(""))
				{
					try {
					PreparedStatement 	preparedStmt = con.prepareStatement(query);
			
					
					preparedStmt.setString(1, fname.getText());
					preparedStmt.setString(2, mname.getText());
					preparedStmt.setString(3, lname.getText());
					preparedStmt.setString(4, age.getText());
					preparedStmt.setString(5, address.getText());
					preparedStmt.setString(6, patientid.getText());
					preparedStmt.setString(7, combostatus.getSelectedItem().toString());
					preparedStmt.setString(8, combogender.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "updated", "Title",
							JOptionPane.INFORMATION_MESSAGE);
					preparedStmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else {
					JOptionPane.showMessageDialog(null, "Empty Fields", "Title",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
				
			}
		});
		btnUpdate.setBounds(140, 404, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.addActionListener(new ActionListener() {
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
				String query = "DELETE FROM info WHERE patientid = '"+ wherestring +"'";
				try {
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted", "Title",
							JOptionPane.INFORMATION_MESSAGE);

					fname.setText("");
					age.setText("");
					address.setText("");
					mname.setText("");
					lname.setText("");
					patientid.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setBounds(239, 404, 89, 23);
		contentPane.add(btnDelete);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			private Connection con;


			public void actionPerformed(ActionEvent arg0) {
				

					try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		con = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    		e.printStackTrace();}
				
				Statement statement2;
				try {
					statement2 = con.createStatement();
					String qry2 = "SELECT * FROM info WHERE patientid = '" + textField.getText() + "' ";
					ResultSet rs = statement2.executeQuery(qry2);
					while (rs.next()) {
						
						wherestring = rs.getString("patientid");
							patientid.setText(wherestring);
						fname.setText(rs.getString("fname"));
						age.setText(rs.getString("age"));
						address.setText(rs.getString("address"));
						mname.setText(rs.getString("mname"));
						lname.setText(rs.getString("lname"));
					
						combogender.setSelectedItem(rs.getString("gender"));
						combostatus.setSelectedItem(rs.getString("status"));
						idglobal = rs.getLong("ID");
				
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(34, 87, 89, 23);
		contentPane.add(btnSearch);

		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Mainframe Mainframe = new Mainframe();
				Mainframe.setVisible(true);
				
			}
		});
		btnBack.setBounds(338, 404, 89, 23);
		contentPane.add(btnBack);

		JLabel lblNewLabel = new JLabel("Patient ID:");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel.setBounds(34, 157, 89, 17);
		contentPane.add(lblNewLabel);

		patientid = new JTextField();
		patientid.setBounds(141, 150, 143, 20);
		contentPane.add(patientid);
		patientid.setColumns(10);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(34, 323, 46, 14);
		contentPane.add(lblGender);

		combogender = new JComboBox();
		combogender.setFont(new Font("Times New Roman", Font.BOLD, 11));
		combogender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		combogender.setSelectedIndex(1);
		combogender.setBounds(141, 323, 142, 20);
		contentPane.add(combogender);

		textField = new JTextField();
		textField.setBounds(34, 58, 176, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
