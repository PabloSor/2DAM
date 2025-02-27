import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../models/album.dart';

class AlbumProvider with ChangeNotifier {
  List<Album> _albums = [];

  List<Album> get albums => _albums;

  Future<void> fetchAlbums(String artistId) async {
    try {
      final response = await http.get(
        Uri.parse(
            "https://www.theaudiodb.com/api/v1/json/2/album.php?i=$artistId"),
      );

      if (response.statusCode == 200) {
        _albums = albumFromJson(response.body);
        notifyListeners();
      } else {
        print("Error: ${response.statusCode}");
      }
    } catch (e) {
      print("Error obteniendo álbumes: $e");
    }
  }
}
