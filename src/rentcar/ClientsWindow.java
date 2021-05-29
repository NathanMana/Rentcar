package rentcar;

import javax.swing.JPanel;

import rentcar.Interface.Window;

public class ClientsWindow implements Window {
	
	public ClientsWindow () {
		
	}
	
	@Override 
	public JPanel builder() {
		return new JPanel();
	}

}
