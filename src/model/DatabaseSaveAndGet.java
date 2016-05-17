package model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Anders on 4/21/2016.
 */
public class DatabaseSaveAndGet {




    public static ArrayList<Node> loadAllSlides(Scene scene){

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Node> slides  = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();

            statement = connection.createStatement();

            LocalDate localDate = LocalDate.now();

            resultSet = statement.executeQuery("SELECT * FROM slides WHERE slide_date = '" + localDate + "';");

            if(connection != null){

                while(resultSet.next()){

                    String startTime = resultSet.getString("start_time");
                    String slideType = resultSet.getString("slide_type");
                    String header = resultSet.getString("header");
                    String text = resultSet.getString("slide_text");
                    String imagePath = resultSet.getString("image_path");



                    switch (slideType){
                        case "SlideEvent":
                            VBox vBox1 = new VBox();
                            vBox1.getStyleClass().add("eventSlide");

                            Label headerLabel1;
                            if(header.equals("null")){
                                headerLabel1 = new Label();
                            } else {
                                headerLabel1 = new Label(header);
                            }
                            headerLabel1.getStyleClass().add("header");
                            headerLabel1.setTextFill(Color.WHITE);
                            headerLabel1.setMaxWidth(Double.MAX_VALUE);
                            headerLabel1.setAlignment(Pos.CENTER);

                            Label startTimeLabel;
                            if(startTime.equals("null")){
                                startTimeLabel = new Label();
                            } else {
                                startTimeLabel = new Label(startTime);
                            }
                            startTimeLabel.getStyleClass().add("header");
                            startTimeLabel.setTextFill(Color.WHITE);

                            Image image1;
                            if(imagePath.equals("") || imagePath.equals("null") || imagePath == null){
                                image1 = new Image("Empty.png");
                            } else {
                                image1 = new Image(imagePath);
                            }
                            ImageView imageView1 = new ImageView(image1);
                            imageView1.fitWidthProperty().bind(scene.widthProperty());

                            Label textLabel1;
                            if(text.equals("null")){
                                textLabel1 = new Label();
                            } else {
                                textLabel1 = new Label(text);
                            }
                            textLabel1.getStyleClass().add("text_area");
                            textLabel1.setTextFill(Color.WHITE);
                            textLabel1.setMaxWidth(Double.MAX_VALUE);
                            textLabel1.setAlignment(Pos.CENTER);

                            vBox1.getChildren().addAll(headerLabel1, startTimeLabel, imageView1, textLabel1);
                            slides.add(vBox1);
                            break;

                        case "SlidePicture":

                            Image image2;
                            if(imagePath.equals("") || imagePath.equals("null") || imagePath == null){
                                image2 = new Image("Empty.png");
                            } else {
                                image2 = new Image(imagePath);
                            }
                            ImageView imageView2 = new ImageView(image2);
                            imageView2.fitHeightProperty().bind(scene.heightProperty());
                            imageView2.fitWidthProperty().bind(scene.widthProperty());
                            slides.add(imageView2);
                            break;

                        case "SlideHappyHour":
                            VBox vBox2 = new VBox();
                            vBox2.getStyleClass().add("happyHourSlide");

                            Label headerLabel3;
                            if(header.equals("null")){
                                headerLabel3 = new Label();
                            } else {
                                headerLabel3 = new Label(header);
                            }
                            headerLabel3.getStyleClass().add("header");
                            headerLabel3.setTextFill(Color.WHITE);
                            headerLabel3.setMaxWidth(Double.MAX_VALUE);
                            headerLabel3.setAlignment(Pos.CENTER);

                            Image image3;
                            if(imagePath.equals("") || imagePath.equals("null") || imagePath == null){
                                image3 = new Image("Empty.png");
                            } else {
                                image3 = new Image(imagePath);
                            }
                            ImageView imageView3 = new ImageView(image3);
                            imageView3.fitWidthProperty().bind(scene.widthProperty());

                            Label textLabel3;
                            if(text.equals("null")){
                                textLabel3 = new Label();
                            } else {
                                textLabel3 = new Label(text);
                            }
                            textLabel3.getStyleClass().add("text_area");
                            textLabel3.setTextFill(Color.WHITE);
                            textLabel3.setMaxWidth(Double.MAX_VALUE);
                            textLabel3.setAlignment(Pos.CENTER);

                            vBox2.getChildren().addAll(headerLabel3, imageView3, textLabel3);
                            slides.add(vBox2);
                            break;

                        case "Twitter":

                            System.out.println("hello");
                            GridPane gridPane = new GridPane();

                            slides.add(gridPane);



                        default:
                            System.out.println("A slide has not been recognized and has not been added to the presentation.");
                    }



                }

            }


        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try{
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            return slides;
        }

    }








}
