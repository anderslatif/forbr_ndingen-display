import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ImageLineEvents extends Application {


    public static void main(String[] args){
        launch(args);
    }


    int slideshowCount = 0;
    ImageView slideshowImageView = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {

        createImageArrayList();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(slideshowImageView);



        showImages();




        Scene scene = new Scene(borderPane, 400, 650);
        borderPane.setOnMouseClicked( e -> primaryStage.close());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }





    ArrayList<Image> imageArrayList = new ArrayList<>();


    public void createImageArrayList(){

        Image image1 = new Image("1.png");
        imageArrayList.add(image1);

        Image image2 = new Image("2.png");
        imageArrayList.add(image2);

        Image image3 = new Image("3.png");
        imageArrayList.add(image3);

        Image image4 = new Image("4.png");
        imageArrayList.add(image4);

    }

    public void showImages(){
        //then the working logic in my eventhandler
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < imageArrayList.size(); i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            slideshowImageView.setImage(imageArrayList.get(slideshowCount));
                            slideshowCount++;
                            System.out.println(slideshowCount);

                            if (slideshowCount >= imageArrayList.size()) {
                                slideshowCount = 0;
                                System.out.println(slideshowCount);
                                showImages();

                            }
                        }
                    });

                    Thread.sleep(2000);

                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }




}