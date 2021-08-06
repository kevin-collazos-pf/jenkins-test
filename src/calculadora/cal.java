/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author CRA
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class cal extends JFrame{
    
    private JPanel PanelConBotones;
    private JButton Botonsin[];
    private JTextField salida;
    
    private JButton b0,bretroceso,bmas,bigual,ac;
    private JButton parenDerecho,parenIzquierdo,multi,div,punto;
    private JButton modulo,potencia;
    int contador=0;
    int arr[]= new int[2];
 public static int prioridad (char opci)
     {
         if ( opci == '+' || opci=='-')
                return 1;
        else if ( opci=='*' || opci=='/'|| opci == '%')
                return 2;
        else if (opci == '^'  )
                return 3;
        //¬¨ Raiz Cuadrada;
        return -1;
     }
 public static  double resuelve (Queue posfija)
          {
            Stack Pila=new Stack();
            double resultado, a , b;
             while(posfija.peek()!=null)
                {
                    char dato=(Character)posfija.poll();
                    if (Character.isDigit(dato))
                        Pila.push(dato);
                    else
                       {
                        String Str =Pila.pop().toString();
                        b=Double.valueOf(Str).doubleValue();
                       //b=(double)Pila.pop();
                        Str =Pila.pop().toString();
                        a=Double.valueOf(Str).doubleValue();
                        if (dato == '+')
                            Pila.push(a+b);
                        else if (dato == '-')
                            Pila.push(a-b);
                        else if (dato == '*')
                             Pila.push(a*b);
                         else if (dato == '/')
                             Pila.push(a/b);
                        else if (dato == '^')
                           Pila.push( Math.pow(a,b));
                        }
                }
                        String Str =Pila.pop().toString();
                        resultado=Double.valueOf(Str).doubleValue();
                        System.out.println(resultado);
                         return resultado;
          }
 public static String uno (String uno)
         {
    // String uno="(5+3)*(2+3)";
        
         char dato,a,x;
    Queue salida= new java.util.LinkedList();
        Stack Pila=new Stack();
        int i=0;
      for(i=0;i<uno.length();i++)
        {
            dato=uno.charAt(i);
            if (Character.isDigit(dato))
                {
                salida.offer(dato);
                }
            else
             {
                if(Pila.empty() || dato=='(' || prioridad(dato)>prioridad((Character)Pila.peek()) )
                    Pila.push(dato);
                else
                    {
                 if(prioridad(dato)==prioridad((Character)Pila.peek()))
                    {
                    x=(Character)Pila.peek();
                    if(x==dato)
                        {
                        Pila.push(dato);
                        }
                    else
                        {
                      x=(Character)Pila.pop();
                        salida.offer(x);
                    Pila.push(dato);
                        }
                    }
                if(dato==')')
                    {
                        while((Character)Pila.peek()!='(')
                        {
                             x=(Character)Pila.pop();
                        salida.offer(x);
                        }
                        Pila.pop();
                    }
                 }
             }//fin del else
        }// fin del metodo
        //vaciar la pila a la cola
         while(!Pila.empty())
             {
            salida.offer(Pila.pop());
            }
        //System.out.println(resuelve(salida));
        String aString = Double.toString(resuelve(salida));
        //return resuelve(salida);
        return aString;
        }
       
    public cal()
            {
         super("GAÑALADORA");
   
         //Etiqueta=new JLabel("esta es una etiqueta",JLabel.CENTER);
         parenDerecho=new JButton("(");
         parenIzquierdo=new JButton(")");
          multi=new JButton("*");
           div=new JButton("/");
           punto=new JButton(".");
         
         b0=new JButton("0");
         bmas=new JButton("+");
         bigual=new JButton("=");
         ac=new JButton("AC");
          modulo=new JButton("%");
           potencia=new JButton("^");
          ac.setForeground(Color.red);
         bretroceso=new JButton("C");
        bretroceso.setForeground(Color.RED);
         salida=new JTextField(10);
        salida.setEditable(false);
        salida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1),"sanlegas",2,SwingConstants.SOUTH,new Font("Calibri",2+1,13),Color.BLUE));
        salida.setBackground(Color.BLUE);
        salida.setFont(new Font("Cambria",2,35));
        add(salida);
    
    
         Botonsin=new JButton[9];
           PanelConBotones=new JPanel();
        //PanelConBotones.setLayout(new GridLayout(1,Botonsin.length));
           //PanelConBotones.setLayout(new GridLayout(3,4));
          //PanelConBotones.setLayout(new GridLayout(4,3));
        //PanelConBotones.setLayout(new GridLayout(5,5));
           //PanelConBotones.setLayout(new GridLayout(6,1));
           PanelConBotones.setLayout(new GridLayout(6,3));
          // PanelConBotones.setLayout(new GridLayout(7,1));
          for(int contador=0; contador< Botonsin.length; contador++)
        {
            Botonsin[contador]=new JButton (""+(contador+1));
            PanelConBotones.add(Botonsin[contador]);
       }
