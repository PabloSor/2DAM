import 'dart:convert';

List<Artist> artistFromJson(String str) => List<Artist>.from(
    json.decode(str)["artists"].map((x) => Artist.fromJson(x)));

class Artist {
  String id;
  String name;
  String thumb;

  Artist({
    required this.id,
    required this.name,
    required this.thumb,
  });

  factory Artist.fromJson(Map<String, dynamic> json) => Artist(
        id: json["idArtist"],
        name: json["strArtist"],
        thumb: json["strArtistThumb"] ??
            "", // Algunas imágenes pueden estar vacías
      );
}
