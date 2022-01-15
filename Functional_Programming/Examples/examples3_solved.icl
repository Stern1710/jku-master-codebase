module examples3_solved
import StdEnv

/////////////////////////////

/* 1.
	Create a class MyMultDiv which has the operations *~ , /~ and has the neutral element as
	myOne. 
	So given two elements apply multiplication and division:
	*~ -> multiplication
	/~ -> division
	After that create an instace for Char.
	Hint: Operations in Char can be done on their integer representations and then convert back to string
	Be careful when you multiply and divide to stay in the range of 255 (you can use modulo)
*/
class MyMultDiv a
where
	 (*~) :: a a -> a
	 (/~) :: a a -> a
	 myOne :: a 

instance MyMultDiv Char
where 
	(*~):: Char Char -> Char
	(*~) a b = toChar ((toInt(a) * toInt(b)) rem 255)
	
	(/~):: Char Char -> Char
	(/~) a b = toChar ((toInt(a) / toInt(b)) rem 255)

	myOne :: Char
	myOne = toChar 1
	
Start = 'a' *~ 'b' //'G'
//Start = 'k' *~ myOne //'k'
//Start= 'z' /~ 'A' //'' //''


::University={uniName::String,students::[Student],teachers::[Teacher]}
::Teacher={tname::String,subject::String}
::Student={studentName::String,age::Int,grades::{Int},favoriteTeacher::Teacher}
ELTE::University
ELTE={uniName="ELTE",students=[Marko,Nikola,Josh,Dame],teachers=[Mary,Peter,John]}
BME::University
BME={uniName="BME",students=[Ana,Josh,Sofi,Nikola],teachers=[Viktor,John,Peter]}
EmptyUni::University
EmptyUni={uniName="Empty",students=[],teachers=[]}
Peter::Teacher
Peter={tname="Peter",subject="Functional"}
Viktor::Teacher
Viktor={tname="Viktor",subject="Math"}
Mary::Teacher
Mary={tname="Mary",subject="OOP"}
John::Teacher
John={tname="John",subject="Functional"}
Marko::Student
Marko={studentName="Marko",age=19,grades={4,4,4,5},favoriteTeacher= Mary}
Sofi::Student
Sofi={studentName="Sofi",age=22,grades={5,5,4,5,5},favoriteTeacher=John}
Dame::Student
Dame={studentName="Dame",age=21,grades={2,3,4,5},favoriteTeacher=Peter}
Ana::Student
Ana={studentName="Ana",age=18,grades={5,5,5,5},favoriteTeacher=Viktor}
Nikola::Student
Nikola={studentName="Nikola",age=19,grades={4,4,4,4,2},favoriteTeacher=Peter}
Nik::Student
Nik={studentName="Nik",age=20,grades={4,4,4,4,3},favoriteTeacher=Peter}
Nik2::Student
Nik2={studentName="Nik2",age=22,grades={4,4,4,4,5},favoriteTeacher=Peter}
Josh::Student
Josh={studentName="Josh",age=22,grades={4,5,5},favoriteTeacher=John}


// 2. Given a University, return an array of all the 
//students or teachers names which are shorter than 6.

isShort :: String -> Bool 
isShort str = (size str) < 6 

shorterThan6::University->{String}
shorterThan6 uni = {na \\ na<- lstt}
where
	lstt =  [st.studentName \\ st <- uni.students | isShort st.studentName ] ++ [tc.tname \\ tc <- uni.teachers | isShort tc.tname ]

//Start=shorterThan6 BME//{"Ana","Josh","Sofi","John","Peter"}
//Start=shorterThan6 ELTE//{"Marko","Josh","Dame","Mary","Peter","John"}
//Start=shorterThan6 EmptyUni//{}

gpacalc :: {Int} -> Real
gpacalc arr = sum l /toReal( length l)
where 	
	l = [toReal(a) \\ a<-:arr]

avgofuni :: University -> Real	
avgofuni uni 
| length (map gpacalc stg)== 0 = 0.0
 = avg( map gpacalc stg)
