import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import 'package:music_db/models/disk_response.dart';

class DisksProvider extends ChangeNotifier {
  final String _baseUrl = 'theaudiodb.com';
  List<DiskResponse> listaDiscos = [];
  

  Future<void> loadDisks(String artistId) async {
    final url = Uri.https(_baseUrl, '/api/v1/json/2/album.php', {'i': artistId});
    
    try {
      final response = await http.get(url).timeout(Duration(seconds: 10));
      final decodedData = json.decode(response.body) as Map<String, dynamic>;

      if (decodedData['album'] != null) {
        listaDiscos = (decodedData['album'] as List)
            .map((disk) => DiskResponse.fromJson(disk))
            .toList();
      } else {
        listaDiscos = [];
      }

      notifyListeners();
    } catch (e) {
      print("Error al cargar discos: $e");
      listaDiscos = [];
      notifyListeners();
    }
  }
}

