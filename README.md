#ex2
Hello to the The Challenge Pokemon Game

General info about the project-
This task is divided into 2 parts, first part, similar to previous tasks, but in this time we create directed and weighted graph.
The second part is the pokemon game, we put on the graphs pokemons and agents that moves all over the graph, in one direction each time, the agents needs to eat the pokemons to collect grade. 
About the first part-
DWGraph_DS class :
This class build a graph that defined by nodes and edges.
Nodes- collection of HashMap of nodes in the graph.
Edges- collection of HashMaps and maps
Edgessi- collection of HashMaps and maps
EdgeSize - is count of the edges in the graph.
MC - is a count of the changes that implement on the graph.
DWGraph_Algo class:
In this class we have functions that helps us to find things like:
If the graph is connected, what is the shortest path, and shortest path distance, init the graph, save and load from files.
IsConnected: To find if the graph is connected I used the dfs algorithm, and because it's a directed graph I checked it twice from to directions, if from the src I can get to each edge, and if from each edge I can get to the src.
shortestpath -Finding the shortest route in a weighted and deliberate graph I used bfs algorithm.
Edge class-
This class build single edge.
An object from class Edge contain the follow feature:
•	Src - the key of tne node that represent the source.
•	Dest - the key of tne node that represent the destination.
•	Tag - its flag that change when pass on edge.
•	Weight - how much cost to pass on this edge in the path.
•	Info-String
Node class:
This class build single node. An object from class Node contain the follow feature:
•	Key - it the ID of this node in the graph.
•	Tag - its flag that change when pass on node.
•	Weight - it represent the cost of the path that take to get from Src to this node that represent the Dest.
•	Location - represent the locatiobn of the node on the axis - X, Y, Z.
•	Info-String

Game Client-
In this part we have the classes that build the game:
CL_Pokemon class:
In this class we get all the details about a single pokemon in the game.
•	Pos - represent the locatiobn of the pokemon on the axis - X, Y, Z.
•	Value - represent the point that the pokemon is worth.
•	Type - represent if the pokemon is on up edge or down edge.
•	edge - represent the deatination of the robot
CL_Agent class:
In this class we get all the details about a single agent in the game.
The class build with this functions:
•	Pos - represent the locatiobn of the robot on the axis - X, Y, Z.
•	Value - represents the points earned by the agent, gives us our grade in the end of the game.
•	Id – every agent has ID.
•	curr_edge - represent the destination node that the agent go to.
•	Speed - represent the speed of the agent, get higher every pokemon he eats.
Arena class:
In this class we get the lists (in LinkedList) the agents and the pokemons we get in the game.
MyFrame class:
This class responsible for drawing all game data and the graphics to user with help class Jframe. 
We draw the pokemons, agents, nodes and edges.
Ex2 class-
In this class we have all the algorithm and the main that maks the game work.
The funcions we have in this class:
•	moveAgants	- we want to make the agents move foreword to the closet pokemon he will find, this function helps us "tell" the agent to move by collecting them first, and "tell" them to which edge go.

•	findEdge – to make the game more useful and get the highest grade we need first to find where are the pokemons on the graph, this function helps us to find the pokemons in the game by using HashSet, we search on the graph and save all the pokemons we found.

•	nextNode- this function helps us to find the shortest path to the next pokemons, then the agent can go to the closet pokemon that he finds.
We collect all the "GeoLocation" we found to every pokemon and then we search the shortest path to that point.

•	Jsontograph- in this function we take all the details we need for the game, the graph we play, agent ID, and where we want to put our agent.

•	Init – we init the game every time the game stops, he init the graph, the pokemons, and the agent every time.



About the Game- 
Levels: 0-23
The goal: eat the most pokemons you can and collect the highest grade with the minimum moves.
Characters in the game: Pokemons and Agent.
How to play?
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
Prerequisites:
•	JDK-11.0.4
•	Eclipse
•	IntelliJ

What I need to do to play?
first you need to Clone that project to your workspace directory, by copy our link of our git, or download that as a zip.
Then you need to run Ex2 class,
A window will show up that will ask from you to enter your ID,
Then you need to choose the level you want to play (you have 0-23 levels.)
And then the game will begin!
When the time ends your grade will save and you can play over and over again until your grade will get higher.
GOOD LUCK AND ENJOY!
