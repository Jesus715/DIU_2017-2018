/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.lang.Double.max;
import static java.lang.Double.min;
import static java.lang.Math.exp;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author Jesus
 */
public class MiOpPP extends BufferedImageOpAdapter {

    public MiOpPP() {}

    
    
    
    /**
     * Obtencion de la imagen con el operador propio ya aplicado. La función
     * matemática en la que se basa es la de multiplicar el valor de la
     * componente blue del pixel por la suma de las coordenadas del propio pixel.
     * @param src : imagen fuente
     * @param dest : imagen modificada con la operación elegida
     * @return BufferedImage filter - imagen con el raster correspondiente a la
     *         imagen modificada con el tipo de operación indicada
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
                 * Le aplicamos la operación propia a la componente blue
                 */
                RGB[2] *= (x+y);

                
                int[] imgMod = {RGB[0], RGB[1], RGB[2]};
                destRaster.setPixel(x, y, imgMod);
            }
        return dest;
    }
    
}
