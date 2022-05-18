package hse.btf.pdfeditor;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProjectNameBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        // blocking events for other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        // window elements
        Label messageLabel = new Label(message);
        Button okButton = new Button("Ok");
        okButton.setOnAction(event -> window.close());

        // layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, okButton);
        layout.setAlignment(Pos.CENTER);

        // displaying window and waiting for it to be closed
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
