import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;
import javax.swing.table.* ;
import javax.swing.event.* ;

class MyJTable extends JTable {

    public MyJTable(TableModel m) {
    	super(m) ;
    }
    
    public String getToolTipText(MouseEvent e) {
        String tip = null;
        Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        int realColumnIndex = convertColumnIndexToModel(colIndex);
		TableModel model = getModel();
		String firstName = (String)model.getValueAt(rowIndex,1);
		String lastName = (String)model.getValueAt(rowIndex,0);	
        
        if (realColumnIndex == 3) { // age ?
            tip = firstName + " " + lastName + " a " + model.getValueAt(rowIndex, 3) + " ans. " + "Modifiez cette valeur si c'est faux." ;
		} else if (realColumnIndex == 4) { // Lillois ?
	    	Boolean lillois = (Boolean)model.getValueAt(rowIndex,4);
	    	tip = "Il semblerait que " + firstName + " " + lastName ;
	    	
	    	if (Boolean.TRUE.equals(lillois))
				tip += " habite la communaute urbaine de Lille" ;
	    	else 
	    		tip += " habite loin de Lille";
		} else { // autre colonne
	    	tip = super.getToolTipText(e);
		}
		return tip;
    }      
}
