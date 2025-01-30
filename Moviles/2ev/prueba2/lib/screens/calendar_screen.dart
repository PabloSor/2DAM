import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

DateTime _focusedDay = DateTime.now();
CalendarFormat _calendarFormat = CalendarFormat.month;
DateTime? _selectedDay;

List<DateTime> diasDestacados = [
  DateTime(2024, 9, 9),
  DateTime(2025, 6, 21),
  DateTime(2025, 1, 29),
  DateTime(2024, 5, 10),
  DateTime(2024, 12, 21),
  DateTime(2024, 12, 25),
  DateTime(2024, 12, 31),
  DateTime(2025, 1, 1),
  DateTime(2025, 1, 5),
  DateTime(2025, 1, 6)
];

class CalendarScreen extends StatefulWidget {
  @override
  _CalendarScreenState createState() => _CalendarScreenState();
}

class _CalendarScreenState extends State<CalendarScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Calendar screen",
          style:
              TextStyle(color: Color.fromARGB(255, 46, 218, 11), fontSize: 25),
        ),
        backgroundColor: const Color.fromARGB(255, 202, 3, 3),
      ),
      body: TableCalendar(
        firstDay: DateTime.utc(2024, 9, 01),
        lastDay: DateTime.utc(2025, 6, 21),
        focusedDay: _focusedDay,
        calendarFormat: _calendarFormat,
        onFormatChanged: (format) {
          if (_calendarFormat != format) {
            setState(() {
              _calendarFormat = format;
            });
          }
        },
        selectedDayPredicate: (day) {
          return isSameDay(_selectedDay, day);
        },
        onDaySelected: (selectedDay, focusedDay) {
          if (!isSameDay(_selectedDay, selectedDay)) {
            setState(() {
              _selectedDay = selectedDay;
              _focusedDay = focusedDay;
            });
          }
      
        },
        calendarBuilders: CalendarBuilders(
          defaultBuilder: (context, day, focusedDay) {
            if (diasDestacados.contains(day)){
              return Container()
            }
          },
        ),
      ),
    );
  }
}
