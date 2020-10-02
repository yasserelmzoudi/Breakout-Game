# Simulation Design Final
### Names

Jack Ellwood (jce22), Yasser Elmzoudi (ye9)

## Team Roles and Responsibilities

 * Team Member #1: Jack Ellwood

 Worked primarily on implementing the Brick and PowerUp abstractions and their concrete classes.  Also implemented the Paddle and Display classes.

 * Team Member #2


## Design goals

The goal for this project was to make a playable game of Brick Breaker that can easily be added on to through the use of abstractions and subclasses.  Ultimately, we've created a base game that can have new features added while adding as litle code as possible.

#### What Features are Easy to Add

New power-ups, bricks, and levels are, in particular, easy to add. The abstract `Brick` and `Power-Up` abstractions make adding new bricks and power-ups as subclasses relatively easy, and levels can be added as a text file by simply following the naming convention of "levelN.txt", where N is the level number.


## High-level Design

#### Core Classes

`Game` is probably the most central class to our design, since `Game` "runs" the application and manages the movement and interactions of the various objects on screen.  Another essential class is `LevelLayout` which includes methods to build different block configurations from a text file.  Each unique game object also has its own class, including `Ball`, `Brick`, `Paddle`, and `PowerUp`, which include various methods for movement and collision that are called by `Game`.  In this way, the game object classes can contain methods that are relevant to their object while still being managed by the game.  We also have the `Display` class, which displays various status indicators related to the game.


## Assumptions that Affect the Design

One of our project's main assumptions is the format of the text files that represent the levels.  These need to be formatted as "levelN.txt", and furthermore, levels are activated in numerical order, so in order to switch the order of levels, the files themselves would have to be renamed.

#### Features Affected by Assumptions

Some other assumptions relate to the `Brick` and `Power-Up` abstractions, which have certain methods that either apply to or need to be implemented by subclasses.  `PowerUp`, for example, has a `fallFromDestroyedBlock()` method, which causes power-ups to fall (although they can also be spawned in by the power-up cheat).  If you wanted a power up that didn't fall, or gravitated toward the block, you might need to make some changes to the `PowerUp` abstraction.  Also, Bricks are denoted by single characters in a level text file.


## New Features HowTo

For adding new power-ups or bricks, simply create a new subclass of `PowerUp` or `Brick` and implement the necessary abstract methods.  Additionally, you'll have to go into the main `PowerUp` class and add your new power-up to `powerUpGenerator()` by giving a brick some random chance to drop your power-up.  Similarly for new bricks, you'll have to go into `LevelLayout` and add to the `brickBuilder()` method by giving your brick a character that signifies its appearance in a level text file.  For new levels, just create a new text file to layout your level and edit the maximum level in `checkBrickCollision()` and `brickBlockCheat()` in the `Game` class.  While `Paddle` and `Ball` are not abstract, if you wanted to add different types of paddles and balls, we might recommend making those classes abstract and creating subclasses from there, although that would potentially require a lot of editing in the main `Game` class.

#### Easy to Add Features

As mentioned above, bricks and power-ups are relatively easy to add, as are new levels.

#### Other Features not yet Done

Some new, interesting features to add might be new balls or paddles, which would be more difficult than adding bricks or power-ups but still feasible.  Utilizing the `Display` class to add new splash screens would also be interesting.
