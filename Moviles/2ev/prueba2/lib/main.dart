import 'package:flutter/material.dart';
import 'package:prueba2/screens/alberto_screen.dart';
import 'package:prueba2/screens/angel_screen.dart';
import 'package:prueba2/screens/aw_screen.dart';
import 'package:prueba2/screens/calendar_screen.dart';
import 'package:prueba2/screens/contacts_screen.dart';
import 'package:prueba2/screens/home_screen.dart';
import 'package:prueba2/screens/juanma_screen.dart';
import 'package:prueba2/screens/lm_screen.dart';
import 'package:prueba2/screens/mario_screen.dart';
import 'package:prueba2/screens/pmdm_screen.dart';
import 'package:prueba2/screens/subjects_screen.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: 'home',
      debugShowCheckedModeBanner: false,
      routes: {
        'home': (context) => HomeScreen(),
        'calendar': (context) => CalendarScreen(),
        'subjects': (context) => SubjectsScreen(),
        'contacts': (context) => ContactsScreen(),
        'PMDM': (context) => PmdmScreen(),
        'AW': (context) => AwScreen(),
        'LM': (context) => LmScreen(),
        'mario': (context) => MarioScreen(),
        'alberto': (context) => AlbertoScreen(),
        'juanma': (context) => JuanmaScreen(),
        'angel': (context) => AngelScreen()
      },
    );
  }

}
