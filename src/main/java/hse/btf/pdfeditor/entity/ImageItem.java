package hse.btf.pdfeditor.entity;

public class ImageItem extends DocumentItem {
    private String imagePath;

    public ImageItem(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
