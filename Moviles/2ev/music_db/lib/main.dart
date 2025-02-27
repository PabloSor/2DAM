import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'providers/artist_provider.dart';
import 'providers/album_provider.dart';
import 'screens/home_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => ArtistProvider()),
        ChangeNotifierProvider(create: (_) => AlbumProvider()),
      ],
      child: MaterialApp(
        title: 'Music App',
        theme: ThemeData.dark(),
        home: HomeScreen(),
      ),
    );
  }
}
