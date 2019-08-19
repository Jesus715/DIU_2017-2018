/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para definir el Rectángulo como un nuevo tipo de JFigura.
 * @author Jesús Ruiz Castellano
 */
public class JRectangulo extends JFigura {

    /**
     * Variable para indicar si el rectangulo es relleno o no.
     */
    private boolean esRellena;
    
    /**
     * Atributos propios del relleno para asignárselos a la figura.
     */
    private JRelleno relleno;
    
    /**
     * Variable para asignar la forma.
     */
    private Rectangle rectangulo;
    
    
// ******************************* CONSTRUCTORES *******************************

    /**
     * Crea una figura Rectángulo vacía.
     */
    public JRectangulo() {
        super ();
        rectangulo = new Rectangle();
        esRellena = false;
        relleno = new JRelleno();
    }

    /**
     * Crea una figura rectangular en el punto pasado como argumento.
     * @param arg0 : punto inicial de creación.
     * @param traz : trazo del rectángulo
     * @param relle : relleno del rectángulo
     */
    public JRectangulo(Point2D arg0, JTrazo traz, JRelleno relle) {
        super(traz, arg0, arg0);
        trazo = traz;
        pInicial = pFinal = arg0;
        rectangulo = new Rectangle((Point)arg0);
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
            
    @Override
    public boolean contains(Point2D pos) {
       return rectangulo.contains(pos);
    }

    /**
     * Método para mover un rectángulo al nuevo punto.
     * @param p : punto al que se va a mover la figura
     */
    @Override
    public void setLocation(Point2D p) {
        rectangulo.setFrame(p.getX(),p.getY(), rectangulo.getWidth(), rectangulo.getHeight());
    }

    /**
     * Método para obtener la posición del rectángulo.
     * @return Point2D - el punto de comienzo del rectángulo
     */
    @Override
    public Point2D getLocation() {
       return rectangulo.getLocation();
    }

    /**
     * Rectángulo que enmarca la figura 
     * @return Rectangle2D - Marco delimitador
     */
    @Override
    public Rectangle2D getBounds2D() {
        return rectangulo.getBounds2D();
    }

    /**
     * Creación del rectángulo hasta un nuevo punto.
     * @param p : nuevo punto final de la figura
     */
    @Override
    public void updateShape(Point2D p) {
        rectangulo.setFrameFromDiagonal(pInicial, p);
    }

    /**
     * Método para identificar que tipo de figura tenemos
     * @return un string con el identificador de la figura
     */
    @Override
    public String getTipoFigura() {
        return "rectangulo";
    }
    
    /**
     * Método para pintar la figura asignándole los atributos correspondientes.
     * Para el degradado necesito 2 puntos a partir de los cuales aplicar el
     * gradiente. Dependiendo del tipo de degradado (horizontal o vertical) 
     * modificaré esos puntos.
     * @param g2d : figura que se va a utilizar para pintar
     */
    @Override
    public void paint (Graphics2D g2d) {
        
        g2d.setComposite(trazo.getComposicion());
        g2d.setRenderingHints(trazo.getRender());
        g2d.setStroke(trazo.getStrokeGros());
        g2d.setColor(trazo.getColor());
        

        if(!esRellena)
            g2d.draw(rectangulo);
        
        else {
            int tipoDegradado = relleno.getTipoDegradado();
 
            // Degradado horizontal
            Point2D uno = new Point2D.Double(rectangulo.getBounds().getMinX(),rectangulo.getBounds().getMaxY()/2);
            Point2D dos = new Point2D.Double(rectangulo.getBounds().getMaxX(),rectangulo.getBounds().getMaxY()/2);
            
            switch (tipoDegradado) {
                case 0: // Sin degradado
                    g2d.setPaint(relleno.getColorFrente());
                    break;
                case 2: // Degradado vertical
                    uno = new Point2D.Double(rectangulo.getBounds().getMaxX()/2,rectangulo.getBounds().getMaxY());
                    dos = new Point2D.Double(rectangulo.getBounds().getMaxX()/2,rectangulo.getBounds().getMinY());
                    break;
                case 3: // inclinado
                    uno = new Point2D.Double(rectangulo.getBounds().getMinX(),rectangulo.getBounds().getMinY());
                    dos = new Point2D.Double(rectangulo.getBounds().getMaxX(),rectangulo.getBounds().getMaxY());
                    break;
            }
            
            relleno.setDegradado(uno,dos); 
            g2d.setPaint(relleno.getDegradado());
            
            g2d.fill(rectangulo);
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
        return "Rectángulo " + trazo + relleno;
    }
}
