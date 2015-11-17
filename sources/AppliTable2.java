import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;
import javax.swing.table.* ;
import javax.swing.event.* ;

/** L'application permettant de manipuler une JTable */
public class AppliTable2 extends JFrame 
{    
    public static void main(String [] a) 
    {
	// pour avoir une fenetre decoree au style Swing
        JFrame.setDefaultLookAndFeelDecorated(true);
		AppliTable i = new AppliTable();
		i.setVisible(true) ;
    }

    private MyJTable table ;
    private JPanel dessin ;
    private String[] formations = {"LP DA2I", "IUT FI1", "IUT FI2", "IUT FC", "IUT AS"};
	private JComboBox comboBox = new JComboBox<String>(formations);
    private TableCellEditor editor = new DefaultCellEditor(comboBox);

    private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    
    public AppliTable2() 
    {
		super("La loi des JTables") ;

		// action a effectuer lorsqu'on ferme la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// definition du gestionnaire de placement 
		getContentPane().setLayout(new FlowLayout()) ;

		// on cree la table a partir d'un modele de table maison
		table = new MyJTable(new MyTableModel()) ;
		table.getColumnModel().getColumn(2).setCellEditor(editor);
		cellRenderer.setToolTipText("Les formations");
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				System.out.println("Modification de la colonne : " + table.getColumnName(e.getColumn()) + " ligne : " 
					+ e.getFirstRow() + " à la valeur " + table.getValueAt(e.getFirstRow(), e.getColumn()));
			}
		});
		// et on la place dans un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));

		getContentPane().add(scrollPane) ;
		setBounds(400,200,800,400) ;
    }
}
