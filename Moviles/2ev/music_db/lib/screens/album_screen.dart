import 'package:flutter/material.dart';
import 'package:music_db/models/disk_response.dart';

class AlbumScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final DiskResponse album = ModalRoute.of(context)!.settings.arguments as DiskResponse;

    return Scaffold(
      appBar: AppBar(title: Text(album.strAlbum)),
      body: Column(
        children: [
          Image.network(album.strAlbumThumb ?? ''),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(album.strDescriptionEn ?? 'Sin descripción'),
          ),
        ],
      ),
    );
  }
}
