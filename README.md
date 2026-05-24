# Mini Railway Reservation System

A console-based Railway Ticket Reservation System built in **Java** that simulates the core functionalities of a real-world train ticket booking system — including berth allocation, RAC (Reservation Against Cancellation), waiting list management, and ticket cancellation with automatic promotion.

---

##  Project Structure

```
RailwayRS/
├── src/
│   ├── Main.java               # Entry point — handles menu, booking & cancellation logic
│   ├── TicketBooker.java       # Core booking engine — manages berths, RAC, waiting list
│   └── Passenger/
│       └── Passenger.java      # Passenger data model
├── out/
│   └── production/RailwayRS/   # Compiled .class files
└── RailwayRS.iml               # IntelliJ IDEA module file
```

---

## Features

- **Ticket Booking** — Book a confirmed berth with a preference for Lower, Middle, or Upper berth
- **Berth Preference Handling** — Allocates the preferred berth if available; falls back to the next available one automatically
- **RAC (Reservation Against Cancellation)** — Books the passenger on RAC if no confirmed berths are available
- **Waiting List** — Adds the passenger to a waiting list if both confirmed berths and RAC are full
- **Ticket Cancellation** — Cancels a booking by Passenger ID and automatically promotes RAC passengers to confirmed berths, and waiting list passengers to RAC
- **View Available Tickets** — Displays current availability across Lower, Middle, Upper berths, RAC, and Waiting List slots
- **View Booked Tickets** — Lists all currently booked passengers with their seat details

---

## Class Overview

### `Passenger` (`src/Passenger/Passenger.java`)
Represents a passenger entity with the following fields:

| Field       | Type   | Description                              |
|-------------|--------|------------------------------------------|
| `passId`    | int    | Auto-incremented unique passenger ID     |
| `name`      | String | Passenger's name                         |
| `age`       | int    | Passenger's age                          |
| `berthPref` | String | Preferred berth — `"L"`, `"M"`, or `"U"`|
| `alloted`   | String | Actual berth allotted — `L`, `M`, `U`, `RAC`, or `WL` |
| `seatNum`   | int    | Assigned seat/position number            |

---

### `TicketBooker` (`src/TicketBooker.java`)
The core reservation engine. Manages all static state representing the train's capacity and passenger records.

**Key static state:**

| Variable    | Description                                  |
|-------------|----------------------------------------------|
| `avaiLow`   | Available Lower berth count                  |
| `avaiMid`   | Available Middle berth count                 |
| `avaiUp`    | Available Upper berth count                  |
| `avaiRac`   | Available RAC slots                          |
| `avaiWait`  | Available Waiting List slots                 |
| `passengers`| `HashMap<Integer, Passenger>` of all bookings|
| `waitList`  | Queue for waiting list passenger IDs         |
| `racList`   | Queue for RAC passenger IDs                  |
| `bookedList`| List of confirmed booked passenger IDs       |

**Key methods:**

| Method           | Description                                               |
|------------------|-----------------------------------------------------------|
| `bookTicket()`   | Confirms a berth booking for a passenger                  |
| `bookRAC()`      | Adds a passenger to the RAC list                          |
| `addToWait()`    | Adds a passenger to the waiting list                      |
| `cancelTicket()` | Cancels a booking and auto-promotes RAC/Waiting passengers|
| `printAvailable()`| Prints current seat availability                         |
| `printBooked()`  | Prints details of all booked passengers                   |

---

### `Main` (`src/Main.java`)
The entry point of the application. Contains the interactive console menu and delegates to `TicketBooker`.

**Menu Options:**

```
1. Book Ticket
2. Cancel Ticket
3. Available Tickets
4. Booked Tickets
5. Exit
```

---

##  Booking Flow

```
Book Ticket Request
        │
        ▼
Preferred Berth Available?
   YES ──► Allot Preferred Berth ──► Confirmed 
        │
       NO
        ▼
Any Other Berth Available?
   YES ──► Allot Next Available Berth ──► Confirmed 
        │
       NO
        ▼
RAC Slot Available?
   YES ──► Add to RAC 
        │
       NO
        ▼
Waiting List Slot Available?
   YES ──► Add to Waiting List 
        │
       NO
        ▼
   No Tickets Available 
```

---

##  Cancellation & Auto-Promotion Flow

When a confirmed ticket is cancelled:
1. The freed berth is returned to the available pool.
2. The **first RAC passenger** is automatically promoted to a confirmed berth.
3. The **first Waiting List passenger** is automatically moved to RAC.

---

##  Getting Started

### Prerequisites
- **Java JDK 8** or higher
- **IntelliJ IDEA** (recommended) or any Java IDE / terminal

### Running in IntelliJ IDEA
1. Open IntelliJ IDEA and select **File → Open**.
2. Navigate to the `RailwayRS/` folder and open it.
3. Wait for the project to index.
4. Open `src/Main.java` and click the **Run** button (▶).

### Running from the Terminal

```bash
# Navigate to the project source directory
cd RailwayRS/src

# Compile all source files
javac -d ../out/production/RailwayRS Passenger/Passenger.java TicketBooker.java Main.java

# Run the compiled program
cd ../out/production/RailwayRS
java Main
```

---

##  Sample Usage

```
***************WELCOME TO RAILWAY RESERVATION SYSTEM***************
1. Book Ticket
2. Cancel Ticket
3. Available ticket
4. Booked Tickets
5. Exit
Enter your choice from above: 1

Enter Passenger name, age and berthPref (L, M or U)
Alice 28 L
Lower Berth Given..
.......Booked Successfully....

Enter your choice from above: 3
Tickets Available..
_____________________
Available LowerBerth : 0
Available MiddleBerth : 1
Available UpperBerth : 1
Available RAC Tickets : 1
Available Waiting List : 1
```

---

## Configuration

The current build is configured for a **single-coach** setup with the following default capacities (defined in `TicketBooker.java`):

| Berth Type    | Capacity |
|---------------|----------|
| Lower Berth   | 1        |
| Middle Berth  | 1        |
| Upper Berth   | 1        |
| RAC           | 1        |
| Waiting List  | 1        |

To scale up capacity, update the static initializers and position lists in `TicketBooker.java`.

---

## Tech Stack

| Technology | Details            |
|------------|--------------------|
| Language   | Java               |
| IDE        | IntelliJ IDEA      |
| Build      | Manual / IntelliJ  |
| Java       | JDK 8+             |

---
