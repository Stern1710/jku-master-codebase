/*
 Assignment solution of
 Simon Sternbauer
 k11812499
 k11812499@students.jku.at
*/

module alternative_assignment_solutions

import StdEnv 

//-----------------

//// Choose 1 from numerical exercises

//-----------------

/* 1. Armstrong number
 If sum of cubes of each digit of the number is equal to the number itself,
 then the number is called an Armstrong number.
 153 = 1^3 + 5^3 + 3^3
 Given a positive integer number, write a function to determine whether it is
 an Armstrong number or not.
*/

splitAdd :: Int -> Int
splitAdd x 
| x == 0 = 0
= (x rem 10)^3 + splitAdd (x/10)

armstrong :: Int -> Bool
armstrong x = x == splitAdd x

//Start = armstrong 153 // True
//Start = armstrong 370 // True
//Start = armstrong 0 // True
//Start = armstrong 12 // False

/* 2. Collatz conjecture

 Given a positive number greater than 1, return how many iterations does it 
 take for that number to fall down to "2" if we keep applying the
 Collatz equation on it.
 Collatz conjecture equation:
 If the number is even -> x/2
 If the number is odd -> 3x+1
 e.g: input: 10 
      steps: 10 -> 5 -> 16 -> 4 -> 2
      output: 4 recursive calls
*/

helper :: Int Int -> Int
helper num c
| num == 2 = c 
| (num rem 2) == 0 = helper (num/2) c+1
= helper (3*num + 1) c+1

collatz :: Int -> Int
collatz x
| x < 1 = abort "The number must be greater than 1"
| x == 2 = 0 //Special case as helper 2 would result in -1 (due to offset for first call of helper
= helper x -1 //-1 to account for first iteration technically not counting towards iterations
			  // After the first call, helper is 0 when passed on to the first iteration

//Start = collatz 2
//Start = collatz 10 // 4
//Start = collatz 20000000 // 144
//Start = collatz 5 // 3
//Start = collatz 0 // "The number must be greater than 1"

/* 4. CoPrimes

 Given 2 numbers, check if they are co-prime.
 Numbers are called co-prime if they do not have
 common divisor.
*/

coPrimes :: Int Int -> Bool
coPrimes a b = ((gcd a b) <= 1)

//Start = coPrimes 12 9 // False
//Start = coPrimes 12 12 // False
//Start = coPrimes 12 13 // True
//Start = coPrimes 5 7 // True


//-----------------

///// Choose 1 from list processing

//-----------------

/* 3. Not Palindrome

 Given a list of lists of integers,
 write a function that gets rid of Palindrome numbers.
 A palindrome number is a number that can be read from left to right or
 from right to left and gets the same number, 
 e.g. 12521 is a palindrome number. 
*/

//Extracts every single digit and adds it to a list
toList :: Int -> [Int]
toList x
| x < 10 = [x]
= [(x rem 10)] ++ toList (x/10)

//Compares if list of digits and the reverse are the same
canStay :: Int -> Bool
canStay x
| x < 10 = True
= (toList x) <> (reverse (toList x))

//Filters out all palindroms
remover :: [Int] -> [Int]
remover [] = []
remover list = filter canStay list

//Iterates over the list and applies the remove filter to it
getRidPal :: [[Int]] -> [[Int]]
getRidPal list = map remover list

//Start = getRidPal [[1,2,1111],[151,22,3455]] // [[1,2],[3455]]
//Start = getRidPal [[1,222],[151,202,50505]] // [[1],[]]
//Start = getRidPal [[],[22]] // [[],[]]

/* 4. List ends

 Given a list of lists, append to the end of every sublist 
 the sum and the length of the sublist
*/

appender :: [Int] -> [Int]
appender [] = [0,0]
appender list = list ++ [sum list] ++ [length list]

append :: [[Int]] -> [[Int]]
append list = map appender list

