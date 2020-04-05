import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int quantity = 10;

    Sprite[] sprites = new Sprite[quantity];
    Sprite[] clonSprites;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainCircles::new);
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        canvas.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) ballPlus();
                if (e.getButton() == MouseEvent.BUTTON3) ballMinus();
            }
        });
        setTitle("Circles");
        initApplication();
        setVisible(true);
    }

    private void ballMinus() {
        if (quantity>0) {
            clonSprites = new Sprite[quantity - 1];
            for (int i = 0; i < clonSprites.length; i++)
                clonSprites[i] = sprites[i];
            sprites = clonSprites;
            quantity = sprites.length;
        }
    }

    private void ballPlus() {
        clonSprites = new Sprite[quantity + 1];
        for (int i = 0; i < clonSprites.length; i++) {
            if (i < quantity) clonSprites[i] = sprites[i];
            else clonSprites[i] = new Ball();
        }
        sprites = clonSprites;
        quantity=sprites.length;
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
        canvas.update();
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
        canvas.render();
    }
}