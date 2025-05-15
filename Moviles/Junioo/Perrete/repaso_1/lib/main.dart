import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Calculadora',
      theme: ThemeData(
        scaffoldBackgroundColor: const Color.fromARGB(255, 255, 255, 255),
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Calculadora en Flutter'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool segundoNumero = false;

  String num1 = "";
  String num2 = "";
  String operacion = "";

  String Resultado = "0";

  Container botonNumero(String numero) {
    return Container(
        height: 90,
        width: 90,
        child: FilledButton(
            onPressed: () {
              accionNumero(numero);
            },
            child: Text(numero, style: TextStyle(fontSize: 50))));
  }

  Container botonAccion(String texto) {
    return Container(
        height: 90,
        width: 90,
        child: ElevatedButton(
            onPressed: () {
              accionSimbolo(texto);
            },
            child: Text(texto, style: TextStyle(fontSize: 50))));
  }

  void accionNumero(String numero) {
    if (!segundoNumero) {
      num1 = num1 + numero;
    } else {
      num2 = num2 + numero;
    }

    if (Resultado == "0") {
      setState(() {
        Resultado = numero;
      });
    } else {
      setState(() {
        Resultado = Resultado + numero;
      });
    }
  }

  void accionSimbolo(String texto) {
    if (texto == "=") {
      if (num1.isNotEmpty && num2.isNotEmpty) {
        double num1Final = double.parse(num1);
        double num2Final = double.parse(num2);
        double resultFinal = 0;

        switch (operacion) {
          case "+":
            resultFinal = num1Final + num2Final;

          case "-":
            resultFinal = num1Final - num2Final;

          case "x":
            resultFinal = num1Final * num2Final;

          case "/":
            resultFinal = num1Final / num2Final;
        }

        setState(() {
          Resultado = resultFinal.toStringAsFixed(2);
        });
      }
    }

    switch (texto) {
      case "C":
        setState(() {
          Resultado = "0";
        });

        num1 = "";
        num2 = "";
        operacion = "";
        segundoNumero = false;

      case "+":
        if (operacion.isEmpty && !segundoNumero) {
          setState(() {
            Resultado = Resultado + texto;
          });
          segundoNumero = true;
          operacion = texto;
        }

      case "-":
        if (operacion.isEmpty && !segundoNumero) {
          setState(() {
            Resultado = Resultado + texto;
          });
          segundoNumero = true;
          operacion = texto;
        }

      case "x":
        if (operacion.isEmpty && !segundoNumero) {
          setState(() {
            Resultado = Resultado + texto;
          });
          segundoNumero = true;
          operacion = texto;
        }

      case "/":
        if (operacion.isEmpty && !segundoNumero) {
          setState(() {
            Resultado = Resultado + texto;
          });
          segundoNumero = true;
          operacion = texto;
        }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              width: double.infinity,
              height: 100,
              padding: EdgeInsets.all(10),
              margin: EdgeInsets.all(10),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(10),
                color: const Color.fromARGB(255, 119, 118, 118),
              ),
              child: Center(
                child: Text(
                  Resultado,
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 60,
                    color: const Color.fromARGB(255, 227, 227, 228),
                  ),
                ),
              ),
            ),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              spacing: 5,
              children: [
                botonNumero("7"),
                botonNumero("8"),
                botonNumero("9"),
                botonAccion("/")
              ],
            ),
            SizedBox(height: 5),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              spacing: 5,
              children: [
                botonNumero("4"),
                botonNumero("5"),
                botonNumero("6"),
                botonAccion("-")
              ],
            ),
            SizedBox(height: 5),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              spacing: 5,
              children: [
                botonNumero("1"),
                botonNumero("2"),
                botonNumero("3"),
                botonAccion("+")
              ],
            ),
            SizedBox(height: 5),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              spacing: 5,
              children: [
                botonAccion("C"),
                botonNumero("0"),
                botonAccion("="),
                botonAccion("x")
              ],
            )
          ],
        ),
      ),
    );
  }
}
