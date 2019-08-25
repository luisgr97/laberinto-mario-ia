/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author luisg
 */
public class A_Estrella {

    //Bloque de atributos: ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Nodo matriz[][] = new Nodo[10][10];
    private Nodo partida;
    private Nodo meta;
    private Nodo padre;
    private boolean encontre_la_meta = false;
    private boolean tengoFlor = false;
    private boolean recursoUsado = false;
    private ArrayList<Nodo> pila = new ArrayList<Nodo>();
    private ArrayList<Nodo> camino = new ArrayList<Nodo>();
    private ArrayList<Nodo> caminoMasOptimo = new ArrayList<Nodo>();
    private int iterador = 0;
    private int profundidad = 0;
    private long tiempoEjecucion;
    private Comparator<Nodo> comparador = new Comparator<Nodo>() {
        @Override
        public int compare(Nodo p1, Nodo p2) {
            // Aqui esta el truco, ahora comparamos p2 con p1 y no al reves como antes
            return new Integer(p2.getCosto()).compareTo(new Integer(p1.getCosto()));
        }
    };

    //Bloque de metodos: ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public A_Estrella(Nodo tablero[][]) {
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

    }//cierra el constructor 

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
        Nodo descartado = new Nodo();
        for (int i = 0; i < movimientosValidos.size(); i++) {
            if ((padre != movimientosValidos.get(i))) {
                if ((padre.getFila() != movimientosValidos.get(i).getFila()) || (padre.getColumna() != movimientosValidos.get(i).getColumna())) {
                    movimientosSinDevolverse.add(movimientosValidos.get(i));
                    System.out.println("Expandi a: " + movimientosValidos.get(i).getFila() + "," + movimientosValidos.get(i).getColumna() + " " + movimientosValidos.get(i).getOperacion() + " costo: " + movimientosValidos.get(i).getCosto());
                }
            }
        }
        return movimientosSinDevolverse;
    }//cierra metodo
    //metodo que me hace la lista de los mivimientos que puedo hacer segun la posicion que me encuentre

    public ArrayList<Nodo> Movimientos(Nodo nd) {

        ArrayList<Nodo> movimientos = new ArrayList<Nodo>();
        int fila = nd.getFila();//capturo
        int columna = nd.getColumna();//capturo
        profundidad += 1;//aumenta la profundidad cada que expande 

        //primera casilla
        if ((fila == 0) && (columna == 0)) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
            movimiento1.setOperacion("Abajo");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento2.setOperacion("Derecha");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);

        }//cierra if

        //borde superior
        if ((fila == 0) && ((columna > 0) && (columna < 9))) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
            movimiento1.setOperacion("Abajo");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
            movimiento2.setOperacion("Izquierda");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);
            Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento3.setOperacion("Derecha");
            determinarCosto(movimiento3, nd);
            movimiento3.setCosto(movimiento3.getCosto() + padre.getCosto());
            movimiento3.setProfundidad(profundidad);
            movimientos.add(movimiento3);

        }//cierra if

        //punta sup derecho
        if ((fila == 0) && (columna == 9)) {
            matriz[fila + 1][columna].setOperacion("Abajo");
            Nodo movimiento1 = EvitarDuplicados(matriz[fila + 1][columna]);
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            matriz[fila][columna - 1].setOperacion("Izquierda");
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);

        }//2erra if

        //borde lateral dercho  
        if ((columna == 9) && ((fila > 0) && (fila < 9))) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
            movimiento1.setOperacion("Arriba");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
            movimiento2.setOperacion("Abajo");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);
            Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna - 1]);
            movimiento3.setOperacion("Izquierda");
            determinarCosto(movimiento3, nd);
            movimiento3.setCosto(movimiento3.getCosto() + padre.getCosto());
            movimiento3.setProfundidad(profundidad);
            movimientos.add(movimiento3);

        }//cierra if

        //punta inf derecho
        if ((fila == 9) && (columna == 9)) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setOperacion("Arriba");
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setOperacion("Izquierda");
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);

        }//cierra if

        //borde inf 
        if ((fila == 9) && ((columna > 0) && (columna < 9))) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
            movimiento1.setOperacion("Arriba");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna - 1]);
            movimiento2.setOperacion("Izquierda");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);
            Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento3.setOperacion("Derecha");
            determinarCosto(movimiento3, nd);
            movimiento3.setCosto(movimiento3.getCosto() + padre.getCosto());
            movimiento3.setProfundidad(profundidad);
            movimientos.add(movimiento3);

        }//cierra if

        //punta inf izq
        if ((fila == 0) && (columna == 9)) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
            movimiento1.setOperacion("Arriba");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento2.setOperacion("Derecha");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);

        }//cierra if

        //borde lateral Izquierdo  
        if ((columna == 0) && ((fila > 0) && (fila < 9))) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);
            movimiento1.setOperacion("Arriba");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
            movimiento2.setOperacion("Abajo");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);
            Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento3.setOperacion("Derecha");
            determinarCosto(movimiento3, nd);
            movimiento3.setCosto(movimiento3.getCosto() + padre.getCosto());
            movimiento3.setProfundidad(profundidad);
            movimientos.add(movimiento3);

        }//cierra if

        //centro del tablero  
        if (((columna > 0) && (columna < 9)) && ((fila > 0) && (fila < 9))) {
            Nodo movimiento1 = EvitarDuplicados(matriz[fila - 1][columna]);///
            movimiento1.setOperacion("Arriba");
            determinarCosto(movimiento1, nd);
            movimiento1.setCosto(movimiento1.getCosto() + padre.getCosto());
            movimiento1.setProfundidad(profundidad);
            movimientos.add(movimiento1);
            Nodo movimiento2 = EvitarDuplicados(matriz[fila + 1][columna]);
            movimiento2.setOperacion("Abajo");
            determinarCosto(movimiento2, nd);
            movimiento2.setCosto(movimiento2.getCosto() + padre.getCosto());
            movimiento2.setProfundidad(profundidad);
            movimientos.add(movimiento2);
            Nodo movimiento3 = EvitarDuplicados(matriz[fila][columna - 1]);
            movimiento3.setOperacion("Izquierda");
            determinarCosto(movimiento3, nd);
            movimiento3.setCosto(movimiento3.getCosto() + padre.getCosto());
            movimiento3.setProfundidad(profundidad);
            movimientos.add(movimiento3);
            Nodo movimiento4 = EvitarDuplicados(matriz[fila][columna + 1]);
            movimiento4.setOperacion("Derecha");
            determinarCosto(movimiento4, nd);
            movimiento4.setCosto(movimiento4.getCosto() + padre.getCosto());
            movimiento4.setProfundidad(profundidad);
            movimientos.add(movimiento4);
        }//cierra if
        if (nd.getTieneFlor() == true) {
            for (int i = 0; i < movimientos.size(); i++) {
                movimientos.get(i).setTieneFlor(true);
            }//cada expansion de un nodo flor tiene flor
        }

        return this.ValidarMovimientos(movimientos);
    }//cierra el metodo
    //metodo para expandir

    public ArrayList<Nodo> Expandir(Nodo nd) {

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
            if (nd.getEstado() == 3) {
                nd.setTieneFlor(true);//si paso por un 3 le pongo la flor
                System.out.println("Tengo una flor");
            }
            int fila = nd.getFila();//capturo
            int columna = nd.getColumna();//capturo
            System.out.println("Voy a expandir el nodo: " + fila + "," + columna + " " + nd.getOperacion() + "Flor: " + nd.getTieneFlor());

            //mira de donde vino, para determinar el nodo padre
            if (nd.getOperacion() == "Arriba") {
                padre = this.EvitarDuplicados(matriz[fila + 1][columna]);
                //determinarCosto(padre);
                System.out.println("El nodo Padre es: " + (fila + 1) + ", " + columna + " " + nd.getOperacion() + " Costo: " + nd.getCosto());
            }

            if (nd.getOperacion() == "Abajo") {
                padre = EvitarDuplicados(matriz[fila - 1][columna]);
                //determinarCosto(padre);
                System.out.println("El nodo Padre es: " + (fila - 1) + ", " + columna + " " + nd.getOperacion() + " Costo: " + nd.getCosto());
            }

            if (nd.getOperacion() == "Izquierda") {
                padre = EvitarDuplicados(matriz[fila][columna + 1]);
                //determinarCosto(padre);
                System.out.println("El nodo Padre es: " + fila + ", " + (columna + 1) + " " + nd.getOperacion() + " Costo: " + nd.getCosto());
            }

            if (nd.getOperacion() == "Derecha") {
                padre = EvitarDuplicados(matriz[fila][columna - 1]);
                //  determinarCosto(padre);
                System.out.println("El nodo Padre es: " + fila + ", " + (columna - 1) + " " + nd.getOperacion() + " Costo: " + nd.getCosto());
            }

        }

        return Movimientos(nd);
    }//cierra el metodo.

    //metodo para calcular la euristica
    public int CalcularHeuristica(Nodo nodo) {
        int fila = nodo.getFila();
        int columna = nodo.getColumna();

        int distanciaX = (int) Math.pow((meta.getFila() - nodo.getFila()), 2);
        int distanciaY = (int) Math.pow((meta.getColumna() - nodo.getColumna()), 2);
        int Hn = (int) Math.ceil(Math.sqrt(distanciaX + distanciaY));

        return Hn;
    }//cierra el metodo que calcula la heuristica

    //metodo para ordenar por costo y heuristica
    public void determinarCosto(Nodo nd, Nodo padre) {

        if ((nd.getTieneFlor() == false) && (padre.getTieneFlor() == false)) {
            if (nd.getEstado() == 0) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 1) {
                nd.setCosto(1);
            }
            if (nd.getEstado() == 2) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 3) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 4) {
                nd.setCosto((7 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 5) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
        } else {
            if (nd.getEstado() == 0) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 1) {
                nd.setCosto(1);
            }
            if (nd.getEstado() == 2) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 3) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 4) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
            if (nd.getEstado() == 5) {
                nd.setCosto((1 + padre.getCosto()) + CalcularHeuristica(nd));
            }
        }//cierra else     
    }//cierra metodo AsginaAnimacion
    //metodo para buscar por costo

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
                    System.out.println(exp.get(j).getFila() + "," + exp.get(j).getColumna() + " " + exp.get(j).getOperacion() + " costo: " + exp.get(j).getCosto());
                }
                Collections.sort(pila, comparador);
                Collections.reverse(pila);
            } else {
                ArrayList<Nodo> exp = Expandir(pila.get(0));
                camino.add(pila.get(0));
                pila.remove(0);
                System.out.println("añadi a la pila: " + "\n");
                for (int j = 0; j < exp.size(); j++) {
                    pila.add(exp.get(j));
                    System.out.println(exp.get(j).getFila() + "," + exp.get(j).getColumna() + " " + exp.get(j).getOperacion() + " costo: " + exp.get(j).getCosto());
                }
                Collections.sort(pila, comparador);
                Collections.reverse(pila);

            }
            iterador++;
        }//cierra el while
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        this.tiempoEjecucion = tiempo;
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo+" y tiene una profundidad de: "+this.profundidad+"\n"); //Mostramos en pantalla el tiempo de ejecución en milisegundo

        EncontrarCamino();
        return this.caminoMasOptimo;
    }

    //metodo que me trae el padre de donde vine
    public Nodo TraerElPadre(Nodo nd) {
        int fila = nd.getFila();
        int columna = nd.getColumna();
        Nodo nodo = new Nodo();
        boolean encontro = false;
        int i = (0);

        if (nd.getEstado() != 2) {
            if (nd.getOperacion() == "Arriba") {
                nodo.setFila(fila + 1);
                nodo.setColumna(columna);
                while (encontro == false) {
                    if ((camino.get(i).getFila() == nodo.getFila()) && (camino.get(i).getColumna() == nodo.getColumna())) {
                        nodo = camino.get(i);
                        encontro = true;
                    }
                    i++;
                }
            }

            if (nd.getOperacion() == "Abajo") {
                nodo.setFila(fila - 1);
                nodo.setColumna(columna);
                while (encontro == false) {
                    if ((camino.get(i).getFila() == nodo.getFila()) && (camino.get(i).getColumna() == nodo.getColumna())) {
                        nodo = camino.get(i);
                        encontro = true;
                    }
                    i++;
                }
            }

            if (nd.getOperacion() == "Izquierda") {
                nodo.setFila(fila);
                nodo.setColumna(columna + 1);
                while (encontro == false) {
                    if ((camino.get(i).getFila() == nodo.getFila()) && (camino.get(i).getColumna() == nodo.getColumna())) {
                        nodo = camino.get(i);
                        encontro = true;
                    }
                    i++;
                }
            }

            if (nd.getOperacion() == "Derecha") {
                nodo.setFila(fila);
                nodo.setColumna(columna - 1);
                while (encontro == false) {
                    if ((camino.get(i).getFila() == nodo.getFila()) && (camino.get(i).getColumna() == nodo.getColumna())) {
                        nodo = camino.get(i);
                        encontro = true;
                    }
                    i++;
                }
            }
        }
        return nodo;
    }

    //metodo que encuentra el camino para ir a la princesa 
    public void EncontrarCamino() {
        camino.add(0, partida);
        int iterador = (camino.size() - 1);
        int tope = (camino.size() - 1);
        System.out.println("Tengo " + iterador + " Elementos en la Pila");
        Nodo nodoPadre = new Nodo();
        boolean encontre = false;
        //Collections.reverse(camino);

        System.out.println("Pila:");
        for (int i = 0; i < camino.size(); i++) {
            System.out.println("(" + camino.get(i).getFila() + "," + camino.get(i).getColumna() + "), " + camino.get(i).getOperacion());
        }

        while (iterador >= 0) {

            if (iterador == tope) {
                caminoMasOptimo.add(camino.get(iterador));
                System.out.println(caminoMasOptimo.get(0).getFila() + "," + caminoMasOptimo.get(0).getColumna() + " " + caminoMasOptimo.get(0).getOperacion());
                nodoPadre = this.TraerElPadre(camino.get(iterador));
                camino.remove(iterador);
            } else if (nodoPadre.getOperacion() != null) {
                caminoMasOptimo.add(nodoPadre);
                nodoPadre = this.TraerElPadre(nodoPadre);
            }

            iterador--;
        }
        caminoMasOptimo.add(partida);
        Collections.reverse(caminoMasOptimo);
        System.out.println("Camino:");
        for (int i = 0; i < caminoMasOptimo.size(); i++) {
            System.out.println("(" + caminoMasOptimo.get(i).getFila() + "," + caminoMasOptimo.get(i).getColumna() + "), " + caminoMasOptimo.get(i).getOperacion());
        }

    }//cierra el metodo

        //metodo para retornar El tiempo de ejecucion y la profundidad del arbol.
    public int [] GetDatosProfundidad_Y_Tiempo(){
      int [] datos = new int [2];
        datos[0]= (int)this.tiempoEjecucion;
        datos[1] = this.profundidad;
      return datos;
    }//cierra el metodo datos.

}
