# Game Plan
## Jack Ellwood, Yasser Elmzoudi

### Breakout Variation Ideas

### Interesting Existing Game Variations

 * Super Breakout is one of the variations I'm most familiar with.  I think having a variety of timed power-ups generally makes the game more replayable and interesting, since power-ups can be randomized which makes replaying through the same level more interesting depending on the power-ups that are dropped.  It also allows for some creativity in the designing the power-ups.

 * Game 2


#### Block Ideas

 * A block that takes multiple hits to break

 * A block that drops a random power up with a certain probability

 * A block that cannot be destroyed


#### Power Up Ideas

 * A multi-ball power-up

 * An extended paddle

 * A sticky paddle


#### Cheat Key Ideas

 * Spawn in extra balls

 * Infinite lives

 * Skip to a level

 * Take away a life


#### Level Descriptions

 * Level 1
   * [Simple Level](https://coursework.cs.duke.edu/compsci307_2020fall/game_team24/-/blob/master/data/testlevel.txt)

   * This is an example of a simple level with varying numbers of blocks on each row.
   Certain random blocks would have power-ups hidden inside, like in Super Breakout. The paddle and all balls would also move at a relatively normal pace.

 * Level 2
   * Block Configuration

   * Variation features

 * Level 3
   * Block Configuration

   * Variation features


### Possible Classes

 * Class 1
   * A `Paddle` class to instantiate a paddle and control its movement.

   * Maybe `moveRight()` and `moveLeft()` methods, or just a general `move()` method that takes a direction as a parameter.

 * Class 2
   * A `Ball` class to instantiate balls and control their physics and collisions.

   * A `hasContact()` method for if a ball is about to hit something.

 * Class 3
   * A `Brick` class to instantiate bricks and deal with holding power-ups and breaking apart.

   * The class could have an `isBroken()` method for when the brick has just been hit by the ball and its "health" is at zero, meaning it should despawn.

 * Class 4
   * A `Game` class to actually build the application and run the game

   * Would need methods like `setupScene()` and something like a `handleCollisions()` method to deal with
   collisions between objects.

 * Class 5
   * Purpose

   * Method
