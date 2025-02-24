import 'package:flutter/material.dart';

class AlbertoScreen extends StatelessWidget {
  const AlbertoScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'ALBERTO',
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
          'Profesor de PMDM \nNombre: Alberto \nTelefono: 644 86 12 53 \nCurso: DAM2',
        ),
      ),
    );
  }
}