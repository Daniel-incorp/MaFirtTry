
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent{

    private BufferedImage bufferedImage;

    public JImageDisplay (int x, int y){

        bufferedImage = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);

        setPreferredSize(new Dimension(x,y));
    }

    //Не полностью понимаю почему
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage (bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    /**
     * just passes variables into setRGB() of BuggeredImage
     */
    public void drawPixel (int x, int y, int rgbColor){
        bufferedImage.setRGB(x,y,rgbColor);
    }

    public BufferedImage getBufferedImage () {
        return bufferedImage;
    }
}
