# Game Plan
## Jack Ellwood, Yasser Elmzoudi

### Breakout Variation Ideas

### Interesting Existing Game Variations

 * Game 1

 Super Breakout is one of the variations I'm most familiar with.  I think having a variety of timed power-ups generally makes the game more replayable and interesting, since power-ups can be randomized which makes replaying through the same level more interesting depending on the power-ups that are dropped.  It also allows for some creativity in the designing the power-ups.

 * Game 2


#### Block Ideas

 * Block 1

 A block that takes multiple hits to break.

 * Block 2

 * Block 3


#### Power Up Ideas

 * Power Up 1

 A multi-ball power-up.

 * Power Up 2

 Extended paddle.

 * Power Up 3


#### Cheat Key Ideas

 * Cheat Key 1

 Spawn in extra balls.

 * Cheat Key 2

 Infinite lives.

 * Cheat Key 3

 * Cheat Key 4


#### Level Descriptions

 * Level 1
   * Block Configuration

   * Variation features

 * Level 2
   * Block Configuration

   * Variation features

 * Level 3
   * Block Configuration

   * Variation features


### Possible Classes

 * Class 1
   * Purpose

   A `Paddle` class to instatiate a paddle and control its movement.

   * Method

   Maybe `moveRight()` and `moveLeft()` methods, or just a general `move()` method that takes a direction as a parameter.

 * Class 2
   * Purpose

   A `Ball` class to instantiate balls and control their physics and collisions.

   * Method

   A `hasContact()` method for if a ball is about to hit something.

 * Class 3
   * Purpose

   A `Brick` class to instantiate bricks and deal with holding power-ups and breaking apart.

   * Method

   The class could have an `isBroken()` method for when the brick has just been hit by the ball and its "health" is at zero, meaning it should despawn.

 * Class 4
   * Purpose

   * Method

 * Class 5
   * Purpose

   * Method
