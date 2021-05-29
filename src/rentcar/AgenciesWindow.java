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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import rentcar.Interface.ManageFormSubmission;
import rentcar.Interface.Window;

public class AgenciesWindow extends JFrame implements ManageFormSubmission, Window {

	private JPanel contentPanel;
	
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

	private JPanel panel_1;
	private JTextField nom;
	private JTextField ville;
	private JTextField codeAgence;
	private JCheckBox capacite;
	
	/**
	 * Create the frame.
	 */
	public AgenciesWindow() {
		
		
	}
	
	@Override 
	public JPanel builder() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(createFilter(), BorderLayout.WEST);
		
		// A custom dans toutes les pages
		JPanel panelContent = customizeContent();
		contentPanel.add(createMosaicContent("Agence", panelContent), BorderLayout.CENTER);
		
		// Pour chaque filtre
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageFormSubmission(e);
			}
		});	
		
		return contentPanel;
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
        
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		nomLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel_1.add(nomLabel);
        
        nom = new JTextField();
        panel_1.add(nom);
        nom.setColumns(10);
        
        JLabel villeLabel = new JLabel("Ville");
        villeLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
        villeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel_1.add(villeLabel);
        
        ville = new JTextField();
        panel_1.add(ville);
        ville.setColumns(10);
        
        
        JLabel codeAgenceLabel = new JLabel("Code Agence");
        codeAgenceLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
        codeAgenceLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel_1.add(codeAgenceLabel);
        
        codeAgence = new JTextField();
        panel_1.add(codeAgence);
        codeAgence.setColumns(10);
        
        capacite = new JCheckBox("Plus de 80% de capacite");
        panel.add(capacite);
        
        searchBtn = formateSubmitBtn(new JButton("Rechercher"));
        panel.add(searchBtn);
        
        return panel;
	}
	
	private JPanel customizeContent() {
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(0, 3, 10, 10));
		JButton btn = new JButton("cc");
		btn.setPreferredSize(new Dimension(100,200));
		panelContent.add(btn);
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		panelContent.add(new JButton("cc"));
		return panelContent;
	}
	
	@Override
	public void manageFormSubmission(ActionEvent e) {
		//System.out.println(brandsSelect.getSelectedItem());
	}

}
