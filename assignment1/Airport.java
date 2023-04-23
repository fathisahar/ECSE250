package assignment1;

public class Airport {
    private int xCoord;
    private int yCoord;
    private int fees;

    // This constructor initializes an airport with x,y coordinates and fees.
    public Airport(int xCoord, int yCoord, int fees){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.fees = fees;
    }

    // This method will return the airport fees associated to the airport.
    public int getFees(){
        return fees;
    }

    // This method will return the distance in kilometers between two airports by using a formula with x,y coordinates.
    public static int getDistance(Airport airportOne, Airport airportTwo){
        double firstTerm = Math.pow((airportOne.xCoord - airportTwo.xCoord), 2); 
        double secondTerm = Math.pow((airportOne.yCoord - airportTwo.yCoord), 2); 
        double doubleDistance = Math.pow(firstTerm + secondTerm, 0.5);
        // The distance is rounded up to the nearest kilometer.
        int distance = (int) Math.ceil(doubleDistance);

        return distance;
    }


}

