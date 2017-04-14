package uk.ac.ucl.cs.radar.userinterface;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{
    private Image _myimage;
    BufferedImage bufferedImage;
    private double scale; 

    public ImageLabel(String text){
        super(text);
        scale =1.0;
    }
    public void increaseScale (double scaleValue){
    	scale += scaleValue;
    }
    public void decreaseScale (double scaleValue){
    	scale -= scaleValue;
    }
    public void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
        {
            _myimage = ((ImageIcon) icon).getImage();
            bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            // paint the Icon to the BufferedImage.
            icon.paintIcon(null, g, 0,0);
            g.dispose();
        }
    }

    @Override
    public void paint(Graphics g){
        //g.drawImage(_myimage, 0, 0, this.getWidth(), this.getHeight(), null);
        
        
    	Graphics2D g2 = (Graphics2D)g; 
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth(); 
        int h = getHeight(); 
        int imageWidth = bufferedImage.getWidth(); 
        int imageHeight =bufferedImage.getHeight(); 
        double x = (w - scale * imageWidth)/2; 
        double y = (h - scale * imageHeight)/2; 
        AffineTransform at = AffineTransform.getTranslateInstance(x,y); 
        at.scale(scale, scale); 
        g2.drawRenderedImage(bufferedImage, at);
    }
/*    protected void paintComponent(Graphics g) 
    { 
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D)g; 
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC); 
        
        Dimension dim = this.getSize();
        int width = dim.width - 10;
        int height = dim.height - 10;
        
        int w = getWidth(); 
        int h = getHeight(); 
        int imageWidth = bufferedImage.getWidth(); 
        int imageHeight =bufferedImage.getHeight(); 
        double x = (w - scale * imageWidth)/2; 
        double y = (h - scale * imageHeight)/2; 
        AffineTransform at = AffineTransform.getTranslateInstance(x,y); 
        at.scale(scale, scale); 
        g2.drawRenderedImage(bufferedImage, at); 
    } */

}
