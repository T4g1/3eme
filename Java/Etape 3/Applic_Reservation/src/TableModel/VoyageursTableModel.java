package TableModel;

import Entity.Voyageur;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model pour les Voyageur
 */
public class VoyageursTableModel extends AbstractTableModel {
    private List<Voyageur> l_object;
    private String[] columnNames = {
            "Id",
            "Username",
            "Nom",
            "Prenom",
            "Adresse",
            "E-mail"
    };
    
    public void setListing(List<Voyageur> l_object) {
        this.l_object = l_object;
    }

    @Override
    public int getRowCount() {
        if(l_object != null) {
            return l_object.size();
        }
        
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(l_object == null) {
            return null;
        }
        
        Voyageur object = l_object.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return object.getId();
            case 1:
                return object.getUsername();
            case 2:
                return object.getNom();
            case 3:
                return object.getPrenom();
            case 4:
                return object.getAdresse();
            case 5:
                return object.getEmail();
        }
        
        return null;
    }
    
    /**
     * Donne l'objet dans la ligne demandée
     * @param rowIndex 
     */
    public Voyageur getValueAt(int rowIndex) {
        if(l_object == null) {
            return null;
        }
        
        return l_object.get(rowIndex);
    }
}
