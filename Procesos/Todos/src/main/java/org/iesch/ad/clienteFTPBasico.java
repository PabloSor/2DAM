package org.iesch.ad;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class clienteFTPBasico extends JFrame {
    private static final long serialVersionUID = 1L;

    //Cabecera parte superior
    static JTextField cab = new JTextField();
    static JTextField cab2 = new JTextField();
    static JTextField cab3 = new JTextField();

    //Mensajes inferiores
    private static JTextField campo = new JTextField();
    private static JTextField campo2 = new JTextField();

    //Botones
    JButton botonCargar = new JButton("Subir fichero");
    JButton botonDescargar = new JButton("Descargar fichero");
    JButton botonBorrar = new JButton("Eliminar fichero");
    JButton botonCreadir = new JButton("Crear carpeta");
    JButton botonBorradir = new JButton("Eliminar carpeta");
    JButton botonSalir = new JButton("Salir");

    //Lista para datos del directorio
    static JList listaDirec = new JList();

    //Contenedor
    private final Container c = getContentPane();

    //Datos del servidor FTP - Servidor local
    static FTPClient cliente = new FTPClient();
    String servidor = "localhost";
    String username = "usuario1";
    String password = "usuario1";
    boolean login;
    static String directorioInicial = "/";


    //Para conocer directorio y fichero
    static String direcSeleccionado = directorioInicial;
    static String ficheroSeleccionado = "";

    //CONSTRUCTOR
    public clienteFTPBasico() throws IOException{
        super ("CLIENTE BASICO FTP");
        cliente.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        cliente.connect(servidor);
        cliente.enterLocalPassiveMode();
        login = cliente.login(username, password);

        cliente.changeWorkingDirectory(directorioInicial);
        FTPFile[] files = cliente.listFiles();
        llenarLista(files, directorioInicial);

        //Campos de pantalla (completar)
        campo.setText("<<Arbol de directorios construido>>");
        cab.setText("Servidor FTP: " + servidor);
        cab2.setText("Usuario: " + username);
        cab3.setText("Directorio raiz: " + directorioInicial );


        //Preparación de la lista
        listaDirec.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));


        //Barra de desplazamiento para la lista (completar)
        JScrollPane barraDespl = new JScrollPane(listaDirec);
        barraDespl.setPreferredSize(new Dimension(335, 420));
        barraDespl.setBounds(5, 65, 334, 420);
        c.add(barraDespl);
        c.setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 600);
        setVisible(true);

        listaDirec.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting())
                    ficheroSeleccionado = "";

                String fic = listaDirec.getSelectedValue().toString();

                if(listaDirec.getSelectedIndex() == 0){
                    if (!fic.equals(directorioInicial)) {
                        try{
                            cliente.changeToParentDirectory();
                            direcSeleccionado = cliente.printWorkingDirectory();
                            cliente.changeWorkingDirectory(direcSeleccionado);
                            FTPFile[] ff2 = cliente.listFiles();
                            campo.setText("");
                            llenarLista(ff2, direcSeleccionado);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }


        });

        //COMPLETAR ESTOS BOTONES
        //botonSalir.addActionListener();
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cerrando programa...");
                System.exit(0);
            }
        });

        //botonCreadir.addActionListener();
        botonCreadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruta = JOptionPane.showInputDialog(null, "Introduce ruta");

                boolean success = false;
                try {success = cliente.makeDirectory(ruta);} catch (IOException ex) {throw new RuntimeException(ex);}

                if (success) {
                    System.out.println("Successfully created directory: " + ruta);
                } else {
                    System.out.println("Failed to create directory. See server's reply.");
                }

            }
        });

        botonCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser();

                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.setDialogTitle("Selecciona el fichero a subir al servidor FTP");

                //SE MUESTRA EN LA VENTANA
                int returnVal = f.showDialog(f, "Cargar");
                if (returnVal ==JFileChooser.APPROVE_OPTION){
                    File file = f.getSelectedFile();
                    String archivo = file.getAbsolutePath();
                    String nombreArchivo = file.getName();
                    try {
                        SubirFichero(archivo, nombreArchivo);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        botonDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directorio = direcSeleccionado;
                if (!direcSeleccionado.equals(directorioInicial)){
                    directorio = directorio + "/";
                }
                if(!ficheroSeleccionado.equals("")) {
                    String nuevoFichero = directorio + ficheroSeleccionado;
                    DescargarFichero(nuevoFichero, ficheroSeleccionado);
                }
            }
        });

        //COMPLETAR ESTOS BOTONES
        //botonBorradir.addActionListener();
        botonBorradir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruta = JOptionPane.showInputDialog(null, "Introduce ruta");
                File directorio = new File(ruta);

                boolean success = false;
                if (!directorio.isDirectory()){
                    try {cliente.removeDirectory(ruta);} catch (IOException ex) {throw new RuntimeException(ex);}
                } else {
                    System.out.println("El directorio no existe");
                }
            }
        });
        
        //botonBorrar.addActionListener();

    }

    private void DescargarFichero(String nuevoFicheroCompleto, String ficheroSeleccionado) {
        String archivoYCarpetaDestino = "";
        String carpetaDestino = "";
        JFileChooser f = new JFileChooser();

        f.setDialogTitle("Selecciona cual es el directorio donde quieres descargar el fichero");

        int returnVal = f.showDialog(null, "Descargar");
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = f.getSelectedFile();
            carpetaDestino = (file.getAbsolutePath()).toString();
            archivoYCarpetaDestino = carpetaDestino + File.separator + nuevoFicheroCompleto;
            try{
                cliente.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(archivoYCarpetaDestino));
                if (cliente.retrieveFile(nuevoFicheroCompleto, out)){
                    JOptionPane.showMessageDialog(null, "Descargado correctamente.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se ha descargado.");
                }
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean SubirFichero (String archivo, String soloNombre) throws IOException {
        cliente.setFileType(FTP.BINARY_FILE_TYPE);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));
        boolean ok = cliente.storeFile(soloNombre,in);

        cliente.changeWorkingDirectory(direcSeleccionado);

        if(ok){
            String s = " " + soloNombre + " => Subido correctamente";
            campo.setText(s);
            campo2.setText("Se va a actualizar el árbol de directorios");
            JOptionPane.showMessageDialog(null, s);
            FTPFile[] ff2 = cliente.listFiles();
            llenarLista(ff2, direcSeleccionado);
        } else {
            String s = " " + soloNombre + " => Error al subir el fichero";
            campo.setText(s);
        }
        in.close();
        return ok;


    }
    private static void llenarLista (FTPFile[] files, String direc){
        if(files == null) return;

        DefaultListModel modelo = new DefaultListModel();
        listaDirec.setForeground(Color.blue);
        Font fuente = new Font("Courier", Font.PLAIN, 12);
        listaDirec.setFont(fuente);

        listaDirec.removeAll();
        try{
            cliente.changeWorkingDirectory(direc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        direcSeleccionado = direc;

        modelo.addElement(direc);

        for (int i = 0; i < files.length; i++) {
            if(!files[i].getName().equals(".")&& !files[i].getName().equals("..")) {
                String f = files[i].getName();
                if (files[i].isDirectory()) f = "(DIR) " + f;
                modelo.addElement(f);
            }

        }
        try {
            listaDirec.setModel(modelo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    public static void main(String[] args) throws Exception {
        new clienteFTPBasico();
    }
}
