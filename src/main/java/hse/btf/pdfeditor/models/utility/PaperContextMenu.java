package hse.btf.pdfeditor.models.utility;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PaperContextMenu extends ContextMenu {
    public PaperContextMenu() {
        super();
        MenuItem menuItem1 = new MenuItem("menu item 1");
        MenuItem menuItem2 = new MenuItem("menu item 2");
        MenuItem menuItem3 = new MenuItem("menu item 3");

        this.getItems().add(menuItem1);
        this.getItems().add(menuItem2);
        this.getItems().add(menuItem3);
    }
}
