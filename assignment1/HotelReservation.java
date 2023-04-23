package assignment1;

public class HotelReservation extends Reservation {
    private Hotel hotel;
    private String type;
    private int nights;
    private int price;

    // This constructor initializes a HotelReservation with a name, the hotel, the room type and the number of nights.
    public HotelReservation(String name, Hotel hotel, String type, int nights) {
        super(name);
        this.hotel = hotel;
        this.type = type;
        this.nights = nights;
        this.price = hotel.reserveRoom(type);
    }

    // This method returns the number of nights on the reservation.
    public int getNumOfNights(){
        return nights;
    }

    // This method returns the cost of the reservation in cents, represented by the price of the room type multipled by the number of nights.
    @Override
    public int getCost() {
        return price * nights;
    }

    // This method returns true if input matches this in type, name, hotel, room type, number of nights, and total cost. Otherwise, it returns false.
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HotelReservation) {
            HotelReservation objRes = (HotelReservation) obj;
            if ((objRes.reservationName().equals(this.reservationName())) &&
                    (objRes.hotel.equals(this.hotel)) && (objRes.type.equals(this.type)) &&
                    (objRes.nights == this.nights) && (objRes.getCost() == this.getCost())) {
                return true;
            }
        }
        return false;
    }
}
