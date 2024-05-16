Data Format:
* Data files are stored in Data/[name of data set]/graph.txt
  * Small data sets (allsports, captchas, cora200, gym) are provided
  * Large data sets (amazon, dblp, livejournal, orkut, youtube) are publicly available at snap.stanford.edu/data/#communities
* Delimiter is hard-coded in driver files
* For Correlation Clustering: 
  * The first line of the file must contain the total number of nodes (anything that follows it on the line will be ignored)
  * Rest of file lists positive edges as [node1] [node2]

To Compile: javac *.java
To Run: java [DriverName] [data set folder name]
* additional heap space may be needed for some experiments; increase the max heap size with the -Xmx flag

Drivers
-------

Hard coded parameters:
* delimiter for data set ("\\s" for all examples here)
* number of Pivot rounds, uniform size constraints, etc. 
* Input: positive edge list

RunImprovedAlg.java
* Method: Ji et al. LP rounding for uniform constraints
* Requires Google OR-Tools (developers.google.com/optimization)

RunMaxKTimed.java
* Method: Pivot, Vote, PLS, VLS for uniform constraints with 2-minute LS time limit

RunPmAlg.java
* Method: Puleo and Milenkovic LP rounding for uniform constraints
* Requires Google OR-Tools (developers.google.com/optimization)

RunUniformILP.java
* Method: ILP solver for uniform constraints
* Requires Google OR-Tools (developers.google.com/optimization)

Code Files
----------

DNode.java
* Implementations of Vote

Helper.java
* Helper functions for reading data sets

Pair.java
* Custom data structure used for heap implementation

PKwik.java
* Pivot algorithm implementations
