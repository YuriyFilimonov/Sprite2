import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    Random random = new Random();
    boolean isChangeColor = random.nextBoolean();
    ColorManagement colorManagement = new ColorManagement(isChangeColor);

    private float vX = (float) (100f + (Math.random() * 201f));
    private float vY = (float) (100f + (Math.random() * 201f));

    Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        colorManagement.update(canvas, deltaTime);
        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }

    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(colorManagement.render());
        g.fillOval((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
