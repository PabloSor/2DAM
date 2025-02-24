import 'package:flutter/material.dart';

class ContactsScreen extends StatelessWidget {
  ContactsScreen({Key? key}) : super(key: key);
  
  final contactos = [
    "mescudern@iesch.org",
    "arodriguezg@iesch.org",
    "jmorenod@iesch.org",
    "asancheze@ieshc.org"
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Contactos',
          style: TextStyle(fontSize: 30, color: Colors.white),
        ),
        backgroundColor: Colors.indigo,
      ),
      body: ListView.separated(
        itemBuilder:
            (context, index) => ListTile(
              leading: Icon(Icons.contact_mail),
              title: Text(contactos[index]),
              onTap: () {
                 if (index == 0) {
                  Navigator.pushNamed(context, 'mario');
                } else if (index == 1) {
                  Navigator.pushNamed(context, 'alberto');
                } else if (index == 2) {
                  Navigator.pushNamed(context, 'juanma');
                } else {
                  Navigator.pushNamed(context, 'angel');
                }
              },
            ),
        separatorBuilder: (context, index) => Divider(),
        itemCount: contactos.length,
      ),
    );
  }
}
