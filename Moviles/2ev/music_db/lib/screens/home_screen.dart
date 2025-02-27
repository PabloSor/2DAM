import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../provider/artist_provider.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final artistsProvider = Provider.of<ArtistsProvider>(context);

    return Scaffold(
      appBar: AppBar(title: Text("Artistas")),
      body: artistsProvider.artists.isEmpty
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: artistsProvider.artists.length,
              itemBuilder: (context, index) {
                final artist = artistsProvider.artists[index];
                return ListTile(
                  leading: Image.network(artist.thumbnail ?? ''),
                  title: Text(artist.name),
                  subtitle: Text(artist.country ?? ''),
                  onTap: () {
                    Navigator.pushNamed(
                      context,
                      'artist',
                      arguments: artist,
                    );
                  },
                );
              },
            ),
    );
  }
}
