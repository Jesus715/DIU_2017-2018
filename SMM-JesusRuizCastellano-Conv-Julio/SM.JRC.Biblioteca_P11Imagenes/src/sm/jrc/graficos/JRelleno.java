/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Point2D;

/**
 * Clase para definir las propiedades del atributo relleno que tiene una figura.
 * @author Jesús Ruiz Castellano
 */
public class JRelleno extends JAtributos {

    /**
     * Color principal del relleno.
     */
    private Color colorFrente;
    
    /**
     * Color secundario del relleno. Color de degradado.
     * Usado para aplicar la propiedad de color degradado.
     */
    private Color colorFondo;
    
    /**
     * Indica el tipo de degradado.
     */
    private int tipoDegradado;
    
    /**
     * Variable para identificar el degradado
     */
    private GradientPaint degradado;
    
    
// ******************************* CONSTRUCTORES *******************************
    
    
    /**
     * Constructor por defecto. Crea un objeto de tipo JRelleno en negro, con
     * opacidad total, sin relleno, sin degradado.
     */
    public JRelleno() {
        super (1.0f, false);
        colorFrente = colorFondo = Color.BLACK;
        tipoDegradado = 0;
    } 
    
    /**
     * Constructor con relleno. Crea un objeto de tipo JRelleno con
     * relleno del color indicado, sin degradado.
     * @param gradoTransparencia : grado de transparencia con el que se crea
     * @param co : color del relleno
     */
    public JRelleno(float gradoTransparencia, Color co) {
        super (gradoTransparencia, true);
        tipoDegradado = 0;
        colorFrente = colorFondo = co;
    }
    

    /**
     * Constructor con parámetros. Crea un objeto de tipo JRelleno según el tipo
     * de degradado. Si existe degradado se asignan los diferentes colores al
     * color de frente y al de fondo. Si no, se asigna el color de frente a ambos.
     * @param gradoTransparencia : grado de transparencia con el que se crea
     * @param colorFre : color principal del relleno
     * @param colorFon : color secundario
     * @param tipoDeg : tipo de degradado (horizontal, vertical)
     */
    public JRelleno(float gradoTransparencia, int tipoDeg, Color colorFre, Color colorFon) {
        super (gradoTransparencia,true);
        tipoDegradado = tipoDeg;
        
        if (tipoDegradado > 0) {
            colorFrente = colorFre;
            colorFondo = colorFon;  
        } else 
           colorFrente = colorFondo = colorFre;
    } 
    
    
    
// ***************************** GETTERS / SETTERS ***************************** 
    
    /**
     * Método para obtener el color del frente del degradado.
     * @return Color - colorFrente : del degradado.
     */
    public Color getColorFrente() {
        return colorFrente;
    }

    /**
     * Método para asignar el color principal del degradado del relleno.
     * @param colorFrent : nuevo color principal
     */
    public void setColorFrente(Color colorFrent) {
        colorFrente = colorFrent;
    }

    /**
     * Método para obtener el color del fondo del degradado.
     * @return Color - colorFondo : color secundario del degradado.
     */
    public Color getColorFondo() {
        return colorFondo;
    }

    /**
     * Método para asignar el color secundario del degradado del relleno.
     * @param colorFond : nuevo color secundario
     */
    public void setColorFondo(Color colorFond) {
       colorFondo = colorFond;
    }

    /**
     * Método para obtener el tipo de degradado del relleno de la figura.
     * @return int - tipoDegradado : orientación del degradado del relleno.
     * 0, sin degradado
     * 1, horizontal
     * 2, vertical
     * 3, inclinado
     */
    public int getTipoDegradado() {
        return tipoDegradado;
    }

    /**
     * Método para asignar el tipo del degradado : nulo, horizontal o vertical.
     * @param tipoDegra : nuevo tipo de degradado
     */
    public void setTipoDegradado(int tipoDegra) {
        tipoDegradado = tipoDegra;
    }

    /**
     * Método para obtener el degradado
     * @return Paint - degradado
     */
    public GradientPaint getDegradado() {
        return degradado;
    }

    /**
     * Método para editar el degradado. Aquí es done se va a crear el objeto
     * GradientPaint con el que voy a pintar el degradado de la figura en función
     * de los puntos dados y de los colores de frente y fondo.
     * Obtenido de : (1) http://swing-facil.blogspot.com/2012/03/gradientpaint-demo-ejemplos-del-uso-de.html
     * @param uno : punto de inicio del degradado
     * @param dos : segundo punto que delimita la linea inicial del degradado
     */
    public void setDegradado(Point2D uno, Point2D dos) {       
        if (tipoDegradado != 0)
            /**
             * Documentación - Bibliografía : (1) http://swing-facil.blogspot.com/2012/03/gradientpaint-demo-ejemplos-del-uso-de.html
             */
            degradado = new GradientPaint(uno, colorFrente, dos, colorFondo);
        else {
            colorFrente = colorFondo;
        }
    }

    
    
    
    /**
     * Método para asignar un String al relleno de la figura.
     * Debido a no haber (o más bien no conocer) un método más sencillo para 
     * imprimir el nombre de un color he tenido que optar por usar las estructuras
     * if/else if para ir asignando los diferentes nombres de colores.
     * @return Devuelve el String con el color y el tipo del degradado de
     *         del relleno de la figura. 
     */
    @Override
    public String toString() {
        
        String relleno = "";
        String col = "";
        String colFond = "";
        
        if (colorFrente == Color.black)
            col = " negro";
        else if (colorFrente == Color.white)
            col = " blanco";
        else if (colorFrente == Color.red)
            col = " rojo";
        else if (colorFrente == Color.green)
            col = " verde";
        else if (colorFrente == Color.blue)
            col = " azul";
        else if (colorFrente == Color.yellow)
            col = " amarillo";
        else if (colorFrente == Color.cyan)
            col = " cyan";
        else if (colorFrente == Color.orange)
            col = " naranja";
        else if (colorFrente == Color.pink)
            col = " rosa";
        else if (colorFrente == Color.magenta)
            col = " magenta";
        else if (colorFrente == Color.gray)
            col = " gris";
        
        if (colorFondo == Color.black)
            colFond = " negro";
        else if (colorFondo == Color.white)
            colFond = " blanco";
        else if (colorFondo == Color.red)
            colFond = " rojo";
        else if (colorFondo == Color.green)
            colFond = " verde";
        else if (colorFondo == Color.blue)
            colFond = " azul";
        else if (colorFondo == Color.yellow)
            colFond = " amarillo";
        else if (colorFondo == Color.cyan)
            colFond = " cyan";
        else if (colorFondo == Color.orange)
            colFond = " naranja";
        else if (colorFondo == Color.pink)
            colFond = " rosa";
        else if (colorFondo == Color.magenta)
            colFond = " magenta";
        else if (colorFondo == Color.gray)
            colFond = " gris";
        
        
        if (esRellena())
            switch (tipoDegradado) {
                case 0:
                    relleno = col;
                    break;
                case 1:
                    relleno = col + ", deg.: -, " + colFond;
                    break;
                case 2:
                    relleno = col + ", deg.: |, " + colFond;
                    break;
            }
        else
            relleno = " sin relleno";
        
        return relleno;
    }
    
}
