package assignment1;

public abstract class Reservation {
    private String name;

    public Reservation(String name){
       this.name = name;
    }

    // This method returns the name on the reservation.
    public final String reservationName(){
        return name;
    }

    // This method returns the cost depending on the type of reservation.
    public abstract int getCost();

    // This method returns a boolean depending on the type of reservations compared.
    public abstract boolean equals(Object obj);
    
}
