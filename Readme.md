Welcome to BugSweeper!

The term 'bug' has long been used to describe a software error. The famous reason for this comes from the Harvard Mark II, which in 1947 was maligned with a real-life moth stuck to the circutry. 

While the purpose of our game is to isolate and destroy bugs, it turns out there ARE BUGS in our game code!

With these bugs, our game won't work properly! It's up to you, noble coder to find and eradiacte the bugs so our game will finally be fun to play!

Let's review a few de-bugging strategies!

A) The use of System.out.println() is an excellent way to see what our code is doing.
B) We can use the 'debugger' to step through code. 
    - Clicking on a number on the side of a code file adds a red 'Breakpoint'
    - When you run in debug mode, the code will stop at your breakpoint, allowing you to analyze values.
    - When stopped at a breakpoint, you can go up the stack to see where that method was called from.

Right away when running the program, you'll encounter several issues:

1) Our program isn't running at all! It's crashing upon startup. At least someone was kind enough to flag our first bug in Grid.java

Check other places where the grid is accessed to see which approach is the intended one!

Phew! Now we have the game running at least. When we look at the board, we see '.' for unexplored tiles and '*' for our bugs.

2) If you run the project a few times, you'll notice the number of bugs seems inconsistent.

We're assigning "final int bugs = 8;" in Bugsweeper.java, but some of the times we run, we have less than eight bugs appearing.

This looks like a good place to use System.out.println to make sure we're creating the right number of bugs. See if you can tell what's going on from the output and find a solution!

Once you consistently have the right number of bugs appearing, it's time to play the game. Unfortunately...

3) Our bugs are already visible! Hmm...I wonder if Grid.printGrid is going to be a good place to start?

You can place a breakpoint at the start of the method and verify we're getting the right argument value passed in. When stopped at the breakpoint, you can back up the stack to see where it's getting called from.

Think about all the places that printGrid is called from. They don't all need to be fixed, so use your best judgement!

Now the game works! Yay!

Hacker Challenge: In Grid.java, there is a comment for the hacker challenge. Can you break recursion and cause a stack overflow? Why does this happen?