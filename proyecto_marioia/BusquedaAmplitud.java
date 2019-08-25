/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author luisg
 */
public class BusquedaAmplitud {

    //Bloque de atributos: ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Nodo matriz[][] = new Nodo[10][10];
    private Nodo partida;
    private Nodo meta;
    private Nodo padre;
    private boolean encontre_la_meta = false;
    private ArrayList<Nodo> pila = new ArrayList<Nodo>();
    private ArrayList<Nodo> camino = new ArrayList<Nodo>();
    private int iterador = 0;
    private int profundidad = 0;
    private long tiempoEjecucion;

    //Bloque de metodos: /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //constructor de la clase.
    public BusquedaAmplitud(Nodo tablero[][]) {
        this.matriz = tablero;

        for (int x = 0; x < matriz.length; x++) {
            System.out.print("|");
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(matriz[x][y].getEstado());
                if (y != matriz[x].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("|");
        }
        EncontrarMetayPartida(this.matriz);
    }

    //metodo que encuentra la posicion donde inicio y termino la partda. 
    public void EncontrarMetayPartida(Nodo tablero[][]) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j].getEstado() == 2) {
                    this.partida = tablero[i][j];
                    System.out.println("padre: " + tablero[i][j].getFila() + "," + tablero[i][j].getColumna());
                }//cierra if
                if (tablero[i][j].getEstado() == 5) {
                    this.meta = tablero[i][j];
                    System.out.println("meta: " + tablero[i][j].getFila() + "," + tablero[i][j].getColumna());
                }//cierra if
            }
        }//cierra segundo for
    }//cierra metodo.
    //metodo que valida los movimientos

    public ArrayList<Nodo> ValidarMovimientos(ArrayList<Nodo> movimientosExp) {
        ArrayList<Nodo> movimientosValidados = new ArrayList<Nodo>();

        for (int i = 0; i < movimientosExp.size(); i++) {
            if (movimientosExp.get(i).getEstado() != 1) {
                movimientosValidados.add(movimientosExp.get(i));//lo agrega si no es una pared.
            }
        }//cierra for
        return EvitarDevolverse(movimientosValidados);//retorna el vector de los movimentos que puedo hacer ya validados
    }//cierra metodo
    //metodo que evita que me devuelva

    public ArrayList<Nodo> EvitarDevolverse(ArrayList<Nodo> movimientosValidos) {
        ArrayList<Nodo> movimientosSinDevolverse = new ArrayList<Nodo>();
        for (int i = 0; i < movimientosValidos.size(); i++) {
            if ((padre != movimientosValidos.get(i))) {
                if ((padre.getFila() != movimientosValidos.get(i).getFila()) || (padre.getColumna() != movimientosValidos.get(i).getColumna())) {
                    movimientosSinDevolverse.add(movimientosValidos.get(i));
                    System.out.println("Expandi a: " + movimientosValidos.get(i).getFila() + "," + movimientosValidos.get(i).getColumna() + " " + movimientosValidos.get(i).getOperacion());
                }
            }
        }

        return movimientosSinDevolverse;
    }//cierra metodo
    //metodo que expande el nodo.

    public ArrayList<Nodo> Expandir(Nodo nd) {

        ArrayList<Nodo> movimientos = new ArrayList<Nodo>();
       
        if (((nd.getFila() == this.meta.getFila()) && (nd.getColumna() == this.meta.getColumna()))) {
            this.encontre_la_meta = true;
            System.out.println("Voy a expandir el nodo: " + nd.getFila() + "," + nd.getColumna() + " " + nd.getOperacion());
            System.out.println("Es la meta asi que termino.");
            pila.add(nd);
            //  System.exit(0);
        }

        if ((nd.getFila() == this.partida.getFila())
                && (nd.getColumna() == this.partida.getColumna())) {
            this.padre = nd;
        }

        if (((nd.getFila() != this.meta.getFila()) || (nd.getColumna() != this.meta.getColumna()))) {

            System.out.println("no es la meta asi que expando:");

            int fila = nd.getFila();//capturo
            int columna = nd.getColumna();//capturo
            System.out.println("Voy a expandir el nodo: " + fila + "," + columna + " " + nd.getOperacion());

//mira de donde vino, para determinar el nodo padre
            if (nd.getOperacion() == "Arriba") {
                padre = this.EvitarDuplicados(matriz[fila + 1][columna]);
                System.out.println("El nodo Padre es: " + (fila + 1) + ", " + columna + " " + nd.getOperacion());
            }

            if (nd.getOperacion() == "Abajo") {
                padre = EvitarDuplicados(matriz[fila - 1][columna]);

                System.out.println("El nodo Padre es: " + (fila - 1) + ", " + columna + " " + nd.getOperacion());
            }

            if (nd.getOperacion() == "Izquierda") {
                padre = EvitarDuplicados(matriz[fila][columna + 1]);

                System.out.println("El nodo Padre es: " + fila + ", " + (columna + 1) + " " + nd.getOperacion());
            }

            if (nd.getOperacion() == "Derecha") {
                padre = EvitarDuplicados(matriz[fila][columna - 1]);
                System.out.println("El nodo Padre es: " + fila + ", " + (columna - 1) + " " + nd.getOperacion());

            }
             profundidad+=1;
            //de aqui en adelante mira que movimientos puede hacer......       
            //primera casilla
            if ((fila == 0) && (columna == 0)) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento1.setOperacion("Abajo");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento2.setOperacion("Derecha");
                movimiento2.setProfundidad(profundidad);  
                movimientos.add(movimiento2);

            }//cierra if

            //borde superior
            if ((fila == 0) && ((columna > 0) && (columna < 9))) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento1.setOperacion("Abajo");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento2.setOperacion("Izquierda");
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);
                Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento3.setOperacion("Derecha");
                movimiento3.setProfundidad(profundidad);
                movimientos.add(movimiento3);

            }//cierra if

            //punta sup derecho
            if ((fila == 0) && (columna == 9)) {
                matriz[fila + 1][columna].setOperacion("Abajo");
                Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                matriz[fila][columna - 1].setOperacion("Izquierda");
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);

            }//cierra if

            //borde lateral dercho  
            if ((columna == 9) && ((fila > 0) && (fila < 9))) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento2.setOperacion("Abajo");
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);
                Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento3.setOperacion("Izquierda");
                movimiento3.setProfundidad(profundidad);
                movimientos.add(movimiento3);

            }//cierra if

            //punta inf derecho
            if ((fila == 9) && (columna == 9)) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento2.setOperacion("Izquierda");
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);

            }//cierra if

            //borde inf 
            if ((fila == 9) && ((columna > 0) && (columna < 9))) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento2.setOperacion("Izquierda");
                movimiento2.setProfundidad(profundidad); 
                movimientos.add(movimiento2);
                Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento3.setOperacion("Derecha");
                movimiento3.setProfundidad(profundidad);
                movimientos.add(movimiento3);

            }//cierra if

            //punta inf izq
            if ((fila == 0) && (columna == 9)) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento2.setOperacion("Derecha");
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);

            }//cierra if

            //borde lateral Izquierdo  
            if ((columna == 0) && ((fila > 0) && (fila < 9))) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento2.setOperacion("Abajo");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento2);
                Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento3.setOperacion("Derecha");
                movimiento3.setProfundidad(profundidad);
                movimientos.add(movimiento3);

            }//cierra if

            //centro del tablero  
            if (((columna > 0) && (columna < 9)) && ((fila > 0) && (fila < 9))) {
                Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);///
                movimiento1.setOperacion("Arriba");
                movimiento1.setProfundidad(profundidad);
                movimientos.add(movimiento1);
                Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
                movimiento2.setOperacion("Abajo");
                movimiento2.setProfundidad(profundidad);
                movimientos.add(movimiento2);
                Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna - 1]);
                movimiento3.setOperacion("Izquierda");
                movimiento3.setProfundidad(profundidad);
                movimientos.add(movimiento3);
                Nodo movimiento4 = EvitarDuplicados(matriz[fila][columna + 1]);
                movimiento4.setOperacion("Derecha");
                movimiento4.setProfundidad(profundidad);
                movimientos.add(movimiento4);
            }//cierra if
        }
        return ValidarMovimientos(movimientos);
    }

    //evita que se me dupliquen las poscisiones en la matriz
    public Nodo EvitarDuplicados(Nodo nodo) {
        Nodo esta = new Nodo();
        boolean banderilla = false;
        if (pila.size() != 0) {
            for (int i = 0; i < pila.size(); i++) {
                if ((pila.get(i).getFila() == nodo.getFila()) || (pila.get(i).getColumna() == nodo.getColumna())) {
                    banderilla = true;
                }
            }
            if (banderilla == true) {
                esta.setFila(nodo.getFila());
                esta.setColumna(nodo.getColumna());
                esta.setEstado(nodo.getEstado());

            } else {
                esta = nodo;
            }

        } else {
            esta = nodo;
        }
        return esta;
    }

    //metodo que busca a la princesa
    public ArrayList<Nodo> Buscar() {
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        int iterador = 0;
        //pila.add(partida);
        while (encontre_la_meta == false) {
            if (iterador == 0) {
                System.out.println("iteracion: " + iterador);
                ArrayList<Nodo> exp = Expandir(partida);
                System.out.println("añadi a la pila: " + "\n");
                for (int j = 0; j < exp.size(); j++) {
                    pila.add(exp.get(j));
                    System.out.println(exp.get(j).getFila() + "," + exp.get(j).getColumna() + " " + exp.get(j).getOperacion());
                }
            } else {
                ArrayList<Nodo> exp = Expandir(pila.get(iterador - 1));

                System.out.println("añadi a la pila: " + "\n");
                for (int j = 0; j < exp.size(); j++) {
                    pila.add(exp.get(j));
                    System.out.println(exp.get(j).getFila() + "," + exp.get(j).getColumna() + " " + exp.get(j).getOperacion());
                }
            }
            iterador++;
        }//cierra el while
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        this.tiempoEjecucion = tiempo;
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo+" y tiene una profundidad de: "+this.profundidad+"\n"); //Mostramos en pantalla el tiempo de ejecución en milisegundo
        EncontrarCamino();
        Collections.reverse(camino);
        return this.camino;
    }

    //metodo que me trae el padre de donde vine
    public Nodo TraerElPadre(Nodo nd) {
        int fila = nd.getFila();
        int columna = nd.getColumna();
        Nodo nodo = new Nodo();
        boolean encontro = false;
        int i = (0);

//        System.out.println("me devuelvo de: " + fila + "," + columna + " " + nd.getOperacion());
        if (nd.getEstado() != 2) {
            if (nd.getOperacion() == "Arriba") {
                nodo.setFila(fila + 1);
                nodo.setColumna(columna);
                while (encontro == false) {
                    if ((pila.get(i).getFila() == nodo.getFila()) && (pila.get(i).getColumna() == nodo.getColumna())) {
                        nodo = pila.get(i);
                        encontro = true;
                    }
                    i++;
                }
                //System.out.println("Voy por: " + (fila + 1) + ", " + columna + " " + nodo.getOperacion());
            }

            if (nd.getOperacion() == "Abajo") {
                nodo.setFila(fila - 1);
                nodo.setColumna(columna);
                while (encontro == false) {
                    if ((pila.get(i).getFila() == nodo.getFila()) && (pila.get(i).getColumna() == nodo.getColumna())) {
                        nodo = pila.get(i);
                        encontro = true;
                    }
                    i++;
                }
                //System.out.println("Voy por: " + (fila - 1) + ", " + columna + " " + nodo.getOperacion());
            }

            if (nd.getOperacion() == "Izquierda") {
                nodo.setFila(fila);
                nodo.setColumna(columna + 1);
                while (encontro == false) {
                    if ((pila.get(i).getFila() == nodo.getFila()) && (pila.get(i).getColumna() == nodo.getColumna())) {
                        nodo = pila.get(i);
                        encontro = true;
                    }
                    i++;
                }
                //System.out.println("Voy por: " + fila + ", " + (columna + 1) + " " + nodo.getOperacion());
            }

            if (nd.getOperacion() == "Derecha") {
                nodo.setFila(fila);
                nodo.setColumna(columna - 1);
                while (encontro == false) {
                    if ((pila.get(i).getFila() == nodo.getFila()) && (pila.get(i).getColumna() == nodo.getColumna())) {
                        nodo = pila.get(i);
                        encontro = true;
                    }
                    i++;
                }
                //System.out.println("Voy por: " + fila + ", " + (columna - 1) + " " + nodo.getOperacion());
            }
        }
        return nodo;
    }

    //metodo que encuentra el camino para ir a la princesa 
    public void EncontrarCamino() {

        int iterador = (pila.size() - 1);
        int tope = (pila.size() - 1);
        System.out.println("Tengo " + iterador + " Elementos en la Pila");
        Nodo nodoPadre = new Nodo();
        boolean encontre = false;

        while (iterador >= 0) {

            if (iterador == tope) {
                camino.add(pila.get(iterador));
                System.out.println(camino.get(0).getFila() + "," + camino.get(0).getColumna() + " " + camino.get(0).getOperacion());
                nodoPadre = this.TraerElPadre(pila.get(iterador));
                pila.remove(iterador);
            } else if (nodoPadre.getOperacion() != null) {
                camino.add(nodoPadre);
                nodoPadre = this.TraerElPadre(nodoPadre);
            }

            iterador--;
        }
        System.out.println("Camino:");
        for (int i = 0; i < camino.size(); i++) {
            System.out.println("(" + camino.get(i).getFila() + "," + camino.get(i).getColumna() + "), " + camino.get(i).getOperacion());
        }

    }
    
    //metodo para retornar El tiempo de ejecucion y la profundidad del arbol.
    public int [] GetDatosProfundidad_Y_Tiempo(){
      int [] datos = new int [2];
        datos[0]= (int)this.tiempoEjecucion;
        datos[1] = this.profundidad;
      return datos;
    }//cierra el metodo datos.

}//cirra la clase.
