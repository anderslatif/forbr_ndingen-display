package model;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Anders on 4/21/2016.
 */
public class DatabaseSaveAndGet {




    public static ArrayList<Node> loadAllSlides(){

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Node> slides  = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM events;");

            if(connection != null){

                while(resultSet.next()){

                    String date = resultSet.getString("date");
                    String slideType = resultSet.getString("slide_type");
                    String header = resultSet.getString("header");
                    String text = resultSet.getString("text");
                    String imagePath = resultSet.getString("image_path");



                    switch (slideType){
                        case "pictureSlide":
                            Image image = new Image(imagePath);
                            ImageView imageView = new ImageView(image);
                            slides.add(imageView);
                            break;
                        case "happyHour":
                            VBox vBox = new VBox();
                            Label headerLabel = new Label(header);
                            Image image1 = new Image(imagePath);
                            ImageView imageView1 = new ImageView(image1);
                            Label textLabel = new Label(text);
                            vBox.getChildren().addAll(headerLabel, imageView1, textLabel);
                            slides.add(vBox);
                            break;
                        case "event":
                            VBox vBox2 = new VBox();
                            Label headerLabel2 = new Label(header);
                            Image image2 = new Image(imagePath);
                            ImageView imageView2 = new ImageView(image2);
                            Label textLabel2 = new Label(text);
                            vBox2.getChildren().addAll(headerLabel2, imageView2, textLabel2);
                            slides.add(vBox2);
                            break;
                        default:
                            System.out.println("This slide has not been recognized and has not been added to the presentation.");

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
