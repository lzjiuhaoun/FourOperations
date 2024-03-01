package cn.lzj66.response;

public class JsonClass {
    private int value;
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JsonClass{" +
                "value=" + value +
                ", text='" + text + '\'' +
                '}';
    }
}
