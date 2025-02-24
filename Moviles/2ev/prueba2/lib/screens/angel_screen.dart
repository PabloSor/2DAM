import 'package:flutter/material.dart';

class AngelScreen extends StatelessWidget {
  const AngelScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'ANGEL',
          style: TextStyle(fontSize: 30, color: Colors.white),
        ),
        backgroundColor: Colors.indigo,
      ),
      body: Center(
        child: ListView(
          padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
          children: [
            CardPmdm(texto: "INFORMACIÓN"),
          ],
        ),
      ),
    );
  }
}

class CardPmdm extends StatelessWidget {
  final String texto;

  const CardPmdm({super.key, required this.texto});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        title: Text(texto),
        subtitle: Text(
          'Profesor de Programación \nNombre: Ángel \nTelefono: 672 32 73 23 \nCurso: DAM1',
        ),
      ),
    );
  }
}