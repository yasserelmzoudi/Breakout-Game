# Game Plan
## Jack Ellwood, Yasser Elmzoudi

### Breakout Variation Ideas

### Interesting Existing Game Variations

 * [Super Breakout](https://www.youtube.com/watch?v=bHQKoESsS80) is one of the variations that we are the most familiar with. We think that having a variety of timed power-ups generally makes the game more replayable and interesting, since power-ups can be randomized which makes replaying through the same level more interesting depending on the power-ups that are dropped. This also allows for some creativity in the designing the power-ups.

 * [Bricks n Balls](https://www.youtube.com/watch?v=n7alxnfUhCE) was another variation that stood out to us and that we found to be very interesting. In conjunction with the "puzzle game feel" that it creates, this variate would work well coupled with our idea of a "disappearing" brick that can only be destroyed at certain times as it allows for players to feel more in control of the ball's movement and enhances the skill required for the game. 


#### Block Ideas

 * A brick that takes multiple hits to break

 * A brick that drops a random power up with a certain probability

 * A brick that cannot be destroyed
 
 * A brick that only can be destroyed if it is hit at a certain time and blinks or is invisible if it cannot be hit


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
     * [Intermediate Level](https://coursework.cs.duke.edu/compsci307_2020fall/game_team24/-/blob/master/data/testlevel2.txt)

     * This is an example of an intermediate level with varying numbers of blocks on each row. Some of these blocks, denoted by a capital `X`, represent blocks that cannot be destroyed. 
   Certain random blocks would have power-ups hidden inside, like in Super Breakout. The paddle would move at a slower pace and all balls would move at a faster pace than in the simple level.

 * Level 3
     * [Advanced Level](https://coursework.cs.duke.edu/compsci307_2020fall/game_team24/-/blob/master/data/testlevel3.txt)
 
     * This is an example of an advanced level with varying numbers of blocks on each row. Some of these blocks, denoted by a capital `X`, represent blocks that cannot be destroyed. Other blocks, denoted by an `i`, represent blocks that can only be destroyed if hit at a certain time.
    Certain random blocks would have power-ups hidden inside, like in Super Breakout. The paddle would move at an even slower pace and all balls would move at an even faster pace than in the intermediate level.


### Possible Classes

 * Class 1
   * A `Paddle` class to instantiate a paddle and control its movement

   * Maybe `moveRight()` and `moveLeft()` methods, or just a general `move()` method that takes a direction as a parameter

 * Class 2
   * A `Ball` class to instantiate balls and control their physics and collisions

   * A `hasContact()` method for if a ball is about to hit something

 * Class 3
   * A `Brick` abstract class to instantiate bricks and deal with holding power-ups and breaking apart

   * The class could have an `isBroken()` method for when the brick has just been hit by the ball and its "health" is at zero, meaning it should "despawn"

 * Class 4
   * A `Game` class to actually build the application and run the game

   * Would need methods like `setupScene()` and something like a `handleCollisions()` method to deal with
   collisions between objects

 * Class 5
   * A Pages interface to handle the different screens to be displayed (instructions, different levels, results)

   * Would have methods similar to `step()` which would step through that Page's actions, and `initialize()` which would instantiate that Page's Objects
