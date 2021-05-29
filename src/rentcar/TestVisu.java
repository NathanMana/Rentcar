package rentcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import rentcar.Interface.ManageFormSubmission;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextPane;

public class TestVisu extends JFrame implements ManageFormSubmission {

	protected JFrame frame = new JFrame("RentCar");
	protected JPanel contentPane;
	
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
	private String categories[] = { "Classic", "Premium", "Luxe" };
	private String brands[] = { "Renault", "Peugeot" };
	private JPanel panel_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestVisu frame = new TestVisu();
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
	public TestVisu() {
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		
		contentPane = (JPanel) frame.getContentPane();
		contentPane.add(this.createToolBar(), BorderLayout.NORTH);
		
		contentPane.add(createFilter(), BorderLayout.WEST);
		
		// A custom dans toutes les pages
		JPanel panelContent = customizeContent();
		contentPane.add(createMosaicContent("Voitures", panelContent), BorderLayout.CENTER);
		
		JPanel footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter un v\u00E9hicule");
		footerPanel.add(btnNewButton);
		
		// Pour chaque filtre
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageFormSubmission(e);
			}
		});	
		
	}
	
	private JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		JButton txtpnText_1 = new JButton();
	    txtpnText_1.setText("V\u00E9hicules");
	    txtpnText_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnText_1);
	    
	    JButton txtpnClients_1 = new JButton();
	    txtpnClients_1.setText("Clients");
	    txtpnClients_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnClients_1);
	    
	    JButton txtpnLocations_1 = new JButton();
	    txtpnLocations_1.setText("Locations");
	    txtpnLocations_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnLocations_1);
	    
	    JButton txtpnAgences_1 = new JButton();
	    txtpnAgences_1.setText("Agences");
	    txtpnAgences_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnAgences_1);
	    
	    JButton txtpnComptabilit_1 = new JButton();
	    txtpnComptabilit_1.setText("Comptabilit\u00E9");
	    txtpnComptabilit_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnComptabilit_1);
	    
	    JButton txtpnCircuits_1 = new JButton();
	    txtpnCircuits_1.setText("Circuits");
	    txtpnCircuits_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(txtpnCircuits_1);
	    
	    return toolbar;
	}
	
	
	/**
	 * Créer l'entête des filtres (toujours le même)
	 * @return JPanel
	 */
	protected JPanel createFilterHeader() {
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 0));
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
		        
        JLabel filterLabel = new JLabel("FILTRE");
        filterLabel.setVerticalAlignment(SwingConstants.TOP);
        filterLabel.setPreferredSize(new Dimension(100, 30));
        filterLabel.setBorder(new EmptyBorder(12, 0, 12, 0));
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel_1.add(filterLabel);
        
        return panel_1;
	}
	
	/**
	 * Ferme le filtre en ajoutant le bouton "rechercher" a la fin
	 * @param panel
	 * @return
	 */
	protected JButton formateSubmitBtn(JButton searchBtn) {
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Border padding = new EmptyBorder(5,5,5,5);
        
        searchBtn.setBorder(new CompoundBorder(border, padding));
        return searchBtn;
	}
	
	/**
	 * Permet de créer le rendu classique avec des mosaiques
	 * @param title
	 * @param panelToAdd
	 * @return
	 */
	protected JPanel createMosaicContent(final String title, final JPanel panelToAdd) {
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(22, 22, 22, 22));
		container.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		container.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel titleWindow = new JLabel(title);
		titleWindow.setVerticalAlignment(SwingConstants.BOTTOM);
		titleWindow.setFont(new Font("SansSerif", Font.BOLD, 18));
		titleWindow.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitle.add(titleWindow);
		
		JScrollPane scrollpane = new JScrollPane(panelToAdd);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		container.add(scrollpane, BorderLayout.CENTER);
		
		return container;
	}
	
	/**
	 * Personnalisation du filtre
	 * @return
	 */
	private JPanel createFilter() {
		// Début création filtre
		JPanel panel = createFilterHeader();
        
		/*
        // Ajouter ici le reste des éléments dans le filtre      
		JLabel labelCategory = new JLabel("Catégorie");
		labelCategory.setPreferredSize(new Dimension(200, 20));
		panel.add(labelCategory);
        categoriesSelect = new JComboBox<String>(categories);
		categoriesSelect.setSize(new Dimension(40, 40));
		panel.add(categoriesSelect);
        
		JLabel labelBrand = new JLabel("Marque");
		labelBrand.setPreferredSize(new Dimension(200, 20));
		panel.add(labelBrand);
		brandsSelect = new JComboBox<String>(brands);
		panel.add(brandsSelect);
        */
        isRented = new JCheckBox("Est d\u00E9j\u00E0 lou\u00E9");
        panel.add(isRented);
        
        searchBtn = formateSubmitBtn(new JButton("Rechercher"));
        panel.add(searchBtn);
        
        return panel;
	}
	
	private JPanel customizeContent() {
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(0, 3, 10, 10));
		
		for (int i = 0; i < 3; i++) {
			JPanel vehicleContainer = createVehicleContainer();
			panelContent.add(vehicleContainer);
		}
		
		return panelContent;
	}
	
	private JPanel createVehicleContainer() {
		JPanel vehicleContainer = new JPanel();
		vehicleContainer.setBackground(Color.WHITE);
		vehicleContainer.setLayout(new BorderLayout(0, 0));
		vehicleContainer.setSize(new Dimension(200, 200));
		
		JTextPane txtpnMarqueModele = new JTextPane();
		txtpnMarqueModele.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtpnMarqueModele.setText("MArque / modele");
		vehicleContainer.add(txtpnMarqueModele, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		vehicleContainer.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Classe : Premium");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		JLabel lblKilomtrage = new JLabel("Kilom\u00E9trage : 50 000");
		lblKilomtrage.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblKilomtrage);
		
		JLabel lblBoiteDeVitesse = new JLabel("Boite de vitesse : automatique");
		lblBoiteDeVitesse.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblBoiteDeVitesse);
		
		JLabel lblCarburantGazole = new JLabel("Carburant : Gazole");
		lblCarburantGazole.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCarburantGazole);
		
		JLabel lblClimatisationOui = new JLabel("Climatisation : oui");
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

		return vehicleContainer;
	}
	
	@Override
	public void manageFormSubmission(ActionEvent e) {
		//System.out.println(brandsSelect.getSelectedItem());
	}

}
