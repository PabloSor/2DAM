import 'package:flutter/material.dart';
import 'package:music_db/models/artist_response.dart';
import 'package:music_db/provider/disks_provider.dart';
import 'package:music_db/widgets/card_swiper.dart';
import 'package:provider/provider.dart';

class ArtistScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final Artist artist = ModalRoute.of(context)!.settings.arguments as Artist;
    final disksProvider = Provider.of<DisksProvider>(context, listen: false);

    // Cargar discos al entrar
    disksProvider.loadDisks(artist.id);

    return Scaffold(
      appBar: AppBar(title: Text(artist.name)),
      body: Column(
        children: [
          Image.network(artist.thumbnail ?? ''),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(artist.biography ?? 'Sin biografía'),
          ),
          Expanded(
            child: Consumer<DisksProvider>(
              builder: (_, provider, __) {
                if (provider.listaDiscos.isEmpty) {
                  return Center(child: CircularProgressIndicator());
                }

                return MyCardSwiper(disks: provider.listaDiscos);
              },
            ),
          ),
        ],
      ),
    );
  }
}
