// screens/artist_detail_page.dart
import 'package:flutter/material.dart';
import 'package:flutter_card_swiper/flutter_card_swiper.dart';
import '../models/artist.dart';
import '../models/album.dart';
import '../provider/apiService.dart';
import 'album_detail_page';

class ArtistDetailPage extends StatelessWidget {
  final Artist artist;

  const ArtistDetailPage({Key? key, required this.artist}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(artist.name)),
      body: SingleChildScrollView(
        child: Column(
          children: [
            // Información del artista
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                artist.biography,
                style: const TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            // Sección de álbumes
            FutureBuilder<List<Album>>(
              future: ApiService.fetchAlbums(artist.id),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                } else if (snapshot.hasError) {
                  return Center(child: Text('Error: ${snapshot.error}'));
                }
                final albums = snapshot.data ?? [];
                if (albums.isEmpty) {
                  return const Center(
                      child: Text('No se encontraron álbumes.'));
                }
                return SizedBox(
                  height: 300,
                  child: CardSwiper(
                    cards: albums.map((album) {
                      return GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (_) => AlbumDetailPage(album: album),
                            ),
                          );
                        },
                        child: Card(
                          elevation: 4,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(16),
                          ),
                          clipBehavior: Clip.antiAlias,
                          child: album.coverUrl.isNotEmpty
                              ? Image.network(
                                  album.coverUrl,
                                  fit: BoxFit.cover,
                                )
                              : Container(
                                  color: Colors.grey,
                                  child: const Center(
                                    child: Text('Sin imagen'),
                                  ),
                                ),
                        ),
                      );
                    }).toList(),
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