//Start = append [[1..5],[1..4],[],[5,6]]  // [[1,2,3,4,5,15,5],[1,2,3,4,10,4],[0,0],[5,6,11,2]]
//Start = append [[(-1),(-2)..(-10)],[12],[5]]  // [[-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-55,10],[12,12,1],[5,5,1]]
//Start = append []  // []

/* 5. Fractions
 Given a list of real numbers, keep only the fraction part of the number
*/

decreaser :: Real -> Real
decreaser x
| x < 1.0 = x
| otherwise = decreaser (x - 1.0)

fraction :: [Real] -> [Real]
fraction [] = []
fraction list = map decreaser list

//Start = fraction [1.2,1.5,0.6] //[0.2,0.5,0.6]
//Start = fraction [1.25, 8.2115548896, 53.21,45.58,0.005] //[0.25,0.2115548896,0.21,0.58,0.00005]
//Start = fraction [] // []

/* 11. Symmetrical lists

 Given a list of lists of integers, write a function 
 which returns a list of symmetrical lists, 
 if the sum of the sublist is greater than 10.
*/

notEmpty :: [Int] -> Bool
notEmpty x = x <> []

mapper :: [Int] -> [Int]
mapper list
| sum list > 10 = list ++ reverse list
= []

symSumGreater10 :: [[Int]] -> [[Int]]
symSumGreater10 list = filter notEmpty (map mapper list)

//Start = symSumGreater10 [[1,2,3],[3,4,5,6],[4,5,1,2]] // [[3,4,5,6,6,5,4,3],[4,5,1,2,2,1,5,4]]
//Start = symSumGreater10 [] // []
//Start = symSumGreater10 [[1..10],[1,2]] // [[1,2,3,4,5,6,7,8,9,10,10,9,8,7,6,5,4,3,2,1]]

//-----------------

//// Choose 1 from arrays or records

//-----------------

/*
	Task 4:
		 Given a list of Companies, return the companyNames of all the good companies in the list,
		 a company is considered to be good if the annual salary of every employee is at least:
				100.0 BSC holder,
				200.50 MSC holder,
				and 500.64 PHD holder. 
*/

:: Degree =   BSC | MSC | PHD     
:: Company = {companyName :: String, numberOfEmloeeys :: Int, employees :: {Employee}} 
:: Employee = { name1 :: String,
                salary :: {Real}, //  array containing the salary of an employee for 12 months 
                age :: Int, 
                degree :: Degree   
              }

e1 :: Employee
e1 = {name1 = "e1", salary = {10.0,20.0,30.0,40.0,50.0,10.0,20.0,30.0,40.0,50.0,10.0,90.0}, age = 40, degree = BSC}
e2 :: Employee
e2 = {name1 = "e2", salary = {5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0}, age = 30, degree = BSC}
e3 :: Employee
e3 = {name1 = "e3", salary = {50.0,20.0,20.0,20.0,20.0,20.0,20.0,20.0,20.0,20.0,20.0,20.0}, age = 60, degree = MSC}
e4 :: Employee
e4 = {name1 = "e4", salary = {5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0}, age = 45, degree = MSC}
e5 :: Employee
e5 = {name1 = "e5", salary = {100.0,110.0,110.0,40.0,50.0,30.0,30.0,30.0,140.0,50.0}, age = 80, degree = PHD}
e6 :: Employee
e6 = {name1 = "e6", salary = {5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0}, age = 45, degree = PHD}

cm1 :: Company
cm1 = {companyName = "cm1", numberOfEmloeeys = 3, employees = {e1,e3,e5}}
cm2 :: Company
cm2 = {companyName = "cm2", numberOfEmloeeys = 2, employees = {e1,e2}}
cm3 :: Company
cm3 = {companyName = "cm3", numberOfEmloeeys = 2, employees = {e1,e3}}
cm4 :: Company
cm4 = {companyName = "cm4", numberOfEmloeeys = 3, employees = {e1,e3,e5}}
cm5 :: Company
cm5 = {companyName = "cm5", numberOfEmloeeys = 3, employees = {e2,e3,e6}}

