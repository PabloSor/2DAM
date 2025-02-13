// models/album.dart
class Album {
  final String id;
  final String title;
  final String coverUrl;
  final String description;

  Album({
    required this.id,
    required this.title,
    required this.coverUrl,
    required this.description,
  });

  factory Album.fromJson(Map<String, dynamic> json) {
    return Album(
      id: json['idAlbum'],
      title: json['strAlbum'],
      coverUrl: json['strAlbumThumb'] ?? '',
      description: json['strDescriptionEN'] ?? '',
    );
  }
}
