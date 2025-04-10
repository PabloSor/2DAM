package org.example;

import java.util.*;

public class CombinacionesSuma {
    public static List<List<Integer>> encontrarCombinaciones(int[] nums, int objetivo) {
        List<List<Integer>> resultado = new ArrayList<>();
        Arrays.sort(nums); // Ordenamos para evitar combinaciones duplicadas
        backtrack(nums, objetivo, 0, new ArrayList<>(), resultado);
        return resultado;
    }

    private static void backtrack(int[] nums, int objetivo, int inicio, List<Integer> actual, List<List<Integer>> resultado) {
        if (objetivo == 0) {
            resultado.add(new ArrayList<>(actual));
            return;
        }

        for (int i = inicio; i < nums.length; i++) {
            if (i > inicio && nums[i] == nums[i - 1]) continue; // Evitar duplicados consecutivos
            if (nums[i] > objetivo) break; // Si el número supera el objetivo, detenemos el bucle

            actual.add(nums[i]);
            backtrack(nums, objetivo - nums[i], i + 1, actual, resultado); // i + 1 para evitar reutilización
            actual.remove(actual.size() - 1); // Retroceder en la búsqueda
        }
    }

    public static void main(String[] args) {
        int[] lista = {10, 1, 2, 7, 6, 1, 5, 8};
        int objetivo = 8;
        List<List<Integer>> combinaciones = encontrarCombinaciones(lista, objetivo);
        System.out.println(combinaciones);
    }
}