import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/product.dart';

class FirebaseService {
  static final CollectionReference productsCollection =
      FirebaseFirestore.instance.collection('products');

  static Future<List<Product>> getProducts() async {
    QuerySnapshot snapshot = await productsCollection.get();
    return snapshot.docs
        .map((doc) => Product.fromMap(doc.id, doc.data() as Map<String, dynamic>))
        .toList();
  }

  static Future<void> addProduct(Product product) async {
    await productsCollection.add(product.toMap());
  }

  static Future<void> updateProduct(Product product) async {
    await productsCollection.doc(product.id).update(product.toMap());
  }

  static Future<void> deleteProduct(String id) async {
    await productsCollection.doc(id).delete();
  }
}
