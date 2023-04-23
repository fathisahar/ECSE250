package assignment1;

public class Basket {
    private Reservation[] reservations;

    // This constructor initialize the field of reservation with an array with no reservations.
    public Basket(){
        reservations = new Reservation[0];
    }

    // This method returns a shallow copy of the array of Reservations of the basket.
    public Reservation[] getProducts(){
        Reservation[] shallowReservations = new Reservation[reservations.length];
        shallowReservations = reservations;
        return shallowReservations;
    }

    // The method adds the reservation at the end of the list of reservation of the basket and returns how many reservations are now there.
    public int add(Reservation reservation) {
        Reservation[] newReservations = new Reservation[reservations.length + 1];
        for (int i = 0; i < reservations.length; i++) {
            newReservations[i] = reservations[i];
        }
        newReservations[newReservations.length - 1] = reservation;

        reservations = new Reservation[newReservations.length];
        for (int i = 0; i < reservations.length; i++) {
            reservations[i] = newReservations[i];
        }
        return reservations.length;
    }


    // The method removes the first occurrence of the specified element from the array of reservation of the basket.
    // If no such reservation exists, then it returns false, otherwise, after removing it, it returns true.
    public boolean remove(Reservation reservation){
        for (int i = 0; i<reservations.length; i++){
           if (reservation.equals(reservations[i])) {
               reservations[i] = null;

               for (int j = i; j<reservations.length-1; j++){
                   reservations[j] = reservations[j+1];
               }

               Reservation[] tempReservations = new Reservation[reservations.length - 1];
               for (int k = 0; k < tempReservations.length; k++) {
                   tempReservations[k] = reservations[k];
               }
               reservations = new Reservation[tempReservations.length];
               for (int k = 0; k < reservations.length; k++) {
                   reservations[k] = tempReservations[k];
               }



               return true;
            }
        }
        return false;
    }

    // This method empties the array of reservations of the basket.
    public void clear(){
        reservations = new Reservation[0];
    }

    // This method returns the number of reservations in the basket.
    public int getNumOfReservations(){
        return reservations.length;
    }

    // This method returns the cost (in cents) of all the reservations in the basket.
    public int getTotalCost(){
        int cost = 0;
        for (int i = 0; i<reservations.length; i++){
            cost += reservations[i].getCost();
        }
        return cost;
    }

}


