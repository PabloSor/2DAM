import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:music_db/models/album_response.dart';

class AlbumProvider extends ChangeNotifier {
  final String _baseUrl = 'theaudiodb.com';
  final String _artistId = '112025';
  List<Album> listaAlbums = [];

  AlbumProvider() {
    print('AlbumProvider inicializado');
    getAlbums();
  }

  getAlbums() async {
    var url = Uri.https(_baseUrl, '/api/v1/json/2/album.php', {'i': _artistId});


    var response = await http.get(url).timeout(Duration(seconds: 30));
    final decodedData = json.decode(response.body) as Map<String, dynamic>;

    listaAlbums = (decodedData['album'] as List)
        .map((album) => Album.fromJson(album))
        .toList();

    notifyListeners();

    listaAlbums.forEach((album) => print(album.title));
  }
}
