import 'package:flutter/material.dart';
import 'package:flutter_card_swiper/flutter_card_swiper.dart';

import 'package:music_db/models/album.dart';
import 'package:music_db/screens/album_screen.dart';

class AlbumSwiper extends StatelessWidget {
  final List<Album> albums;

  AlbumSwiper({required this.albums});

  @override
  Widget build(BuildContext context) {
    return CardSwiper(
      cardsCount: albums.length,
      cardBuilder: (context, index) {
        return GestureDetector(
          onTap: () => Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => AlbumScreen(album: albums[index])),
          ),
          child: Image.network(albums[index].cover),
        );
      },
    );
  }
}
