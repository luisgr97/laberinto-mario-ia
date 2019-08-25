/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author luisg
 */
public class Archivos {

    String texto_del_Documento;
    int matriz_de_estados[][] = new int[10][10];

    //Metodo para leer el archivo:
    public int[][] LeerTxt(String URL) {

        try {
            BufferedReader bufer;
            bufer = new BufferedReader(new FileReader(URL));
            String variableTemporal = "";
            String buferRead;
            while ((buferRead = bufer.readLine()) != null) {//mientras tenga que leer, ejecute....
                variableTemporal += buferRead;//concatena todo lo que lea en el archivo   
            }//cierra el while
            texto_del_Documento = variableTemporal;

        } catch (Exception e) {
            System.err.println("No se encontro archivo");
        }//cierra el catch
        ConstructorMatriz(texto_del_Documento);//cuando tiene los numeros construeye la matriz
        return matriz_de_estados;//retorna el texto obtenido del documento

    }//cierra el metodo LeerTxt

    public void ConstructorMatriz(String Estados) {
        String cadenaA = Estados.replace(" ", "");//limpia los espacios en la cadena
        int marcador = 0;
        for (int i = 0; i < 10; i++) {//ciclo que recorre las filas
            for (int j = 0; j < 10; j++) {//ciclo que recorre las columnas
                matriz_de_estados[i][j] = Integer.parseInt(Character.toString(cadenaA.charAt(j)));
                marcador = j + 1;//marca la posicion que tengo remplazar en la nueva cadena.
            }//cierra primer for
            String cadenaB = cadenaA.substring(marcador, cadenaA.length());//acorta la cadena
            cadenaA = cadenaB;//reasigana el valor de la cadena.               
        }//cierra el ultimo for.
    }//cierra el metodo ConstructorMAtriz

}//cierra la clase.
