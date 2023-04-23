package assignment2;

public class World {
    private Caterpillar caterpillar;
    private Position currentFood;
    private Region region;
    private ActionQueue actions;
    private TargetQueue foodPositions;
    private GameState gameState;

    public World(TargetQueue foodPositions, ActionQueue actions){
        this.foodPositions = foodPositions;
        this.actions = actions;
        region = new Region(0,0,15,15);
        caterpillar = new Caterpillar();
        currentFood = foodPositions.dequeue();
        gameState = GameState.MOVE;
    }

    public void step(){
        Direction direction = null;
        if (actions.isEmpty()) {
            gameState = GameState.NO_MORE_ACTION;
        } else {
            direction =  actions.dequeue();
        }
        if (gameState != GameState.MOVE && gameState != GameState.EAT){
            return;
        }
        Position newPosition = new Position(caterpillar.getHead());
        Direction E = Direction.EAST;
        Direction W = Direction.WEST;
        Direction S = Direction.SOUTH;
        Direction N = Direction.NORTH;
        switch(direction){
            case EAST:
                newPosition.moveEast();
                break;
            case WEST:
                newPosition.moveWest();
                break;
            case SOUTH:
                newPosition.moveSouth();
                break;
            case NORTH:
                newPosition.moveNorth();
                break;
        }
        if (!region.contains(newPosition)){
            gameState = GameState.WALL_COLLISION;
            //caterpillar.move(newPosition);
        } else if (caterpillar.selfCollision(newPosition)){
            gameState = GameState.SELF_COLLISION;
        } else if (currentFood.getDistance(newPosition) == 0){
            caterpillar.eat(newPosition);
            if (foodPositions.isEmpty()){
                gameState = GameState.DONE;
            } else {
                currentFood = foodPositions.dequeue();
                gameState = GameState.EAT;
            }
        } else {
            gameState = GameState.MOVE;
            caterpillar.move(newPosition);
        }
    }

    public GameState getState(){
        return gameState;
    }

    public Caterpillar getCaterpillar(){
        return caterpillar;
    }

    public Position getFood(){
        return currentFood;
    }

    public boolean isRunning(){
        if (gameState == GameState.EAT || gameState == GameState.MOVE){
            return true;
        } else {
            return false;
        }
    }
}
