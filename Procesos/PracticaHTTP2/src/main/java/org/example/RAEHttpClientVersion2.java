package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Scanner;

public class RAEHttpClientVersion2 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Dame una palabra para encontrar");
        String palabra = scanner.nextLine();
        scanner.close();

        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://dle.rae.es/" + palabra);
            request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41");

            try (CloseableHttpResponse response = httpClient.execute(request);
                 BufferedWriter writer = new BufferedWriter(new FileWriter("resultadoV2.html"))) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String resultado = EntityUtils.toString(entity);
                    writer.write(resultado);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}