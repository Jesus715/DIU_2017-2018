/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Stroke;

/**
 * Clase para definir las propiedades del atributo trazo que tiene una figura.
 * @author Jesús Ruiz Castellano
 */
public class JTrazo extends JAtributos {
    
    /**
     * Color del trazo.
     */
    private Color color;
    
    /**
     * Anchura de la línea de trazo.
     */
    private int grosor;
    
    /**
     * Indica si el trazo es continuo o no.
     * true, si es discontinuo
     * false, si es liso
     */
    private boolean esDiscontinuo;
    
    /**
     * Indica el tipo de trazo.
     */
    private Stroke strokeGros;
    
    /**
     * Indica si la figura es alisada.
     */
    protected boolean esAlisado;
    
    /**
     * Variable asociada al alisado del contorno.
     */
    private RenderingHints render;
    
     
    /**
     * Vector para definir el patrón de discontinuidad del trazo, el cuál se
     * va a mantener constante.
     */
    private float[] patronTrazo = {5.0f,5.0f};

  
// ******************************* CONSTRUCTORES ******************************* 
    
    
    /**
     * Constructor por defecto. Crea un objeto de tipo JTrazo en negro, con
     * un grosor normal, línea continua y sin alisar.
     */
    public JTrazo() {
        super(1.0f, false);
        esAlisado = false;
        color = Color.BLACK;
        grosor = 1;
        esDiscontinuo = false;
        strokeGros = new BasicStroke(grosor);
        render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF); 
    }
    
    /**
     * Constructor por parámetros. Crea un trazo con las propiedades que se le 
     * pasan a través de los siguientes parámetros. 
     * En función de si es discontinuo o no, se crea un tipo de Stroke diferente.
     * Esto también ocurre en el caso de si es alisado o no con el Renderizado.
     * @param gradoTransparencia : grado de transparencia con el que se crea
     * @param esAlisa : bandera que indica si la figura esta alisada o no.
     * @param col : color con el que se crea el trazo de la figura.
     * @param groso : grosor incial de la silueta de la figura.
     * @param esDiscont  : variable para indicar si el trazo es inicialmente
     *                         liso o discontinuo.
     */
    public JTrazo( float gradoTransparencia, boolean esAlisa, Color col, int groso, boolean esDiscont) {
        super(gradoTransparencia, false);
        esAlisado = esAlisa;
        color = col;
        grosor = groso;
        esDiscontinuo = esDiscont;
      
        if (esAlisado)    
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        else 
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
        
        if (esDiscontinuo)
            strokeGros = new BasicStroke(grosor, 1, BasicStroke.JOIN_MITER, 1.0f, patronTrazo, 0.0f);
        else
            strokeGros = new BasicStroke(grosor);
    }
  
    
// ***************************** GETTERS / SETTERS ***************************** 

    
    /**
     * Método para asignar el grosor del trazo.
     * @param numero : anchura del grosor
     */
    public void setGrosor(int numero) {
        grosor = numero;
    }

    /**
     * Método para obtener el grosor del trazo.
     * @return int - grosor : el ancho del trazo.
     */
    public int getGrosor() {
        return grosor;
    }
    
    /**
     * Método para asignar el tipo de Stroke del trazo. 
     * Si es continuo, se asignara un Stroke básico a partir del grosor.
     * Si es discontinuo se crea un trazo discontinuo con terminaciones cuadradas
     * al estilo Miter de las líneas con el patrón de discontinuidad por defecto 
     * y el grosor pasado por parámetro.
     * @param gros : nuevo grosor del trazo.
     */
    public void setStrokeGrosor(int gros) {
        grosor = gros;
        if (!esDiscontinuo) 
            /**
             * Liso
             */
            strokeGros = new BasicStroke(grosor);
        else  
            /**
             * Discontinuo
             */
            strokeGros = new BasicStroke(grosor,1,BasicStroke.JOIN_MITER,1.0f,patronTrazo, 0.0f);
    }

    /**
     * Método para obtener el Stroke del trazo.
     * @return Stroke - strokeGros : el Stroke del trazo.
     */
    public Stroke getStrokeGros() {
        return strokeGros;
    }
    
    /**
     * Método para obtener el color del trazo.
     * @return Color - color : del trazo.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método para asignar el color del trazo.
     * @param color : nuevo color del trazo
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Método para comprobar si está alisada la figura.
     * @return boolean - esAlisado : la bandera que indica si está alisada. 
     * true, está alisada
     * false, no está alisada
     */
    public boolean esAlisado() {
        return esAlisado;
    }

    /**
     * Método para asignar un nuevo estado de alisado.
     * @param esAlisada : modifica el estado de alisado
     */
    public void setAlisado(boolean esAlisada) {
        this.esAlisado = esAlisada;
    }
    
    /**
     * Método para obtener el grosor del trazo.
     * @return boolean - esDiscontinuo : indica si la línea es discontinua o no.
     * true, si es discontinuo
     * false, es línea continua
     */
    public boolean esDiscontinuo() {
        return esDiscontinuo;
    }

    /**
     * Método para definir la continuidad o discontinuidad de la línea del trazo
     * de la figura.
     * @param esDiscontinuo : nuevo estado de discontinuidad
     * true, es discontinuo
     * false, es continuo
     */
    public void setEsDiscontinuo(boolean esDiscontinuo) {
        this.esDiscontinuo = esDiscontinuo;
    }

    /**
     * Método para obtener el renderizado del trazo.
     * @return RenderingHints - render : el renderizado del trazo.
     */
    public RenderingHints getRender() {
        return render;
    }

    /**
     * Método para asignar el tipo de Renderizado del trazo. 
     * Si es alisado, se activará el VALUE_ANTIALIAS.
     * Si no es alisado, se desactivará el VALUE_ANTIALIAS.
     * @param esAlisa : nuevo estado de alisado
     */
    public void setRender(boolean esAlisa) {
        esAlisado = esAlisa;
        if (esAlisado)
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        else 
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    

    /**
     * Método para obtener el patrón de discontinuidad del trazo.
     * @return float[] - patronTrazo : vector con el patrón de discontinuidad.
     */
    public float[] getPatronTrazo() {
        return patronTrazo;
    }


    /**
     * Método para asignar un String al trazo de la figura.
     * Debido a no haber (o más bien no conocer) un método más sencillo para 
     * imprimir el nombre de un color he tenido que optar por usar la estructura
     * if/else if para ir asignando los diferentes nombres de colores.
     * @return Devuelve el String con el color y la discontinuidad de la línea 
     *         de trazo.
     */
    @Override
    public String toString() {  
        String continua = "--- ";
        String col = "";
        
        if (esDiscontinuo)
            continua = "- - - ";
        if (esAlisado)
            continua += "alisada";
        
        if (color == Color.black)
            col = "negro";
        else if (color == Color.white)
            col = "blanco";
        else if (color == Color.red)
            col = "rojo";
        else if (color == Color.green)
            col = "verde";
        else if (color == Color.blue)
            col = "azul";
        else if (color == Color.yellow)
            col = "amarillo";
        else if (color == Color.cyan)
            col = "cyan";
        else if (color == Color.orange)
            col = "naranja";
        else if (color == Color.pink)
            col = "rosa";
        else if (color == Color.magenta)
            col = "magenta";
        else if (color == Color.gray)
            col = "gris";
        

        return  continua + " [" + col + "] GROSOR = " + grosor ;
    }  
}
