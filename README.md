
#_OOP Project - Santa Claus is Coming to ACS Students: Stage 2_
_MARIA TEODOR_ -
_323CD_
---
#Project Structure
* common
  * **Constants** - stores project's constant values
* enums
  * **Category** - stores all possible categories
  * **Cities** - stores all possible cities
  * **CityStrategyEnum** - stores all possible strategies for sorting children
  * **ElvesType** - stores all possible elves
* fileio
  * input
    * **AnnualChangeInputData** - record of annual changes
    * **ChildInputData** - record of child information
    * **ChildUpdateInputData** - class for child updates
    * **GiftInputData** - record of gift information
    * **Input** - record of all the information received as input
    * **InputLoader** - record that populates an input instance
  * output
    * **OutputGenerator** - class that goes through years and generates JSONString to be written in 
    an output file
    * **Writer** - class that writes JSONObject in an output file
* gifts
  * **Gift** - object that represents a gift with its attributes
  * **GiftDatabase** - Singleton instance of a gift database
* main
  * **Main** - entry point for implementation, goes through all input files
* santa
  * averageScoreStrategies
    * **BabyStrategy** - strategy for computing the average nice score for children under 5 years 
    old
    * **KidStrategy** - strategy for computing the average nice score for children under 12 years
      old
    * **Strategy** -  interface implemented by baby, kid and teen strategies
    * **StrategyFactory** - factory that receives a child and determines its average nice score 
    based on its age
    * **TeenStrategy** - strategy for computing the average nice score for children under 19 years
      old
  * sortingStrategies
    * **IdComparator** - child comparator based on their ids
    * **NiceScoreComparator** - child comparator based on their nice score
  * **Child** - object that represents a child and its attributes
  * **Santa** - class that gives presents to children and stores current year information and future
  changes
* utils
  * **Utils** - implements useful methods
* year
  * **AnnualChange** - stores information concerning an annual change
  * **ChildUpdate** - stores information about the updates brought to a child over a year
  * **YearlyData** -  stores data regarding a year, and has a constructor that accepts a previous 
  year and an AnnualChange instance

#Implementation

The entry point into the program is the main class, which goes through all the input files in order 
to eventually generate the output. Then the input for each file is obtained through an **InputLoader** 
instance. The input is then taken over by a Santa object, which first stores information about the 
first year, the yearly changes to come, and the number of years the simulation runs for. 

The change from one year to the next happens in the YearlyData class, which has a constructor with 
this sole purpose. Each year, there are created new objects for each child, in order to be able to 
instantly eliminate young adults, change their age and implement child updates. In order to compute 
the average score of each child according to their age, I combined the Strategy and Factory patterns 
to select how it is computed. Then the bonus was added to obtain the final score. While the **Child** 
objects are replaced, the gift objects remain the same, the sole modification brought to them being 
the updates brought to the quantity field, when a gift is given to a child. The **YearlyData** is also 
where the budgetUnit is computed and the assignedBudget fields for all children are updated with 
their values, taking into account the black and pink elves. Through this class, the children list 
can be ordered by the niceScoreCity rule, since, in this case, a comparator could not be written 
given the fact that it implied information not contained in a Child object. For the other two 
child sorting strategies, I created two classes that implement the **Comparator** interface.

The **Santa** object is responsible for giving the gifts according to the rules, taking into account the
yellow elf. It contains a method entitled **executeYear**, which gives the gifts to the children.

Later on, the **executeYear** method is used in an **OutputGenerator** instance, which is responsible 
for going through all the years and generates a JSONObject to be written in the output file through 
a **Writer** object.

#Patterns
* **Builder** pattern - ChildUpdateInputData class - helpful due to the possible null value of the 
newNiceScore,
* **Factory and Strategy** patterns - StrategyFactory class implements a factory that generates 
strategies for computing the average score of each child,
* **Singleton** pattern - GiftDatabase is contains a Singleton instance, since there can only be one 
* database
***
#References
* [Assignment link](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2)
* [OCW link](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema) VideosDB homework assignment 
skeleton, which was used as inspiration for the classes responsible for using the input and writing 
the output. 
