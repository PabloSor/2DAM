import 'package:flutter/material.dart';
import 'package:flutter_card_swiper/flutter_card_swiper.dart';
import '../models/album.dart';
import '../screens/album_screen.dart';

class AlbumSwiper extends StatelessWidget {
  final List<Album> albums;

  const AlbumSwiper({Key? key, required this.albums}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      child: CardSwiper(
        cardsCount: albums.length,
        cardBuilder: (context, index, horizontalOffset, verticalOffset) {
          final album = albums[index];
          return GestureDetector(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => AlbumScreen(album: album),
                ),
              );
            },
            child: Container(
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15),
                image: DecorationImage(
                  image: NetworkImage(album.cover),
                  fit: BoxFit.cover,
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}