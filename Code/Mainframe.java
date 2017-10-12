

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.EventQueue;



public class Mainframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe frame = new Mainframe();
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
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPatientsInfo = new JButton("Patient's Info:");
		btnPatientsInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPatientsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatientINFO PatientINFO = new PatientINFO();
				PatientINFO.setVisible(true);
			}
		});
		btnPatientsInfo.setBounds(130, 36, 165, 23);
		contentPane.add(btnPatientsInfo);
		
		JButton btnNewButton = new JButton("Patient's Confinement");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CONFINEMENT CONFINEMENT = new CONFINEMENT();
				CONFINEMENT.setVisible(true);
			}
		});
		btnNewButton.setBounds(130, 70, 165, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Billing/Discharge");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Billing Billing =  new Billing();
						Billing.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(130, 136, 165, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login Login = new Login();
				Login.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(130, 183, 165, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Medicine");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicineINFO MedicineINFO = new MedicineINFO();
				MedicineINFO.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(130, 104, 165, 23);
		contentPane.add(btnNewButton_3);
	}

}
