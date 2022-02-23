package rrenat358;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

//██████████████████████████████████████████████████████████████████████
//══════════════════════════════════════════════════════════════════════
//══════════════════════════════════════════════════════════════════════
public class WindowGame extends JFrame {
    private static WindowGame windowGame;
    private static Image background;

    private static Image drop;
    private static float dropLeft=200;
    private static float dropTop=-80;
    private static float dropV=200; //"скорость" капли

    //вермя текущее
    private static long lastFrameTime=System.nanoTime();

    private static int scoreClick; //очки пойманых каплей

    private static Image gameOver;

    //██████████████████████████████████████████████████████████████████████
    public static void main(String[] args) throws IOException {
        background = ImageIO.read(Objects.requireNonNull(WindowGame.class.getResourceAsStream("images/Sky-01.jpg")));
        gameOver = ImageIO.read(Objects.requireNonNull(WindowGame.class.getResourceAsStream("images/GAME-OVER-02.png")));
        drop = ImageIO.read(Objects.requireNonNull(WindowGame.class.getResourceAsStream("images/drop.03.png")));

        windowGame = new WindowGame(); //создаём объект WindowGame()
        //настройка окна ↓
        windowGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowGame.setLocation(200,10);
        windowGame.setSize(1200,800);
        windowGame.setResizable(false);

        FieldGame fieldGame = new FieldGame(); //создаём объект класса fieldGame
        windowGame.add(fieldGame); //рисуем в нашем окне
        windowGame.setVisible(true);

        //Собития мыши. Нажатие по капли
        fieldGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                //Правая и нижняя граница рисунка капли
                float dropRight = dropLeft + drop.getWidth(null);
                float dropBottom = dropTop + drop.getHeight(null);

                //Клик по капле
                boolean dropIsClick = x >= dropLeft && x <= dropRight && y >= dropTop && y <= dropBottom;
                if (dropIsClick){
                    dropTop = -80;
                    dropLeft = (int) (Math.random()*(fieldGame.getWidth()-drop.getWidth(null)));
                    dropV = dropV + 50;
                    scoreClick++;
                    windowGame.setTitle("поймано капель: " + scoreClick);

                }
            }
        });

    }

    //██████████████████████████████████████████████████████████████████████
    //новый метод для рисования
    public static void onRepaint(Graphics g){
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;


        dropTop = dropTop + dropV * deltaTime;
        g.drawImage(background, 0, 0, null);
        g.drawImage(drop, (int) dropLeft, (int) dropTop, null);


        if(dropTop > windowGame.getHeight()) g.drawImage(gameOver, 330, 130, null);

//        g.fillOval(10,10,200,100);

    }
    //██████████████████████████████████████████████████████████████████████
    //новый класс для панелей для рисования
    private static class FieldGame extends JPanel {

        //переопределяем компонент из JPanel
        @Override
        protected void paintComponent(Graphics g){

            //сново получаем доступ к JPanel
            super.paintComponent(g); //отрисовка панели
            onRepaint(g);
            repaint();

        }
    //██████████████████████████████████████████████████████████████████████

    }


}
