import 'package:flutter/material.dart';

class ButtonTextWidget extends StatelessWidget {
  final String text;

  const ButtonTextWidget({
    Key? key,
    required this.text,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: const TextStyle(fontSize: 24),
    );
  }
}