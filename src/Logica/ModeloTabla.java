/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jp
 */
public class ModeloTabla extends AbstractTableModel {

    private final Class[] tipoColumnas = null;
    private final String[] cabeceras = null;
    private List<Object> listaObjetos = new ArrayList<>();

    public ModeloTabla() {
        super();
       

    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void resizeColumnWidth() {
        int cumulativeActual = 0;
        int padding = 15;
        for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
            /* int width = 50; // Min width 
     TableColumn column = columnModel.getColumn(columnIndex); 
     for (int row = 0; row < getRowCount(); row++) { 
      TableCellRenderer renderer = getCellRenderer(row, columnIndex); 
      Component comp = prepareRenderer(renderer, row, columnIndex); 
      width = Math.max(comp.getPreferredSize().width + padding, width); */
        }
        /*  if (columnIndex < getColumnCount() - 1) { 
      column.setPreferredWidth(width); 
      cumulativeActual += column.getWidth(); 
     } else { //LAST COLUMN 
      //Use the parent's (viewPort) width and subtract the previous columbs actual widths. 
      column.setPreferredWidth((int) getParent().getSize().getWidth() - cumulativeActual); 
     } */
    }
}