where 
	stg = [st.grades \\  st<-uni.students]
//Start = avgofuni ELTE


// 3. Write a function which will take an array of Universities and 
// return the University with the highest overall gpa 
// (the average of the average of each student).

highestGpa::{University}->String
highestGpa uniarr 
| length list == 0 = abort "No universities given"
=  fst (hd( sortBy (\x y = snd x > snd y ) [(uni.uniName,(avgofuni uni)) \\ uni <-:uniarr]))
where list = [y\\y<-:uniarr]


//Start=highestGpa {ELTE,BME,EmptyUni}//"BME"
//Start=highestGpa {ELTE,BME} //"BME"
//Start=highestGpa {EmptyUni,EmptyUni}//"Empty"
//Start=highestGpa {ELTE} //"ELTE"
//Start=highestGpa {}//"No universities given"


// 4. Create a toString instance for Student such that for given student 
// ex. Nikola={studentName="Nikola",age=19,grades={4,4,4,4,2},favoriteTeacher=Peter} 
// it gives "Nikola 3.6 Peter" where 3.6 is the student's gpa 
// and Peter is the student's favorite teacher's name

instance toString Student
where 
	toString st = st.studentName +++ " "  +++ toString( gpacalc st.grades) +++ " " +++ st.favoriteTeacher.tname

//Start=toString Nikola//"Nikola  3.6  Peter"
//Start=toString Marko//"Marko  4.25  Mary"
//Start=toString Nik//"Nik  3.8  Peter"
//Start=toString Dame//"Dame  3.5  Peter"


// 5. Write an instance of operator + for 
//lists of Int such that [1,2,3]+[2,2,2,3]=[3,4,5]

instance + [Int]
where
    (+) a b = [x+y \\ x<-a & y<-b]

//Start = [1,2,3] + [2,2,2,3]
	

// 6. Given two arrays, return new array such that i-th element of it is maximum of i-th element of first and second arrays.
// So for example, when we calculate 5th element of result array, we look at 5th element of first and 5th element of second arrays
// And choose maximum of the two.
// You can assume that arrays have same length. 

maxOfTwo :: {Int} {Int} -> {Int}
maxOfTwo ara arb = {max a b \\ a<-:ara & b<-:arb }
//Start = maxOfTwo {} {} // {}
//Start = maxOfTwo {1} {5} // {5}
//Start = maxOfTwo {1,5,4} {2,3,6} // {2,5,6}
//Start = maxOfTwo {1,2,3,4,5} {1,2,3,4,5} // {1,2,3,4,5}


// 7. You are given array of integers.
// Your function should return true if each value appears at least twice in the array, and it should return false
// if any element is distinct.

find :: Int {Int} -> [Int]
find i arr = [b\\ b<- l | b == i]
where
	l = [a\\ a<-:arr]

is2 :: Int {Int} -> Bool
is2 i arr = length (find i arr) >= 2

f7 :: {Int} -> Bool
f7 arr = and [is2 i arr \\ i <-: arr]

//Start = f7 {1,2,3,1,3,2,2,2} // True
//Start = f7 {1,2,3,4,3,2,1} // False
//Start = f7 {1,1,1,3,3,4,3,2,4,2} // True


// 8. An array is monotonic if it is either monotone increasing or monotone decreasing
// A is monotone increasing if for all i<=j, A[i]<=A[j]
// A is monotone decreasing if for all i<=j, A[i]>=A[j]
// Given array, your task is to decide if it is monotonic.

isMonotonic :: {Int} -> Bool
isMonotonic arr = (l == x) || (l == reverse x)
where 
	l = [a \\ a <-: arr]
	x = sort l
	
//Start = isMonotonic {6,5,4,4} // True
//Start = isMonotonic {1,3,2} // False
//Start = isMonotonic {1,2,4,5} // True
//Start = isMonotonic {1,1,1} // True

//////////////////

:: Tree a = Node a (Tree a) (Tree a) | Leaf

