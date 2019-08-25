/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author luisg
 */
public class Nodo extends JLabel {//clase nodo que representa una casilla del tablero que hereda de JLabel

    //Bloque de atributos para la clase.
    private int costo;//variable que almacena el costo de una operacion
    private int estado;//variable que almacena el estado de la casilla
    private int heuristica;//variabl que almacena la funcion heuristica
    private int fila;//guarda la fila donde se hubica
    private int columna;//guarda la columna donde se hubica
    private String operacion;//guarda la operacion que realiza (movimiento)
    private boolean tieneFlor;//me dice si puedo pasar por todo lado
    private int profundidad;//guarda la profundidad del nodo

    //Bloque de metodos para la clase:
    public Nodo() {
        this.costo = 0;
        this.estado = 0;
        this.heuristica = 0;
        this.profundidad = 0;
        this.tieneFlor = false;
        this.setSize(150, 150);
        this.setVisible(true);

    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
    
    

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    

    public boolean getTieneFlor() {
        return tieneFlor;
    }

    public void setTieneFlor(boolean tieneFlor) {
        this.tieneFlor = tieneFlor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

}//cierra la clase.
