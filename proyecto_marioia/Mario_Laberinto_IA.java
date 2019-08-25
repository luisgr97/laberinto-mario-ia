/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import com.sun.prism.paint.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author luisg
 */
public class Mario_Laberinto_IA extends javax.swing.JFrame {

    //Bloque de atributos:
    private Nodo nodo;
    private String personaje;
    //private Reproductor reproductor = new Reproductor() ;
    private String[] enemigosBase = {"Hongo", "Tortuga", "Tortuga_Alada"};
    private String[] enemigosMedio = {"Tortuga_Armdillo", "Fantasma", "Tortuga_Erizo"};
    private String[] enemigosAlto = {"Bouser", "Rey_Sapo"};
    private String[] poderesMario = {"Maso_mario", "Ranita", "Llamita"};
    private String[] poderesLuigi = {"Llamita", "Capita_Luigi", "Ranita"};
    private Random aleatoreo;
    private int datos[] = new int[2];
    private int algoritmo_de_busqueda;
    private int iteraciones = 1;
    private Nodo matriz[][] = new Nodo[10][10];
    private Container contenedor;
    private BusquedaAmplitud ia1;
    private BusquedaCosto ia2;
    private BusquedaProfundidadSinCiclos ia3;
    private Avara ia4;
    private A_Estrella ia5;
    private JOptionPane mensajero;
    private ArrayList<Nodo> camino;
    private ArrayList<Nodo> pila;
    private boolean termine = false;
    private PanelFinalJuego gameover;
    //objetos para el manejo del tiempo
    //constante para los segundos de espera para la progressbar
    public final static int SECONDS = 2;
    private ImageIcon imagen;
    private String txt=null;
    //objeto timer
    Timer tiempo = new Timer(SECONDS, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try { 
            tiempo.setDelay(1000);
            //JOptionPane.showMessageDialog(null, "Hola");
             if (camino.get(iteraciones).getEstado() == 3) {
                if (personaje == "Kinopio") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Powers\\Kinopio_Fantasma.gif"));
                  txt = "src\\Resource\\Imagens\\Powers\\Kinopio_Fantasma.gif";
                }
                if (personaje == "Mario") {
                    int i = aleatoreo.nextInt(3);
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Powers\\" + poderesMario[i] + ".gif"));
                   txt ="src\\Resource\\Imagens\\Powers\\" + poderesMario[i] + ".gif"; 
                }
                if (personaje == "Luigi") {
                    int i = aleatoreo.nextInt(3);
                     matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Powers\\" +poderesLuigi[i] + ".gif"));
                     txt = "src\\Resource\\Imagens\\Powers\\" +poderesLuigi[i] + ".gif";
                }
            }//cierra principal if
            else {
                 if (txt !=null){
                   if (personaje == "Kinopio") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon(txt));
                }
                if (personaje == "Mario") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon(txt));
                }
                if (personaje == "Luigi") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon(txt));
                }
                if (personaje == "princesa") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon(txt));
                }
                 }else {
                   if (personaje == "Kinopio") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\Kinopio.gif"));
                }
                if (personaje == "Mario") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\Mario.gif"));
                }
                if (personaje == "Luigi") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\Luigi.gif"));
                }
                if (personaje == "princesa") {
                    matriz[camino.get(iteraciones).getFila()][camino.get(iteraciones).getColumna()].setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\princesa.gif"));
                } 
                 
                 
                 }
            }//cierra else
           
                HacerAnimacion();
            }catch(Exception ex){};      
          
        }//cierra action performed
    });

    public Mario_Laberinto_IA(int Matriz[][], String Personaje, int opc) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);//le indica que debe abrirse en pantalla completa
        this.personaje = Personaje;//guarda el nombre del personaje
        //this.algoritmo_de_busqueda = Algoritmo_de_Busqueda;//gurda el algoritmo a usar.

        for (int i = 0; i < 10; i++) {//ciclo que rellena el laberinto de la matriz
            for (int j = 0; j < 10; j++) {
                nodo = new Nodo();//crea un elemento nodo
                nodo.setFila(i);
                nodo.setColumna(j);
                nodo.setEstado(Matriz[i][j]);//toma el valor de la casilla en la matriz.
                this.add(AsiganaAnimacion(nodo));//agrega el nodo grafico a la frame.
                matriz[i][j] = nodo;
            }//for interno
            this.add(AsiganaAnimacion(nodo));//agrega el nodo grafico a la frame.
        }//for externo

        switch (opc) {
            case 1:
                this.ia1 = new BusquedaAmplitud(matriz);
                camino = ia1.Buscar();
                this.datos = ia1.GetDatosProfundidad_Y_Tiempo();
                break;
            case 2:
                this.ia2 = new BusquedaCosto(matriz);
                camino = ia2.Buscar();
                this.datos = ia2.GetDatosProfundidad_Y_Tiempo();
                break;
            case 3:
                this.ia3 = new BusquedaProfundidadSinCiclos(matriz);
                camino = ia3.Buscar();
                this.datos = ia3.GetDatosProfundidad_Y_Tiempo();
                break;
            case 4:
                this.ia4 = new Avara(matriz);
                camino = ia4.Buscar();
                this.datos = ia4.GetDatosProfundidad_Y_Tiempo();
                break;
            case 5:
                this.ia5 = new A_Estrella(matriz);
                camino = ia5.Buscar();
                this.datos = ia5.GetDatosProfundidad_Y_Tiempo();
                break;

        }
        mensajero = new JOptionPane();
        mensajero.showMessageDialog(this, "Por favor espere!\n Buscando...", "Mario IA", HEIGHT, new ImageIcon("src\\Resource\\Imagens\\Tags\\Load.gif"));
        HacerAnimacion();
    }//cierra constructor mario IA

    //metodo para asignar una imagen inicial 
    public Nodo AsiganaAnimacion(Nodo nd) {//funcion que le asinga una imagen a un nodo
        if (nd.getEstado() == 0) {
            nd.setIcon(null);//le quita la imagen
            nd.setBackground(null);//le cambia el fondo
            nd.setCosto(1);
        }
        if (nd.getEstado() == 1) {
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Tags\\pared.jpg"));//le agrega la imagen
        }
        if (nd.getEstado() == 2) {
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\" + personaje + ".gif"));//le agrega la imagen
            nd.setCosto(0);
        }
        if (nd.getEstado() == 3) {
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Tags\\Poderes.gif"));//le agrega la imagen
            nd.setCosto(1);
        }
        if (nd.getEstado() == 4) {
            aleatoreo = new Random();
            int aux = aleatoreo.nextInt(3);
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Enemigs\\" + enemigosBase[aux] + ".gif"));//le agrega la imagen
            nd.setCosto(7);
        }
        if (nd.getEstado() == 5) {
            if (personaje == "princesa") {
                nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Characters\\Mario.gif"));//le agrega la imagen
                nd.setCosto(1);
            }//agrega a mario como reen en caso de que selecione a la princesa
            else {
                nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Tags\\Princesa.gif"));//le agrega la imagen
                nd.setCosto(1);
            }
        }
        if (nd.getEstado() == 6) {
            aleatoreo = new Random();
            int aux = aleatoreo.nextInt(3);
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Enemigs\\" + enemigosMedio[aux] + ".gif"));//le agrega la imagen
        }

        if (nd.getEstado() == 7) {
            aleatoreo = new Random();
            int aux = aleatoreo.nextInt(2);
            nd.setIcon(new ImageIcon("src\\Resource\\Imagens\\Enemigs\\" + enemigosAlto[aux] + ".gif"));//le agrega la imagen
        }
//System.out.println("nodo: "+nd.getX()+", "+nd.getY()+" estado: "+  nd.getEstado());
        return nd;
    }//cierra metodo AsginaAnimacion

    //metodo para Animar el movimiento en el tablero.
    public void HacerAnimacion() {

        if (iteraciones < this.camino.size()) {

            if (iteraciones==1){
                
                this.tiempo.start();
            }
            else{
                if (iteraciones ==2){this.matriz[camino.get(iteraciones - 2).getFila()][camino.get(iteraciones - 2).getColumna()].setIcon(null);//le quita la imagen = camino.get(iteraciones - 1);
            this.matriz[camino.get(iteraciones - 2).getFila()][camino.get(iteraciones - 2).getColumna()].setBackground(null);
            }
            //blanquea la casilla donde estaba
            this.tiempo.start();
            this.matriz[camino.get(iteraciones - 1).getFila()][camino.get(iteraciones - 1).getColumna()].setIcon(null);//le quita la imagen = camino.get(iteraciones - 1);
            this.matriz[camino.get(iteraciones - 1).getFila()][camino.get(iteraciones - 1).getColumna()].setBackground(null);
            
            }
            iteraciones++;
        }//cierra while
              if (iteraciones>=camino.size()){
                // Ventana nuevap = new Ventana();
                 tiempo.stop();
                 JOptionPane.showMessageDialog(rootPane, "El costo de la busqueda fue: "+this.datos[0]+" Milisegundos"+"\n"
                         +"La profundidad del arbol fué: "+this.datos[1]+"\n"
                         +"Hace :"+camino.size()+" Movimientos desede"+"\n"
                 + "("+camino.get(0).getFila()+","+camino.get(0).getColumna()+")  Hasta "
                  + "("+camino.get(camino.size()-1).getFila()+","+camino.get(camino.size()-1).getColumna()+")");
               gameover = new PanelFinalJuego(personaje);
              }
                
    }//cierra el metodo que hace la animacion

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laberinto Mario IA");
        getContentPane().setLayout(new java.awt.GridLayout(10, 10, 2, 2));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