//maps the different degrees to an Integer
instance toInt Degree
where
	toInt BSC = 1
	toInt MSC = 2
	toInt PHD = 3

//Checks if sale is adequate for an employee
adequateSalery :: Employee -> Bool
adequateSalery emp
| (toInt emp.degree) == (toInt BSC) = sal >= 100.0
| (toInt emp.degree) == (toInt MSC) = sal >= 200.5
| (toInt emp.degree) == (toInt PHD) = sal >= 500.64
= False
where
	sal = sum [elem \\ elem <-: (emp.salary)]

//Removes bad companies out (bad = employes with adequate salaries < number of employees)
isGood :: Company -> Bool
isGood comp = (length (filter adequateSalery emps)) >= (comp.numberOfEmloeeys)
where
	emps = [elem \\ elem <-: (comp.employees)]

getName :: Company -> String
getName c = c.companyName

goodCompanies :: [Company] -> [String]
goodCompanies [] = []
goodCompanies comp = map getName (filter isGood comp)

//Start = goodCompanies [cm1,cm2,cm3]  // ["cm1","cm3"]
//Start = goodCompanies [cm1,cm2,cm3,cm4,cm5] // ["cm1","cm3","cm4"]
//Start = goodCompanies [cm2,cm5] // []
//Start = goodCompanies [cm1,cm3,cm4] // ["cm1","cm3","cm4"]

//-----------------

//// Choose 1 from trees

//-----------------

/* Task 1:
	Given the rose tree and depth of the level we want to delete,
	remove all elements at that level. Children nodes of the deleted
	node should become children of its parent (hence push up by 1 level).
	For example, if we have a tree:
                      1                               Level: 0
                   /     \
                  2       3                           Level: 1
               /  |  \    |  \                   
              4   5  6    7   8    <-- Row to delete. Level: 2
            / | \   / \   | \  \ 
           9 10 11 12 13 14 15 16                     Level: 3

	After deleting 2nd level row, nodes (4,5,6,7 and 8) are deleted
	and their children (9,10,11,12,13,14,15 and 16) become children of
	the 1st level row (2 and 3). Hence we get a new tree:
                      1                               Level: 0
                  /       \
                 2          3                         Level: 1
            / |  |  | \   / | \
           9 10 11 12 13 14 15 16                     Level: 2

	NOTE: Levels start from 0 not 1. You are guaranteed that you are
	never asked to delete the first row (Level 0).
*/

:: RoseTree a = TNode a [(RoseTree a)]

tree1 = TNode 5 []
tree2 = TNode 3 [(TNode 2 [(TNode 3 [])]), tree1, tree1]
tree3 = TNode 1 [tree1, tree2, tree2]

rebuildChildren :: ((RoseTree a), Int, Int) -> [(RoseTree a)]
rebuildChildren ((TNode num kids), lvl, cur) 
| lvl == cur = kids
= [(TNode num (foldr (++) [] (map rebuildChildren nextLevel)))]
where
	nextLevel = [(singleKid, lvl, cur+1) \\ singleKid <- kids]

deleteRow :: Int (RoseTree a) -> (RoseTree a) 
deleteRow lvl (TNode num kids) = (TNode num (foldr (++) [] (map rebuildChildren nextLevel)))
where
	nextLevel = [(singleKid, lvl, 1) \\ singleKid <- kids]

//Start = deleteRow 5 tree1 // (TNode 5 [])
//Start = deleteRow 1 tree2 // (TNode 3 [(TNode 3 [])])
//Start = deleteRow 1 tree3 // (TNode 1 [(TNode 2 [(TNode 3 [])]),(TNode 5 []),(TNode 5 []),(TNode 2 [(TNode 3 [])]),(TNode 5 []),(TNode 5 [])])
//Start = deleteRow 2 tree3 // (TNode 1 [(TNode 5 []),(TNode 3 [(TNode 3 [])]),(TNode 3 [(TNode 3 [])])])


