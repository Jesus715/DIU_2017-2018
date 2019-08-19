/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jrc.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author Jesus
 */
public class MiOpCC extends BufferedImageOpAdapter{

    
    public MiOpCC () {}
    
    
    /**
     * Obtencion de la imagen con el operador componente a componente ya aplicado.
     * Consiste en que se multiplica el valor de cada banda por el valor absoluto 
     * de la arcotangente del valor de cada banda.
     * 
     * @param src : imagen fuente
     * @param dest : imagen modificada con la operación elegida
     * @return BufferedImage filter - imagen con el raster correspondiente a la
     *         imagen modificada con la operación.
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
            for (int y = 0; y < src.getHeight(); y++)  
                /**
                 * Recorremos cada una de las bandas de la imagen
                 */
                for (int banda = 0; banda < srcRaster.getNumBands(); banda++) {
                    /**
                     * Obtenemos la banda, necesitamos las coordenadas del píxel en el que estamos
                     */
                    int valorBanda = srcRaster.getSample(x, y, banda);
                    /**
                     * Le aplicamos el valor absoluto de la arcotangente de la banda
                     */
                    valorBanda *= abs(atan(valorBanda));
                    destRaster.setSample(x, y, banda, valorBanda);
                }
        return dest;
    }
}
