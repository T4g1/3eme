package TableModel;

import Entity.Order;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model pour les Room
 */
public class OrderTableModel extends AbstractTableModel {
    private List<Order> l_object;
    private String[] columnNames = {
            "Id",
            "Titulaire",
            "Numéro de chambre",
            "Commande payée ?",
            "Début de location",
            "Durée",
            "Status"
    };
    
    public void setListing(List<Order> l_object) {
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
        
        Order object = l_object.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return object.getId();
            case 1:
                return object.getUsernameTitulaire();
            case 2:
                return object.getNumeroChambre();
            case 3:
                return object.isPaye();
            case 4:
                return object.getDebut();
            case 5:
                return object.getDuree();
            case 6:
                switch(object.getStatus()) {
                    case 0:
                        return "Absent";
                    case 1:
                        return "Présent";
                };
        }
        
        return null;
    }
    
    /**
     * Donne l'objet dans la ligne demandée
     * @param rowIndex 
     */
    public Order getValueAt(int rowIndex) {
        if(l_object == null) {
            return null;
        }
        
        return l_object.get(rowIndex);
    }
}
