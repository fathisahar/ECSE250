package assignment1;

public class Hotel {
    private String name;
    private Room[] rooms;

    // This constructor initializes a hotel with a name and an array of rooms.
    public Hotel(String name, Room[] roomsInput){
        this.name = name;
        this.rooms = new Room[roomsInput.length];
        // This loop will allow a deep copy of the input array of rooms.
        for (int i = 0; i < rooms.length; i++){
            // A copy constructor is used to create a room in the Hotel's array of rooms with the same fields as the room from the input array.
            this.rooms[i] = new Room(roomsInput[i]);
        }
    }

    // The method changes the availability of the first available room of the specified type in the hotel.
    // If successful, it returns the price of the room. Otherwise, an IllegalArgumentException is thrown.
    public int reserveRoom(String type){
        if (Room.findAvailableRoom(rooms, type) == null){
            throw new IllegalArgumentException();
        } else {
            int price = Room.findAvailableRoom(rooms, type).getPrice();
            Room.findAvailableRoom(rooms, type).changeAvailability();
            return price;
        }
    }

    // The method makes a room of that type available again.
    // It returns true if the operation was possible, false otherwise.
    public boolean cancelRoom(String type){
        return Room.makeRoomAvailable(rooms, type);
    }
}
