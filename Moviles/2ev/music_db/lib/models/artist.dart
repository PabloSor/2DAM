// models/artist.dart
class Artist {
  final String id;
  final String name;
  final String thumbnail;
  final String biography;

  Artist({
    required this.id,
    required this.name,
    required this.thumbnail,
    required this.biography,
  });

  factory Artist.fromJson(Map<String, dynamic> json) {
    return Artist(
      id: json['idArtist'],
      name: json['strArtist'],
      thumbnail: json['strArtistThumb'] ?? '',
      biography: json['strBiographyEN'] ?? '',
    );
  }
}
