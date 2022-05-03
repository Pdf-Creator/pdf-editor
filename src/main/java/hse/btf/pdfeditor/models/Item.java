package hse.btf.pdfeditor.models;

// внешний прямоугольник, в который обрамлен контент
public abstract class Item {
    // default width and height
    private static final int defaultWidth = 150;
    private static final int defaultHeight = 80;

    // field center
    private static final int centerX = 388;
    private static final int centerY = 272;
    /**
     * сделать размер центра изменяемым
     * подумать над минимальным размером Item'а
     * и обработать ситуации, когда размер, например, не может быть уменьшен,
     * потому что контент внутри не позволяет этого сделать
     **/

    private int x = centerX;
    private int y = centerY;
    private int w = defaultWidth;
    private int h = defaultHeight;
    private String color = "white";
}
