Summary
In this project, we design a public transit system that tracks and
calculates fares for anyone who uses a travel card (called "cardholder")
to enter and exit the system. The transit system only contains subways and buses.
Fares are calculated based on time and stations(for subways)/stops(for buses)


Instructions
First, set up subway and bus lines in stations.txt.
For each subway line, use the format:
subway:
Bloor-Yonge
Wellesley
...

For each bus line, use the format:
bus:
Bloor-Yonge
Queens Park
...

All other actions, such as buy card, take subway, happen in events.txt
To buy a card, first give information about the user and then buy a card. Use the following format:
Steven steven@mail.com steven123
Steven bought 1 card

When taking a subway/bus, cardholder is expected to tap the card when enter and exit the station.
Use the following format in event.txt:
Card 1000 enters Bloor-Yonge subway station at 2018-05-01 10:00:00

When user links a card to his/her own account, use format: Sophia adds Card1000
When user lost and a suspends a card, use format: Xiaolei suspends Card1000
When user founds the card and reverses the suspended card, use format: Yuxing reverses Card1000
When user removes a card, use format: Yunfan removes Card1000
When user adds money to a card, use format: Yunfan adds Card1000 10
