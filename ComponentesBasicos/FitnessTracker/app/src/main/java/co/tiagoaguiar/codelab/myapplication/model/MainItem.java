package co.tiagoaguiar.codelab.myapplication.model;

public class MainItem {

    private int id;
    private int drawableId;
    private int name;
    private int color;

    public MainItem(int id, int drawableId, int name, int color) {
        this.id = id;
        this.drawableId = drawableId;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