/* 
	Task 2:
		Flex Tree can have 4 types of nodes: Ternary, Binary, Unary and Terminal. As
		names suggest these nodes have 3, 2, 1 and 0 children nodes respectively. 
		Terminal nodes do not store any value, they indicate end of the tree.

		Write a function that takes a flex tree as an argument and returns pruned 
		flex tree back. Pruning a tree means changing its node types based on the
		unused children. For example: If you have a ternary node that has exactly 1
		TerminalNode, it should be deprecated to binary node, as having a terminal node
		does not make sense and it is waste of space. If ternary node has exactly 2 terminal
		nodes it should be deprecated to UnaryNode. If ternary node has exactly 3 terminal
		nodes than it is still deprecated to UnaryNode (we can't change it to terminal because 
		the value stored in the ternary node should be preserved) which will have 1 terminal 
		node as a child. Same rules apply to binary node as well, if it has 1 or 2 
		terminal nodes, it should be deprecated to UnaryNode.

		Your function should return a new FlexTree after all unnecessary TerminalNodes are
		removed and nodes are deprecated as needed. Hence the tree is pruned.

For example, if we have a FlexTree:

                 (TernaryNode 1)
            /          |         \
    (BinaryNode 2) TerminalNode (UnaryNode 3)
    /          \                       |            
TerminalNode (UnaryNode 4)        (BinaryNode 5)
                  |                 /           \
             TerminalNode       TerminalNode  TerminalNode


After pruning it should look like this:
                 (BinaryNode 1)
                /               \
        (UnaryNode 2)        (UnaryNode 3)
             |                    |            
        (UnaryNode 4)        (UnaryNode 5)
             |                    |           
        TerminalNode          TerminalNode  
*/

:: FlexTree a = TernaryNode a (FlexTree a) (FlexTree a) (FlexTree a)
              | BinaryNode a (FlexTree a) (FlexTree a)
              | UnaryNode a (FlexTree a)
              | TerminalNode

ftree1 = UnaryNode 1 (BinaryNode 2 TerminalNode TerminalNode)
ftree2 = BinaryNode 1 TerminalNode ftree1
ftree3 = TernaryNode 3 TerminalNode (UnaryNode 3 TerminalNode) (UnaryNode 3 TerminalNode)
ftree4 = TernaryNode 1 ftree2 TerminalNode (BinaryNode 2 (TernaryNode 1 TerminalNode TerminalNode TerminalNode) (BinaryNode 2 ftree2 ftree3))

//Converts each node to a number for comparison
instance toInt (FlexTree a)
where
	toInt TerminalNode = 1
	toInt (UnaryNode num c1) = 2
	toInt (BinaryNode num c1 c2) = 3
	toInt (TernaryNode num c1 c2 c3) = 4
	
//Makes comparisons based on toInt possible
instance == (FlexTree a)
where
	(==) t1 t2 = (toInt t1) == (toInt t2)

//Prunes tree according to Children of nodes
pruneTree :: (FlexTree a) -> (FlexTree a)
pruneTree (TerminalNode) = TerminalNode
pruneTree (UnaryNode num c1) = (UnaryNode num (pruneTree c1))
pruneTree (BinaryNode num c1 c2)
| c1 == TerminalNode && c2 == TerminalNode = (UnaryNode num TerminalNode)
| c1 == TerminalNode = (UnaryNode num (pruneTree c2))
| c2 == TerminalNode = (UnaryNode num (pruneTree c1))
= (BinaryNode num (pruneTree c1) (pruneTree c2))
pruneTree (TernaryNode num c1 c2 c3) 
| c1 == TerminalNode && c2 == TerminalNode && c3 == TerminalNode = (UnaryNode num TerminalNode)
| c1 == TerminalNode && c2 == TerminalNode = (UnaryNode num (pruneTree c3))
| c1 == TerminalNode && c3 == TerminalNode = (UnaryNode num (pruneTree c2))
| c2 == TerminalNode && c3 == TerminalNode = (UnaryNode num (pruneTree c1))
| c1 == TerminalNode = (BinaryNode num (pruneTree c2) (pruneTree c3))
| c2 == TerminalNode = (BinaryNode num (pruneTree c1) (pruneTree c3))
| c3 == TerminalNode = (BinaryNode num (pruneTree c1) (pruneTree c2))
= (TernaryNode num (pruneTree c1) (pruneTree c2) (pruneTree c3)) 

