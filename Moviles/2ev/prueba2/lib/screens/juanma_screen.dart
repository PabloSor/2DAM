import 'package:flutter/material.dart';

class JuanmaScreen extends StatelessWidget {
  const JuanmaScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'JUANMA',
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
          'Profesor de Aceso a Datos \nNombre: Juan Manuel \nTelefono: 682 73 94 01 \nCurso: DAM2',
        ),
      ),
    );
  }
}