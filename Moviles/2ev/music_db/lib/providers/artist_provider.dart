import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:music_db/models/artist.dart';

class ArtistProvider with ChangeNotifier {
  List<Artist> _artists = [];
  List<Artist> get artists => _artists;
  
  get http => null;

  Future<void> fetchArtists() async {
    try {
      final response = await http.get(
        Uri.parse("https://www.theaudiodb.com/api/v1/json/2/artist.php?i=1"),
      );

      if (response.statusCode == 200) {
        // Verifica si la respuesta tiene datos
        final data = jsonDecode(response.body);
        if (data['artists'] != null) {
          _artists = artistFromJson(response.body);
        } else {
          _artists = []; // Lista vacía si no hay datos
          print("No se encontraron artistas");
        }
        notifyListeners();
      } else {
        print("Error: ${response.statusCode}");
      }
    } catch (e) {
      print("Error obteniendo artistas: $e");
    }
  }
}