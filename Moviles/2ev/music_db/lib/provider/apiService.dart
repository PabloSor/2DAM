// services/api_service.dart
import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/artist.dart';
import '../models/album.dart';

class ApiService {
  static const String baseUrl = 'https://theaudiodb.com/api/v1/json/2';

  // Ejemplo: obtener artistas. Ajusta la URL según la documentación de la API.
  static Future<List<Artist>> fetchArtists() async {
    final response = await http.get(Uri.parse('$baseUrl/artist.php?i=112024'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      final List<dynamic> artistsJson = data['artists'];
      return artistsJson.map((json) => Artist.fromJson(json)).toList();
    } else {
      throw Exception('Error al cargar los artistas');
    }
  }

  // Obtener álbumes del artista
  static Future<List<Album>> fetchAlbums(String artistId) async {
    final response =
        await http.get(Uri.parse('$baseUrl/album.php?i=$artistId'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      final List<dynamic> albumsJson = data['album'];
      return albumsJson.map((json) => Album.fromJson(json)).toList();
    } else {
      throw Exception('Error al cargar los álbumes');
    }
  }
}
