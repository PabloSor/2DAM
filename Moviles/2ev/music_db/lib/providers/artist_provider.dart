import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../models/artist.dart';

class ArtistProvider with ChangeNotifier {
  List<Artist> _artists = [];

  List<Artist> get artists => _artists;

  Future<void> fetchArtists() async {
    try {
      final response = await http.get(
        Uri.parse("https://www.theaudiodb.com/api/v1/json/2/artist.php?i=1"),
      );

      if (response.statusCode == 200) {
        _artists = artistFromJson(response.body);
        notifyListeners();
      } else {
        print("Error: ${response.statusCode}");
      }
    } catch (e) {
      print("Error obteniendo artistas: $e");
    }
  }
}
