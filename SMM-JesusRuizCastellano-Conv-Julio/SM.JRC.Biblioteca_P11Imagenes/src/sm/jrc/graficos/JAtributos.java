/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.graficos;

import java.awt.AlphaComposite;
import java.awt.Composite;

/**
 * Clase que agrupa todos los atributos de una misma Figura.
 * @author Jesús Ruiz Castellano
 */
public class JAtributos {
    
    /**
     * Indica el grado de transparencia de la figura. 
     */
    protected float gradoTransparencia;
    
    /**
     * Variable para editar la composicion de la figura.
     * Asociada a la transparencia.
     */
    protected Composite composicion;
    
    /**
     * Indica si la figura tiene relleno.
     */
    protected boolean esRellena;
    

// ******************************* CONSTRUCTORES ******************************* 
    
    
    /**
     * Constructor por defecto. Crea unos atributos por defecto, con forma de Punto,
     * el máximo de opacidad, con un trazo por defecto, sin relleno.
     */
    public JAtributos() {
        gradoTransparencia = 1.0f;
        composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gradoTransparencia);
        esRellena = false;   
    }
    
    /**
     * Constructor por parámetros. Crea unos atributos a partir de los siguientes
     * parámetros : 
     * @param gradoTransparen : grado indicado por el deslizador de la interfaz
     * @param esRell : indica si esta rellena
     */
    public JAtributos(float gradoTransparen, boolean esRell) {
        gradoTransparencia = (float)gradoTransparen/100;
        composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gradoTransparencia);
        esRellena = esRell;
    }


// ***************************** GETTERS / SETTERS *****************************     
    
    
    /**
     * Método para comprobar si es transparente. 
     * Si el grado de transparencia es menor que 100, tiene transparencia.
     * @return boolean - esTransparente, la bandera de transparencia. 
     * true, es transparente
     * false, no es transparente
     */
    public boolean esTransparente() {
        return (gradoTransparencia < 100.0f);
    }

    /**
     * Obtencion del valor del grado de transparencia de la figura.
     * @return float - gradoTransparencia : el valor que contiene dicho grado. 
     * 0, Totalmente transparente
     * 100, Totalmente opaca
     */
    public float getGradotransparencia() {
        return gradoTransparencia;
    }

    /**
     * Método para asignar un valor al grado de la transparencia.
     * Como el valor del deslizador de la interfaz gráfica es de tipo entero, 
     * se le hace un casting a float, para poder pasárselo a la instancia de
     * AlphaComposite.
     * @param valor : valor obtenido del deslizador
     */
    public void setGradotransparencia(int valor) {
        gradoTransparencia = (float)valor/100;
        composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gradoTransparencia);
    }

    /**
     * Obtencion del valor de la composición. Asociado a la transparencia
     * @return Composite - composición : el valor que contiene dicha variable. 
     */
    public Composite getComposicion() {
        return composicion;
    }

    /**
     * Método para asignar un valor de composición. Asociado a la transparencia
     * @param composicion : la composición nueva
     */
    public void setComposicion(Composite composicion) {
        this.composicion = composicion;
    }
    
    /**
     * Método para comprobar si es rellena.
     * @return boolean - esRellena : la bandera del relleno. 
     * true, es rellena
     * false, si no es rellena
     */
    public boolean esRellena() {
        return esRellena;
    }

    /**
     * Método para asignar una nuevo estado de relleno.
     * @param esRellena : modifica el estado del relleno
     */
    public void setRellena(boolean esRellena) {
        this.esRellena = esRellena;
    }
    
    
    /**
     * Método para asignar un String a los atributos de la figura.
     * @return Devuelve el String con una breve descripción de los atributos.
     */
    @Override
    public String toString() {
        return null;
    }
}
