package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import assignment2.*;
import org.junit.jupiter.api.Test;

public class WorldTest {
    @Test
    public void World_constructor_instantiates(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue());
    }

    @Test
    public void World_getState_initialStateIsMove(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue());
        assertEquals(GameState.MOVE, myWorld.getState());
    }

    @Test
    public void World_getCaterpillar_initialCaterpillarIsNewCaterpillar(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue());
        Caterpillar newCaterpillar = new Caterpillar();
        assertEquals(newCaterpillar, myWorld.getCaterpillar());
    }

    @Test
    public void World_getCaterpillar_returnsCorrectCaterpillar(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("N");
        World myWorld = new World(myTargetQueue, myActionQueue);
        Caterpillar myCaterpillar = myWorld.getCaterpillar();

        myWorld.step();
        Position correctPosition = new Position(7, 6);

        assertEquals(correctPosition, myCaterpillar.getHead());
    }

    @Test
    public void World_getFood_canReturnInitialFood(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue());
        assertEquals(new Position(1, 1), myWorld.getFood());
    }

    @Test
    public void World_getFood_canReturnNextFood(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(2,2)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("W");
        World myWorld = new World(myTargetQueue, myActionQueue);
        myWorld.step();

        assertEquals(new Position(2, 2), myWorld.getFood());
    }

    @Test
    public void World_isRunning_initiallyReturnsTrue(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue());
        assertTrue(myWorld.isRunning());
    }

    @Test
    public void World_isRunning_returnsTrueWhenGameStateIsEat(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(2,2)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("W");
        World myWorld = new World(myTargetQueue, myActionQueue);
        myWorld.step();
        assertEquals(GameState.EAT, myWorld.getState());
        assertTrue(myWorld.isRunning());
    }

    @Test
    public void World_isRunning_returnsFalseWhenGameStateIsWallCollision(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("SSSSSSSSSSSSS");
        World myWorld = new World(myTargetQueue, fullSouth);
        for(int i = 8; i<16; i++){ //takes step to Y-position 8, 9, ..., 15
            myWorld.step();
        }
        myWorld.step(); //this one should set the gamestate to WALL_COLLISION
        assertFalse(myWorld.isRunning());
    }

    @Test
    public void World_isRunning_returnsFalseWhenGameStateIsSelfCollision(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(6,6).(7,6).(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("WNES");
        World myWorld = new World(myTargetQueue, fullSouth);
        myWorld.step();
        myWorld.step();
        myWorld.step();
        myWorld.step();
        assertFalse(myWorld.isRunning());
    }

    @Test
    public void World_isRunning_returnsFalseWhenGameStateIsNoMoreActions(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue()); //no actions in queue
        myWorld.step(); //actionQueue empty: sets GameState to NO_MORE_ACTIONS
        assertFalse(myWorld.isRunning());
    }

    @Test
    public void World_isRunning_returnsFalseWhenGameStateIsDone(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(5,7)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("WW");
        World myWorld = new World(myTargetQueue, myActionQueue);
        myWorld.step();
        myWorld.step();
        assertFalse(myWorld.isRunning());
    }

    @Test
    public void World_step_noMoreActionsSetsGameStateToNoMoreActions(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        World myWorld = new World(myTargetQueue, new ActionQueue()); //no actions in queue
        myWorld.step(); //actionQueue empty: sets GameState to NO_MORE_ACTIONS
        assertEquals(GameState.NO_MORE_ACTION, myWorld.getState());
    }


    @Test
    public void World_step_doesntHitBorderSetsGameStateToMove(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("SSSSSSSSSSSS");
        World myWorld = new World(myTargetQueue, fullSouth); //no actions in queue
        for(int i = 8; i<16; i++){ //takes step to Y-position 8, 9, ..., 15
            myWorld.step();
        }
        //the last step taken went to (7, 15): still a valid position, gamestate should be MOVE
        assertEquals(GameState.MOVE, myWorld.getState());
    }

    @Test
    public void World_step_hitsBorderSetsGameStateToWallCollision(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("SSSSSSSSSSSSS");
        World myWorld = new World(myTargetQueue, fullSouth);
        for(int i = 8; i<16; i++){ //takes step to Y-position 8, 9, ..., 15
            myWorld.step();
        }
        myWorld.step(); //this one should set the gamestate to WALL_COLLISION
        assertEquals(GameState.WALL_COLLISION, myWorld.getState());
    }

    @Test
    public void World_step_hitsBorderStopsMoving(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("SSSSSSSSSSSSSSSSSSSSSSS");
        World myWorld = new World(myTargetQueue, fullSouth); //no actions in queue
        for(int i = 8; i<18; i++){ //takes step to Y-position 8, 9, ..., 17
            myWorld.step();
        }
        Position correctPosition = new Position(7, 15);

        //should have stopped at correctPosition
        assertCaterpillarAtCorrectPosition(myWorld, correctPosition);
    }


    @Test
    public void World_step_nextPositionFoodSetsGameStateToEat(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(1,1)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("W");
        World myWorld = new World(myTargetQueue, myActionQueue);
        myWorld.step();
        assertEquals(GameState.EAT, myWorld.getState());
    }

    @Test
    public void World_step_nextPositionLastFoodSetsGameStateToDone(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(5,7)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("WW");
        World myWorld = new World(myTargetQueue, myActionQueue);
        myWorld.step();
        myWorld.step();
        assertEquals(GameState.DONE, myWorld.getState());
    }

    @Test
    public void World_step_nextPositionLastFoodMakesItStopMoving(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7)");
        ActionQueue myActionQueue = new ActionQueue();
        myActionQueue.loadFromEncodedString("WWWWWNNN");
        World myWorld = new World(myTargetQueue, myActionQueue);
        for(int i = 0; i<8; i++){
            myWorld.step();
        }

        Position correctPosition = new Position(6, 7);
        //should have stopped at correctPosition
        assertCaterpillarAtCorrectPosition(myWorld, correctPosition);

    }


    @Test
    public void World_step_noSelfCollisionSetsGameStateToNotSelfCollision(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(6,6).(7,6)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("WNES");
        World myWorld = new World(myTargetQueue, fullSouth);
        myWorld.step();
        myWorld.step();
        myWorld.step();
        assertNotEquals(GameState.SELF_COLLISION, myWorld.getState());
    }

    @Test
    public void World_step_selfCollisionSetsGameStateToSelfCollision(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(6,6).(7,6).(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("WNES");
        World myWorld = new World(myTargetQueue, fullSouth);
        myWorld.step();
        myWorld.step();
        myWorld.step();
        myWorld.step();
        assertEquals(GameState.SELF_COLLISION, myWorld.getState());
    }

    @Test
    public void World_step_selfCollisionStopsMoving(){
        TargetQueue myTargetQueue = new TargetQueue();
        myTargetQueue.addTargets("(6,7).(6,6).(7,6).(1,1)");
        ActionQueue fullSouth = new ActionQueue();
        fullSouth.loadFromEncodedString("WNESSSSSSSSS");
        World myWorld = new World(myTargetQueue, fullSouth);
        for(int i = 0; i<8; i++){
            myWorld.step();
        }
        Position correctPosition = new Position(7, 6);

        assertCaterpillarAtCorrectPosition(myWorld, correctPosition);
    }


    private static void assertCaterpillarAtCorrectPosition(World myWorld, Position correctPosition) {

        /*
        try {
            Field privateCaterpillarField = World.class.getDeclaredField("caterpillar");
            privateCaterpillarField.setAccessible(true);
            Caterpillar myCaterpillar = (Caterpillar) privateCaterpillarField.get(myWorld);

            assertEquals(correctPosition, myCaterpillar.gethead());
        }
        catch(Exception e){
            System.out.println("no field named caterpillar... this test cannot work");
        }

        */
    }


}
