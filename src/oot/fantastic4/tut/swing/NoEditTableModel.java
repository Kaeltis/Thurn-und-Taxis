package oot.fantastic4.tut.swing;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Patrick on 05.01.14.
 */
public class NoEditTableModel extends DefaultTableModel {

    public NoEditTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