instance == (Tree a) | == a
where
    == Leaf Leaf = True
    == (Node x1 l1 r1) (Node x2 l2 r2) = and[x1==x2, l1==l2, r1==r2]
    == _ _ = False

specialTree :: (Tree Int)
specialTree = Node 10 (Node 4 (Node 1 (Node 0 Leaf Leaf)(Node 2 Leaf Leaf))(Node 5 Leaf (Node 6 Leaf Leaf)))(Node 15 (Node 12 (Node 11 Leaf Leaf)(Node 13 Leaf Leaf))(Node 17 (Node 16 Leaf Leaf)(Node 19 (Node 18 Leaf Leaf)(Node 20 Leaf Leaf))))

notPrime :: Int -> Bool
notPrime x
| x <= 1 = True
= not(isEmpty[n\\n<-[2..(x-1)]|x rem n == 0])

/* 9.
Please write a function that, given a Tree and a predicate,
will find nodes that do not return True for the predicate
and will remove those nodes and their subtrees.
Note: The expected return results are listed below with an equality
for your convenience, so that you do not have to manually check your result.
If your result is correct, the Start statement should return a True.
*/

//check :: (a -> Bool) a ->a

pruneTree :: (Tree a) (a -> Bool) -> (Tree a)
pruneTree Leaf pred = Leaf
pruneTree (Node x l Leaf) pred 
| pred x = (Node x (pruneTree l pred) Leaf)
=Leaf
pruneTree (Node x Leaf r) pred 
| pred x = (Node x Leaf (pruneTree r pred))
=Leaf
pruneTree (Node x l r) pred 
| pred x = (Node x (pruneTree l pred) (pruneTree r pred))
=Leaf

//Start = pruneTree specialTree isEven == (Node 10 (Node 4 Leaf Leaf) Leaf) //True
//Start = pruneTree specialTree ((<)7) == (Node 10 Leaf (Node 15 (Node 12 (Node 11 Leaf Leaf) (Node 13 Leaf Leaf)) (Node 17 (Node 16 Leaf Leaf) (Node 19 (Node 18 Leaf Leaf) (Node 20 Leaf Leaf))))) //True
//Start = pruneTree specialTree notPrime == (Node 10 (Node 4 (Node 1 (Node 0 Leaf Leaf) Leaf) Leaf) (Node 15 (Node 12 Leaf Leaf) Leaf)) //True


/* 10.
Given a tree and an integer. Find all the nodes that are equal to the integer and give the sum 
of their direct children (Leaf counts as 0).
*/

exn::(Tree Int)->Int
exn Leaf = 0
exn (Node x l r) = x

f10::(Tree Int) Int->Int
f10 Leaf n = 0
f10  (Node x l r) n 
|x==n = exn l + exn r + (f10 l  n) +( f10 r n)
=(f10 l n) +(f10 r n)


//Start = f10 (Node 2 Leaf Leaf) 3 //0
//Start = f10 (Node 3 (Node 1 Leaf Leaf) (Node 1 Leaf Leaf)) 3 //2
//Start = f10 (Node 1 (Node 0 Leaf Leaf)(Node 2 Leaf Leaf)) 1 //2
//Start = f10 (Node 2 (Node 1 Leaf Leaf)(Node 2 (Node 3 Leaf Leaf) (Node 1 Leaf Leaf))) 2 //7
//Start = f10 (Node 2 (Node 1 Leaf Leaf)(Node 2 Leaf (Node 1 Leaf Leaf))) 2 //4


////   Exercises  INSTANCES
//Defining the behaviour of some types for different operators


// 11. Make an instance of + for the String type.
//Start = "Hello "+"World"

instance + String
where
   (+) s1 s2 = s1 +++ s2

//Start = "Hello "+"World"


// 12. Make an instance of the operator - for 
//lists of Int such that [1,2,3]-[2,2,2,3]=[1]
//Start = [1,2,3]-[2,2,2,3]

instance - [Int]
where
    (-) a b = [x \\ x<-a | not (isMember x b)]
     
//Start :: [Int]
//Start = [1,2,3] - [2,2,2,3]	


