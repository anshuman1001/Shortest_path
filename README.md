# Shortest_path
A* is a simple path finding algorithm. 
Goal is to find the shortest path from S to E. 

Algorithm Rules:
1. you may move only to cell denoted by '.'
2. cost for any single valid move is 1 
3. no diagonal moves
4. read map from a file 
5. code will be tested against several maps 
6. one example map and solution is given below

W - Obstacle or Wall
. - Clear Path, ie one can move to this cell
S - Start
E - End
* - Path taken
" - Visited

Map Sample

. . . . . . . . . . . . W . . . . . . . 
. . . . . . . . . . . . W . . . . . . . 
. . . . . . . W . . . . W . . . . . . . 
W . W W . . . W . . . . W . W . . . . . 
S . . W . . . W W W W w W . W . . . . E 
W . W . W . . . . . . . W . W . . . . . 
. . . . W . . . . . . . W . W . . . . . 
. . . . W . . . . . . . . . W . . . . . 
. . . . . W . . . . . . . . W . . . . . 
. . . . . . W . . . . . . . W . . . . . 



One possible path to E

" " " " " " " " " " " " W . . . . . . . 
" " " " " " " " " " " " W " " " . . . . 
" * * * * * * W " " " " W * * * * * " " 
W * W W " " * W " " " " W * W " " * * * 
S * " W " " * W W W W w W * W " . " . E 
W " W " W " * * * * * * W * W . . . . . 
" " " " W " " " " " " * W * W . . . . . 
" " " " W " " " " " " * * * W . . . . . 
" " " " " W " " " " " " " " W . . . . . 
" " " " " " W " " " " " " " W . . . . .
