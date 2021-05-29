package rentcar.Table;

import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import rentcar.Entity.Employee;

public class EmployeeTable extends AbstractTableModel {
    private final Employee[] employees;
 
    private final String[] entetes = {"Id employé", "Nom", "Prénom", "Est chauffeur", "S'est déjà connecté", "Agence", "Modifier", "Supprimer"};
 
    public EmployeeTable(List<Employee> listLoyaltyPrograms) {
        super();
 
        employees = new Employee[listLoyaltyPrograms.size()];
        
        for (int i = 0; i < listLoyaltyPrograms.size(); i++) {
        	employees[i] = listLoyaltyPrograms.get(i);
        }
    }
 
    public int getRowCount() {
        return employees.length;
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
                return employees[rowIndex].getIdEmployee();
            case 1:
                return employees[rowIndex].getName();
            case 2:
                return employees[rowIndex].getFirstname();
            case 3:
            	String isDriver = "oui";
            	if (!employees[rowIndex].isDriver())
            		isDriver = "non";
            	return isDriver;
            case 4:
            	String alreadyConnected = "oui";
            	if (employees[rowIndex].getPassword() == null || employees[rowIndex].getPassword().isEmpty())
            		alreadyConnected = "non";
            	return alreadyConnected;
            case 5:
            	return employees[rowIndex].getAgency().getName();
            case 6:
            	return new JButton("Modifier");
            case 7:
            	return new JButton("Supprimer");
            default:
                return null; //Ne devrait jamais arriver
        }
    }
}
