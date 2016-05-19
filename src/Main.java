/**
 * Created by Anders on 5/2/2016.
 */
import model.DatabaseSaveAndGet;

import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;


import java.util.ArrayList;


public class Main extends Application {


    public static void main(String[] args){
        launch(args);
    }

    ArrayList<Node> presentation;
    int slideshowCount = 0;
    BorderPane borderPane;
    int secondsPerSlide = 2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        borderPane = new BorderPane();

        Scene scene = new Scene(borderPane);




        presentation = DatabaseSaveAndGet.loadAllSlides(scene);

        Node dummyNode = new StackPane();
        presentation.add(dummyNode);


        showPresentation();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case UP:
                        changeSecondsPerSlide(10);
                        break;
                    case DOWN:
                        changeSecondsPerSlide(-10);
                        break;
                    case RIGHT:
                        changeSecondsPerSlide(1);
                        break;
                    case LEFT:
                        changeSecondsPerSlide(-1);
                        break;

                    case VOLUME_UP:
                        changeSecondsPerSlide(1);
                        break;
                    case VOLUME_DOWN:
                        changeSecondsPerSlide(1);

                    default:
                        System.out.println("Couldn't change how long to display the slides");
                }
            }
        });



        //borderPane.setCenter(Controller.getInstagramGrid());
        //primaryStage.setMaximized(true);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        borderPane.setOnMouseClicked( e -> primaryStage.close());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void changeSecondsPerSlide(int value){
        if((secondsPerSlide + value) > 0){
            this.secondsPerSlide = secondsPerSlide + value;
            System.out.println("Seconds Per Slide: " + secondsPerSlide);
        }
    }





    public void showPresentation(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < presentation.size(); i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(presentation.get(slideshowCount) instanceof javafx.scene.layout.GridPane){
                                borderPane.setCenter(Controller.getInstagramGrid());
                                slideshowCount++;
                            } else {
                                borderPane.setCenter(presentation.get(slideshowCount));
                                slideshowCount++;
                                //System.out.println(slideshowCount);
                            }

                            if (slideshowCount >= presentation.size()) {
                                slideshowCount = 0;
                                //System.out.println(slideshowCount);
                                showPresentation();

                            }
                        }
                    });

                    Thread.sleep(1000 * secondsPerSlide);

                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }




}