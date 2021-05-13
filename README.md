# Maze-Generator

It creates a window that draws a maze with rooms.

It starts with an array full of walls, then cuts out rooms, followed by a maze around the rooms. Then, it cuts out connections to each room, and optionally reduces the complexity of the maze.

Uses some outdated packages/functions

Upon running the code, it will ask a few prompts.

1. Enter scale of dungeon. (0 for large dungeons, 1 for small dungeons)<br />
This just modifies the scale the dungeon is drawn in
2. Enter size of dungeon. (x, y)<br />
This asks for the width followed by the height of the dungeon, separated by a space. Each dimension must be at least 13.
Mazes of size 50 by 50 or more have better results.
3. Enter attempted number of rooms.<br />
The code will loop the inputted number of times and attempt to place a room somewhere in the dungeon. 
If there is already an existing room at that location, it will give up that attempt.
4. Enter 0 for full maze, 1 for reduced dungeon<br />
Entering 0 will cause the code to end once the maze is generated. Entering 1 will make the code reduce the amount of paths and dead ends, to make it more like a dungeon connected by paths instead of a dungeon in a maze.

The code will then create the dungeon and print out a string of characters that corresponds to it.
