/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para definir la Línea como uno de los tipos de JFigura.
 * @author Jesús Ruiz Castellano
 */
public class JLinea extends JFigura {
    
    
    /**
     * Variable para asignar la forma.
     */
    protected Line2D.Float linea;
    
    
    
// ******************************* CONSTRUCTORES *******************************
    
    /**
     * Crea una nueva JFigura JLinea por defecto. Es un punto fuera del plano.
     */
    public JLinea() {
        super();
        linea = new Line2D.Float();
    }
    
    /**
     * Crea una nueva JFigura JLinea situándola en el punto inicial arg0, y que
     * llega hasta el mismo punto (sería una figura punto).
     * @param arg0 : punto inicial
     * @param traz : trazo de la línea
     * @param re : relleno de la figura (no tiene, será uno vacío)
     */
    public JLinea(Point2D arg0, JTrazo traz, JRelleno re) {
        super(traz, arg0, arg0);
        trazo.setRellena(false);
        trazo = traz;
        pInicial = arg0;
        pFinal = arg0;
        linea = new Line2D.Float(); 
    }
    

    
// ******************************* SOBREESCRITAS *******************************

    /**
     * Método para obtener el relleno de una figura
     * @return JRelleno : null, ya que la línea no tiene relleno
     */
    @Override
    public JRelleno getRelleno() {
        return null;
    }
    
    /**
     * Método para actualizar el relleno de la figura con el que le pasamos
     * @param relle : nuevo relleno
     */
    @Override
    public void setRelleno(JRelleno relle){
        relle = null;
    }
    
    /**
     * Método para ver si un punto está contenido en la línea.
     * @param pos : punto a comprobar
     * @return boolean - si está o no.
     */
    @Override
    public boolean contains(Point2D pos) {        
        return (linea.ptLineDist(pos) <= 3.0);
    }
    
    /**
     * Método para mover una línea al nuevo punto.
     * @param nuevo : nuevo punto al q se va a mover la línea
     */
    @Override
    public void setLocation (Point2D nuevo) {
        double distanciaX, distanciaY;
        Point2D punto;
        
        distanciaX = (nuevo.getX() - linea.getX1());
        distanciaY = (nuevo.getY() - linea.getY1());
        punto = new Point2D.Double(linea.getX2() + distanciaX, linea.getY2() + distanciaY);
        linea.setLine(nuevo, punto);
    }

    /**
     * Método para obtener la posición de la línea.
     * @return Point2D - el punto de comienzo de la línea
     */
    @Override
    public Point2D getLocation() {
        return linea.getP1();
    }
    
    /**
     * Rectángulo que enmarca la figura línea
     * @return Rectangle2D - Marco delimitador
     */
    @Override
    public Rectangle2D getBounds2D() {
       return linea.getBounds();
    }

    /**
     * Creación de la línea hasta el nuevo punto.
     * @param p : nuevo punto final de creación de la línea
     */
    @Override
    public void updateShape(Point2D p) {
        linea.setLine(pInicial, p);
    }

    /**
     * Método para identificar que tipo de figura tenemos
     * @return un string con el identificador de la figura
     */
    @Override
    public String getTipoFigura() {       
        return "linea";
    }
    
    /**
     * Método para pintar la figura asignándole los atributos correspondientes
     * @param g2d : figura que se va a utilizar para pintar
     */
    @Override
    public void paint(Graphics2D g2d) {
        
        g2d.setComposite(trazo.getComposicion()); // transparencia
        g2d.setRenderingHints(trazo.getRender());
        g2d.setStroke(trazo.getStrokeGros());
        g2d.setColor(trazo.getColor());
        
        g2d.draw(linea);
    }

    /**
     * Método para asignar un nombre determinístico a la figura de este tipo, 
     * teniendo en cuenta que si tiene el mismo punto de origen que de fin, la
     * figura es un punto.
     * @return nombre
     */
    @Override
    public String toString() {
        return "Línea "+ trazo;
    }
}
