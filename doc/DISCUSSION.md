## Lab Discussion
### Team 24
### Yasser Elmzoudi (ye9), Jack Ellwood (jce22)


### Issues in Current Code

#### Method or Class
 * Design issues
 
  - A few magic numbers that can be made constants
      - Game: Line 188
      - Game: Line 162
  - "if" and "for" statements that are nested too deeply than can be refactored and made their own methods
      - Game: Line 170
      
 * Design issue
 
   - Checking for cheat codes can be broken up into various methods called from switch statement
       - Game: Line 128
   - PowerUp activate() should eventually not take the entire Game as a parameter
       - PowerUp: Line 69
 

#### Method or Class
 * Design issues
 
 - Useless imports that can be removed
     - Game: Line 24
     - PowerUp: Line 3
     - PowerUp: Line 4
     - PowerUp: Line 6

 * Design issue
 
  - Program should throw Exception when reading Blocks from file
      - Game: Line 104
  - Variable names should not include "my" 
      - Ball: Line 18

### Refactoring Plan

 * What are the code's biggest issues?
 
 Overall, having too many complex methods, many of which have nested for loops/if statements.

 * Which issues are easy to fix and which are hard?
 
 Our complex methods shouldn't be too hard to fix, since most of them will only
 require taking each loop or if statement out and putting them in their own methods.
 One of our harder issues will be our PowerUp class' `activate()` method, which currently
 takes an entire game object as its input.  This is definitely too much state to pass
 into the method, so we'll have to figure out what parameters will need to be passed
 once we finalize our power-up types.

 * What are good ways to implement the changes "in place"?

For dealing with complex methods, simply adding new helper methods will be a good way
to reuse code without having to completely alter what we already have.

### Refactoring Work

 * Issue chosen: Fix and Alternatives
 
 Refactoring power-ups. Using a switch statement to decide which power-up method to call
 rather than calling each power-up method and having an if statement inside each one.

 * Issue chosen: Fix and Alternatives

Refactoring the `buildBlocksFromFile()` method. We will refactor nested for loops and
generally make new helper methods by grouping code with similar functionality.
