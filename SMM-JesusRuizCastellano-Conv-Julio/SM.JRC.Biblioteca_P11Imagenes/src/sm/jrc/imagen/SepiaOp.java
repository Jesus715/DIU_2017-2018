/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.lang.Double.min;
import sm.image.BufferedImageOpAdapter;



/**
 * Clase creada para personalizar el operador de Sepia.
 * @author Jesús Ruiz Castellano
 */
public class SepiaOp extends BufferedImageOpAdapter {

    /**
     * Constructor por defecto que crea un operador vacío. 
     */
    public SepiaOp () {
    }
    
    
    /**
     * Método para crear el filtro sepia.
     * Documentación - Bibliografía : (6) https://www.dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-sepia-image
     * @param src - Imagen fuente que vamos a copiar.
     * @param dest - Imagen destino donde se va a copiar la imagen fuente ya transformada.
     * @return BufferedImage - Imagen ya con el filtro Sepia.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        
        if (src == null) {
            throw new NullPointerException(" No hay ninguna imagen fuente");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        /**
         * Obtenemos la cuadrícula de las imágenes fuente y destino
         */
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        
        /**
         * Recorremos la imagen fuente pixel a pixel 
         */
        for (int x = 0; x < src.getWidth(); x++) 
            for (int y = 0; y < src.getHeight(); y++) {
                
                int[] RGB = null; 
                /**
                 * Obtenemos el píxel
                 */
                RGB = srcRaster.getPixel(x, y, RGB);
                
                /**
                 * Se calcula el mínimo del valor de la suma de cada una de las componentes del píxel 
                 * multiplicadas por un coeficiente
                 */
                int sepiaR = (int)min(255 , 0.393*RGB[0] + 0.769*RGB[1] + 0.189*RGB[2]);
                int sepiaG = (int)min(255, 0.349*RGB[0] + 0.686*RGB[1] + 0.168*RGB[2]);
                int sepiaB = (int)min(255, 0.272*RGB[0] + 0.534*RGB[1] + 0.131*RGB[2]);  
                
                /**
                 * Y se reemplaza este valor, en lugar del que tenía el píxel anteriormente
                 * No tenemos que cambiar el valor alfa porque solo controla la 
                 * transparencia del píxel
                 */
                int[] imgMod = {sepiaR, sepiaG, sepiaB};
                destRaster.setPixel(x, y, imgMod);
            }
        
        return dest;
    }
    
}
