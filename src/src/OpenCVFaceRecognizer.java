package src;


import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import javax.swing.JApplet;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BHAVANA
 */
public class OpenCVFaceRecognizer {
    public static int predictedLabel;
    
     public static void main(String[] args) {
        String trainingDir = "C:\\Users\\BHAVANA\\Documents\\NetBeansProjects\\AudioPlayer\\src\\src\\sana";
        Mat testImage = imread("C:\\Users\\BHAVANA\\Desktop\\sssn.png", CV_LOAD_IMAGE_GRAYSCALE);

        File root = new File(trainingDir);

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);

        MatVector images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.getIntBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }

        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()

        faceRecognizer.train(images, labels);

        predictedLabel = faceRecognizer.predict(testImage);

        System.out.println("Predicted label: " + predictedLabel);
        if(predictedLabel==1){
            System.out.println("happy");
        }
        else if(predictedLabel==2)
            System.out.println("sad");
        else if(predictedLabel==3)
            System.out.println("shock");
        else if(predictedLabel==4)
            System.out.println("Anger");
        else
            System.out.println("Neutral");
        if(args.length>=1){
         new Player(args[0]);
      }
      else new Player("NUL");
    }
      public static class Applet extends JApplet {
      public void init() {
         //new Player();
      }
    
    }
}
