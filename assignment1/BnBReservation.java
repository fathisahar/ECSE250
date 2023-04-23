package assignment1;

public class BnBReservation extends HotelReservation {

    // This constructor initializes a BnBReservation and creates HotelReservation with a name, the hotel, the room type and the number of nights.
    public BnBReservation(String name, Hotel hotel, String type, int nights) {
        super(name, hotel, type, nights);
    }

    // This method returns the cost of the reservation in cents. With breakfast, the cost includes 10$ per night in addition to the cost of reserving the room.
    @Override
    public int getCost(){
        return super.getCost() + this.getNumOfNights()*10*100;
    }

}
