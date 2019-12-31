/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.control.produtos;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Taís
 */
public class SelecionarArquivoActionListener implements ActionListener{
    
    private JLabel imagemLabel;
    private File imagemSelecionada;

    public SelecionarArquivoActionListener(JLabel imagemLabel) {
        this.imagemLabel = imagemLabel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new JPEGImageFileFilter());
        int res = fc.showOpenDialog(null);
        // We have an image!
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                imagemLabel.setText(null);
                imagemLabel.setIcon(null);
                
                BufferedImage image = ImageIO.read(file);
                double ratio = image.getHeight()/ image.getWidth();
                
                int canvasWidth = 380;
                int canvasHeight = 190;
                double labelRatio = canvasHeight / canvasWidth;
 
                int x1 = 0; // top left X position
                int y1 = 0; // top left Y position
                int x2 = 0; // bottom right X position
                int y2 = 0; // bottom right Y position
         
                if (image.getWidth() < canvasWidth && image.getHeight() < canvasHeight) {
                    // the image is smaller than the canvas
                    x1 = (canvasWidth - image.getWidth())  / 2;
                    y1 = (canvasHeight - image.getHeight()) / 2;
                    x2 = image.getWidth(null) + x1;
                    y2 = image.getHeight(null) + y1;

                } else {
                    if (labelRatio > ratio) {
                        y1 = canvasHeight;
                        // keep image aspect ratio
                        canvasHeight = (int) (canvasWidth * ratio);
                        y1 = (y1 - canvasHeight) / 2;
                    } else {
                        x1 = canvasWidth;
                        // keep image aspect ratio
                        canvasWidth = (int) (canvasHeight / ratio);
                        x1 = (x1 - canvasWidth) / 2;
                    }
                    x2 = canvasWidth + x1;
                    y2 = canvasHeight + y1;
                }
        
                BufferedImage resizedImage = new BufferedImage(380, 190, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(image, x1, y1, x2, y2, 0, 0, image.getWidth(), image.getHeight(), null);
                
                imagemLabel.setIcon(new ImageIcon(resizedImage));                
                imagemSelecionada = file;
                System.out.println("matsunoki.listener.produtos.SelecionarArquivoActionListener.actionPerformed()" + getImagemSelecionada());
            }
        } catch (Exception iOException) {
        }
    }

    /**
     * @return the imagemSelecionada
     */
    public File getImagemSelecionada() {
        return imagemSelecionada;
    }
    
    
}
