package openCV;

import org.opencv.core.Mat;
import nu.pattern.OpenCV;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY;
import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgproc.Imgproc.CV_HOUGH_GRADIENT;


public class CoinFinder {

    public static void main(String[] args) throws InterruptedException { //TODO: for debugging

        OpenCV.loadShared();

        String file = "src/main/resources/image2.jpg";
        Mat src = Imgcodecs.imread(file, CV_LOAD_IMAGE_COLOR);

        System.out.println(findCoins(src).toString());


    }

    public static ArrayList<Integer> findCoins(Mat mat) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<Integer>();


        //TODO: collapse code... only temporarily chunky for optimization
        Mat gray = new Mat();
        Imgproc.cvtColor(mat, gray, COLOR_RGB2GRAY);
        Imgcodecs.imwrite("gray.jpg", gray);

        Mat edge = new Mat();
        Imgproc.Canny(gray, edge,100, 150, 3, true);
//        Imgproc.cvtColor(gray, edge, CV_8U);
        Imgcodecs.imwrite("edge.jpg", edge);

        Mat draw = new Mat();
        edge.convertTo(draw, CV_8U);

        while(draw.cols()>1000) { //TODO: make this better
            Imgproc.resize(draw, draw, new Size(draw.cols()*0.7,draw.rows()*0.7));
        }

        Imgcodecs.imwrite("draw.jpg", draw);

        Mat blur = new Mat();
//        Imgproc.GaussianBlur(draw, blur, new Size(9, 9), 2, 2);
        Imgproc.medianBlur(draw, blur, 1);
        Imgcodecs.imwrite("blur.jpg", blur);


        Mat circles = new Mat();

        Imgproc.HoughCircles(blur, circles, CV_HOUGH_GRADIENT, 1, blur.rows() / 8,200, 100, 0, 0);

        System.out.println(circles.size());

//        TimeUnit.SECONDS.sleep(1);

        for (int x = 0; x < circles.cols(); x++) {
            double[] c = circles.get(0, x);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            // circle center
            Imgproc.circle(blur, center, 1, new Scalar(0,100,100), 3, 8, 0 );
            // circle outline
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(blur, center, radius, new Scalar(255,0,255), 3, 8, 0 );
        }
        HighGui.imshow("detected circles", blur);
        HighGui.waitKey();

//        System.exit(0);


        return list;

    }

}