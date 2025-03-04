import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../localization/app_localizations.dart';
import '../providers/auth_provider.dart';
import 'product_page.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  bool _loading = false;
  String _error = '';

  @override
  Widget build(BuildContext context) {
    final loc = AppLocalizations(Localizations.localeOf(context));
    return Scaffold(
      appBar: AppBar(title: Text(loc.translate('register'))),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: _loading
            ? const Center(child: CircularProgressIndicator())
            : Column(
                children: [
                  TextField(
                    controller: _emailController,
                    decoration: InputDecoration(labelText: loc.translate('email')),
                  ),
                  TextField(
                    controller: _passwordController,
                    decoration: InputDecoration(labelText: loc.translate('password')),
                    obscureText: true,
                  ),
                  const SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () async {
                      setState(() {
                        _loading = true;
                        _error = '';
                      });
                      try {
                        await Provider.of<AuthProvider>(context, listen: false)
                            .register(_emailController.text, _passwordController.text);
                        Navigator.pushReplacement(context,
                            MaterialPageRoute(builder: (_) => const ProductPage()));
                      } catch (e) {
                        setState(() {
                          _error = e.toString();
                        });
                      } finally {
                        setState(() {
                          _loading = false;
                        });
                      }
                    },
                    child: Text(loc.translate('register')),
                  ),
                  if (_error.isNotEmpty)
                    Text(_error, style: const TextStyle(color: Colors.red)),
                ],
              ),
      ),
    );
  }
}
