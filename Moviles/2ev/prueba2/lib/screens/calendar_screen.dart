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
];

List<DateTime> diasDestacados2 = [
  DateTime(2024, 10, 5),
  DateTime(2024, 12, 31),
  DateTime(2025, 1, 1),
  DateTime(2025, 1, 5),
  DateTime(2025, 1, 6)
];

class CalendarScreen extends StatefulWidget {
  const CalendarScreen({super.key});

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
      body: Column(
        children: [
          TableCalendar(
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
                if (diasDestacados.any((elemnt) => isSameDay(elemnt, day))) {
                  return Container(
                    margin: EdgeInsets.all(5),
                    alignment: Alignment.center,
                    decoration: BoxDecoration(
                        shape: BoxShape.circle, color: Colors.red),
                    child: Text(
                      '${day.day}',
                      style: TextStyle(color: Colors.white, fontSize: 16),
                    ),
                  );
                }
                if (diasDestacados2.any((elemnt) => isSameDay(elemnt, day))) {
                  return Container(
                    margin: EdgeInsets.all(5),
                    alignment: Alignment.center,
                    decoration: BoxDecoration(
                        shape: BoxShape.circle, color: Colors.yellow),
                    child: Text(
                      '${day.day}',
                      style: TextStyle(color: Colors.white, fontSize: 16),
                    ),
                  );
                }
                return null;
              },
            ),
          ),
          SizedBox(
            height: 20,
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Row(
                  children: [
                    _buildLegendItem(Colors.red, "Fechas de lo que sea"),
                  ],
                ),
                SizedBox(width: 20),

                Row(
                  children: [
                    _buildLegendItem(
                        Colors.yellow, "Fechas de lo mismo pero otro color"),
                  ],
                )
                // Color 1
              ],
            ),
          ),
        ],
      ),
    );
  }
}

Widget _buildLegendItem(Color color, String text) {
  return Row(
    children: [
      Container(
        width: 16,
        height: 16,
        decoration: BoxDecoration(shape: BoxShape.circle, color: color),
      ),
      SizedBox(width: 8),
      Text(
        text,
        style: TextStyle(fontSize: 20),
      )
    ],
  );
}
