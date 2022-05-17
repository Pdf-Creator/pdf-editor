package hse.btf.pdfeditor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloSceneController implements Initializable {
    @FXML
    private TextField projectNameField;
    // добавить выгрузку имен проектов из файла (десериализация) -- при создании сцены

    @FXML
    private Button createButton;

    @FXML
    private ListView<String> projectNamesListView;

    private List<String> projectNamesList = new ArrayList<>();
    private String currentProjectName = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectNamesListView.getItems().addAll(projectNamesList);
        projectNamesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentProjectName = projectNamesListView.getSelectionModel().getSelectedItem();
                projectNameField.setText(currentProjectName);
            }
        });
    }

    @FXML
    void createProjectButton(ActionEvent event) throws IOException {
        // closing previous
        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("work-window.fxml"));

        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setMaximized(false);
        stage.initStyle(StageStyle.DECORATED);

        stage.setTitle("Working Window");
        stage.setScene(scene);
        stage.show();
    }
}