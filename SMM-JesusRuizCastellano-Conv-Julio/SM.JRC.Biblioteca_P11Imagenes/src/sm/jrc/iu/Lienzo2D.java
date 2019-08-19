/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.iu;


import java.awt.BasicStroke;
import sm.jrc.graficos.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para dibujar figuras
 * @author Jesús Ruiz Castellano
 */
public class Lienzo2D extends javax.swing.JPanel {

    private ArrayList<JFigura> vFiguras;
    private JFigura figura, figuraEditar;
    private JTrazo trazo;
    private JRelleno relle;
    private Point2D pAux, puntoMovimiento;
    private Color colorT, colorF, colorFond;
    private TipoFormas forma;
    private boolean relleno;
    private float gradotransparencia;
    private boolean alisado;
    private boolean mover;
    private boolean editar;
    private boolean esDiscont;
    private int numEstado;
    private Stroke strokeGros;
    private int grosor;
    private int tipoDegradado;
    
    /**
     * Crea nuevo Lienzo2D
     */
    public Lienzo2D() {
        iniciar();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        formMouseDragged(evt);
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pAux = evt.getPoint();

        if (editar)  {
           figuraEditar = figura; 
           figura = null;
        }
        else 
            createJFigura(evt);
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(mover) {
            if (figuraEditar != null)
                figuraEditar.setLocation(evt.getPoint());
        }
        else {
            figura.updateShape(evt.getPoint());

            boolean esta = false;

            for (int i = 0 ; i < vFiguras.size() && !esta ; i++ )
                if (figura == vFiguras.get(i)) 
                    esta = true;

            if (!esta) 
                vFiguras.add(figura);

        }
        
        repaint();
    }//GEN-LAST:event_formMouseDragged


// --------------------------------- GETTERS -----------------------------------    

    /**
     *
     * @return
     */
    public ArrayList<JFigura> getvFiguras() {
        return vFiguras;
    } 
    
    public boolean isRelleno() {
        return relleno;
    }

    public float gradoTransparencia() {
        return gradotransparencia;
    }

    public boolean isAlisado() {
        return alisado;
    }

    public boolean isMover() {
        return mover;
    }
    
    public Color getColorTrazo() {
        return colorT;
    }

    public int getTipoDegradado() {
        return tipoDegradado;
    }
 
    public Color getColorFrente() {
        return colorF;
    }
    
    public Color getColorFondo() {
        return colorFond;
    }
    
    public TipoFormas getForma() {
        return forma;
    }    
    
    public int getNumEstado() {
        if (forma == TipoFormas.LINEA)
            numEstado = 1;
        else if (forma == TipoFormas.RECTANGULO && !relle.esRellena())
            numEstado = 2;
        else if (forma == TipoFormas.RECTANGULO && relle.esRellena())
            numEstado = 3;
        else if (forma == TipoFormas.ELIPSE && !relle.esRellena())
            numEstado = 4;
        else if (forma == TipoFormas.ELIPSE && relle.esRellena())
            numEstado = 5;
 
        return numEstado;
    }
    
    public boolean getEditar() {
        return editar;
    }

    public int getGrosor () {
        return grosor;
    }
        
    public Stroke getStrokeGrosor() {
        return strokeGros;
    }
    
    public JFigura getSelectedJFigura(){
        
        for (JFigura s:vFiguras)
            if (s == figuraEditar) 
                return s;
        
        return null;
    }
    
    public void setSelectedJFigura(JFigura figSeleccionada){
        figuraEditar = figSeleccionada;
    }

    public Point2D getPuntoMov() {
        return puntoMovimiento;
    }

    public JTrazo getTrazo() {
        
        JTrazo traz;
        
        if (figuraEditar!= null)
            traz = figuraEditar.getTrazo();
        else 
            traz = trazo;
        
        return traz;
    }

    public JRelleno getRelle() {
        
        JRelleno rell;
        
        if (figuraEditar!= null)
            rell = figuraEditar.getRelleno();
        else
            rell = relle;
        
        return rell;
    }
    
    
    
// --------------------------------- SETTERS ----------------------------------- 
    
    public void setvFiguras(ArrayList<JFigura> vSh) {
        vFiguras = vSh;
    }
    
    // Para el InitComponents de LienzoImagen2D
    public void setColor (Color co) {
        relle.setColorFondo(co);
        relle.setColorFrente(co);
        
        if (figuraEditar!= null)
            figuraEditar.getTrazo().setColor(co);
        
        repaint();
    }
    
    public void setColorTrazo(Color col) {
        colorT = col;
//        figura.getTrazo().setColor(colorT);
        
        if (figuraEditar!= null && editar) // Editar figura seleccionada
            figuraEditar.getTrazo().setColor(colorT);
        else // crear figura
            trazo.setColor(colorT);
        
        repaint();
    }    

    public void setTipoDegradado(int tipoDegrad) {
        tipoDegradado = tipoDegrad;
        
        if (figuraEditar!= null)
            figuraEditar.getRelleno().setTipoDegradado(tipoDegradado);
        
//        if (mover)
//            figura.getRelleno().setDegradado(tipoDegradado, figura., pAux);
        
        repaint();
    }
    
    public void setColorFrente(Color col) {
        colorF = col;
        
        if (figuraEditar!= null)
            figuraEditar.getRelleno().setColorFrente(colorF);
//        if (mover) // Editar figura seleccionada
//            figura.getRelleno().setColorFrente(colorF);
//        else // crear figura
//            relle.setColorFrente(colorF);
        
        repaint();
    } 
    
