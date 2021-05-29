package rentcar;

import javax.swing.JPanel;

import rentcar.Interface.Window;

public class AccountingWindow implements Window {
	
	public AccountingWindow () {
		
	}
	
	@Override 
	public JPanel builder() {
		return new JPanel();
	}

}
