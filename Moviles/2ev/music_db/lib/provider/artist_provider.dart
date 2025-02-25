import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:music_db/models/artist_response.dart';

class ArtistProvider extends ChangeNotifier {
  final String _baseUrl = 'theaudiodb.com';
  List<Artist> listaArtistas = [];

  ArtistProvider() {
    print('ArtistProvider inicializado');
    getArtists();
  }

  getArtists() async {
    var url =
        Uri.https(_baseUrl, '/api/v1/json/2/search.php', {'s': 'Coldplay'});

    var response = await http.get(url).timeout(Duration(seconds: 30));
    final decodedData = json.decode(response.body) as Map<String, dynamic>;

    listaArtistas = (decodedData['artists'] as List)
        .map((artist) => Artist.fromJson(artist))
        .toList();

    notifyListeners();

    listaArtistas.forEach((artist) => print(artist.name));
  }
}
