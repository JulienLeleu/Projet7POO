import javax.swing.table.* ;

/** Le modele de table (TableModel) decrit la structure et le contenu des donnees,
 *  tandis que JTable ne fait que donner une representation graphique de ces
 *  donnees. */
public class MyTableModel2 extends AbstractTableModel 
{
    // nom des champs
    private String[]
	columnNames = {"Nom", "Prenom", "Formation", "Age", "Lillois ?"};

    // donnees brutes
    private Object[][] data = {
	{"Belorgey", "Nicolas",
	 "DA2I", new Integer(20), new Boolean(false)},
	{"Biencourt", "Cyril",
	 "DA2I", new Integer(20), new Boolean(false)}
    };	

    public MyTableModel2() {
    	super();
    }

    // Redefinition de quelques methodes de AbstractTableModel 

    /** Nombre de colonnes dans la table */
    public int getColumnCount() { 
		return columnNames.length ; 
    }

    /** Nombre de lignes dans la table */
    public int getRowCount() { 
		return data.length ; 
    }
    
    /** Nom du champ correspondant a la colonne specifiee */
    public String getColumnName(int col) { 
		return columnNames[col] ; 
    }
    
    /** Valeur du champ de la colonne col pour la ligne specifiee */
    public Object getValueAt(int row, int col) { 
		return data[row][col] ; 
    }

    public Class getColumnClass(int i) {
    	return data[1][i].getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex > 1 && columnIndex < getColumnCount();
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	if (isCellEditable(rowIndex,columnIndex) && aValue.getClass() == getColumnClass(columnIndex)) {
    		data[rowIndex][columnIndex] = aValue;
    		fireTableCellUpdated(rowIndex, columnIndex);
    	}
    }
}