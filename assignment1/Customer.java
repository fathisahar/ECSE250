package assignment1;

public class Customer {
    private String name;
    private int balance;
    private Basket basket;

    // This constructor initializes a Customer with a name and an initial balance, and creates an empty Basket.
    public Customer(String name, int balance){
        this.name = name;
        this.balance = balance;
        this.basket = new Basket();
    }

    // This method returns the name of the customer.
    public String getName(){
        return name;
    }

    //This method returns the balance of the customer.
    public int getBalance(){
        return balance;
    }

    // This method returns the reference to the basket of the customer.
    public Basket getBasket(){
        return basket;
    }

    // This method adds cents to the balance of the customer if it is a positive amount and returns the new balance.
    // Otherwise, it will throw an IllegalArgumentException with an appropriate message.
    public int addFunds(int cents){
        if (cents>0) {
            balance += cents;
            return balance;
        } else {
            throw new IllegalArgumentException("You cannot add a negative amount to the balance.");
        }
    }

    // This method adds a reservation to the basket of the customer if the name on the reservation matches the name of the customer.
    // If it is successful it returns the number of reservations in the basket of this customer.
    // Otherwise, it should throw an IllegalArgumentException.
    public int addToBasket(Reservation reservation){
        if (reservation.reservationName().equals(name)){
            return basket.add(reservation);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // The method adds the corresponding reservation to the basket of the customer and returns the number of reservations that are now in the basket of this customer.
    public int addToBasket(Hotel hotel, String type, int nights, boolean breakfast){
        if (!breakfast){
            HotelReservation hotelReservation = new  HotelReservation(name, hotel, type, nights);
            return basket.add(hotelReservation);
        } else {
            BnBReservation bnbReservation = new BnBReservation(name, hotel, type, nights);
            return basket.add(bnbReservation);
        }
    }

    // The method adds the corresponding reservation to the basket of the customer and returns the number of reservations that are now in their basket.
    public int addToBasket(Airport airportOne, Airport airportTwo){
        FlightReservation flightReservation = new FlightReservation(name, airportOne, airportTwo);
        return basket.add(flightReservation);
    }

    // This method removes the input reservation from the basket of the customer.
    // The method returns a boolean indicating whether of not the operation was successful.
    public boolean removeFromBasket(Reservation reservation){
        return basket.remove(reservation);
    }

    // This method charges the customer the total cost of the basket, clears it, and returns the leftover balance.
    // If the customer's balance is not able to cover the cost of the basket, IllegalStateException is thrown.
    public int checkOut(){
        if (basket.getTotalCost() < balance){
            int leftOver = balance - basket.getTotalCost();
            basket.clear();
            return leftOver;
        } else {
            throw new IllegalStateException();
        }

    }
}
