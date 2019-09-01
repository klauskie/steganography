import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        if (args[0].equals("-h")) {
            // hide message in image
            System.out.println("hiding message...");

            try {
                // read image
                File file1 = new File(args[1]);
                BufferedImage newImage = ImageIO.read(file1);

                Hidder hide = new Hidder(newImage);

                String message = hide.getBinaryText(args[2]);
                int blocks = message.length() / 2;
                newImage = hide.inImage(message, blocks);

                // write image file
                File outputfile = new File(file1.getParent() + "/1_" + file1.getName());
                ImageIO.write(newImage, getFileExtension(file1.getName()), outputfile);

            } catch (IOException e) {
                e.printStackTrace();
            }



        } else if (args[0].equals("-r")) {
            // read message
            System.out.println("reading message...");

            try {
                // read image
                File file1 = new File(args[1]);
                BufferedImage image = ImageIO.read(file1);

                Finder find = new Finder(image);

                // read hidden message
                System.out.println(find.getMessageInImage());

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("HELP: ");
            System.out.println("hide message: -h <path_to_image> <message>");
            System.out.println("read message: -r <path_to_image>");
        }

    }

    private static String getFileExtension(String filename) {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }
        return extension;
    }

}
