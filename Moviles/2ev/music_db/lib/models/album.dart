import 'dart:convert';

List<Album> albumFromJson(String str) =>
    List<Album>.from(json.decode(str)["album"].map((x) => Album.fromJson(x)));

class Album {
  String id;
  String title;
  String cover;

  Album({
    required this.id,
    required this.title,
    required this.cover,
  });

  factory Album.fromJson(Map<String, dynamic> json) => Album(
        id: json["idAlbum"],
        title: json["strAlbum"],
        cover: json["strAlbumThumb"] ?? "",
      );
}
