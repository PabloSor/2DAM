import 'package:flutter/material.dart';
import 'package:music_db/models/artist.dart';
import 'package:music_db/screens/artist_screen.dart';

class ArtistCard extends StatelessWidget {
  final Artist artist;

  ArtistCard({required this.artist});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Image.network(artist.thumb, width: 50, height: 50),
      title: Text(artist.name),
      onTap: () => Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => ArtistScreen(artist: artist))),
    );
  }
}
