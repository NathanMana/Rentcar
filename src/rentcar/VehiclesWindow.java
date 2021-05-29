package rentcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import rentcar.Entity.Agency;
import rentcar.Entity.Category;
import rentcar.Entity.Vehicle;
import rentcar.Enum.FuelType_Enum;
import rentcar.Enum.VehicleState_Enum;
import rentcar.Interface.ManageFormSubmission;
import rentcar.Interface.Window;
import rentcar.Service.AgencyService;
import rentcar.Service.CategoryService;
import rentcar.Service.VehicleService;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class VehiclesWindow extends JFrame implements ManageFormSubmission, Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;
	
	/**
	 * JPanel a mettre à jour
	 */
	private JPanel mosaic;
	
	/**
	 * Elements dans le formulaire
	 */
	private JComboBox<String> categoriesSelect;
	private JComboBox<String> brandsSelect;
	private JCheckBox isRented;
	private JButton searchBtn;

	/**
	 * Valeurs possibles pour les différentes listes du formulaire
	 */
	private List<Agency> listAgencies = AgencyService.getListAgencies();
	private List<Category> listCategories = CategoryService.getListCategories();
	private List<Vehicle> listVehicles = VehicleService.getListVehicles();
	private String arrayAgencies[];
	private String arrayBrands[];
	private String arrayCategories[];
	
	
	/**
	 * Bouton d'ajout de véhicule
	 */
	private JButton addVehicleBtn;
	String listFuelType[] = { FuelType_Enum.Diesel.name(), FuelType_Enum.Essence.name() };
	String vehicleStates[] = { VehicleState_Enum.Neuf.name(), VehicleState_Enum.Normal.name(), VehicleState_Enum.Habimé.name() };

	public VehiclesWindow() {
		listAgencies = AgencyService.getListAgencies();
		listCategories = CategoryService.getListCategories();
		listVehicles = VehicleService.getListVehicles();
	}
	
	public JPanel builder() {

		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		if (listAgencies.size() == 0) {
			return null;
		}
		
		if (listCategories.size() == 0) {
			return null;
		}
				
		arrayAgencies = new String[listAgencies.size()];
		arrayCategories = new String[listCategories.size() + 1];
		arrayBrands = new String[listVehicles.size() + 1];
		
		for (int i = 0; i < listAgencies.size(); i++) {
			arrayAgencies[i] = listAgencies.get(i).getName();
		}
		
		for (int i = 0; i < listCategories.size(); i++) {
			arrayCategories[i + 1] = listCategories.get(i).getName();
		}
		
		List<String> listNamesBrands = new ArrayList<String>();
		for (int i = 0; i < listVehicles.size(); i++) {
			listNamesBrands.add(listVehicles.get(i).getBrand());
		}
		HashSet<String> setBrands = new HashSet<String>(listNamesBrands);
		Integer counter = 1;
		for (Iterator<String> it = setBrands.iterator(); it.hasNext();) {
			arrayBrands[counter] = it.next();
			counter++;
		}
		
		// Quand la page a un filtre, utiliser ca
		contentPanel.add(createFilter(), BorderLayout.WEST);
		
		// Footer
		JPanel footerPanel = new JPanel();
		addVehicleBtn = new JButton("Ajouter un v\u00E9hicule");
		footerPanel.add(addVehicleBtn);
		contentPanel.add(footerPanel, BorderLayout.SOUTH);		
		
		// Probleme avec la mosaique
		mosaic = new JPanel();
		contentPanel.add(mosaic, BorderLayout.CENTER);
		
		refreshContent();
		
		// Pour chaque filtre
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageFormSubmission(e);
			}
		});	
		
		// Action sur ajout véhicule
		addVehicleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editVehicle(e, new Vehicle());
			}
		});	
		
		return contentPanel;
		
	}
	
	/**
	 * Personnalisation du filtre
	 * @return
	 */
	private JPanel createFilter() {
		// Début création filtre
		JPanel panel = SharedWindow.createFilterHeader();
        
        // Ajouter ici le reste des éléments dans le filtre      
		JLabel labelCategory = new JLabel("Catégorie");
		labelCategory.setPreferredSize(new Dimension(200, 20));
		panel.add(labelCategory);
        categoriesSelect = new JComboBox<String>(arrayCategories);
		categoriesSelect.setSize(new Dimension(40, 40));
		panel.add(categoriesSelect);
        
		JLabel labelBrand = new JLabel("Marque");
		labelBrand.setPreferredSize(new Dimension(200, 20));
		panel.add(labelBrand);
		brandsSelect = new JComboBox<String>(arrayBrands);
		panel.add(brandsSelect);

        isRented = new JCheckBox("Est d\u00E9j\u00E0 lou\u00E9");
        panel.add(isRented);
        
        searchBtn = SharedWindow.formateSubmitBtn(new JButton("Rechercher"));
        panel.add(searchBtn);
        
        return panel;		
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel customizeContent() {
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(0, 3, 10, 10));		
		
		String cat = "";
		String brand = "";
		if (categoriesSelect.getSelectedItem() != null) {
			cat = categoriesSelect.getSelectedItem().toString();
		}
		
		if (brandsSelect.getSelectedItem() != null) {
			brand = brandsSelect.getSelectedItem().toString();
		}
		
		List<Vehicle> listVehicles = VehicleService.getListVehiclesForSearch(cat, brand, isRented.isSelected());
		for (Vehicle vehicle : listVehicles) {
			
			JPanel vehicleContainer = createVehicleContainer(vehicle);
			panelContent.add(vehicleContainer);
		}

		return panelContent;
	}
	
	private JPanel createVehicleContainer(Vehicle vehicle) {
		JPanel vehicleContainer = new JPanel();
		vehicleContainer.setBackground(Color.WHITE);
		vehicleContainer.setLayout(new BorderLayout(0, 0));
		vehicleContainer.setSize(new Dimension(200, 200));
		
		JTextPane txtpnMarqueModele = new JTextPane();
		txtpnMarqueModele.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtpnMarqueModele.setText(vehicle.getBrand() + " / " + vehicle.getModel());
		vehicleContainer.add(txtpnMarqueModele, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		vehicleContainer.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		// récupérer la catégorie
		Category cat = CategoryService.getCategory(vehicle.getIdCategory());
		if (cat == null) {
			return null;
		}
		
		JLabel lblNewLabel = new JLabel("Classe : " + cat.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		JLabel lblKilomtrage = new JLabel("Kilom\u00E9trage : " + vehicle.getMileage());
		lblKilomtrage.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblKilomtrage);
		
		String boite = "Manuelle";
		if (vehicle.isType_of_gearbox()) {
			boite = "Automatique";
		}
		
		JLabel lblBoiteDeVitesse = new JLabel("Boite de vitesse : " + boite);
		lblBoiteDeVitesse.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblBoiteDeVitesse);
		
		JLabel lblCarburantGazole = new JLabel("Carburant : " + vehicle.getFuel_type());
		lblCarburantGazole.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCarburantGazole);
		
		String airConditioned = "non";
		if (vehicle.isIs_air_conditionned()) {
			airConditioned = "oui";
		}
		
		JLabel lblClimatisationOui = new JLabel("Climatisation : " + airConditioned);
		lblClimatisationOui.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblClimatisationOui);
		
		// BTN
		JPanel panelFooter = new JPanel();
		JButton btnEdit = new JButton();
		btnEdit.setText("Modifier");
		panelFooter.add(btnEdit);

		JButton btnDelete = new JButton();
		btnDelete.setText("Supprimer");
		panelFooter.add(btnDelete);
		
		vehicleContainer.add(panelFooter, BorderLayout.SOUTH);
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VehicleService.removeVehicle(vehicle);
				refreshContent();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editVehicle(e, vehicle);
			}
		});
		
		return vehicleContainer;
	}
	
	@Override
	public void manageFormSubmission(ActionEvent e) {
		refreshContent();	
	}
	
	public void editVehicle(ActionEvent e, Vehicle vehicle) {
		JFrame frameAddVehicle = new JFrame();
		frameAddVehicle.getContentPane().setBackground(Color.WHITE);
		frameAddVehicle.setVisible(true);
		frameAddVehicle.setResizable(true);
		frameAddVehicle.setSize(500, 500);
		frameAddVehicle.setLocationRelativeTo(null);
		frameAddVehicle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container contentNewVehicle = (JPanel) frameAddVehicle.getContentPane();
		Vehicle v = new Vehicle();
		if (vehicle.getNumber_plate() != null) {
			v = vehicle;
		}
		
		JPanel form = createFormAddVehicle(frameAddVehicle, v);
		contentNewVehicle.add(form);
	}
	
	private JPanel createFormAddVehicle(JFrame frame, Vehicle vehicle) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		String listFuelType[] = { FuelType_Enum.Diesel.name(), FuelType_Enum.Essence.name() };
		String vehicleStates[] = { VehicleState_Enum.Neuf.name(), VehicleState_Enum.Normal.name(), VehicleState_Enum.Habimé.name() };
		
		/**
		 * Listes dans le formulaire d'ajout
		 */
		List<Agency> listAgencies = AgencyService.getListAgencies();
		List<Category> listCategories = CategoryService.getListCategories();
		
		JLabel lblNumberPlate = new JLabel("Plaque d'immatriculation");
		panel.add(lblNumberPlate);
		
		JTextField valueNumberPlate = new JTextField(vehicle.getNumber_plate());
		panel.add(valueNumberPlate);
		valueNumberPlate.setColumns(10);
		
		JLabel lblKilometrage = new JLabel("Kilom\u00E9trage");
		panel.add(lblKilometrage);
		
		JTextField valueKilometrage = new JTextField(("" + vehicle.getMileage()));
		valueKilometrage.setColumns(10);
		panel.add(valueKilometrage);
		
		JLabel lblModel = new JLabel("Mod\u00E8le");
		panel.add(lblModel);
		
		JTextField valueModle = new JTextField(vehicle.getModel());
		valueModle.setColumns(10);
		panel.add(valueModle);
			
		JLabel lblMarque = new JLabel("Marque");
		panel.add(lblMarque);
		
		JTextField valueMarque = new JTextField(vehicle.getBrand());
		valueMarque.setColumns(10);
		panel.add(valueMarque);
		
		JLabel lblEtat = new JLabel("Etat");
		panel.add(lblEtat);
		
		JComboBox<String> valueState = new JComboBox<String>(vehicleStates);
		valueState.getModel().setSelectedItem(vehicle.getState());
		valueState.setBounds(237, 135, 126, 40);
		panel.add(valueState);
		
		JLabel lblCarburant = new JLabel("Carburant");
		panel.add(lblCarburant);
		
		JComboBox<String> valueCarburant = new JComboBox<String>(listFuelType);
		valueCarburant.getModel().setSelectedItem(vehicle.getFuel_type());
		panel.add(valueCarburant);
		
		JRadioButton airConditionned = new JRadioButton("Est climatis\u00E9");
		airConditionned.setSelected(vehicle.isIs_air_conditionned());
		panel.add(airConditionned);
		
		JRadioButton typeDeBoite = new JRadioButton("Est une boite auto");
		typeDeBoite.setSelected(vehicle.isType_of_gearbox());
		panel.add(typeDeBoite);
		
		JLabel lblAgence = new JLabel("Agence");
		lblAgence.setBounds(66, 197, 161, 20);
		panel.add(lblAgence);
		
		JComboBox<String> valueAgence =  new JComboBox<String>(arrayAgencies);
		if (vehicle.getIdAgency() > 0) {
			valueAgence.getModel().setSelectedItem(AgencyService.getAgency(vehicle.getIdAgency()).getName());		
		}
		panel.add(valueAgence);
		
		JLabel lblTypeVehicle = new JLabel("Type");
		panel.add(lblTypeVehicle);
		
		JComboBox<String> typeVehicle = new JComboBox<String>(arrayCategories);
		if (vehicle.getIdCategory() > 0) {
			typeVehicle.getModel().setSelectedItem(CategoryService.getCategory(vehicle.getIdCategory()).getName());		
		}
		panel.add(typeVehicle);
		
		String lblBtn = "Ajouter";
		if (vehicle.getIdAgency() > 0) {
			lblBtn = "Modifier";
		}
		JButton btnNewButton = new JButton(lblBtn);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// récupérer toutes les valeurs
				try {
					int indexAgency = valueAgence.getSelectedIndex();
					int indexCategory = typeVehicle.getSelectedIndex();
					
					//Récupérer l'objet agence et catégorie complet
					Agency agency = listAgencies.get(indexAgency);
					if (agency == null) {
						return;
					}
					
					System.out.println(indexCategory);
					Category category = listCategories.get(indexCategory - 1);
					if (category == null) {
						return;
					}
					
					Vehicle newVehicle = new Vehicle(
							valueNumberPlate.getText(),
							Double.parseDouble(valueKilometrage.getText()),
							typeDeBoite.isSelected(),
							airConditionned.isSelected(),
							FuelType_Enum.valueOf(valueCarburant.getSelectedItem().toString()),
							VehicleState_Enum.valueOf(valueState.getSelectedItem().toString()),
							valueMarque.getText(),
							valueModle.getText(),
							agency.getId_agency(),
							category.getIdCategory()
							);
					
					if (vehicle.getIdAgency() > 0) {
						// Forcément on édite
						if (VehicleService.updateVehicle(newVehicle) == 0) {
							return;
						}
					} else {
						if(VehicleService.addVehicle(newVehicle) == 0) {
							return;
						}
					}
					
					refreshContent();
					frame.dispose();
				}
				catch(Exception exception) {
					
				}
				
				
			}
		});	
		
		return panel;
	}
	
	private void refreshContent() {
		contentPanel.remove(mosaic);
		contentPanel.repaint();
		contentPanel.revalidate();	
		JPanel panelContentVehicles = customizeContent();
		mosaic = SharedWindow.createMosaicContent("Voitures", panelContentVehicles);
		contentPanel.add(mosaic, BorderLayout.CENTER);
		contentPanel.repaint();
		contentPanel.revalidate();
	}
}