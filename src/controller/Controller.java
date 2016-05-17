package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.TwitterAPI;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

import java.util.List;

/**
 * Created by Anders on 5/15/2016.
 */
public class Controller {

    public static GridPane getTwitterGrid(){

        int row = 0;

        GridPane gridPane = new GridPane();

        List<String> tweetings = TwitterAPI.searchTwitter("Forbrændingen");


        for(String tweet : tweetings){

            System.out.println(tweet);
        }

        System.out.println("-------------------------------------------");


        List<Status> tweetStatuses = TwitterAPI.searchTwitterForStatus("Forbrændingen");

        for(Status twee : tweetStatuses){


            ImageView imageView = new ImageView(twee.getUser().getBiggerProfileImageURL());
            gridPane.add(imageView, 0, row);


            TextArea textArea1 = new TextArea(twee.getUser().getScreenName());
            textArea1.setEditable(false);
            textArea1.setMaxWidth(Double.MAX_VALUE);
            textArea1.getStyleClass().add("twitter_user");
            gridPane.add(textArea1, 1, row);


            TextArea textArea2 = new TextArea(twee.getText());
            textArea2.setEditable(false);
            textArea2.setWrapText(true);
            textArea2.setMaxWidth(Double.MAX_VALUE);
            textArea2.getStyleClass().add("twitter_message");
            gridPane.add(textArea2, 2, row);

            row++;
        }


        System.out.println("-------------------------------------------");


/*        List<Status> tweets = TwitterAPI.getMentionsTimeline();



        for(Status tweet : tweets){
            tweet.getUser().getScreenName();
            tweet.getUser().getName();
            tweet.getText();




            String urlFromId = TwitterAPI.getProfileImageURL(tweet.getUser().getId());

            Image image = new Image(urlFromId);
            ImageView imageView = new ImageView(image);
            gridPane.add(imageView, row, 0);

            TextField textField = new TextField(tweet.getText());
            gridPane.add(textField, row, 1);

            row++;
        }*/






        return gridPane;
    }


}
