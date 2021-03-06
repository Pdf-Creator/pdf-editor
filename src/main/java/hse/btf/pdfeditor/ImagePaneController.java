package hse.btf.pdfeditor;

import hse.btf.pdfeditor.utils.FileUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ImagePaneController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImageActions();
    }

    private void setImageActions() {
        // добавить рамки + посмотреть в видео, что можно делать с картинкой
        FileUtil.loadImage(newImageButton);
    }

    @FXML
    public Button newImageButton;
}
