package rrenat358;

import javax.swing.*;
import java.awt.*;

public class WindowGame extends JFrame {
    private static WindowGame windowGame;

    public static void main(String[] args) {
        windowGame = new WindowGame(); //создаём объект WindowGame()
        //настройка окна ↓
        windowGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowGame.setLocation(200,100);
        windowGame.setSize(800,600);
        windowGame.setResizable(false);

        FieldGame fieldGame = new FieldGame(); //создаём объект класса fieldGame
        windowGame.add(fieldGame); //рисуем в нашем окне

        windowGame.setVisible(true);


    }

    //новый метод для рисования
    public static void onRepaint(Graphics g){
        g.fillOval(10,10,200,100);

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
