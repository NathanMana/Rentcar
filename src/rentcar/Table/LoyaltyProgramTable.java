package rentcar.Table;

import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import rentcar.Entity.Employee;
import rentcar.Entity.Loyalty_program;

public class LoyaltyProgramTable extends AbstractTableModel {
    private final Loyalty_program[] loyaltyPrograms;
 
    private final String[] entetes = {"Id programme", "Durée", "Description", "Réduction", "Prix", "Modifier", "Supprimer"};
 
    public LoyaltyProgramTable(List<Loyalty_program> listLoyaltyPrograms) {
        super();
 
        loyaltyPrograms = new Loyalty_program[listLoyaltyPrograms.size()];
        
        for (int i = 0; i < listLoyaltyPrograms.size(); i++) {
        	loyaltyPrograms[i] = listLoyaltyPrograms.get(i);
        }
    }
 
    public int getRowCount() {
        return loyaltyPrograms.length;
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return loyaltyPrograms[rowIndex].getIdLoyaltyProgram();
            case 1:
                return loyaltyPrograms[rowIndex].getDuration();
            case 2:
                return loyaltyPrograms[rowIndex].getDescription();
            case 3:
            	return loyaltyPrograms[rowIndex].getReduction_rate();
            case 4:
            	return loyaltyPrograms[rowIndex].getPrice();
            case 5:
            	return new JButton("Modifier");
            case 6:
            	return new JButton("Supprimer");
            default:
                return null; //Ne devrait jamais arriver
        }
    }
}
