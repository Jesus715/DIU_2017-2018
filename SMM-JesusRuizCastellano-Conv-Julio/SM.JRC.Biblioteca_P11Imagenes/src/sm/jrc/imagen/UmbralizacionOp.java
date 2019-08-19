/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase creada para personalizar el operador de Umbralización.
 * @author Jesús Ruiz Castellano
 */
public class UmbralizacionOp extends BufferedImageOpAdapter {
   
    /**
     * Valor de referencia del umbral 
     */
    private int umbral;
    
    /**
     * Color que se va a usar para umbralizar
     */
    private Color color;
    
    /**
     * Variable para decidir qué operación utilizar
     */    
    private int tipo;
    
    /**
     * Crea una nueva forma UmbralizacionOp a partir del umbral obtenido a través
     * del deslizador de la VentanaPrincipal.
     * @param umbral : valor del deslizador
     */
    public UmbralizacionOp(int umbral) {
        this.umbral = umbral;
    }

    /**
     * Obtencion del color con el que se umbraliza
     * @return Color color - color para umbralizar
     */    
    public Color getColor() {
        return color;
    }
    /**
     * Selección del color con el que se umbraliza
     * @param color : color de la umbralización 
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Obtencion del tipo de operación para umbralizar
     * @return int tipo - tipo de operación de umbralización
     */
    public int getTipo() {
        return tipo;
    }
    /**
     * Selección del tipo de umbralización
     * @param tipo : tipo de operación de umbralización seleccionada
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    /**
     * Obtencion de la imagen con el operador de umbralización ya aplicado
     * @param src : imagen fuente
     * @param dest : imagen modificada con la operación elegida
     * @return BufferedImage filter - imagen con el raster correspondiente a la
     *         imagen modificada con el tipo de umbralización correspondiente
     */    
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException(" No hay ninguna imagen fuente");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        
        for (int x = 0; x < src.getWidth(); x++) 
            for (int y = 0; y < src.getHeight(); y++) {
                
                int[] RGB = null;  
                RGB = srcRaster.getPixel(x, y, RGB);
                
                /**
                 * Cálculo del valor medio
                 */
                int valorInt = (RGB[0] + RGB[1] + RGB[2]) /3; // Media
                int valorMin = 0;
                int valorMax = 255;
                /**
                 * Valores para la Umbralización normal
                 */               
                if (tipo == 1) { 
                    valorMin = 0;
                    valorMax = 255;
                }
                /**
                 * Valores para la Umbralización inversa
                 */                 
                else if (tipo == 2) { 
                    valorMin = 255;
                    valorMax = 0;
                }
                
                /**
                 * Compruebo si el pixel esta o no por debajo de la Media
                 */
                int umbralR = (valorInt < umbral) ? valorMin : valorMax;
                int umbralG = (valorInt < umbral) ? valorMin : valorMax;
                int umbralB = (valorInt < umbral) ? valorMin : valorMax;
                /**
                 * Valores para la Umbralización basada en color
                 */ 
                if (tipo == 3) {
                    umbralR = (valorInt < umbral) ? color.getRed() : valorMax;
                    umbralG = (valorInt < umbral) ? color.getGreen() : valorMax;
                    umbralB = (valorInt < umbral) ? color.getBlue() : valorMax;
                }
                if(src.getColorModel().hasAlpha()){
                    int[] imgMod = {umbralR, umbralG, umbralB, color.getRed(), color.getGreen(), color.getBlue()};
                    destRaster.setPixel(x, y, imgMod);
                }
                else {
                    int[] imgMod = {umbralR, umbralG, umbralB};
                    destRaster.setPixel(x, y, imgMod);
                }
            }
        
        return dest;
    }
    
}
