import java.awt.*;

public class ColorManagement {
    private boolean variableColor;

    private int baseR = (int) (Math.random() * 256);
    private int baseG = (int) (Math.random() * 256);
    private int baseB = (int) (Math.random() * 256);

    private float vR = (float) Math.random() * 10.0f;
    private float vG = (float) Math.random() * 10.0f;
    private float vB = (float) Math.random() * 10.0f;

    private int r, g, b;

    private float summVR, summVG, summVB;

    private Color color = new Color(baseR, baseG, baseB);

    ColorManagement(boolean variableColor) {
        this.variableColor = variableColor;
    }

    public Color getColor() {
        return color;
    }

    void update(GameCanvas canvas, float deltaTime) {
        if (variableColor) {
            summVR += vR;
            summVG += vG;
            summVB += vB;

            r = baseR + (int) (summVR * deltaTime);
            if (r > 255) {
                r = 255;
                vR = -vR;
            }
            if (r < 0) {
                r = 0;
                vR = -vR;
            }

            g = baseG + (int) (summVG * deltaTime);
            if (g > 255) {
                g = 255;
                vG = -vG;
            }
            if (g < 0) {
                g = 0;
                vG = -vG;
            }
            b = baseB + (int) (summVB * deltaTime);
            if (b > 255) {
                b = 255;
                vB = -vB;
            }
            if (b < 0) {
                b = 0;
                vB = -vB;
            }

            color = new Color(r, g, b);
            System.out.println(deltaTime + "\tred\t" + r + "\tgreen\t" + g + "\tblue\t" + b);
        }
    }

    Color render() {
        return color;
    }
}