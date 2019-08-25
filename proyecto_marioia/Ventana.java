/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_marioia;

import java.applet.AudioClip;//Libreria para la reproduccion de audios.
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;

/**
 *
 * @author luisg
 */
public class Ventana extends javax.swing.JFrame {

    //Bloque de Atributos:
    Random aleatoreo;
    Reproductor reproductor;//Objeto de la clase reproductor para el auidio.
    Archivos gestorArchivos = new Archivos();//Objeto de la clase Archivos para el manejo de archivos.
    Mario_Laberinto_IA laberinto;//Objeto que representa el tablero de juego.

    //variables logicas 
    Boolean personaje_selected = false;//variable logica para cambiar el personaje
    Boolean archivo_cargado = false;//variable logica para manejar el archivo
    String personaja = null;//variable que almacena el ultimo personaje seleccionado
    String rutaArchivo = null;//variable que guarda la ruta del archivo a jugar

    public Ventana() throws Exception {
        initComponents();
        this.setSize(1020, 753);
        this.setResizable(false);//asigan el tamaño fijo para la ventana
        ///aleatoreo = new Random(8);//Asegura que se escoja una Pista distinta cada vez
        // reproductor = new Reproductor();
        try {
            //   reproductor.AbrirFichero("C:\\Users\\luisg\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto_MarioIA\\src\\Resource\\Sounds\\Sounds-Background\\8");
        } catch (Exception ex) {
            //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        // reproductor.Play();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        Titulo = new javax.swing.JLabel();
        Sound = new javax.swing.JLabel();
        Personaje_1 = new javax.swing.JLabel();
        Personaje_2 = new javax.swing.JLabel();
        Personaje_3 = new javax.swing.JLabel();
        Personaje_4 = new javax.swing.JLabel();
        Start = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        listaniveles = new javax.swing.JComboBox<>();
        Cargar = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MARIO L-IA");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().setLayout(null);

        label1.setBackground(new java.awt.Color(51, 153, 255));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("Seleccióne un Personaje:");
        getContentPane().add(label1);
        label1.setBounds(10, 250, 290, 36);

        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Tags/Titulo.png"))); // NOI18N
        getContentPane().add(Titulo);
        Titulo.setBounds(620, -10, 310, 190);

        Sound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Tags/Sonido_2.jpg"))); // NOI18N
        getContentPane().add(Sound);
        Sound.setBounds(270, 150, 50, 50);

        Personaje_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Characters/Mario.gif"))); // NOI18N
        Personaje_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Personaje_1(evt);
            }
        });
        getContentPane().add(Personaje_1);
        Personaje_1.setBounds(40, 300, 30, 60);

        Personaje_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Characters/Luigi.gif"))); // NOI18N
        Personaje_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Personaje_2(evt);
            }
        });
        getContentPane().add(Personaje_2);
        Personaje_2.setBounds(120, 300, 30, 60);

        Personaje_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Characters/Kinopio.gif"))); // NOI18N
        Personaje_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Personaje_3(evt);
            }
        });
        getContentPane().add(Personaje_3);
        Personaje_3.setBounds(190, 300, 30, 60);

        Personaje_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Characters/princesa.gif"))); // NOI18N
        Personaje_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Personaje_4(evt);
            }
        });
        getContentPane().add(Personaje_4);
        Personaje_4.setBounds(260, 300, 30, 60);

        Start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Tags/Start.png"))); // NOI18N
        Start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FuncionStart(evt);
            }
        });
        getContentPane().add(Start);
        Start.setBounds(20, 550, 320, 180);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Tags/Logo_U.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(800, 540, 210, 190);

        listaniveles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4", "Nivel 5", "Nivel 6", "Nivel 7", "Nivel 8", "Nivel 9", "Nivel 10" }));
        listaniveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecionComboBox(evt);
            }
        });
        getContentPane().add(listaniveles);
        listaniveles.setBounds(10, 370, 120, 30);

        Cargar.setText("Cargar Nivel");
        Cargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargarArchivo(evt);
            }
        });
        getContentPane().add(Cargar);
        Cargar.setBounds(140, 370, 110, 30);

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Imagens/Tags/Fondo.jpg"))); // NOI18N
        getContentPane().add(Fondo);
        Fondo.setBounds(0, 0, 1020, 783);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Bloque de metodos que determinan el personaje Seleccionado........    
    private void Personaje_1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Personaje_1
        // TODO add your handling code here:
    
        if (personaje_selected == true) {

            //elimina las selecciones posteriores
            Personaje_1.setBorder(null);
            Personaje_2.setBorder(null);
            Personaje_3.setBorder(null);
            Personaje_4.setBorder(null);

            //seleciona el personaje1
            Personaje_1.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaja = "Mario";
            System.out.println("Seleccione a: " + personaja);
        } else if (personaje_selected == false) {//cumple la primer seleccion
            Personaje_1.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaje_selected = true;
            personaja = "Mario";
            System.out.println("Seleccione a: " + personaja);
        }

    }//GEN-LAST:event_Personaje_1

    private void Personaje_2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Personaje_2
        // TODO add your handling code here:
        if (personaje_selected == true) {

            //elimina las selecciones posteriores
            Personaje_1.setBorder(null);
            Personaje_2.setBorder(null);
            Personaje_3.setBorder(null);
            Personaje_4.setBorder(null);

            //seleciona el personaje1
            Personaje_2.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaja = "Luigi";
            System.out.println("Seleccione a: " + personaja);
        } else if (personaje_selected == false) {//cumple la primer seleccion
            Personaje_2.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaje_selected = true;
            personaja = "Luigi";
            System.out.println("Seleccione a: " + personaja);
        }
    }//GEN-LAST:event_Personaje_2

    private void Personaje_3(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Personaje_3
        // TODO add your handling code here:
        if (personaje_selected == true) {

            //elimina las selecciones posteriores
            Personaje_1.setBorder(null);
            Personaje_2.setBorder(null);
            Personaje_3.setBorder(null);
            Personaje_4.setBorder(null);

            //seleciona el personaje1
            Personaje_3.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaja = "Kinopio";
            System.out.println("Seleccione a: " + personaja);
        } else if (personaje_selected == false) {//cumple la primer seleccion
            Personaje_3.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaje_selected = true;
            personaja = "Kinopio";
            System.out.println("Seleccione a: " + personaja);
        }
    }//GEN-LAST:event_Personaje_3

    private void Personaje_4(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Personaje_4
        // TODO add your handling code here:
        if (personaje_selected == true) {

            //elimina las selecciones posteriores
            Personaje_1.setBorder(null);
            Personaje_2.setBorder(null);
            Personaje_3.setBorder(null);
            Personaje_4.setBorder(null);

            //seleciona el personaje1
            Personaje_4.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaja = "princesa";
            System.out.println("Seleccione a: " + personaja);

        } else if (personaje_selected == false) {//cumple la primer seleccion
            Personaje_4.setBorder(BorderFactory.createLineBorder(Color.yellow));
            personaje_selected = true;
            personaja = "princesa";
            System.out.println("Seleccione a: " + personaja);

        }
    }//GEN-LAST:event_Personaje_4

