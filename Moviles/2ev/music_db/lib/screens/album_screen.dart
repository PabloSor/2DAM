import 'package:flutter/material.dart';
import 'package:music_db/models/album.dart';

class AlbumScreen extends StatelessWidget {
  final Album album;

  AlbumScreen({required this.album});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(album.title)),
      body: Center(child: Image.network(album.cover)),
    );
  }
}