//Start = pruneTree TerminalNode // TerminalNode
//Start = pruneTree ftree1 // (UnaryNode 1 (UnaryNode 2 TerminalNode))
//Start = pruneTree ftree2 // (UnaryNode 1 (UnaryNode 1 (UnaryNode 2 TerminalNode)))
//Start = pruneTree ftree3 // (BinaryNode 3 (UnaryNode 3 TerminalNode) (UnaryNode 3 TerminalNode))
//Start = pruneTree ftree4 // (BinaryNode 1 (UnaryNode 1 (UnaryNode 1 (UnaryNode 2 TerminalNode))) (BinaryNode 2 (UnaryNode 1 TerminalNode) (BinaryNode 2 (UnaryNode 1 (UnaryNode 1 (UnaryNode 2 TerminalNode))) (BinaryNode 3 (UnaryNode 3 TerminalNode) (UnaryNode 3 TerminalNode)))))


//-----------------

//// Choose 1 from instances or classes or enumeration type

//-----------------

:: Major = CS | Math | Physics | Linguistics
:: Course = {name::String, major :: Major, credits:: Int, max_student:: Int}

Programming::Course
Programming = {name="Programming", major=CS, credits=5, max_student=50}

Functional::Course
Functional = {name="Functional", major=CS, credits=5, max_student=120}

Imperative::Course
Imperative = {name="Imperative", major=CS, credits=4, max_student=150}

Analysis::Course
Analysis = {name="Analysis", major=Math, credits=4, max_student=85}

BasicMath::Course
BasicMath = {name="BasicMath", major=Math, credits=2, max_student=40}

Hungarian::Course
Hungarian = {name="Hungarian", major=Linguistics, credits=3, max_student=20}

/* 
	Task 1:
		Create an instance `+` for the record Course, 
		such that for two records C1 and C2 when added (C1+C2) will give
 		a record Course where:
 			- name is the concatenation of the first letter in C1's and C2's names.
 			- major is the course with the greatest max_student.
 			- credits is the greatest credit if courses have same major,
 				or the average of credits, if the courses have different major (you don't have to use real number for average)
 			- max_student is C1's max_student plus C2's max_student.
*/
//Maps each major to an integer
instance toInt Major
where
	toInt CS = 1
	toInt Math = 2
	toInt Physics = 3
	toInt Linguistics = 4

//Constructor for a new course
mkCourse :: String Major Int Int -> Course
mkCourse n m c max = {name=n, major=m, credits=c, max_student=max}

//Gets first character of each course name and adds them together
initials :: Course Course -> String
initials c1 c2 = toString(c1.name.[0]) +++ toString(c2.name.[0])

//Checks which course has more students
greaterCourse :: Course Course -> Major
greaterCourse c1 c2
| c1.max_student > c2.max_student = c1.major
= c2.major

//Gets credits, larger one when same major, average when different majors
getCredits :: Course Course -> Int
getCredits c1 c2
| (toInt c1.major) == (toInt c2.major) && c1.credits > c2.credits = c1.credits
| (toInt c1.major) == (toInt c2.major) = c2.credits
= (c1.credits + c2.credits)/2

//Sum of students from two courses
getStudents :: Course Course -> Int
getStudents c1 c2 = c1.max_student + c2.max_student

instance + Course
where
	(+) c1 c2 = mkCourse (initials c1 c2) (greaterCourse c1 c2) (getCredits c1 c2) (getStudents c1 c2)

//Start = Functional + Imperative // (Course "FI" CS 5 270)
//Start = BasicMath + Hungarian // (Course "BH" Math 2 60)
//Start = Analysis + Programming // (Course "AP" Math 4 135)
//Start = Imperative + BasicMath // (Course "IB" CS 3 190)