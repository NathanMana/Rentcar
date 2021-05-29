package rentcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class SharedWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame frame = new JFrame("RentCar");
	protected JPanel contentPane;
	
	/**
	 * Créer l'entête des filtres (toujours le même)
	 * @return JPanel
	 */
	public static JPanel createFilterHeader() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 0));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
		        
        JLabel filterLabel = new JLabel("FILTRE");
        filterLabel.setVerticalAlignment(SwingConstants.TOP);
        filterLabel.setPreferredSize(new Dimension(100, 30));
        filterLabel.setBorder(new EmptyBorder(12, 0, 12, 0));
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(filterLabel);
        
        return panel;
	}
	
	/**
	 * Ferme le filtre en ajoutant le bouton "rechercher" a la fin
	 * @param panel
	 * @return
	 */
	public static JButton formateSubmitBtn(JButton searchBtn) {
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
	public static JPanel createMosaicContent(final String title, final JPanel panelToAdd) {
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
	
	public static JFrame createPopUp() {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return frame;
	}
	
}
