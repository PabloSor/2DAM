package org.example.ejercicio1ayuda;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class HelloController {
    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private ComboBox<String> actividadFisica;

    @FXML
    private ComboBox<String> fruta;

    @FXML
    private ComboBox<String> fumar;

    @FXML
    private ComboBox<String> alcohol;

    @FXML
    private Button resultado;

    @FXML
    private Button borrar;

    @FXML
    private Button ayuda;

    @FXML
    private Button malIMG;

    @FXML
    private Button regularIMG;

    @FXML
    private Button bienIMG;



    @FXML
    protected void initialize(){
        malIMG.setVisible(false);
        regularIMG.setVisible(false);
        bienIMG.setVisible(false);

        actividadFisica.getItems().addAll("Nunca", "A veces", "Todos los días");
        fruta.getItems().addAll("Nunca", "A veces", "Todos los días");
        fumar.getItems().addAll("Si", "No");
        alcohol.getItems().addAll("Nunca", "Ocasionalmente", "Todos los días");
    }

    @FXML
    protected void actionCalcular(){
        malIMG.setVisible(false);
        regularIMG.setVisible(false);
        bienIMG.setVisible(false);

        int puntos = 0;

        // Actividad física
        if (actividadFisica.getValue().equals("A veces")){
            puntos += 10;
        } else if (actividadFisica.getValue().equals("Todos los días")) {
            puntos += 20;
        }


        // Comer fruta y verdura
        if (fruta.getValue().equals("A veces")){
            puntos += 10;
        } else if (fruta.getValue().equals("Todos los días")) {
            puntos += 20;
        }


        // Fumar
        if (fumar.getValue().equals("No")){
            puntos += 20;
        }


        // Consumir alcohol
        if (alcohol.getValue().equals("Nunca")){
            puntos += 20;
        } else if (alcohol.getValue().equals("Ocasionalmente")) {
            puntos += 10;
        }

        // Calcular la imagen que debe aparecer
        if (puntos < 26){
            malIMG.setVisible(true);
        } else if (puntos < 51) {
            regularIMG.setVisible(true);
        }else {
            bienIMG.setVisible(true);
        }
    }

    @FXML
    protected void actionDelete(){

        // Imágenes
        malIMG.setVisible(false);
        regularIMG.setVisible(false);
        bienIMG.setVisible(false);

        // ComboBox
        actividadFisica.setValue("");
        fruta.setValue("");
        fumar.setValue("");
        alcohol.setValue("");

        // Nombre y apellidos
        nombre.clear();
        apellidos.clear();
    }


    @FXML
    protected void actionCHM() throws IOException {
        Desktop.getDesktop().open(new File("Ayuda/ExamenEv2.chm"));
    }

    
}