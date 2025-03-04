import 'package:flutter/material.dart';
import '../services/firebase_service.dart';
import '../services/http_service.dart';
import '../main.dart';
import '../models/product.dart';

class ProductProvider extends ChangeNotifier {
  List<Product> products = [];

  Future<void> fetchProducts() async {
    if (MyApp.useFlutterFire) {
      products = await FirebaseService.getProducts();
    } else {
      products = await HttpService.getProducts();
    }
    notifyListeners();
  }

  Future<void> addProduct(Product product) async {
    if (MyApp.useFlutterFire) {
      await FirebaseService.addProduct(product);
    } else {
      await HttpService.addProduct(product);
    }
    await fetchProducts();
  }

  Future<void> updateProduct(Product product) async {
    if (MyApp.useFlutterFire) {
      await FirebaseService.updateProduct(product);
    } else {
      await HttpService.updateProduct(product);
    }
    await fetchProducts();
  }

  Future<void> deleteProduct(String id) async {
    if (MyApp.useFlutterFire) {
      await FirebaseService.deleteProduct(id);
    } else {
      await HttpService.deleteProduct(id);
    }
    await fetchProducts();
  }
}
