package assignment1;

public class FlightReservation extends Reservation {
    private Airport departure;
    private Airport arrival;

    // This constructor initializes a FlightReservation with a name, a departure Airport and an arrival Airport.
    // If the airports are equal, it throws an IllegalArgumentException.
    public FlightReservation(String name, Airport departure, Airport arrival) {
        super(name);
        if (!departure.equals(arrival)) {
            this.departure = departure;
            this.arrival = arrival;
        } else {
            throw new IllegalArgumentException("No room of such type can be created.");
        }
    }

    // This method returns the cost of the reservation.
    // The cost is calculated by adding together the fuels cost, the airports’ fees, and $53.75, and rounding up to the nearest cent.
    @Override
    public int getCost() {
        double fuel = (Airport.getDistance(departure, arrival) / 167.52) * (1.24 * 100);
        double fees = departure.getFees() + arrival.getFees();
        int price = (int) Math.ceil(fuel + fees + (53.75 * 100));
        return price;
    }

    // This method return true if input matches this in type, name, and airports. Otherwise, it returns false.
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FlightReservation) {
            FlightReservation objRes = (FlightReservation) obj;
            if ((objRes.reservationName().equals(this.reservationName())
                    && (objRes.departure.equals(this.departure))
                    && (objRes.departure.equals(this.departure)))) {
                return true;
            }
        }
        return false;
    }
}

