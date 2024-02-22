import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class SeeFood {

public static String getFilename() {
        String fileName = "";
final FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPEG & PNG Files Only",
        "jpg",
        "jpeg",
        "png"
        );

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select an Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
        fileName = fileChooser.getSelectedFile().getAbsolutePath();
        }

        return fileName;
        } // end getFilename

/**
 * Displays the specified image overlaid with a message that says
 * either "Hot Dog" or "Not Hot Dog" depending on if a hot dog
 * is detected by Google Cloud Vision.
 *
 * NOTE: You may NOT change the method name, the return type,
 *       the parameter list, or the throws clause.
 *
 * NOTE: the exceptions listed in the throws clause are there because
 *       you do NOT handle the exceptions inside this method.
 *       You WILL handle the exceptions in main.
 *
 * @param filename the name of the image file.
 *
 * @throws IOException if an I/O error occurs when calling GoogleCloudVision.detectImageLabels
 * @throws InterruptedException here because GoogleCloudVision.detectImageLabels can throw this exception.
 */
public static void labelImage(String filename) throws IOException, InterruptedException {
final String hotDogKeyword = "hot dog";
        ArrayList<ObjectLabel> objectLabels = GoogleCloudVision.detectImageLabels(filename);

        /*
            Right after getting the ArrayList of ObjectLabel back from calling GoogleCloudVision.detectImageLabels,
            you MUST do a System.out.println of the GoogleCloudVision.detectImageLabels. This will make the TAs job
            easier for grading ObjectLabel.toString().
        */
        System.out.println(objectLabels.toString());

        // Using that list, your code will determine if the image contains a hot dog or not.
        boolean imageContainsHotDog = false;

        for (ObjectLabel label : objectLabels) {
        if (StringUtils.containsIgnoreCase(label.getLabel(), hotDogKeyword)) {
        imageContainsHotDog = true;
        }
        }

// The method will then display the image in a JLabel in a JFrame.
final JFrame frame = new JFrame("SeeFood");
final JLabel imageLabel = new JLabel(new ImageIcon(filename));
        imageLabel.setLayout(new BorderLayout());
        JLabel textLabel = new JLabel();

        // These properties are the same regardless of if it's a hot dog or not
        Font biggerFont = textLabel.getFont().deriveFont(Font.BOLD, 36f);
        textLabel.setFont(biggerFont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);

        if (imageContainsHotDog) {
        textLabel.setText("Hot Dog");
        textLabel.setBackground(Color.GREEN);

        // Add the text label to the image label
        imageLabel.add(textLabel, BorderLayout.NORTH);
        } else {
        textLabel.setText("Not Hot Dog");
        textLabel.setBackground(Color.RED);

        // Add the text label to the image label
        imageLabel.add(textLabel, BorderLayout.SOUTH);
        }

        // Add the image label to the frame
        frame.add(imageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        } // end labelImage

/**
 * The entry point of the program.
 *
 * NOTE: You may NOT change the method name, the return type,
 *       the parameter list, or the throws clause.
 *
 * NOTE: You are NOT allowed to add exceptions to the throws clause of main,
 *       You MUST handle any the checked exceptions thrown by labelImage.
 *       Your exception handling code must graphically (using JOptionPane)
 *       show an error message for the user.
 */
public static void main(String[] args)  {
        // TODO: write the code to call the appropriate methods to do the following:
        //       + prompt the user for a JPEG or PNG file using a JFileChooser (defaulting to the images folder)
        //       + then, as long as the user has chosen a file, display the
        //         file in a JFrame with either "Hot Dog" or "Not Hot Dog"
        //         displayed in the appropriate place/color on the image depending
        //         on whether Google Cloud Vision detects a hot dog in the image.
        //         If the user has NOT selected a file, then notify them graphically,
        //            and exit the program with an error code.

        String filename = getFilename();

        System.out.println("The file path is: " + filename);

        if (!filename.isBlank()) {
        try {
        labelImage(filename);
        } catch (IOException | InterruptedException exception) {
        JFrame errorFrame = new JFrame("An Error Occured!");

        JLabel errorText = new JLabel();
        errorText.setText(String.format(
        "There was an error trying to process the image.\n Error: %s", exception.getMessage())
        );


        errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorFrame.add(errorText);
        errorFrame.pack();
        errorFrame.setVisible(true);
        }
        }

        } // end main

        } // end class SeeFood
