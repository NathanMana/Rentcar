package rentcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import rentcar.Entity.Agency;
import rentcar.Entity.Category;
import rentcar.Entity.Employee;
import rentcar.Entity.Loyalty_program;
import rentcar.Entity.Vehicle;
import rentcar.Enum.FuelType_Enum;
import rentcar.Enum.VehicleState_Enum;
import rentcar.Interface.ManageFormSubmission;
import rentcar.Interface.ViewTable;
import rentcar.Interface.Window;
import rentcar.Service.AgencyService;
import rentcar.Service.CategoryService;
import rentcar.Service.EmployeeService;
import rentcar.Service.LoyaltyProgramService;
import rentcar.Service.VehicleService;
import rentcar.Table.EmployeeTable;
import rentcar.Table.JTableButtonMouseListener;
import rentcar.Table.JTableButtonRenderer;
import rentcar.Table.LoyaltyProgramTable;

public class AdminWindow extends JFrame implements ManageFormSubmission, Window, ViewTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	/**
	 * Bouton recherche dans le filtre
	 */
	private JButton searchBtn;

	/**
	 * Bouton pour ajouter un programme
	 */
	private JButton addEmployee;
	
	/**
	 * Contient la liste des éléments
	 */
	private List<Employee> listEmployees;
	
	/**
	 * Tableau de résultats
	 */
	private JTable table;
	
	/**
	 * Popup d'ajout
	 */
	private JFrame frameAddEmployee;
	
	/**
	 * Liste des agences
	 */
	private List<Agency> listAgencies = AgencyService.getListAgencies();
	private String arrayAgencies[];
	
	public AdminWindow() {
	}
	
	public JPanel builder() {
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());	
		
		// Ajout footer
		JPanel footerPanel = new JPanel();
		addEmployee = new JButton("Ajouter un employé");
		footerPanel.add(addEmployee);
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		
		// Ajout contenu
		JPanel content = new JPanel();
		contentPane.add(createContent(), BorderLayout.CENTER);
		
		// Action sur ajout véhicule
		addEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editEmployee(new Employee());
			}
		});			
		
		return contentPane;
			
	}
	
	/**
	 * Affiche le tableau
	 * @return
	 */
	private JPanel createContent() {
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(0, 1));

		// Récupérer la liste des éléments
		listEmployees = EmployeeService.getListEmployees();
		
		table = new JTable(new EmployeeTable(listEmployees));
		TableColumnModel columnModel = table.getColumnModel();
		table.setRowHeight(40);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("Modifier").setCellRenderer(buttonRenderer);
		table.getColumn("Supprimer").setCellRenderer(buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table, this));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(new Dimension(600, 0));
		panelContent.add(scrollPane, BorderLayout.CENTER);
        pack();
        
        return panelContent;
	}
	
	public void editEmployee(Employee employee) {
		frameAddEmployee = SharedWindow.createPopUp();
		
		Container contentFrame = (JPanel) frameAddEmployee.getContentPane();
		Employee emp = new Employee();
		if (employee.getIdEmployee() > 0) {
			emp = employee;
		}
		
		JPanel form = createFormAddEmployee(frameAddEmployee, emp);
		contentFrame.add(form);
	}

	/**
	 * Met en forme le formulaire
	 * @param frame
	 * @param program
	 * @return
	 */
	private JPanel createFormAddEmployee(JFrame frame, Employee employee) {
		arrayAgencies = new String[listAgencies.size()];
		
		for (int i = 0; i < listAgencies.size(); i++) {
			arrayAgencies[i] = listAgencies.get(i).getName();
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		JLabel firstnameLbl = new JLabel("Prénom");
		panel.add(firstnameLbl);	
		JTextField firstname = new JTextField(employee.getFirstname());
		panel.add(firstname);
		
		JLabel nameLbl = new JLabel("Nom");
		panel.add(nameLbl);	
		JTextField name = new JTextField(employee.getName());
		panel.add(name);
		
		JLabel addressLbl = new JLabel("Adresse");
		panel.add(addressLbl);	
		JTextField address = new JTextField(employee.getStreet_address());
		panel.add(address);
		
		JLabel cityLbl = new JLabel("Ville");
		panel.add(cityLbl);	
		JTextField city = new JTextField(employee.getCity());
		panel.add(city);
		
		JLabel zipcodeLbl = new JLabel("Code postal");
		panel.add(zipcodeLbl);	
		JTextField zipcode = new JTextField(employee.getZipcode());
		panel.add(zipcode);
		
		JLabel phoneNUmberLbl = new JLabel("Numéro de téléphone");
		panel.add(phoneNUmberLbl);	
		JTextField phoneNUmber = new JTextField(employee.getPhone_number());
		panel.add(phoneNUmber);
		
		JLabel agencyLbl = new JLabel("Agence");
		panel.add(agencyLbl);
		JComboBox<String> agency =  new JComboBox<String>(arrayAgencies);
		if (employee.getIdAgency() > 0) {
			agency.getModel().setSelectedItem(AgencyService.getAgency(employee.getIdAgency()).getName());		
		}
		panel.add(agency);
		
		// IS admin ? 
		JRadioButton role = new JRadioButton("Role administrateur ?");
		panel.add(role);
		
		JLabel codeLbl = new JLabel("Code de connexion (a transmettre a l'employé pour sa première connexion)");
		panel.add(codeLbl);
		String randomCode = "";
		if (employee.getCode() == null || employee.getCode().isEmpty()) {
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
		        char randomizedCharacter = (char) (random.nextInt(26) + 'a');
		        randomCode += randomizedCharacter;
			}
			employee.setCode(randomCode);
		}
		JLabel code = new JLabel(randomCode);
		panel.add(code);
		
		String lblBtn = "Ajouter";
		if (employee.getIdEmployee() > 0) {
			lblBtn = "Modifier";
		}
		JButton btnNewButton = new JButton(lblBtn);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// récupérer toutes les valeurs
				
				try {
					if (firstname.getText().isEmpty() || name.getText().isEmpty()) {
						return;
					}
					
					int indexAgency = agency.getSelectedIndex();					
					Agency agencyEntity = listAgencies.get(indexAgency);
					if (agencyEntity == null) {
						return;
					}
					
					employee.setCity(city.getText());
					employee.setFirstname(firstname.getText());
					employee.setName(name.getText());
					employee.setStreet_address(address.getText());
					employee.setZipcode(zipcode.getText());
					employee.setRole(role.isSelected());
					employee.setPhone_number(phoneNUmber.getText());
					employee.setIdAgency(agencyEntity.getId_agency());
					
					System.out.println(employee);

					if (employee.getIdEmployee() > 0) {
						EmployeeService.updateEmployee(employee);
					} else {
						EmployeeService.addEmployee(employee);
					}
					
					Rentcar.createNewWindow(new AdminWindow());
					frame.dispose();
				}
				catch(NumberFormatException exception) {
					System.out.println("Erreur format nombre");
				}
				catch(Exception exception) {
					System.out.println("Erreur");
				}
				
			}
		});		
		return panel;
	}

	@Override
	public void manageFormSubmission(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void editEntity(Integer row) {
		// Il faut trouver le programme dans la liste
		Employee employee = listEmployees.get(row);
		editEmployee(employee);
	}
	
	public void deleteEntity(Integer row) {
		// Chercher le programme
		Employee employee = listEmployees.get(row);
		EmployeeService.removeEmployee(employee);
		Rentcar.createNewWindow(new AdminWindow());
	}
	
}