//metodos para gestionar la informacion del nivel a cargar    
    private void CargarArchivo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarArchivo
        // TODO add your handling code here:
        JFileChooser buscador_archivo = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
        buscador_archivo.setFileFilter(filter);//crea el filtro y añade solo txt
        buscador_archivo.showOpenDialog(this);//abre la ventana

        int seleccion = buscador_archivo.showSaveDialog(this);
        if (seleccion == buscador_archivo.APPROVE_OPTION) {//si selecciona un archivo entonces  captura la URL
            File fichero = buscador_archivo.getSelectedFile();
            rutaArchivo = fichero.getPath();
            System.out.println(rutaArchivo);
            if (rutaArchivo != null) {
                archivo_cargado = true;//ya no se puede seleccionar el combobox
                this.listaniveles.enable(false);//evita que use el combobox
            }//cierra if interno 

        }//cierra el if 
    }//GEN-LAST:event_CargarArchivo

    private void SelecionComboBox(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecionComboBox
        // TODO add your handling code here:
        if (archivo_cargado == false) {

            if (evt.getSource() != this.listaniveles.getSelectedItem()) {
                if (this.listaniveles.getSelectedItem() == "Nivel 1") {
                    rutaArchivo = "src\\Resource\\Levels\\1.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);
                }
                if (this.listaniveles.getSelectedItem() == "Nivel 2") {
                    rutaArchivo = "src\\Resource\\Levels\\2.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 3") {
                    rutaArchivo = "src\\Resource\\Levels\\3.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 4") {
                    rutaArchivo = "src\\Resource\\Levels\\4.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 5") {
                    rutaArchivo = "src\\Resource\\Levels\\5.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 6") {
                    rutaArchivo = "src\\Resource\\Levels\\6.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 7") {
                    rutaArchivo = "src\\Resource\\Levels\\7.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 8") {
                    rutaArchivo = "src\\Resource\\Levels\\8.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 9") {
                    rutaArchivo = "src\\Resource\\Levels\\9.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
                if (this.listaniveles.getSelectedItem() == "Nivel 10") {
                    rutaArchivo = "src\\Resource\\Levels\\10.txt";
                    archivo_cargado = true;
                    System.out.println(rutaArchivo);

                }
            }//si selecione algo del comobo box

        }//si no hay archivo cargado
        else {
            archivo_cargado = false;
            SelecionComboBox(evt);//desactiva el true, para cambiar la seleccion.
        }
    }//GEN-LAST:event_SelecionComboBox

    //metodo para arrancar la matriz del juego.
    private void FuncionStart(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuncionStart
    try{    // TODO add your handling code here:
        if ((personaje_selected == true) && (archivo_cargado == true)) {
            String opc = JOptionPane.showInputDialog("Seleccione un Algoritmo de Busqueda: "
                    + "\n" + "*/Busqueda No Informada: " + "\n" + "    1.Busqueda Por Amplitud" + "\n" + "    2.Busqueda Por Costo Uniforme"
                    + "\n" + "    3.Busqueda Por Profundidad Evitando Ciclos"
                    + "\n" + "*/Busqueda Informada: "  + "\n" + "    4.Busqueda Por Avara"+ "\n" + "    5.Busqueda Por A*"
            );
            this.dispose();//cirra la ventana sin detener la ejecucion
            System.out.println("La opcion selecionada fue: " + opc);
            int Opcion = Integer.parseInt(opc);
            this.laberinto = new Mario_Laberinto_IA(this.gestorArchivos.LeerTxt(rutaArchivo), this.personaja,Opcion);
            this.laberinto.setVisible(true);

        } else {//mensaje que indica que debe seleccionar personaje y un nivel
            JOptionPane.showMessageDialog(this, "Para Iniciar la Partida debe seleccionar un personaje y un nivel, o en su defecto cargar un archivo de nivel.");
        }
    }catch(Exception e){
          ////sin implementacion..
    };
    }//GEN-LAST:event_FuncionStart

    //metodo main y atributos de la ventana.......
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Ventana().setVisible(true);
                } catch (Exception ex) {
                    // Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Personaje_1;
    private javax.swing.JLabel Personaje_2;
    private javax.swing.JLabel Personaje_3;
    private javax.swing.JLabel Personaje_4;
    private javax.swing.JLabel Sound;
    private javax.swing.JLabel Start;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label label1;
    private javax.swing.JComboBox<String> listaniveles;
    // End of variables declaration//GEN-END:variables
}
