/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintpfinal;

import java.awt.image.BufferedImage;
import java.io.File;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * Sublcase creada para poder trabajar con vídeos. 
 * Es necesario tener instalada la versión correspondiente a la arquitectura
 * de su equipo de VLCPlayer
 * @author Jesús Ruiz Castellano
 */
public class VentanaInternaVLCPlayer extends VentanaInterna  {

    /**
     * Variable creada para poder comunicar esta ventana con la principal
     */
    private VentanaPrincipalPFinal parent;
    private EmbeddedMediaPlayer vlcplayer = null;
    private File fMedia;
    
    private VentanaInternaVLCPlayer (VentanaPrincipalPFinal Parent, File f) {
        
        initComponents();
        parent = Parent;
        fMedia = f;
        EmbeddedMediaPlayerComponent aVisual = new EmbeddedMediaPlayerComponent();
        panel.add(aVisual,java.awt.BorderLayout.CENTER);
        vlcplayer = aVisual.getMediaPlayer();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    

    public static VentanaInternaVLCPlayer getInstance(VentanaPrincipalPFinal Parent, File f){
        VentanaInternaVLCPlayer v = new VentanaInternaVLCPlayer(Parent,f);
        return (v.vlcplayer!=null?v:null);
    }

    public EmbeddedMediaPlayer getVlcplayer() {
        return vlcplayer;
    }    
    
    
    public void play() {
        if (vlcplayer != null) {
            if(vlcplayer.isPlayable()){
                //Si se estaba reproduciendo
                System.out.println("\n ENTRA EN PLAY VIDEO !!!!");
                vlcplayer.play();
            } else {
                vlcplayer.playMedia(fMedia.getAbsolutePath());
            }
        }
    }
    
    public void stop() {
        if (vlcplayer != null) {
            if (vlcplayer.isPlaying()) {
                vlcplayer.pause();
            } else {
                vlcplayer.stop();
            }
        }
    }

    public File getfMedia() {
        return fMedia;
    }

    public void setfMedia(File fMedia) {
        this.fMedia = fMedia;
    }

    
    @Override
    public BufferedImage getImage(){
        return vlcplayer.getSnapshot();
    }
    
    
    @Override
    public int getType() {
        return 3;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosPFinal/AbrirVideo.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(500, 400));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para cerrar la ventana y volver a activar el botón de Guardado de 
     * la ventana principal.
     * @param evt : evento de cerrar la ventana
     */
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        stop();
        vlcplayer = null;
        parent.getBotonGuardar().setEnabled(true);
    }//GEN-LAST:event_formInternalFrameClosing

    public void addMediaPlayerEventListener(MediaPlayerEventListener ml) {
        if (vlcplayer != null) {
            vlcplayer.addMediaPlayerEventListener(ml);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
