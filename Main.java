import javax.swing.*;
import java.awt.*;


import java.util.Scanner;
import javax.swing.JFrame;

public class Main  {

    public static void main(String[] args) {




        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Casper Avda");





        public void reset()
        {
            Scanner scan=new Scanner(System.in);
            int sayi1=scan.nextInt();
            int sayi2= scan.nextInt();
            Map_Generator Map=new Map_Generator(sayi1,sayi2);
            Map.generateMap();
            uygulama uyg=new uygulama(Map,sayi1,sayi2);
            window.add(uyg);
            window.pack();


            window.setLocationRelativeTo(null);
            window.setVisible(true);

            uyg.start_thread();
        }












    }


}