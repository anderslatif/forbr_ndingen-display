import javafx.animation.*;
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

/*        Timeline timeline = new Timeline();
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
        */


/*        new AnimationTimer(){
            @Override
            public void handle(long delay){

                for(ImageView slide : slides){

                  borderPane.setCenter(slide);

                    for(int i = 0; i < 999999999; i++);{}
                }
            }
        }.start();*/


        borderPane.setCenter(slides.get(0));



//        borderPane.setOnMouseClicked( e -> primaryStage.close());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }







    ArrayList<ImageView> slides = new ArrayList<>();


    public void createPresentation(){

        Image image1 = new Image("1.png");
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);
        slides.add(imageView1);

        Image image2 = new Image("2.png");
        ImageView imageView2 = new ImageView();
        imageView1.setImage(image2);
        slides.add(imageView2);

        Image image3 = new Image("3.png");
        ImageView imageView3 = new ImageView();
        imageView1.setImage(image3);
        slides.add(imageView3);

        Image image4 = new Image("4.png");
        ImageView imageView4 = new ImageView();
        imageView1.setImage(image4);
        slides.add(imageView4);

    }



}
