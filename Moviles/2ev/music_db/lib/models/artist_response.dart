// models/artist.dart
class Artist {
  final String id;
  final String name;
  final String thumbnail;
  final String biography;
  final String country;

  Artist({
    required this.id,
    required this.name,
    required this.thumbnail,
    required this.biography,
    required this.country,
  });

  factory Artist.fromJson(Map<String, dynamic> json) {
    return Artist(
      id: json['idArtist'],
      name: json['strArtist'],
      thumbnail: json['strArtistThumb'] ?? '',
      biography: json['strBiographyEN'] ?? '',
      country: json['strCountry'] ?? '',
    );
  }
}
