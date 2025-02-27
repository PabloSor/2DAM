import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:music_db/models/artist_response.dart';

class ArtistsProvider extends ChangeNotifier {
  final String _baseUrl = 'theaudiodb.com';
  List<Artist> artists = [];

  ArtistsProvider() {
    loadArtists();
  }

  Future<void> loadArtists() async {
    final url = Uri.https(_baseUrl, '/api/v1/json/2/search.php', {'s': 'Coldplay'});
    
    try {
      final response = await http.get(url);
      final decodedData = json.decode(response.body);

      if (decodedData['artists'] != null) {
        artists = (decodedData['artists'] as List)
            .map((artist) => Artist.fromJson(artist))
            .toList();
      }

      notifyListeners();
    } catch (e) {
      print("Error al cargar artistas: $e");
    }
  }
}
