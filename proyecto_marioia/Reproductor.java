/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;

import java.io.File;
//import javazoom.jlgui.basicplayer.BasicPlayer;


public class Reproductor {
  //  public BasicPlayer player;

    public Reproductor() {
    //    player = new BasicPlayer();
    }
public void coge(String y){

}
    public void Play() throws Exception {
       // player.play();
    }

    public void AbrirFichero(String ruta) throws Exception {
       // player.open(new File(ruta));
    }

    public void Pausa() throws Exception {
       // player.pause();
    }

    public void Continuar() throws Exception {
        //player.resume();
    }

    public void Stop() throws Exception {
      //  player.stop();
    }

   
}
/*
        public void Sonido(){
        try{
            FileInputStream fis;
            Player player;
            fis = new FileInputStream(
                    "C:\\Users\\luisg\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto_MarioIA\\src\\Resource\\Sounds\\Sounds-Background\\0");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play();          // Llamada al método play
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
*/