class Place {
  final int id;
  final String name;
  final String country;
  final String imageUrl;

  Place({required this.id, required this.name, required this.country, required this.imageUrl});

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      id: json['id'],
      name: json['name'],
      country: json['country'],
      imageUrl: json['imageUrl'],
    );
  }
}
