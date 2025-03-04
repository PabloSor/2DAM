import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/product.dart';

class HttpService {
  static const String baseUrl = 'https://tu-backend.com/api/products';

  static Future<List<Product>> getProducts() async {
    final response = await http.get(Uri.parse(baseUrl));
    if (response.statusCode == 200) {
      Iterable list = json.decode(response.body);
      return list.map((json) => Product.fromJson(json)).toList();
    } else {
      throw Exception('Error al obtener productos');
    }
  }

  static Future<void> addProduct(Product product) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(product.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception('Error al agregar producto');
    }
  }

  static Future<void> updateProduct(Product product) async {
    final url = '$baseUrl/${product.id}';
    final response = await http.put(
      Uri.parse(url),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(product.toJson()),
    );
    if (response.statusCode != 200) {
      throw Exception('Error al actualizar producto');
    }
  }

  static Future<void> deleteProduct(String id) async {
    final url = '$baseUrl/$id';
    final response = await http.delete(Uri.parse(url));
    if (response.statusCode != 200) {
      throw Exception('Error al borrar producto');
    }
  }
}
