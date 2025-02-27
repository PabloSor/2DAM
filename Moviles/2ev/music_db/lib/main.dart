import 'package:flutter/material.dart';
import 'package:music_db/provider/album_provider.dart';
import 'package:music_db/provider/artist_provider.dart';
import 'package:music_db/provider/disks_provider.dart';
import 'package:music_db/screens/album_screen.dart';
import 'package:music_db/screens/artist_screen.dart';
import 'package:music_db/screens/screens.dart';
import 'package:provider/provider.dart';

void main() => runApp(AppState());

class AppState extends StatelessWidget {
  const AppState({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(providers: [
      ChangeNotifierProvider(create: (_) => DisksProvider(), lazy: false),
      ChangeNotifierProvider(create: (_) => AlbumProvider(), lazy: false),
      ChangeNotifierProvider(create: (_) => ArtistsProvider(), lazy: false),
    ], child: MyApp());
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Discos',
        initialRoute: 'home',
        routes: {
          'home': (BuildContext context) => HomeScreen(),
          'artist': (BuildContext context) => ArtistScreen(),
          'album': (BuildContext context) => AlbumScreen(),
        },
        theme: ThemeData.light()
            .copyWith(appBarTheme: AppBarTheme(color: Colors.indigo)));
  }
}
