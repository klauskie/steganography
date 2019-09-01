import java.awt.Color;
import java.awt.image.BufferedImage;

public class Hidder extends Base {

    private BufferedImage image;

    public Hidder(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage inImage (String message, int blocks) {

        int written = 0;
        setDataInImage(message.length()/8);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = this.x_index; x < image.getWidth(); x+=2) {

                if ( written > blocks ){
                    System.out.println("Letters: " + written);
                    return image;
                }

                if (x % 2 == 0) {
                    int c = image.getRGB(x,y);
                    Color color = new Color(c);

                    int newRed = this.alterPixel(color.getRed(), this.getTwoByIndex(message, x));

                    //System.out.println(Integer.toBinaryString(color.getRed()) + " - " + Integer.toBinaryString(newRed));

                    Color newColor = new Color(newRed, color.getGreen(), color.getBlue());
                    image.setRGB(x, y, newColor.getRGB());
                    written++;
                }

            }
        }
        //System.out.println("Pixels: " + written);

        return image;
    }

    public void setDataInImage(int charsInMessage) {
        int c = this.image.getRGB(0,0);
        Color color = new Color(c);

        String ogs = Integer.toBinaryString(color.getGreen()).substring(0,3);
        String last4 = Integer.toBinaryString(charsInMessage);

        //System.out.println( Integer.toBinaryString(color.getGreen()) + " : " + ogs + " - " + last4);

        int green = Integer.parseInt(ogs + last4, 2);

        Color colorWithInfo = new Color(color.getGreen(), green, color.getBlue());

        this.image.setRGB(0,0, colorWithInfo.getRGB());
    }
}
