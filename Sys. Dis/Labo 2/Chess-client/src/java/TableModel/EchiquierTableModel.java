package TableModel;

import Entity.Echiquier;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model pour le Jtable du Lobby
 * @author T4g1
 */
public class EchiquierTableModel extends AbstractTableModel {
    private List<Echiquier> l_echiquier;
    private String[] columnNames = {
            "Nom de la partie",
            "Nombre de joueurs présent"
    };
    
    public void setListing(List<Echiquier> l_echiquier) {
        this.l_echiquier = l_echiquier;
    }

    @Override
    public int getRowCount() {
        if(l_echiquier != null) {
            return l_echiquier.size();
        }
        
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(l_echiquier == null) {
            return null;
        }
        
        Echiquier echiquier = l_echiquier.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return echiquier.getNom();
            case 1:
                return echiquier.getPlayerCount() + "/2";
            case 2:
                return echiquier;
        }
        
        return null;
    }
    
    
}
