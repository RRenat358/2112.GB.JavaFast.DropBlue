package rrenat358;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WindowGame extends JFrame {
    private static WindowGame windowGame;
    private static Image background;
    private static Image gameOver;
    private static Image drop;

    public static void main(String[] args) throws IOException {
        background = ImageIO.read(WindowGame.class.getResourceAsStream("images/Sky-01.jpg"));
        gameOver = ImageIO.read(WindowGame.class.getResourceAsStream("images/GAME-OVER-02.png"));
//        drop = ImageIO.read(WindowGame.class.getResourceAsStream("GitHub.1f4a7.png"));
        drop = ImageIO.read(WindowGame.class.getResourceAsStream("images/drop.03.png"));

        windowGame = new WindowGame(); //создаём объект WindowGame()
        //настройка окна ↓
        windowGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowGame.setLocation(200,10);
        windowGame.setSize(1200,800);
        windowGame.setResizable(false);


        FieldGame fieldGame = new FieldGame(); //создаём объект класса fieldGame
        windowGame.add(fieldGame); //рисуем в нашем окне

        windowGame.setVisible(true);


    }

    //новый метод для рисования
    public static void onRepaint(Graphics g){
        g.drawImage(background, 0, 0, null);
        g.drawImage(drop, 100, 100, null);
        g.drawImage(gameOver, 330, 130, null);
//        g.fillOval(10,10,200,100);

    }

    //новый класс для панелей для рисования
    private static class FieldGame extends JPanel {

        //переопределяем компонент из JPanel
        @Override
        protected void paintComponent(Graphics g){

            //сново получаем доступ к JPanel
            super.paintComponent(g); //отрисовка панели
            onRepaint(g);

        }



    }


}
