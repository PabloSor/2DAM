import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../localization/app_localizations.dart';
import '../models/product.dart';
import '../providers/product_provider.dart';

class ProductFormPage extends StatefulWidget {
  final Product? product;
  const ProductFormPage({super.key, this.product});

  @override
  State<ProductFormPage> createState() => _ProductFormPageState();
}

class _ProductFormPageState extends State<ProductFormPage> {
  final _nameController = TextEditingController();
  final _descController = TextEditingController();
  bool _loading = false;

  @override
  void initState() {
    super.initState();
    if (widget.product != null) {
      _nameController.text = widget.product!.name;
      _descController.text = widget.product!.description;
    }
  }

  @override
  Widget build(BuildContext context) {
    final loc = AppLocalizations(Localizations.localeOf(context));
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.product == null ? loc.translate('add_product') : loc.translate('edit_product')),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: _loading
            ? const Center(child: CircularProgressIndicator())
            : Column(
                children: [
                  TextField(
                    controller: _nameController,
                    decoration: InputDecoration(labelText: loc.translate('name')),
                  ),
                  TextField(
                    controller: _descController,
                    decoration: InputDecoration(labelText: loc.translate('description')),
                  ),
                  const SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () async {
                      setState(() {
                        _loading = true;
                      });
                      final product = Product(
                        id: widget.product?.id ?? '',
                        name: _nameController.text,
                        description: _descController.text,
                      );
                      final provider = Provider.of<ProductProvider>(context, listen: false);
                      if (widget.product == null) {
                        await provider.addProduct(product);
                      } else {
                        await provider.updateProduct(product);
                      }
                      setState(() {
                        _loading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: Text(loc.translate('save')),
                  ),
                ],
              ),
      ),
    );
  }
}
