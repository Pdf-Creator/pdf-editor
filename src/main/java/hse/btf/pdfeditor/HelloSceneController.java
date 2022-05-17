package hse.btf.pdfeditor;

//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloSceneController implements Initializable {
    @FXML
    private TextField projectNameField;

    @FXML
    private Button createButton;

    @FXML
    private ListView<String> projectNamesListView;
    // добавить выгрузку имен проектов из файла (десериализация) -- при создании сцены

    private List<String> projectNamesList = new ArrayList<>();
    private String currentProjectName = "";

//    private void serializeOrDeserializeProjectsNames(String regime) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//        File jsonFile = new File("projectNamesList.json");
//        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
//
//        // try to (de)serialize
//        try {
//            switch (regime) {
//                case ("serialize"): {
//                    objectMapper.writeValue(jsonFile, projectNamesList);
//                }
//
//                case ("deserialize"): {
//                    projectNamesList = objectMapper.readValue(jsonFile, type);
//                }
//
//                default:
//                    System.out.println("Wring regime while serializing/ deserializing");
//            }
//        } catch (IOException ex) {
//            System.out.print("Error while deserialization from file: " + ex.getMessage());
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        projectNamesList = List.of("Name1");
//        serializeOrDeserializeProjectsNames("serialize");
        //serializeOrDeserializeProjectsNames("deserialize");



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
    public void createProjectButton(ActionEvent event) throws IOException {
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
        // выгрузить состояние всей прошлой сцены
    }
}