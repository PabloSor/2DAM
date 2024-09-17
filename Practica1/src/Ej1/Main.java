package Ej1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.OptionalDouble;
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
        IntStream.range(0, 5).forEach(i -> Numeros.add(SolicitarNumero()));

        //Con otro steam, imprimo los numeros pares
        System.out.println("Numeros pares:");
        Numeros.stream().filter(num -> num % 2 == 0).forEach(System.out::println);

        //Lo mismo para numeros entre 5 y 10 (5 incluido)
        System.out.println("Numeros >4 && <10:");
        Numeros.stream().filter(num -> num >= 5 && num < 10).forEach(System.out::println);

        //Calculo y muestro la media aritmetica de los 5 numeros
        System.out.println("Media aritmetica de los 5 numeros:");
        OptionalDouble media = Numeros.stream().mapToDouble(Integer::doubleValue).average();
        System.out.println(media.getAsDouble());
    }
}
