import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 400, 650);


        createPresentation();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(5);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        ImageView imageView = new ImageView();
        borderPane.setCenter(imageView);


        for(Image slide : slides){

            imageView.setImage(slide);

            KeyValue keyValue = new KeyValue(imageView.imageProperty(), slide);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), keyValue);

            timeline.getKeyFrames().add(keyFrame);

        }


        timeline.play();






//        borderPane.setOnMouseClicked( e -> primaryStage.close());
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }







    ArrayList<Image> slides = new ArrayList<>();


    public void createPresentation(){

        Image image1 = new Image("1.png");
        slides.add(image1);

        Image image2 = new Image("2.png");
        slides.add(image2);

        Image image3 = new Image("3.png");;
        slides.add(image3);

        Image image4 = new Image("4.png");
        slides.add(image4);

    }



}
