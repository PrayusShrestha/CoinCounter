package packageInterface;


import java.util.*;
import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;



public class UI extends Application {

    private static BorderPane backgroundPane = new BorderPane();
    public static Image coinImg;
    private static Pane resultPane = new Pane();
    private static HBox userControls = new HBox();
    private static VBox result = new VBox(10);


    public void start(Stage stage) throws Exception{

        Button uploadButton = new Button("Upload Image");
        Label uploadLabel = new Label();
        uploadLabel.setPrefWidth(50);

        ComboBox smallestCoinChoices = new ComboBox();

        LinkedHashMap<String, Integer> dictionary = new LinkedHashMap<String, Integer>();

        dictionary.put("Nickel - 5¢", 5);
        dictionary.put("Dime - 10¢", 10);
        dictionary.put("Quarter - 25¢", 25);
        dictionary.put("Loonie - $1", 1);
        dictionary.put("Toonie - $2", 2);

        List keys = new ArrayList<>(dictionary.keySet());

        for(int i = 0; i<keys.size(); i++){
            smallestCoinChoices.getItems().add(keys.get(i));
        }


        uploadButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser imgUploader = new FileChooser();
            imgUploader.setTitle("Upload Image");
            File selectedFile = imgUploader.showOpenDialog(null);

            if(selectedFile != null){
                //nu.pattern.OpenCV.loadShared();
                String imgLoc = selectedFile.toURI().toString();
                System.out.println();
                uploadLabel.setText(imgLoc);
                coinImg = new Image(imgLoc);
                Mat src = Imgcodecs.imread(imgLoc);
            }
        });

        Button runButton = new Button("Count the Coins!");
//        if(smallestCoinChoices.getValue() != null){
//            runButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
//                System.out.println(dictionary.get(smallestCoinChoices.getValue()));
//            });
//        }

        //int smallestCoinValue = dictionary.get(smallestCoinChoices.getValue());

        userControls.getChildren().addAll(uploadButton,uploadLabel,smallestCoinChoices);
        result.getChildren().add(runButton);

        result.setBackground((new Background(new BackgroundFill(Color.BLUE,null,null))));
        backgroundPane.setRight(result);
        backgroundPane.setBottom(userControls);

        Scene inputScene = new Scene(backgroundPane,400,500);

        stage.setScene(inputScene);
        stage.show();



    }

    private static boolean isSmallestChecked(ComboBox comboBox){
        if(comboBox.getValue() != null){
            return true;
        }
        else{
            return false;
        }
    }


    public static void main(String[] args){
        launch(args);

    }

}
