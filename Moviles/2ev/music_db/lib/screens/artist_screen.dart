import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/album_provider.dart';
import '../widgets/album_swiper.dart';
import '../models/artist.dart';

class ArtistScreen extends StatefulWidget {
  final Artist artist;

  ArtistScreen({required this.artist});

  @override
  _ArtistScreenState createState() => _ArtistScreenState();
}

class _ArtistScreenState extends State<ArtistScreen> {
  @override
  void initState() {
    super.initState();
    Provider.of<AlbumProvider>(context, listen: false)
        .fetchAlbums(widget.artist.id);
  }

  @override
  Widget build(BuildContext context) {
    final albums = Provider.of<AlbumProvider>(context).albums;

    return Scaffold(
      appBar: AppBar(title: Text(widget.artist.name)),
      body: Column(
        children: [
          Image.network(widget.artist.thumb),
          Expanded(child: AlbumSwiper(albums: albums)),
        ],
      ),
    );
  }
}
