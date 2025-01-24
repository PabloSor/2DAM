import 'package:flutter/material.dart';

void main() {
  runApp(CalculadoraApp());
}

class CalculadoraApp extends StatelessWidget {
  const CalculadoraApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Calculadora(),
    );
  }
}

class Calculadora extends StatefulWidget {
  const Calculadora({super.key});

  @override
  _CalculadoraState createState() => _CalculadoraState();
}

class _CalculadoraState extends State<Calculadora> {
  String _output = "0";
  String _input = "";
  double? _num1;
  double? _num2;
  String? _operador;

  void _botonPresionado(String valor) {
    setState(() {
      if (valor == "C") {
        _output = "0";
        _input = "";
        _num1 = null;
        _num2 = null;
        _operador = null;
      } else if (valor == "+" ||
          valor == "-" ||
          valor == "*" ||
          valor == "/" ||
          valor == "%") {
        _num1 = double.tryParse(_input);
        _operador = valor;
        _input = "";
      } else if (valor == "=") {
        _num2 = double.tryParse(_input);
        if (_num1 != null && _num2 != null && _operador != null) {
          switch (_operador) {
            case "+":
              _output = (_num1! + _num2!).toString();
              break;
            case "-":
              _output = (_num1! - _num2!).toString();
              break;
            case "*":
              _output = (_num1! * _num2!).toString();
              break;
            case "/":
              _output = _num2 != 0 ? (_num1! / _num2!).toString() : "Error";
              break;
            case "%":
              _output = (_num1! % _num2!).toString();
              break;
          }
        }
        _input = _output;
        _num1 = null;
        _num2 = null;
        _operador = null;
      } else {
        _input += valor;
        _output = _input;
      }
    });
  }

  Widget _crearBoton(String texto) {
    return Expanded(
      child: ElevatedButton(
        onPressed: () => _botonPresionado(texto),
        style: ElevatedButton.styleFrom(
            padding: EdgeInsets.all(20),
            backgroundColor: Color.fromARGB(80, 0, 255, 0)),
        child: Text(
          texto,
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Calculadora"),
        centerTitle: true,
        backgroundColor: Color.fromARGB(255, 255, 0, 0),
      ),
      body: Column(
        children: [
          Expanded(
            child: Container(
              alignment: Alignment.center,
              padding: EdgeInsets.symmetric(horizontal: 12),
              child: Text(
                _output,
                style: TextStyle(fontSize: 100, fontWeight: FontWeight.bold),
              ),
            ),
          ),
          Column(
            children: [
              Row(
                spacing: 10.0,
                children: [
                  _crearBoton("7"),
                  _crearBoton("8"),
                  _crearBoton("9"),
                  _crearBoton("/")
                ],
              ),
              Row(
                spacing: 10.0,
                children: [
                  _crearBoton("4"),
                  _crearBoton("5"),
                  _crearBoton("6"),
                  _crearBoton("*")
                ],
              ),
              Row(
                spacing: 10.0,
                children: [
                  _crearBoton("1"),
                  _crearBoton("2"),
                  _crearBoton("3"),
                  _crearBoton("-")
                ],
              ),
              Row(
                spacing: 10.0,
                children: [
                  _crearBoton("C"),
                  _crearBoton("0"),
                  _crearBoton("%"),
                  _crearBoton("+")
                ],
              ),
              Row(
                spacing: 10.0,
                children: [
                  _crearBoton("="),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }
}
