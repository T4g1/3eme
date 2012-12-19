package TableModel;

import Entity.Room;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model pour les Room
 */
public class RoomTableModel extends AbstractTableModel {
    private List<Room> l_object;
    private String[] columnNames = {
            "Numero",
            "Categorie",
            "Douche(s)",
            "Baignoire(s)",
            "Cuvette(s)",
            "Places",
            "Prix (HTVA)"
    };
    
    public void setListing(List<Room> l_object) {
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
        
        Room object = l_object.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return object.getId();
            case 1:
                return object.getCategorie();
            case 2:
                return object.getDouche();
            case 3:
                return object.getBaignoire();
            case 4:
                return object.getCuvette();
            case 5:
                return object.getPlaces();
            case 6:
                return object.getPrix();
        }
        
        return null;
    }
    
    /**
     * Donne l'objet dans la ligne demandée
     * @param rowIndex 
     */
    public Room getValueAt(int rowIndex) {
        if(l_object == null) {
            return null;
        }
        
        return l_object.get(rowIndex);
    }
}
