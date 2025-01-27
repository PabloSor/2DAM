import 'package:flutter/material.dart';
import 'button_text_widget.dart';

class ButtonWidget extends StatelessWidget {
  final String text;
  final VoidCallback onPressed;

  const ButtonWidget({
    Key? key,
    required this.text,
    required this.onPressed,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ElevatedButton(
        onPressed: onPressed,
        style: ElevatedButton.styleFrom(
          padding: const EdgeInsets.all(20),
          backgroundColor: const Color.fromARGB(80, 0, 255, 0),
        ),
        child: ButtonTextWidget(text: text),
      ),
    );
  }
}