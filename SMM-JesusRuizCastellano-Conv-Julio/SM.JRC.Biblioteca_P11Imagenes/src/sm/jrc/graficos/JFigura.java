/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Se trata de una súper clase para definir las diferentes formas geométricas
 * que se van a poder dibujar. 
 * De aquí heredarán los atributos el resto de subclases figura. Serán de 
 * visibilidad de tipo protegido, para que las subclases puedan tener acceso 
 * directo a ellos.
 * @author Jesús Ruiz Castellano 
 */
public abstract class JFigura {
    
    /**
     * Atributos propios del trazo para asignárselos a la figura.
     */
    protected JTrazo trazo;
    
    /**
     * Punto de comienzo de la línea.
     */
    protected Point2D pInicial;
    
    /**
     * Punto de fin de la línea.
     */
    protected Point2D pFinal;
    
// ******************************* CONSTRUCTORES *******************************
    
    
    /**
     * Crea una figura por defecto, cuyos puntos están fuera del plano, y con un
     * trazo y relleno por defecto.
     */
    public JFigura() {
        pInicial = pFinal = new Point(-10,-10);
        trazo = new JTrazo();
    }

    /**
     * Crea una figura con unos puntos determinados y un trazo por defecto.
     * @param pIni : punto de inicio, de creación de la figura.
     * @param pFin : punto de finalización de la figura.
     */
    public JFigura(Point2D pIni, Point2D pFin) {
        trazo = new JTrazo();
        pInicial = pIni;
        pFinal = pFin;
    }
    
    /**
     * Crea una figura con unos puntos y un trazo determinados.
     * @param traz : trazo que tendrá la figura.
     * @param pIni : punto de inicio, de creación de la figura.
     * @param pFin : punto de finalización de la figura.
     */
    public JFigura(JTrazo traz, Point2D pIni, Point2D pFin) {
        trazo = traz;
        pInicial = pIni;
        pFinal = pFin;
    }
    
    
// ***************************** GETTERS / SETTERS *****************************

    /**
     * Método para obtener el trazo.
     * @return JTrazo : trazo actual de la figura
     */
    public JTrazo getTrazo() {
        return trazo;
    }

    /**
     * Método para asignar un nuevo trazo a la figura.
     * @param traz : trazo nuevo de la figura
     */
    public void setTrazo(JTrazo traz) {
        trazo = traz;
    }

    /**
     * Método para obtener el punto de inicio de la figura.
     * @return Point2D : el punto inicial
     */
    public Point2D getpInicial() {
        return pInicial;
    }


    /**
     * Método para obtener el punto de fin de la figura.
     * @return Point2D : el punto final 
     */
    public Point2D getpFinal() {
        return pFinal;
    }

    /**
     * Método para modificar el punto final de la figura.
     * @param pFin : nuevo punto final
     */
    public void setpFinal(Point2D pFin) {
        pFinal = pFin;
    }
    
    
    
    
// ********************************* ABSTRACTAS ********************************
    
    /**
     * Método que devuelve si el punto pasado como parámetro pertenece a la figura.
     * @param pos : punto que se quiere comprobar
     * @return boolean : Indica si el punto pertenece a la figura o no.
     */
    public abstract boolean contains(Point2D pos);
    
    /**
     * Método para mover la figura seleccionada al punto indicado.
     * @param p : punto al que se quiere mover la figura
     */
    public abstract void setLocation( Point2D p);
    
    /**
     * Método para obtener el punto de inicio de la figura.
     * @return Point2D : punto de inicio
     */
    public abstract Point2D getLocation();
    
    /**
     * Método para crear el rectangulo que delimita a la figura
     * @return Rectangle2D : el marco delimitador
     */
    public abstract Rectangle2D getBounds2D();
    
    /**
     * Método para crear finalmente la figura, una vez que se le pasa el punto
     * de fin.
     * @param p : punto de fin de creación de la figura
     */
    public abstract void updateShape(Point2D p);
    
    
    /**
     * Método para obtener el relleno de una figura
     * @return JRelleno : el relleno de la figura
     */
    public abstract JRelleno getRelleno();
    
    /**
     * Método para actualizar el relleno de la figura con el que le pasamos
     * @param relle : nuevo relleno
     */
    public abstract void setRelleno(JRelleno relle);
    
    /**
     * Método para identificar que tipo de figura tenemos
     * @return un string con el identificador de la figura
     */
    public abstract String getTipoFigura();
    
    /**
     * Método para dibujar la figura.
     * @param g2d : figura que se va a dibujar
     */
    public abstract void paint(Graphics2D g2d);
    
    /**
     * Método para asignar un String a la figura. Será sobrecargado en cada tipo
     * específico de figura.
     * @return String : el nombre de la figura 
     */
    @Override
    public abstract String toString();
    
   
}