    public void setColorFondo(Color col) {
        colorFond = col;
        
        if (figuraEditar!= null)
            if (tipoDegradado != 0)
                figuraEditar.getRelleno().setColorFondo(colorFond);
            else
                figuraEditar.getRelleno().setColorFondo(colorF);
//            if (mover) // Editar figura seleccionada
//                figura.getRelleno().setColorFondo(colorFond);
//            else // crear figura
//                relle.setColorFondo(colorFond);
//        else
//            if (mover)
//                figura.getRelleno().setColorFondo(colorF);
//            else    
//                relle.setColorFondo(colorF); 
        
        repaint();
    } 
    
    public void setForma(TipoFormas form) {
        forma = form;
    }    
    
    public void setNumEstado(int numE) {
        numEstado = numE;
    }

    
    public void setEditar (boolean edi) {
        editar = edi;
    }
    
    
    public void setRelleno(boolean rell) {
        relleno = rell;
//        figura.getRelleno().setRellena(relleno);
        if (figuraEditar!= null && editar) // Editar figura seleccionada
            figuraEditar.getRelleno().setRellena(relleno);
        else // crear figura
            relle.setRellena(relleno);
        
        repaint();
    }

    public void setTransparencia(int valor) {
        gradotransparencia = valor;
        
        if (figuraEditar!= null && editar) // Editar figura seleccionada
            figuraEditar.getTrazo().setGradotransparencia((int)gradotransparencia);
        else {// crear figura
            trazo.setGradotransparencia((int)gradotransparencia);
        }
        
        repaint();
    }

    public void setAlisado(boolean ali) {
        alisado = ali;

        if (figuraEditar!= null && editar) { // Editar figura seleccionada
            figura.getTrazo().setAlisado(alisado);
            figuraEditar.getTrazo().setRender(alisado);
        }
        else { // crear figura
            trazo.setAlisado(alisado);
            trazo.setRender(alisado);
        }
        
        repaint();
    }

    public void setMover(boolean mov) {
        mover = mov;
    }
    

    /**
     * 
     * @param esDisco 
     */
    public void setEsDiscont(boolean esDisco) {
        esDiscont = esDisco;
        
        if(figuraEditar!= null && editar) // Editar figura seleccionada
            figuraEditar.getTrazo().setEsDiscontinuo(esDiscont);
        else // crear figura 
            trazo.setEsDiscontinuo(esDiscont);
    }
    
    
    
    public void setStrokeGrosor(int gros) {
        grosor = gros;
        
        //strokeGros = figuraEditar.getTrazo().getStrokeGros();

        if(figuraEditar!= null && editar) // Editar figura seleccionada
            figuraEditar.getTrazo().setStrokeGrosor(grosor);
        else {// crear figura 
            trazo.setStrokeGrosor(grosor);
            //System.out.println("    ENTRA EN CREAR EN LIENZOOOOOOOO ");
        }
        
        repaint();
    }
  
    

// --------------------------------- PROPIAS -----------------------------------
    
    public void iniciar() {
        pAux = puntoMovimiento = new Point(-10,-10);
        colorT = colorF = colorFond = Color.BLACK;
        gradotransparencia = 100.0f;
        forma = TipoFormas.LINEA;
        trazo = new JTrazo();
        relle = new JRelleno(); 
        relleno = alisado = mover = esDiscont = editar = false;
        numEstado = tipoDegradado = 0;
        grosor = 1;
        strokeGros = new BasicStroke(grosor);
        vFiguras = new ArrayList();
        initComponents();
    }
    

    private void createJFigura (java.awt.event.MouseEvent evt) {

        trazo = new JTrazo (gradotransparencia,alisado,colorT,grosor,esDiscont);
        
        if (relleno) {
            relle = new JRelleno(gradotransparencia, tipoDegradado, colorF, colorFond);
          //  System.out.println("HA CREADO UN RELLENO");
        }
        else
            relle = new JRelleno();
        
        switch (forma) {
            
            case LINEA:
                figura = new JLinea(pAux,trazo,relle);
                break;
            case RECTANGULO:
                figura = new JRectangulo(pAux,trazo,relle);
               // System.out.println("RECTANGULO");
                break;
            case ELIPSE:
                figura = new JElipse(pAux,trazo,relle);
                break;
        }   
    }
    
    
    /**
     * 
     * @param g 
     * @param anchura 
     * @param altura 
     */
    public void marcoLienzo (Graphics g, int anchura, int altura) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        // creo un rectángulo con los limites del panel del Lienzo
        Rectangle2D margen = new Rectangle(super.getBounds());
        margen.setFrameFromDiagonal(0, 0, anchura, altura);
        g2d.setPaint(Color.GRAY);
        Stroke margenTrazo; 
        float patron[] = {5.0f,5.0f};
        margenTrazo = new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, patron, 0.0f);
        g2d.setStroke(margenTrazo);            
        g2d.setClip(margen);
        g2d.draw(margen);
    }
    
    
    @Override
    public void paint (Graphics g) {
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        // pinto el marco del Lienzo
        marcoLienzo(g, super.getWidth()-10, super.getHeight()-10);
        
        System.out.println("(4) TAM de vFiguras = " + vFiguras.size());
        
        
        
        for (JFigura s: vFiguras) {
            //System.out.println(" (5) PINTANDO FIGURA EN LIENZO " + figura);
            s.paint(g2d);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