PanelConBotones.add(b0);
PanelConBotones.add(bretroceso);
PanelConBotones.add(ac);
PanelConBotones.add(bmas);
PanelConBotones.add(bigual);
//parenDerecho,parenIzquierdo,multi,div,punto;
PanelConBotones.add(parenDerecho);
PanelConBotones.add(parenIzquierdo);
PanelConBotones.add(multi);
PanelConBotones.add(div);
PanelConBotones.add(punto);
PanelConBotones.add(modulo);
PanelConBotones.add(potencia);
bretroceso.setEnabled(false);
ac.setEnabled(false);
         add(PanelConBotones,BorderLayout.SOUTH);
         b0.addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"0");
                                        activaRetroceso();
                                        //activaRetroceso();
                                }
                        }
                );
                 Botonsin[0].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"1");
                                        activaRetroceso();
                                }
                        }
                );
                 Botonsin[1].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"2");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[2].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"3");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[3].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"4");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[4].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"5");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[5].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"6");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[6].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"7");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[7].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"8");
                                        activaRetroceso();
                                }
                        }
                );
                Botonsin[8].addActionListener(new ActionListener()
                    {
                                public void actionPerformed(ActionEvent e)
                                {
                                        salida.setText(salida.getText()+"9");
                                        activaRetroceso();
                                }
                        }
                );
                bretroceso.addActionListener(new ActionListener()///////
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                String xxx=salida.getText();
                                  if(xxx=="")
                                    {
                                   // System.out.println("ya no hay nada");
                                    }
                                if(xxx!="")
                                {
                                        xxx=xxx.substring(0,xxx.length()-1);
                                        salida.setText(xxx);
                                        //System.out.println(""+xxx);
                                }
                        }
                }
                );
                 ac.addActionListener(new ActionListener()///////
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                String xxx=salida.getText();
                                  if(xxx=="")
                                    {
                                   // System.out.println("ya no hay nada");
                                    }
                                if(xxx!="")
                                {
                                        //xxx=xxx.substring(0,xxx.length()-1);
                                    xxx="";
                                        salida.setText(xxx);
                                        //System.out.println(""+xxx);
                                }
                        }
                }
                );
               
        bigual.addActionListener(new ActionListener()////////////////////////////////////////////////
        {
                                public void actionPerformed(ActionEvent e)
                                {
                                    String dos=salida.getText();
                                        //uno("(5+3)*(2+3)");
                                   // String uno=uno(dos);
                                        
                                            //uno(dos);
                                            salida.setText("");
                                        salida.setText(salida.getText()+""+uno(dos));
                                     //System.out.println(""+uno);
                                        activaRetroceso();
                                }
                        }
                );
                
                parenDerecho.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"(");
                                        activaRetroceso();
                                }
                        }
                );
                parenIzquierdo.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+")");
                                        activaRetroceso();
                                }
                        }
                );
                //bmas
               bmas.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"+");
                                        activaRetroceso();
                                }
                        }
                );
                multi.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"*");
                                        activaRetroceso();
                                }
                        }
                );
                div.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"/");
                                        activaRetroceso();
                                }
                        }
                );
                punto.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+".");
                                        activaRetroceso();
                                }
                        }
                );
                //modulo,potencia;
                modulo.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"%");
                                        activaRetroceso();
                                }
                        }
                );
                potencia.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    String uno=salida.getText();
                                        salida.setText(salida.getText()+"^");
                                        activaRetroceso();
                                }
                        }
                );
      }// fin del costructor
    public void activaRetroceso()
    {
        if(!(salida.getText().equals("")))
            {
                bretroceso.setEnabled(true);
                ac.setEnabled(true);
                }
        else
                bretroceso.setEnabled(false);
    }
    public static void main (String []args)
    {
        cal Ventana= new cal();
       Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Ventana.setSize(350,300);
      Ventana.setSize(280,380);
       //Ventana.setSize(500,250);
       Ventana.setLocation(505,200);
       Ventana.setVisible(true);
    }
}