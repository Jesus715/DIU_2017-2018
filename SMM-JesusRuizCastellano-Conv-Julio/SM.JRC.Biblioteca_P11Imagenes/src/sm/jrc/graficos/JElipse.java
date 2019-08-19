/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * Clase para definir la Elipse como otro tipo de JFigura.
 * @author Jesús Ruiz Castellano
 */
public class JElipse extends JFigura{

    /**
     * Variable para indicar si la elipse es rellena o no.
     */
    private boolean esRellena;
    
    /**
     * Atributos propios del relleno para asignárselos a la elipse.
     */
    private JRelleno relleno;
    
    /**
     * Variable para asignar la forma.
     */
    private Ellipse2D elipse;
 
    
// ******************************* CONSTRUCTORES *******************************  

    /**
     * Crea una figura Rectángulo vacía.
     */
    public JElipse() {
        elipse = new Ellipse2D.Double();
        esRellena = false;
        relleno = new JRelleno();
    }
    
    /**
     * Crea una elipse vacía, en el punto pasado como argumento.
     * @param arg0 : punto inicial
     * @param traz : trazo de la figura
     * @param relle : relleno de creación de la elipse
     */
    public JElipse (Point2D arg0, JTrazo traz, JRelleno relle) {
        super(traz, arg0, arg0);
        trazo = traz;
        pInicial = pFinal = arg0;
        elipse = new Ellipse2D.Double();
        relleno = relle;
        esRellena = relleno.esRellena;
    }
    
    
    
// ******************************* SOBREESCRITAS *******************************
    
    
    
    /**
     * Método para obtener el relleno de una figura
     * @return JRelleno : el relleno de la figura
     */
    @Override
    public JRelleno getRelleno() {
        return relleno;
    }
    
    /**
     * Método para actualizar el relleno de la figura con el que le pasamos
     * @param relle : nuevo relleno
     */
    @Override
    public void setRelleno(JRelleno relle){
        relleno = relle;
    }
    
    /**
     * Método para ver si un punto está contenido en la elipse.
     * @param pos : punto a comprobar
     * @return boolean - si está o no.
     */
    @Override
    public boolean contains(Point2D pos) {
        return elipse.contains(pos);
    }

    /**
     * Método para mover una elipse a un nuevo punto.
     * @param p : punto al que se va a mover la figura
     */
    @Override
    public void setLocation(Point2D p) {
       elipse.setFrame(p.getX(),p.getY(), elipse.getWidth(), elipse.getHeight());
    }

    /**
     * Método para obtener la posición de la elipse a través del rectángulo que la
     * delimita.
     * @return Point2D - el punto de comienzo de la elipse
     */
    @Override
    public Point2D getLocation() {
        return elipse.getBounds().getLocation();
    }

    /**
     * Rectángulo que enmarca la figura 
     * @return Rectangle2D - Marco delimitador
     */
    @Override
    public Rectangle2D getBounds2D() {
        return elipse.getBounds2D();
    }

    /**
     * Creación de la Elipse hasta un nuevo punto.
     * @param p : nuevo punto final de la figura 
     */
    @Override
    public void updateShape(Point2D p) {
        elipse.setFrameFromDiagonal(pInicial, p);
    }

    /**
     * Método para identificar que tipo de figura tenemos
     * @return un string con el identificador de la figura
     */
    @Override
    public String getTipoFigura() {
        return "elipse";
    }
    
    /**
     * Método para pintar la figura asignándole los atributos correspondientes.
     * Para el degradado necesito 2 puntos a partir de los cuales aplicar el
     * gradiente. Dependiendo del tipo de degradado (horizontal o vertical) 
     * modificaré esos puntos.
     * @param g2d : figura que se va a utilizar para pintar
     */
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setComposite(trazo.getComposicion());
        
        g2d.setRenderingHints(trazo.getRender());
        g2d.setStroke(trazo.getStrokeGros());
        g2d.setColor(trazo.getColor());
        
        if(!esRellena)
            g2d.draw(elipse);
        
        else {
            int tipoDegradado = relleno.getTipoDegradado();
            // Degradado horizontal
            Point2D uno = new Point2D.Double(elipse.getBounds().getMinX(),elipse.getBounds().getMaxY()/2);
            Point2D dos = new Point2D.Double(elipse.getBounds().getMaxX(),elipse.getBounds().getMaxY()/2);
            
            switch (tipoDegradado) {
                case 0: // Sin degradado
                    g2d.setPaint(relleno.getColorFrente());
                    break;
                case 2: // Degradado vertical
                    uno = new Point2D.Double(elipse.getBounds().getMaxX()/2,elipse.getBounds().getMaxY());
                    dos = new Point2D.Double(elipse.getBounds().getMaxX()/2,elipse.getBounds().getMinY());
                    break;
                case 3: // inclinado
                    uno = new Point2D.Double(elipse.getBounds().getMinX(),elipse.getBounds().getMinY());
                    dos = new Point2D.Double(elipse.getBounds().getMaxX(),elipse.getBounds().getMaxY());
                    break;
            }
            
            relleno.setDegradado(uno,dos); 
            g2d.setPaint(relleno.getDegradado());
            
            g2d.fill(elipse);
        }
    }
    
    /**
     * Método para asignar un nombre determinístico a la figura de este tipo, 
     * teniendo en cuenta que si tiene el mismo punto de origen que de fin, la
     * figura es un punto.
     * @return nombre
     */
    @Override
    public String toString() {
        return "Elipse " + trazo + relleno;
    }
    
}
