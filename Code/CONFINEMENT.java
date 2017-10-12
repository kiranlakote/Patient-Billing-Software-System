

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CONFINEMENT extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JTextField doctor;
	private JTextField illness;
	private JTextField name;
	private JTextField qunatity;
	protected long idglobal;
	private static JComboBox medecine;
	static Connection con6;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CONFINEMENT frame = new CONFINEMENT();
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
	public CONFINEMENT() {
		addWindowListener(new WindowAdapter() {
		
			public void windowOpened(WindowEvent arg0) {
				DefaultComboBoxModel medecinemodel = (DefaultComboBoxModel) medecine.getModel() ;
				medecinemodel.removeAllElements();
							Statement statement23;
							
							try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		con6 = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    		}
							try {
								
								statement23 = con6.createStatement();
								String qry = "SELECT * FROM mede";
								
								ResultSet rs = statement23.executeQuery(qry);
								int count = 0;

								while (rs.next()) {
									count++;
									medecinemodel.addElement(rs.getString("mede"));
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

					
					
		setBounds(100, 100, 450, 491);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPatientsConfinement = new JLabel("PATIENT'S CONFINEMENT");
		lblPatientsConfinement.setFont(new Font("Lucida Console", Font.BOLD, 15));
		lblPatientsConfinement.setBounds(46, 45, 224, 20);
		contentPane.add(lblPatientsConfinement);

		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblDoctor.setBounds(144, 231, 68, 14);
		contentPane.add(lblDoctor);

		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblRoom.setBounds(144, 200, 68, 20);
		contentPane.add(lblRoom);

		JLabel lblMedicine = new JLabel("Medicine:");
		lblMedicine.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblMedicine.setBounds(144, 290, 68, 20);
		contentPane.add(lblMedicine);

		search = new JTextField();
		search.setBounds(243, 96, 115, 20);
		contentPane.add(search);
		search.setColumns(10);

		doctor = new JTextField();
		doctor.setBounds(243, 228, 115, 20);
		contentPane.add(doctor);
		doctor.setColumns(10);

		JLabel lblIllness = new JLabel("Illness:");
		lblIllness.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblIllness.setBounds(144, 256, 84, 26);
		contentPane.add(lblIllness);

		illness = new JTextField();
		illness.setBounds(243, 259, 115, 20);
		contentPane.add(illness);
		illness.setColumns(10);

		JButton btnAdmit = new JButton("ADMIT");
		btnAdmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdmit.addActionListener(new ActionListener() {
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
	    		}
				String query = "INSERT into confine (patienid, room, doctor, illness, medecine,quantity) VALUES (?,?,?,?,?,?)";

				if (!doctor.getText().equals("") && !illness.getText().equals("") && !qunatity.getText().equals("")) {
					try {
						Statement statement = con.createStatement();

						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString(1, name.getText());
						preparedStmt.setString(2, comboBox.getSelectedItem().toString());
						preparedStmt.setString(3, doctor.getText());

						preparedStmt.setString(4, illness.getText());
						preparedStmt.setString(5, medecine.getSelectedItem().toString());
						preparedStmt.setString(6, qunatity.getText());

						preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Patient Added", "Title", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				else {

					JOptionPane.showMessageDialog(null, "Fields should not be empty.", "Title",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnAdmit.setBounds(181, 372, 89, 23);
		contentPane.add(btnAdmit);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			private Connection con;
			private Connection con33;

			public void actionPerformed(ActionEvent arg0) {

		try{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    		con = DriverManager.getConnection("jdbc:odbc:patient");
	    	}catch(ClassNotFoundException e){
	    		System.err.println("Failed to Load Driver");
	    		e.printStackTrace();
	    	}catch (SQLException e){
	    		System.err.println("Unable to Connect");
	    	}

				Statement statement2;
				try {
					statement2 = con.createStatement();
					String qry2 = "SELECT * FROM info WHERE patientid = '" + search.getText() + "' ";
					ResultSet rs = statement2.executeQuery(qry2);
					int count = 0;
					while (rs.next()) {
						count++;
						name.setText(rs.getString("fname"));
						idglobal = rs.getLong("ID");

					}
					if (count == 0) {

						JOptionPane.showMessageDialog(null, "Patient Not Found!", "Title",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				///search add medice
				
				DefaultComboBoxModel medecinemodel = (DefaultComboBoxModel) medecine.getModel() ;
				medecinemodel.removeAllElements();
							Statement statement23;
							try {
								statement23 = con.createStatement();
								String qry = "SELECT * FROM mede";
								
								ResultSet rs = statement23.executeQuery(qry);
								int count = 0;

								while (rs.next()) {
									count++;
									medecinemodel.addElement(rs.getString("mede"));
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				
				

			}
		});
		btnNewButton.setBounds(243, 127, 115, 23);
		contentPane.add(btnNewButton);

		JLabel lblSearchPatientId = new JLabel("Search Patient ID:");
		lblSearchPatientId.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblSearchPatientId.setBounds(83, 99, 115, 14);
		contentPane.add(lblSearchPatientId);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		comboBox.setBounds(243, 194, 115, 20);
		contentPane.add(comboBox);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(243, 163, 115, 20);
		contentPane.add(name);

		JLabel lblPatientId = new JLabel("Patient Name:");
		lblPatientId.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblPatientId.setBounds(144, 165, 89, 20);
		contentPane.add(lblPatientId);

		medecine = new JComboBox();
		medecine.setFont(new Font("Times New Roman", Font.BOLD, 11));
		medecine.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "Paracetamol", "Neozep", "Ambroxole", "Amoxicilin"}));
		medecine.setBounds(243, 290, 115, 20);
		contentPane.add(medecine);

		qunatity = new JTextField();
		qunatity.setBounds(243, 321, 115, 20);
		contentPane.add(qunatity);
		qunatity.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		lblQuantity.setBounds(144, 322, 68, 20);
		contentPane.add(lblQuantity);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(291, 372, 89, 23);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Mainframe Mainframe = new Mainframe();
				Mainframe.setVisible(true);
			}
		});
		
		contentPane.add(btnBack);
	}

}
