package rentcar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import rentcar.Entity.Employee;
import rentcar.Interface.Window;
import rentcar.Service.EmployeeService;

public class LoginWindow extends JFrame implements Window {
	
	private JPanel contentPane;
	
	/**
	 * Champs texte pour le mail
	 */
	private JTextField usernameField;
	
	/**
	 * Champs passowrd pour le mot de passe
	 */
	private JPasswordField passwordField;
	
	private JTextField codeField;

	public LoginWindow() {
		
	}
	
	public JPanel builder() {
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		
		JLabel lblConnexion = new JLabel("CONNEXION");
		lblConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexion.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblConnexion.setBounds(451, 108, 376, 56);
		contentPane.add(lblConnexion);
		
		JLabel labelEmail = new JLabel("Pseudo");
		labelEmail.setFont(new Font("SansSerif", Font.BOLD, 22));
		labelEmail.setBounds(451, 189, 152, 21);
		contentPane.add(labelEmail);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		usernameField.setBounds(451, 221, 376, 32);
		contentPane.add(usernameField);
		
		JLabel labelPassword = new JLabel("Mot de passe");
		labelPassword.setFont(new Font("SansSerif", Font.BOLD, 22));
		labelPassword.setBounds(451, 288, 152, 21);
		contentPane.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(451, 320, 376, 32);
		contentPane.add(passwordField);
		
		JButton connectionBtn = new JButton("Se connecter");
		connectionBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		connectionBtn.setBounds(539, 390, 211, 33);
		contentPane.add(connectionBtn, BorderLayout.SOUTH);
		
		// Other method
		
		JLabel lblConnexion2 = new JLabel("PREMIERE CONNEXION");
		lblConnexion2.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexion2.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblConnexion2.setBounds(451, 440, 376, 56);
		contentPane.add(lblConnexion2);
		
		JLabel labelCode = new JLabel("Code");
		labelCode.setFont(new Font("SansSerif", Font.BOLD, 22));
		labelCode.setBounds(451, 500, 152, 21);
		contentPane.add(labelCode);
		
		codeField = new JTextField();
		codeField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		codeField.setBounds(451, 530, 376, 32);
		contentPane.add(codeField);
		
		JButton connectionBtn2 = new JButton("Se connecter");
		connectionBtn2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		connectionBtn2.setBounds(539, 570, 211, 33);
		contentPane.add(connectionBtn2, BorderLayout.SOUTH);
		
		connectionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionTreatment();
			}
		});
		
		connectionBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firstConnection();
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(contentPane);
		
		return contentPane;
	}
	
	/**
	 * Recherche l'utilisateur
	 */
	private void connectionTreatment() {
		try {	
			// récupérer les données
			String username = usernameField.getText();
			System.out.println("1");
			if (username == "") {
				return;
			}
			
			String password = new String(passwordField.getPassword());
			System.out.println("2");
			Employee emp = EmployeeService.getEmployeeByUsername(username);
			System.out.println(emp.toString());
			if (emp == null) {
				return;
			}
			
			if (!emp.getPassword().equalsIgnoreCase(password)) {
				return;
			}
			
			// Authentifier et rediriger
			Rentcar.user = emp;
			Rentcar.contentPane.removeAll();;
			Rentcar.contentPane.repaint();
			Rentcar.contentPane.revalidate();	
			Rentcar.createToolBar();
			Window vehicle = new VehiclesWindow();
			Rentcar.contentPane.add(vehicle.builder(), BorderLayout.CENTER);
			
		}
		catch(Exception e) {
			
		}
	}
	
	private void firstConnection() {
		
		// récupérer la personne
		Employee employee = EmployeeService.getEmployeeByCode(codeField.getText());
		if (employee == null) {
			return;
		}
		
		JFrame frameConnection = SharedWindow.createPopUp();
		frameConnection.setSize(200,200);
		JPanel contentFrame = (JPanel) frameConnection.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		JLabel usernameLbl = new JLabel("Pseudo");
		panel.add(usernameLbl);	
		JTextField username = new JTextField();
		panel.add(username);
		
		JLabel passwordLbl = new JLabel("Mot de passe");
		panel.add(passwordLbl);	
		JTextField password = new JPasswordField();
		panel.add(password);
		
		JButton validation = new JButton("Valider");
		panel.add(validation);
		contentFrame.add(panel);
		
		validation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employee.setUsername(username.getText());
				employee.setPassword(password.getText());
				EmployeeService.updateEmployee(employee);
				
				frameConnection.dispose();
				Rentcar.user = employee;
				Rentcar.contentPane.removeAll();;
				Rentcar.contentPane.repaint();
				Rentcar.contentPane.revalidate();	
				Rentcar.createToolBar();
				Window vehicle = new VehiclesWindow();
				Rentcar.contentPane.add(vehicle.builder(), BorderLayout.CENTER);
			}
		});
		
	}
}
