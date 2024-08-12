Data Format:
* Data files are stored in Data/[name of data set]/graph.txt
  * Small data sets (allsports, captchas, cora200, gym) are provided
* Delimiter is hard-coded in driver files
* For Correlation Clustering: 
  * The first line of the file must contain the total number of nodes (anything that follows it on the line will be ignored)
  * Rest of file lists positive edges as [node1] [node2]

Drivers
-------

Hard coded parameters:
* delimiter for data set ("\\s" for all examples here)
* number of Pivot rounds, uniform size constraints, etc. 
* Input: positive edge list

RunImprovedAlg.java
* Method: Ji et al. LP rounding for uniform constraints
* Requires Google OR-Tools (developers.google.com/optimization)

RunMaxKCorrelation.java
* Method: Pivot, Vote, Pivot and Adjust

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

Data Generation
---------------

WriteSyntheticGraph.java
* Code used to generate synthetic graphs
