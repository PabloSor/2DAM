package org.example.ejercicio1ayuda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField num1;

    @FXML
    private TextField num2;

    @FXML
    private TextField result;

    @FXML
    private Button suma;

    @FXML
    private Button resta;

    @FXML
    private Button multi;


    @FXML
    protected void actionSumar(){
        Float field1 = Float.valueOf(num1.getText());
        Float field2 = Float.valueOf(num2.getText());

        result.setText(String.valueOf(field1+field2));
    }

    @FXML
    protected void actionRestar(){
        Float field1 = Float.valueOf(num1.getText());
        Float field2 = Float.valueOf(num2.getText());

        result.setText(String.valueOf(field1-field2));
    }

    @FXML
    protected void actionMulti(){
        Float field1 = Float.valueOf(num1.getText());
        Float field2 = Float.valueOf(num2.getText());

        result.setText(String.valueOf(field1*field2));
    }

    
}