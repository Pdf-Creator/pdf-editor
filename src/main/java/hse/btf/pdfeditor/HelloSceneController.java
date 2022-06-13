package hse.btf.pdfeditor;

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

import static hse.btf.pdfeditor.serializers.SavingClassKt.deserializeProjectsNames;
import static hse.btf.pdfeditor.serializers.SavingClassKt.serializeProjectsNames;

public class HelloSceneController implements Initializable {
    @FXML
    private TextField projectNameField;

    @FXML
    private Button createButton;

    @FXML
    private Button openButton;

    @FXML
    private ListView<String> projectNamesListView;

    private final List<String> projectNamesList = new ArrayList<>();
    private String currentProjectName = "";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("hello-scene");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deserializeProjectsNames(projectNamesList);

        projectNamesListView.getItems().addAll(projectNamesList);
        projectNamesListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            currentProjectName = projectNamesListView.getSelectionModel().getSelectedItem();
            projectNameField.setText(currentProjectName);
        });
    }

    @FXML
    public void createProjectButton(ActionEvent event) throws IOException {
        // check whether the project with that name is already created
        if (projectNamesList.contains(projectNameField.getText())) {
            ProjectNameBox.display(resourceBundle.getString("label.project-name.non-new"), resourceBundle.getString("message.project-name.non-new"));
            return;
        }

        if (projectNameField.getText().isEmpty()) {
            ProjectNameBox.display(resourceBundle.getString("label.project-name.empty"), resourceBundle.getString("message.project-name.empty"));
            return;
        }

        // add new project name to the name list
        projectNamesList.add(projectNameField.getText());
        serializeProjectsNames(projectNamesList);

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

    @FXML
    public void openProjectButton() {
        // check whether project with that name doesn't exist
        if (!projectNamesList.contains(projectNameField.getText())) {
            ProjectNameBox.display(resourceBundle.getString("label.project-name.non-existing"), resourceBundle.getString("message.project-name.non-existing"));
        }

        // выгрузить состояние всей прошлой сцены
    }
}