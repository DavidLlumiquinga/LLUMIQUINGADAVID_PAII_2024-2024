package Nuevo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JButton boton1, boton2, boton3;
    private JPanel panelDibujo;

    public Main() {
        setTitle("Ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Botones
        boton1 = new JButton("Triangulo");
        boton2 = new JButton("Cuadrado");
        boton3 = new JButton("Circulo");

   
        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
       
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 200);
            }
        };

  
        //Triangulo
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                Graphics g = panelDibujo.getGraphics();
                g.setColor(Color.RED);
                int[] xPoints = {50, 100, 150}; 
                int[] yPoints = {150, 50, 150}; 
                int nPoints = 3; 
                g.fillPolygon(xPoints, yPoints, nPoints);
               
            }
        });

        //Cuadrado
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = panelDibujo.getGraphics();
                g.setColor(Color.BLUE);
                g.fillRect(50, 50, 100, 100);
            }
        });

        //Circulo
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	   Graphics g = panelDibujo.getGraphics();
            	    g.setColor(Color.GREEN);
            	    g.fillOval(50, 50, 100, 100);
            }
        });

    
        JPanel panelBotones = new JPanel();
        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.NORTH);
        getContentPane().add(panelDibujo, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ventana = new Main();
                ventana.setVisible(true);
            }
        });
    }
}