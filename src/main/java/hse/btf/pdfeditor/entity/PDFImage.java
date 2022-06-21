package hse.btf.pdfeditor.entity;

public class PDFImage extends PDFItem {
    private String imagePath;

    public PDFImage(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
