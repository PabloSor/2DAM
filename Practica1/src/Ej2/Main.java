package Ej2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {

    //Creo una funcion para solicitar numeros al usuario
    public static Integer SolicitarNumero(){
        String Number = JOptionPane.showInputDialog("Introduce un número entero");
        return Integer.valueOf(Number);
    }

    //Creo la lista donde almacenare los numeros
    static ArrayList<Integer> Numeros = new ArrayList<>();

    public static void main(String[] args) {
        //Añado 5 numeros a la lista llamando a la funcion anterior.
        IntStream.range(0, 3).forEach(i -> Numeros.add(SolicitarNumero()));

        //Con un stream aplico un sort, como el sort va de menor a mayor, le doy la vuelta, lo paso a lista e imprimo.
        Numeros.stream().sorted(Comparator.reverseOrder()).toList().forEach(System.out::println);
    }

}
