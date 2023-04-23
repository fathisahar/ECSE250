package assignment1;

public class Room {
    private String type;
    private int price;
    private boolean available;

    // This constructor initializes a room with a type, price and its availability.
    // The fields are dependent on its type, and only three types are accepted. Otherwise, an exception is thrown.
    public Room(String roomType) {
        if (roomType.equals("double")) {
            this.type = roomType;
            this.price = 90 * 100;
            this.available = true;
        } else if (roomType.equals("queen")) {
            this.type = roomType;
            this.price = 110 * 100;
            this.available = true;
        } else if (roomType.equals("king")) {
            this.type = roomType;
            this.price = 150 * 100;
            this.available = true;
        } else {
            throw new IllegalArgumentException("No room of such type can be created.");
        }
    }

    // This constructor creates a copy of the input room.
    public Room(Room room) {
        this.type = room.type;
        this.price = room.price;
        this.available = room.available;
    }

    // This method returns the type of the room.
    public String getType() {
        return type;
    }

    // This method returns the price of the room.
    public int getPrice() {
        return price;
    }

    // This method sets the value stored in the availability field to be the opposite of the one currently there.
    public void changeAvailability() {
        if (available) {
            available = false;
        } else {
            available = true;
        }
    }

    // The method returns the first available room in the array of the indicated type.
    // If no such room exists, the method returns null.
    public static Room findAvailableRoom(Room[] rooms, String roomType) {
        for (Room room : rooms) {
            if (room.type.equals(roomType) && room.available) {
                return room;
            }
        }
        return null;
    }

    // The method makes the first unavailable room in the array of the indicated type available again.
    // If successful, the method should return true, otherwise the method should return false.
    public static boolean makeRoomAvailable(Room[] rooms, String roomType) {
        for (Room room : rooms) {
            if (room.type.equals(roomType) && !room.available) {
                room.changeAvailability();
                return true;
            }
        }
        return false;
    }
}
