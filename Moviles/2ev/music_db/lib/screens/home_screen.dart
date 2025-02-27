import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/artist_provider.dart';
import '../widgets/artist_card.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    super.initState();
    Provider.of<ArtistProvider>(context, listen: false).fetchArtists();
  }

  @override
  Widget build(BuildContext context) {
    final artists = Provider.of<ArtistProvider>(context).artists;

    return Scaffold(
      appBar: AppBar(title: Text("Artistas")),
      body: ListView.builder(
        itemCount: artists.length,
        itemBuilder: (context, index) => ArtistCard(artist: artists[index]),
      ),
    );
  }
}
