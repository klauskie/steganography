import java.awt.Color;
import java.awt.image.BufferedImage;

public class Finder extends Base {

    private BufferedImage image;
    private int messageSize;

    public Finder(BufferedImage image) {
        this.image = image;
        this.messageSize = getDataInImage();
    }

    public String getMessageInImage() {

        String result = "";
        String messageResult = "";
        int counter = 0;
        int written = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = this.x_index; x < image.getWidth(); x+=2) {

                if ( written >= messageSize ){
                    return messageResult;
                }

                if (x % 2 == 0) {
                    int c = image.getRGB(x,y);
                    Color color = new Color(c);

                    result += this.getTwoByIndex(Integer.toBinaryString(color.getRed()), 8);
                    counter++;
                }

                if (counter == 4) {
                    // write letter
                    int parseInt = Integer.parseInt(result, 2);
                    messageResult += (char)parseInt;
                    counter = 0;
                    result = "";
                    written++;
                }

            }
        }

        return messageResult;

    }

    public int getDataInImage() {
        int c = this.image.getRGB(0,0);
        Color color = new Color(c);

        String last4 = Integer.toBinaryString(color.getGreen()).substring(3);
        return Integer.parseInt(last4, 2);
    }

}
