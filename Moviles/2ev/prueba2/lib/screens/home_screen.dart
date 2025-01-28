import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  final opciones = const ["calendar", "subjects", "contacts"];
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text(
            "Home screen",
            style: TextStyle(color: Colors.white, fontSize: 25),
          ),
          backgroundColor: const Color.fromARGB(255, 3, 202, 10),
        ),
        body: ListView.separated(
          itemBuilder: (context, index) => FloatingActionButton(
            child: Text(opciones[index]),
            onPressed: () => Navigator.pushNamed(context, opciones[index]),
          ),
          separatorBuilder: (context, index) => Divider(),
          itemCount: opciones.length,
        ));
  }
}
