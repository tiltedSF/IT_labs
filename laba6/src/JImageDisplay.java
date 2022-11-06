import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent{

    public java.awt.image.BufferedImage image;

    public JImageDisplay(int height, int width) {

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        super.setPreferredSize(new Dimension(width, height));

    }

    public void clearImage(){

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                image.setRGB(x, y, 0);
            }
        }

    }

    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x, y, rgbColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

}
