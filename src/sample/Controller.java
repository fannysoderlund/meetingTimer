package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button womanButton;
    public Button manButton;
    public Label secondsLabel;
    public Label minutesLabel;
    public Button startButton;
    public Button stopButton;
    public Button statistikButton;
    private Timeline timeline;
    private int timeSeconds = 0;
    private int timeMinutes= 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        minutesLabel.setText("0" + timeMinutes);
        secondsLabel.setText("0" + timeMinutes);

        startButton.setOnAction(actionEvent -> {
            AnimationTimer animationTimer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                }
            };
            animationTimer.start();
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(1000),
                            (EventHandler<javafx.event.ActionEvent>) event -> {
                              timeSeconds++;
                              if (timeSeconds<10) {
                                  secondsLabel.setText(
                                          "0" + timeSeconds);
                              }else {
                                  secondsLabel.setText(
                                          String.valueOf(timeSeconds));
                              }
                                if (timeSeconds>=59) {
                                    timeSeconds=0;
                                    timeMinutes++;
                                    if (timeMinutes<10) {
                                        minutesLabel.setText(
                                                "0" + timeMinutes);
                                    }else {
                                        minutesLabel.setText(
                                                String.valueOf(timeMinutes));
                                    }
                                }
                            }));
            timeline.playFromStart();

});
        stopButton.setOnAction(actionEvent -> {
            try {
                timeline.stop();
            }catch (Exception e) {

            }
        });

        manButton.setOnAction(actionEvent -> {
            timeline.stop();
            try {
                Path womanPath = Paths.get("man.txt");
                List<String> woman = Collections.singletonList(minutesLabel.getText() + secondsLabel.getText());
                Files.write(womanPath, woman, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

            } catch (Exception e) {
                e.printStackTrace();
            }
            minutesLabel.setText("0" + timeMinutes);
            secondsLabel.setText("0" + timeMinutes);
            timeSeconds=0;
            timeMinutes=0;

        });
        womanButton.setOnAction(actionEvent -> {
            timeline.stop();
            try {
                Path womanPath = Paths.get("woman.txt");
                List<String> woman = Collections.singletonList(minutesLabel.getText()  + secondsLabel.getText());
                Files.write(womanPath, woman, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

            } catch (Exception e) {
                e.printStackTrace();
            }
            minutesLabel.setText("0" + timeMinutes);
            secondsLabel.setText("0" + timeMinutes);
            timeSeconds=0;
            timeMinutes=0;

        });

        statistikButton.setOnAction(actionEvent -> {
            try {
                Node node = (Node) actionEvent.getSource();
                Scene scene = node.getScene();
                Stage stage = (Stage) scene.getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("statistik.fxml"));
                Scene newScene = new Scene(root);

                stage.setTitle("");
                newScene.getStylesheets().add("layout.css");
                stage.setScene(newScene);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });





}
}
