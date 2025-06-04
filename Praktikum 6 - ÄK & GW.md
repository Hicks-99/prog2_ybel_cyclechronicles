

| Eingabe                              | gültige ÄK                      | ungültige ÄK      |
| :----------------------------------- | :------------------------------ | :---------------- |
| TYP                                  | gÄK1: RACE, SINGLE_SPEED, FIXIE | uÄK4: E-Bike      |
|                                      |                                 | uÄK5: Gravel-Bike |
| Number of pending customer order *x* | gÄK2: $x = 0$                   | uÄK6: $x > 1$     |
| Number of orders overall *n*         | gÄK3: $[0, 4]$                  | uÄK7: $n>4$       |


| Testnummer          | 1    | 2    | 3            | 4     | 5         | 6           | 7         | 8         |
| :------------------ | :--- | :--- | :----------- | ----- | --------- | ----------- | --------- | --------- |
| geprüfter GW        | gÄK1 | gÄK2 | gÄK3o        | gÄK3u | uÄK4      | uÄK5        | uÄK6u     | uÄK7u     |
| TYP                 | RACE | RACE | SINGLE_SPEED | FIXIE | E-BIKE    | GRAVEL-BIKE | RACE      | RACE      |
| *X*                 | 0    | 0    | 0            | 0     | 0         | 0           | 1         | 0         |
| *N*                 | 1    | 2    | 4            | 0     | 1         | 2           | 3         | 5         |
| Erwartetes Ergebnis | OK   | OK   | OK           | OK    | EXCEPTION | EXCEPTION   | EXCEPTION | EXCEPTION |
