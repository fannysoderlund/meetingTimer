package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class StatistikController implements Initializable {
    public Button goBackButton;
    public Label menAmountLabel;
    public Label womenAmountLabel;
    public Label menMinutesLabel;
    public Label menSecondsLabel;
    public Label womenMinutesLabel;
    public Label womenSecondsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        goBackButton.setOnAction(actionEvent -> {
            try {
                Node node = (Node) actionEvent.getSource();
                Scene scene = node.getScene();
                Stage stage = (Stage) scene.getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene newScene = new Scene(root);

                stage.setTitle("Talartidsstatistik");
                newScene.getStylesheets().add("layout.css");
                stage.setScene(newScene);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        readFiles();
    }


    public void readFiles() {
        int totalMenSeconds = 0;
        int totalMenMinutes=0;
        int totalWomenSeconds=0;
        int totalWomenMinutes = 0;


        try {
            Path woman = Paths.get("woman.txt");
            List<String> women = Files.readAllLines(woman);
            Path man = Paths.get("man.txt");
            List<String> men = Files.readAllLines(man);

            menAmountLabel.setText(String.valueOf(men.size()));
            womenAmountLabel.setText(String.valueOf(women.size()));

            for (String s : men) {
                totalMenMinutes += Integer.parseInt(s.substring(0, 2));
                totalMenSeconds += Integer.parseInt(s.substring(2, 4));
            }


            for (String s : women) {
                totalWomenMinutes += Integer.parseInt(s.substring(0, 2));
                totalWomenSeconds += Integer.parseInt(s.substring(2, 4));
            }

            if (totalMenMinutes<=9) {
                menMinutesLabel.setText("0" + totalMenMinutes);
            }else {
                menMinutesLabel.setText(String.valueOf(totalMenMinutes));
            }
            if (totalMenSeconds<=9) {
                menSecondsLabel.setText("0" + totalMenSeconds);
            }else {
                menSecondsLabel.setText(String.valueOf(totalMenSeconds));
            }
            if (totalWomenMinutes<=9) {
                womenMinutesLabel.setText("0" + totalWomenMinutes);
            }
            else {
                womenMinutesLabel.setText(String.valueOf(totalWomenMinutes));
            }
            if (totalWomenSeconds<=9) {
                womenSecondsLabel.setText("0" + totalWomenSeconds);
            }else {
                womenSecondsLabel.setText(String.valueOf(totalWomenSeconds));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
