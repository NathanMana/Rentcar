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
import rentcar.Entity.Loyalty_program;
import rentcar.Entity.Vehicle;
import rentcar.Enum.FuelType_Enum;
import rentcar.Enum.VehicleState_Enum;
import rentcar.Interface.ManageFormSubmission;
import rentcar.Interface.ViewTable;
import rentcar.Interface.Window;
import rentcar.Service.AgencyService;
import rentcar.Service.CategoryService;
import rentcar.Service.LoyaltyProgramService;
import rentcar.Service.VehicleService;
import rentcar.Table.JTableButtonMouseListener;
import rentcar.Table.JTableButtonRenderer;
import rentcar.Table.LoyaltyProgramTable;

public class LoyaltyProgramWindow extends JFrame implements ManageFormSubmission, Window, ViewTable {

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
	private JButton addLoyaltyProgram;
	
	/**
	 * Contient la liste des éléments
	 */
	private List<Loyalty_program> listLoyaltyPrograms;
	
	/**
	 * Tableau de résultats
	 */
	private JTable tableau;
	
	public LoyaltyProgramWindow() {
	}
	
	public JPanel builder() {
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());	
		
		// Ajout footer
		JPanel footerPanel = new JPanel();
		addLoyaltyProgram = new JButton("Ajouter un programme");
		footerPanel.add(addLoyaltyProgram);
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		
		// Ajout contenu
		JPanel content = new JPanel();
		contentPane.add(createContent(), BorderLayout.CENTER);
		
		// Action sur ajout véhicule
		addLoyaltyProgram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editProgram(new Loyalty_program());
			}
		});			
		
		return contentPane;
			
	}
	
	/**
	 * Créer le corps de la page
	 * @return
	 */
	private JPanel createContent() {
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(0, 1));

		// Récupérer la liste des éléments
		listLoyaltyPrograms = LoyaltyProgramService.getListLoyaltyPrograms();
		
		tableau = new JTable(new LoyaltyProgramTable(listLoyaltyPrograms));
		TableColumnModel columnModel = tableau.getColumnModel();
		tableau.setRowHeight(40);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		tableau.getColumn("Modifier").setCellRenderer(buttonRenderer);
		tableau.getColumn("Supprimer").setCellRenderer(buttonRenderer);
		tableau.addMouseListener(new JTableButtonMouseListener(tableau, this));
		JScrollPane scrollPane = new JScrollPane(tableau);
		scrollPane.setSize(new Dimension(600, 0));
		panelContent.add(scrollPane, BorderLayout.CENTER);
        pack();
        
        return panelContent;
	}
	
	public void editProgram(Loyalty_program program) {
		JFrame frameAddProgram = SharedWindow.createPopUp();
		
		Container contentFrame = (JPanel) frameAddProgram.getContentPane();
		Loyalty_program prog = new Loyalty_program();
		if (program.getIdLoyaltyProgram() > 0) {
			prog = program;
		}
		
		JPanel form = createFormAddProgram(frameAddProgram, prog);
		contentFrame.add(form);
	}

	/**
	 * Met en forme le formulaire
	 * @param frame
	 * @param program
	 * @return
	 */
	private JPanel createFormAddProgram(JFrame frame, Loyalty_program program) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		JLabel durationLbl = new JLabel("Durée");
		panel.add(durationLbl);	
		JTextField duration = new JTextField(program.getDuration() + "");
		panel.add(duration);
		
		JLabel descriptionLbl = new JLabel("Description");
		panel.add(descriptionLbl);	
		JTextField description = new JTextField(program.getDescription());
		panel.add(description);
		
		JLabel priceLbl = new JLabel("Prix");
		panel.add(priceLbl);	
		JTextField price = new JTextField("" + program.getPrice());
		panel.add(price);
		
		JLabel reductionRateLbl = new JLabel("Réduction");
		panel.add(reductionRateLbl);	
		JTextField reduction = new JTextField("" + program.getReduction_rate());
		panel.add(reduction);
		
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		JLabel dateLbl = new JLabel("Date expiration");
		panel.add(dateLbl);	
		JTextField date = new JTextField(f.format(program.getExpiration_date()));
		panel.add(date);
		
		String lblBtn = "Ajouter";
		if (program.getIdLoyaltyProgram() > 0) {
			lblBtn = "Modifier";
		}
		JButton btnNewButton = new JButton(lblBtn);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// récupérer toutes les valeurs
				try {
					if (duration.getText().isEmpty() || price.getText().isEmpty() || reduction.getText().isEmpty() || date.getText().isEmpty()) {
						return;
					}
					
					java.util.Date d = f.parse(date.getText());
					
					program.setDescription(description.getText().toString());
					program.setDuration(Integer.parseInt(duration.getText()));
					program.setExpiration_date(d);
					program.setPrice(Double.parseDouble(price.getText().toString()));
					program.setReduction_rate(Double.parseDouble(reduction.getText().toString()));

					if (program.getIdLoyaltyProgram() > 0) {
						LoyaltyProgramService.updateLoyaltyProgram(program);
					} else {
						LoyaltyProgramService.addLoyaltyProgram(program);
					}
					
					Rentcar.createNewWindow(new LoyaltyProgramWindow());
					frame.dispose();
				}
				catch(NumberFormatException exception) {
					System.out.println("Erreur format nombre");
				}
				catch(ParseException exception) {
					System.out.println("Erreur format date");
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
		Loyalty_program program = listLoyaltyPrograms.get(row);
		editProgram(program);
	}
	
	public void deleteEntity(Integer row) {
		// Chercher le programme
		Loyalty_program program = listLoyaltyPrograms.get(row);
		LoyaltyProgramService.removeLoyaltyProgram(program);
		Rentcar.createNewWindow(new LoyaltyProgramWindow());
	}
	
}



