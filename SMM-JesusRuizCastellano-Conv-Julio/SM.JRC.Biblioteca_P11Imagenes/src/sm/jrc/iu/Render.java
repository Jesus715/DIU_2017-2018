/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.iu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Clase para diseñar el formato de cada ítem de los comboBox de los colores
 * @author Jesús Ruiz Castellano
 */
public class Render extends javax.swing.JPanel implements ListCellRenderer<Color> {

    /**
     * Crea una nueva forma PanelrenderColor, una nueva Celda.
     */
    public Render() {
        initComponents();
    }

    /**
     * Modifica el color de fondo de la celda al asignado a través de :
     * @param co : color de la celda
     */
    public void setColor (Color co) {
        botColor.setBackground(co);
    }
    
    /**
     * Modifica el tamaño de la celda con : 
     * @param i : tamaño de la anchura
     * @param j : tamaño de la altura
     */
    @Override
    public void setSize (int i, int j) {
        botColor.setSize(i,j);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botColor = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(40, 27));
        setLayout(null);

        botColor.setMinimumSize(new java.awt.Dimension(20, 20));
        botColor.setPreferredSize(new java.awt.Dimension(25, 25));
        add(botColor);
        botColor.setBounds(0, 0, 30, 30);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * En este método es donde especifico las propiedades que quiero que tenga 
     * cada una de las celdas del comboBox.
     * @param jlist : lista de colores
     * @param e : color de la celda
     * @param i : ítem seleccionado
     * @param bln : booleano
     * @param bln1 : booleano
     * @return Component - b, es un objeto ítem del comboBox de colores 
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Color> jlist, Color e, int i, boolean bln, boolean bln1) {
        
        Render b = new Render();
        b.setColor(e);
        b.setSize(24,24);

        return b;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botColor;
    // End of variables declaration//GEN-END:variables
}