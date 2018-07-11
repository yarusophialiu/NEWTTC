Summary
In this project, we design a public transit system that tracks and
calculates fares for anyone who uses the presto card (called "cardholder")
to enter and exit the system. The transit system only contains subways and buses.
Fares are calculated based on time and stations(for subways)/stops(for buses)

Notes:
1. the program will print information when any user buy any amount of card, or any balance change happened in the card
   including add balance, enter bus station and exit subway station.
2. the program will also print when errors happened such as balance not enough and a suspended card trying to get into a station.
3. DO NOT END line with any sort of punctuation, and for empty lines, do not put space in them, keep them empty.


Instructions
First, set up subway and bus lines in stations.txt.
For each subway line, use the format:
Bloor-Yonge neighbour: Wellesley                //the first word should be the name of the station followed by " neighbour: " and its neighbour stations
Wellesley neighbour: Bloor-Yonge College        //note that stations can only be count as one word, for example, Union Station need to be written as Union_Station
...                                             //start a new line when you need to add new stations and the last line of the stations.txt MUST BE null

For each bus line, use the format:
bus:
Bloor-Yonge neighbour: Queens_Park
Queens_Park neighbour: Bloor-Yonge College
...

All other actions, such as buy card, take subway, happen in events.txt
To buy a card, first give information about the user and then buy a card. Use the following format:
Steven steven@mail.com steven123
Steven bought 1 card

When taking a subway/bus, cardholder is expected to tap the card when enter and exit the station.
Use the following format in event.txt:
Card 1000 enters Bloor-Yonge subway station at 2018-05-01 10:00:00          // same as the above, station name can only be one word, Union Station need to be written as Union_Station


When user lost and suspends a card, use format:
Card 1004 is suspended at 2018-05-01 17:19:26

When user founds the card and reverses the suspended card, use format:
Card 1004 is retrieved at 2018-05-01 17:29:26

When user adds money to a card, use format:
10 dollars is added to card 1000 at 2018-05-01 17:39:26     // do not write dollar sign instead.

when user want to obtain information on recent trips, use format:
Card 1002 recent trip

when the day ended and want to make a report, use format:
Day ended, report generated             // report are only generated when the day ended.
