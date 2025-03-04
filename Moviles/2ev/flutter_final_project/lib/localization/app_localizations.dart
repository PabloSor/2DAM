import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'dart:async';

class AppLocalizations {
  final Locale locale;
  AppLocalizations(this.locale);

  static const _localizedValues = <String, Map<String, String>>{
    'en': {
      'login': 'Login',
      'register': 'Register',
      'email': 'Email',
      'password': 'Password',
      'logout': 'Logout',
      'products': 'Products',
      'add_product': 'Add Product',
      'edit_product': 'Edit Product',
      'delete': 'Delete',
      'save': 'Save',
      'name': 'Name',
      'description': 'Description',
    },
    'es': {
      'login': 'Acceder',
      'register': 'Registrarse',
      'email': 'Correo',
      'password': 'Contraseña',
      'logout': 'Cerrar sesión',
      'products': 'Productos',
      'add_product': 'Agregar Producto',
      'edit_product': 'Editar Producto',
      'delete': 'Borrar',
      'save': 'Guardar',
      'name': 'Nombre',
      'description': 'Descripción',
    },
  };

  String translate(String key) {
    return _localizedValues[locale.languageCode]?[key] ?? key;
  }

  static const LocalizationsDelegate<AppLocalizations> delegate = _AppLocalizationsDelegate();
}

class _AppLocalizationsDelegate extends LocalizationsDelegate<AppLocalizations> {
  const _AppLocalizationsDelegate();
  
  @override
  bool isSupported(Locale locale) => ['en', 'es'].contains(locale.languageCode);
  
  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(AppLocalizations(locale));
  }
  
  @override
  bool shouldReload(_AppLocalizationsDelegate old) => false;
}
