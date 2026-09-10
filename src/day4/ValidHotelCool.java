//HEAP: Flight Object
//┌───────────────────────────────┐
//│ flightId: 101                 │
//│ flightCode: "6E-2044"         │
//│ seatInventory (Reference) ───┼─────┐
//└───────────────────────────────┘     │
//                                      ▼
//                      HEAP: int[] Array Object
//                      ┌─────────┬─────────┬─────────┬─────────┐
//             Indices: │   [0]   │   [1]   │   [2]   │   [3]   │
//                      ├─────────┼─────────┼─────────┼─────────┤
//              Values: │   901   │    0    │    0    │    0    │
//                      └─────────┴─────────┴─────────┴─────────┘
//                      (Occupied) (Vacant)  (Vacant)  (Vacant)


//JVM Starts
//      |
//Class Loading
//      |
//Static Block Executes
//      |
//main() Starts
//      |
//Create Flights
//      |
//Create Passengers
//      |
//Calculate Fare
//      |
//Seat Allocation
//      |
//Eligibility Validation
//      |
//Board Passenger
//      |
//Generate Boarding Pass
//      |
//Program Ends

//Index   Value
//
//0       0
//1       0
//2       0
//3       0

// just like the code we've in the hotel we are having in the flight module just like the calculations we are having in their modules of buying purchasing and calculating upon it.
// total earning of each hotels including their bills and the setup and the tickets.








