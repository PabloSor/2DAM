// screens/artist_list_page.dart
import 'package:flutter/material.dart';
import '../models/artist.dart';
import '../provider/apiService.dart';
import 'artist_detail_page.dart';

class ArtistListPage extends StatelessWidget {
  const ArtistListPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Artistas')),
      body: FutureBuilder<List<Artist>>(
        future: ApiService.fetchArtists(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          final artists = snapshot.data ?? [];
          return ListView.builder(
            itemCount: artists.length,
            itemBuilder: (context, index) {
              final artist = artists[index];
              return ListTile(
                leading: artist.thumbnail.isNotEmpty
                    ? Image.network(artist.thumbnail)
                    : const Icon(Icons.person),
                title: Text(artist.name),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (_) => ArtistDetailPage(artist: artist),
                    ),
                  );
                },
              );
            },
          );
        },
      ),
    );
  }
}
