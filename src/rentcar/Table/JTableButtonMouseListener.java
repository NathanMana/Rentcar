
package rentcar.Table;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JTable;

import rentcar.Interface.ViewTable;

/**
 * Classe fortement inspirée de code sur StackOverflow
 *
 */
public class JTableButtonMouseListener extends MouseAdapter {
    private final JTable table;
    private ViewTable obj;

    public JTableButtonMouseListener(JTable table, ViewTable obj) {
        this.table = table;
        this.obj = obj;
    }

    public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
        int row    = e.getY()/table.getRowHeight(); //get the row of the button

                /*Checking the row or column is valid or not*/
        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
            Object value = table.getValueAt(row, column);
            if (value instanceof JButton) {
            	// Si clique sur bouton modifier
            	if (column == 5) {	
            		obj.editEntity(row);            	
            	}
            	
            	if (column == 6) {	
            		obj.deleteEntity(row);            	
            	}
            }
        }
    }
}