package rentcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import rentcar.Entity.Employee;
import rentcar.Entity.Vehicle;
import rentcar.Interface.Window;

public class Rentcar {
	
	static JFrame frame;
	static JPanel contentPane;
	static Employee user;
		
	public static void main(String[] args) throws Exception {
	
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		frame = new JFrame("RentCar");
		//JFrame frame = new JFrame("RentCar");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		contentPane = (JPanel) frame.getContentPane();
		
		Window wd = new LoginWindow();
		JPanel window = wd.builder();
		
		// createToolBar();
		
		contentPane.add(window, BorderLayout.CENTER);	
		
		frame.addWindowListener(new WindowAdapter() {
			// Code récupéré et inspiré sur Stack Overflow
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment quitter l'application ?", "Quitter l'application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	System.exit(0);
		        }
		    }
		});
		
		frame.setVisible(true); // A mettre en dernier
	}
	
	/**
	 * 
	 * @return
	 */
	public static void createToolBar() {
		JToolBar toolbar = new JToolBar();
		
		boolean isUserADriver = user.isDriver();
		if (isUserADriver) {
			JButton agenciesWd = new JButton();
		    agenciesWd.setText("Agences");
		    agenciesWd.setFont(new Font("SansSerif", Font.PLAIN, 16));
		    toolbar.add(agenciesWd);
		    
		    JButton circuitsWd = new JButton();
		    circuitsWd.setText("Circuits");
		    circuitsWd.setFont(new Font("SansSerif", Font.PLAIN, 16));
		    toolbar.add(circuitsWd);
		    
		    agenciesWd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createNewWindow(new AgenciesWindow());
				}
			});
		    
		    circuitsWd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createNewWindow(new CircuitsWindow());
					
				}
			});
		}
		
		JButton vehicles = new JButton();
		vehicles.setText("V\u00E9hicules");
		vehicles.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(vehicles);
	    
	    JButton clientsWd = new JButton();
	    clientsWd.setText("Clients");
	    clientsWd.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(clientsWd);
	    
	    JButton locationsWd = new JButton();
	    locationsWd.setText("Locations");
	    locationsWd.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(locationsWd);
	    
	    if (user.getRole()) {
	    	JButton comptaWd = new JButton();
		    comptaWd.setText("Comptabilit\u00E9");
		    comptaWd.setFont(new Font("SansSerif", Font.PLAIN, 16));
		    toolbar.add(comptaWd);
		
		    JButton loyaltyBtn = new JButton();
		    loyaltyBtn.setText("Programme de fidélité");
		    loyaltyBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		    toolbar.add(loyaltyBtn);
		    
		    JButton adminBtn = new JButton();
		    adminBtn.setText("Gérer les employés");
		    adminBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		    toolbar.add(adminBtn);
		    
		    comptaWd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createNewWindow(new AccountingWindow());
				}
			});
		    
		    /**
		     * Clique sur le bouton "Programme de fidélité"
		     */
		    loyaltyBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createNewWindow(new LoyaltyProgramWindow());
				}
			});
		    
		    /**
		     * Espace administrateur pour gérer les employés
		     */
		    adminBtn.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent e) {
		    		createNewWindow(new AdminWindow());
		    	}
		    });
	    }
	    
	    JButton deconnectBtn = new JButton();
	    deconnectBtn.setText("Se déconnecter");
	    deconnectBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    toolbar.add(deconnectBtn);
	    
	    contentPane.add(toolbar, BorderLayout.NORTH);
	    
	    // EVENT LISTENER
	    vehicles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewWindow(new VehiclesWindow());
			}
		});
	    
	    clientsWd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewWindow(new ClientsWindow());
			}
		});
	    
	    locationsWd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewWindow(new LocationsWindow());
			}
		});
	    
	   
	    deconnectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user = null;
				contentPane.removeAll();;
				contentPane.repaint();
				contentPane.revalidate();	
				Window window = new LoginWindow();
				contentPane.add(window.builder(), BorderLayout.CENTER);
			}
		});
	
	}
	
	public static void createNewWindow(Window window) {
		contentPane.remove(1);
		contentPane.repaint();
		contentPane.revalidate();	
		contentPane.add(window.builder(), BorderLayout.CENTER);
	}
}
