package java_demo.fastjson;

import java.io.Serializable;

public class Friend implements Serializable {
    private static final long serialVersionUID=1L;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "color='" + color + '\'' +
                '}';
    }
}
