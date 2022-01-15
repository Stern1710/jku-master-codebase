module slides_codes

import StdEnv

////  CODE EXAMPLES of SLIDES
////////////// SLIDES1

//Start = 4+5 // 9
//Start = 42 // 42
//Start = 3+10*2 // 23
//Start = sqrt 3.0 // 1.73...

double :: Int -> Int
double x = x + x

quadruple :: Int -> Int
quadruple x = double (double x)

//Start = double 2
//Start = quadruple 2

factorial :: Int -> Int
factorial n = prod [1 .. n]

//Start = factorial 5


// two cases 
abs1 :: Int -> Int
abs1 x
| x<0 = ~x
| otherwise = x

Start = abs1 -4 // 4

// otherwise can be omitted 
abs2 :: Int -> Int
abs2 x
| x<0 = ~x
= x

//Start = abs2 4 // 4

// more then two guards or cases
signof :: Int -> Int
signof x
| x>0 = 1
| x==0 = 0
| x<0 = -1
 
//Start =  signof -8 // -1

factor :: Int -> Int
factor n
| n==0 = 1
| n>0 = n * factor (n-1)

//Start = factor 5

power :: Int Int -> Int
power x n
| n == 0 = 1
= x * power x (n-1)

//Start = power 2 5


l1 :: [Int]
l1 = [1, 2, 3, 4, 5]
l2 :: [Bool]
l2 = [True, False, True]
l3 :: [Real->Real]
l3 = [sin, cos, sin]
l4 :: [[Int]]
l4 = [[1, 2, 3], [8, 9]]
l5 :: [a]
l5 = []
l6 :: [Int]
l6 = [1..10]
l7 :: [Int]
l7 = [1..]

//Start = l4

//Start = [1..10]     
//Start = [1,2..10]   
//Start = [1,0.. -10] 
//Start = [1.. -10]   
//Start = [1..0]      
//Start = [1..1]      
//Start = [1,3..4]  
//Start = [1..]       
//Start = [1,3..]     
//Start = [100,80..]

//Start = [1,2..10]

//Start = hd [1, 2, 3, 4, 5]       
//Start = tl [1, 2, 3, 4, 5]      
//Start = drop 2 [1, 2, 3, 4, 5]   
//Start = take 2 [1, 2, 3, 4, 5]   
//Start = [1, 2, 3] ++ [6, 7]      
//Start = reverse [1, 2, 3]        
//Start = length [1, 2, 3, 4]      
//Start = last [1, 2, 3]           
//Start = init [1, 2, 3]           
//Start = isMember 2 [1, 2, 3]     
//Start = isMember 5 [1, 2, 3]     
//Start = flatten [[1,2], [3, 4, 5], [6, 7]]  

//Start = take 2 []                
//Start = drop 5 [1,2,3]           
//Start = take 2 [1 .. 10]         
//Start = drop ([1..5]!!2) [1..5]  

//Start = reverse [1,3..10]           
//Start = reverse [5,4 .. -5]         
//Start = isMember 0 []               
//Start = isMember -1 [1..10]         
//Start = isMember ([1..5]!!1) [1..5] 

// some list patterns
triplesum :: [Int] -> Int
triplesum [x, y, z] = x + y + z

//Start = triplesum [1,2,4] // 7  [1,2,3,4] error

head :: [Int] -> Int
head [x : y] = x

//Start = head [1..5] // 1

tail :: [Int] -> [Int]
tail [x : y] = y

//Start = tail [1..5] // [2,3,4,5]

// omitting values
f :: Int Int -> Int
f _ x = x

//Start = f 4 5 // 5

// patterns with list constructor
g :: [Int] -> Int
g [x, y : z] =  x + y

//Start = g [1, 2, 3, 4, 5] // 3

// patterns + recursively applied functions
lastof :: [Int] -> Int
lastof [x] = x
lastof [x : y] = lastof y

//Start = lastof [1..10] // 10

// recursive functions on lists
sum1 :: [Int] -> Int
sum1 x
| x == [] = 0
| otherwise = hd x + sum1 (tl x)

//Start = sum1 [1..5] // 15 

sum2 :: [Int] -> Int
sum2 [] = 0
sum2 [first : rest] = first + sum2 rest

//Start = sum2 [1..5] // 15 

// recursive function with any element pattern
length1 :: [Int] -> Int
length1 [] = 0
length1 [_ : rest]= 1 + length1 rest

//Start = length1 [1..10] // 10 l1 :: [Int]
//Start = isMember ([1..5]!!1) [1..5] 

//Start = filter isEven [1..10] // [2,4,6,8,10]

odd x = not (isEven x)
//Start = odd 23 // True

//Start = filter (not o isEven) [1..100] // [1,3,5,..,99]

//Start = takeWhile isEven [2,4,6,7,8,9] // [2, 4, 6]

//Start = dropWhile isEven [2,4,6,7,8,9] // [7, 8, 9]

//Start = map inc [1, 2, 3]        // [2, 3, 4]

//Start = map double [1, 2, 3]     // [2, 4, 6]

// lambda expressions
//Start = map (\x = x*x+2*x+1) [1..10] // [4,9,16,25,36,49,64,81,100,121]

//Start = foldr (+) 10 [1, 2, 3]   // 16

product1 = foldr (*) 1

//Start = product1 [1, 2, 3] // 6

and1 :: [Bool] -> Bool
and1 [] = True
and1 [x:xs] = x && and1 xs

and2 = foldr (&&) True

//Start = and2 [True, True, False] // False

sumF = foldr (+) 0

//Start = sumF [1, 2, 3] // 6 


qsort :: [a] -> [a] | Ord a
qsort [] = []
qsort [c : xs] = qsort [x \\ x <- xs | x <  c] ++ [c] ++
                 qsort [x \\ x <- xs | x >= c]

// Start = qsort [2,1,5,3,6,9,0,1] // [0,1,1,2,3,5,6,9]

// sort is the built in operation for sorting

// Start = sort [3,1,4,2,0] // [0,1,2,3,4]

// inserting in already sorted list
Insert :: a [a] -> [a] | Ord a
Insert e [] = [e]
Insert e [x : xs]
| e <= x = [e , x : xs]
| otherwise = [x : Insert e xs]

// Start = Insert 5 [2, 4 .. 10] // [2,4,5,6,8,10]

mysort :: [a] -> [a] | Ord a
mysort [] = []
mysort [a:x] = Insert a (mysort x)

// Start = mysort [3,1,4,2,0] // [0,1,2,3,4]

// Start = Insert 3 (Insert 1 (Insert 4 (Insert 2 (Insert 0 [] ))))

merge1 :: [a] [a] -> [a] | Ord a
merge1 [] ys = ys
merge1 xs [] = xs
merge1 [x : xs] [y : ys]
| x <= y = [x : merge1 xs [y : ys]]
| otherwise = [y : merge1 [x : xs] ys]

// Start = merge1 [2,5,7] [1,5,6,8] // [1,2,5,5,6,7,8]
// Start = merge1 [] [1,2,3] // [1,2,3]
// Start = merge1 [1,2,10] [] // [1,2,10]
// Start = merge1 [2,1] [4,1] // [2,1,4,1]
// Start = merge1 [1,2] [1,4] // [1,1,2,4]

msort :: [a] -> [a] | Ord a
msort xs
| len <= 1 = xs
| otherwise = merge (msort ys) (msort zs)
where
   ys = take half xs
   zs = drop half xs
   half = len / 2
   len = length xs

// Start = msort [2,9,5,1,3,8] // [1,2,3,5,8,9]

fromn :: Int -> [Int]
fromn n = [n : fromn (n+1)]

//Start = fromn 8

//Start = map ((^)3) [1..] 

//Start = takeWhile ((>) 1000) (map ((^)3) [1..])

repeat1 :: a -> [a]
repeat1 x = list 
where list = [x:list]

//Start = repeat1 5

repeatn1 ::  Int a -> [a]
repeatn1 n x = take n (repeat x)

//Start = repeatn1 5 8

iterate1 :: (a->a) a -> [a]
iterate1 f x = [x: iterate1 f (f x)]

//Start = iterate1 inc 5 // [5,6,7,8,9,...]

//Start = iterate1 ((+)1) 5 // [5,6,7,8,9,...]

//Start = iterate1 ((*)2) 1 // [1,2,4,8,16,...]

//Start = iterate1 (\ x= x/10) 54321 // [54321,5432,543,54,5,0,0...]

// Prime numbers

divisible :: Int Int -> Bool
divisible x n = x rem n == 0

denominators :: Int -> [Int]
denominators x = filter (divisible x) [1..x]

prime :: Int -> Bool
prime x = denominators x == [1,x]

primes :: Int -> [Int]
primes x = filter prime [1..x]

//Start = primes 100 // [2,3,5,7,...,97]

sieve :: [Int] -> [Int]
sieve [p:xs] = [p: sieve [ i \\ i <- xs | i rem p <> 0]]

//Start = take 100 (sieve [2..]) 

// Define a function CountOccurrences that counts the number of times a given element is
// occurring in a given list.

CountOccurrences :: a [a] -> Int | == a
CountOccurrences a [x : xs] = f a [x : xs] 0
where
      f a [] i = i
      f a [x : xs] i
        | a == x = f a xs i+1
                 = f a xs i

//Start = CountOccurrences 2 [2, 3, 4, 2, 2, 4, 2, 1] // 4



////////////// SLIDES2



//// Records - Person

:: Person = { name :: String
            , birthdate :: (Int,Int,Int)
            , fpprogramer :: Bool
            }

IsfpUser :: Person -> String
IsfpUser {fpprogramer = True} = "Yes"
IsfpUser _                    = "No"

//Start = IsfpUser { name = "Me"
 //                , birthdate = (1,1,1999)
 //                , fpprogramer = True}    // "Yes"


GetName :: Person -> String
GetName p = p.name

GetName2 :: Person -> String
GetName2 {name} = name

ChangeN :: Person String -> Person
ChangeN p s = {p & name = s} 

//Start = ChangeN {name = "XY", birthdate = (1,1,2000), fpprogramer = True} "Alex" 



//// Records - Point

:: Point = {  x       ::  Real
            , y       ::  Real
            , visible ::  Bool
            }

:: Vector = { dx       ::  Real
            , dy       ::  Real
            }
  
Origo :: Point
Origo = { x = 0.0
        , y = 0.0
        , visible = True
        }
Dist :: Vector
Dist = { dx = 1.0
       , dy = 2.0
       }

IsVisible :: Point -> Bool
IsVisible {visible = True} = True
IsVisible _                = False

xcoordinate :: Point -> Real
xcoordinate p = p.x

hide :: Point -> Point
hide p = { p & visible = False }

Move :: Point Vector -> Point
Move p v = { p & x = p.x + v.dx, y = p.y + v.dy } 

//Start = Move (hide Origo) Dist



//// Records - Q type

:: Q = { nom :: Int
       , den :: Int
       } 
       
QZero :: Q       
QZero = { nom = 0, den = 1 }    
QOne :: Q
QOne = { nom = 1, den = 1 }

simplify :: Q -> Q
simplify {nom=n,den=d}
  | d == 0 = abort " denominator is 0"
  | d < 0  = { nom = ~n/g, den = ~d/g}
  | otherwise =  { nom = n/g, den = d/g}
  where g = gcdm n d

gcdm :: Int Int -> Int
gcdm x y = gcdnat (abs x) (abs y)
  where gcdnat x 0 = x
        gcdnat x y = gcdnat y (x rem y)

mkQ :: Int Int -> Q
mkQ n d = simplify { nom = n, den = d } 

//Start = mkQ 81 90


//// Arrays

MyArray :: {Int}
MyArray = {1,3,5,7,9}

//Start = MyArray
//Start = MyArray.[2] // 5

MapArray1 f a = {f e \\ e <-: a}

//Start :: {Int}
//Start = MapArray1 inc MyArray



//// Algebraic types, trees


:: Day = Mon | Tue | Wed | Thu | Fri | Sat | Sun

:: Tree a = Node a (Tree a) (Tree a)
          | Leaf

sizeT :: (Tree a) -> Int
sizeT Leaf = 0
sizeT (Node x l r) = 1 + sizeT l + sizeT r

//Start = aTree

//Start = sizeT aTree     // 4

depth :: (Tree a) -> Int
depth Leaf = 0
depth (Node _ l r) = (max (depth l) (depth r)) + 1

//Start = depth aTree // 2



treesort :: ([a]-> [a]) | Eq, Ord a
treesort = collect o listtoTree

listtoTree :: [a] -> Tree a | Ord, Eq a
listtoTree [] = Leaf
listtoTree [x:xs] = insertTree x (listtoTree xs)

insertTree :: a (Tree a) -> Tree a | Ord a
insertTree e Leaf = Node e Leaf Leaf
insertTree e (Node x le ri)
   | e<=x = Node x (insertTree e le) ri
   | e>x  = Node x le (insertTree e ri)

collect :: (Tree a) -> [a]
collect Leaf = []
collect (Node x le ri) = collect le ++ [x] ++ collect ri

//Start = treesort [3, 1, 5, 9, 2, 7, 0]


atree = Node 2 (Node 1 Leaf Leaf) (Node 3 Leaf Leaf)

//Start = atree

:: Tree2 a = Node2 a (Tree2 a) (Tree2 a)
           | Leaf2 a

nrNodes :: (Tree2 a) -> Int
nrNodes (Leaf2 y) = 1
nrNodes (Node2 x l r) = 1 + nrNodes l + nrNodes r


aTree2 :: Tree2 Int

aTree2 = Node2 4 (Node2 2 (Node2 1 (Leaf2 1) (Leaf2 1)) (Node2 3 (Leaf2 3) (Leaf2 3))) (Leaf2 5)

//Start = aTree2

//Start = nrNodes aTree2     // 9


:: Tree3 a b = Node3 a (Tree3 a b) (Tree3 a b)
             | Leaf3 b
 
aTree3 :: Tree3 Int Real

aTree3 = Node3 2 (Node3 1 (Leaf3 1.1) (Leaf3 2.5)) (Node3 3 (Leaf3 3.0) (Leaf3 6.9))

//Start = aTree3

sumLeaves :: (Tree3 Int Real) -> Real
sumLeaves (Leaf3 y) = y
sumLeaves (Node3 x le ri) = sumLeaves le + sumLeaves ri

//Start = sumLeaves aTree3 //13.5


// Triple branches
:: Tree4 a = Node4 a (Tree4 a) (Tree4 a) (Tree4 a)
          | Leaf4


// Rose-tree - tree with variable multiple branches
// No leaf constructor, node with no branches
:: Tree5 a = Node5 a [Tree5 a]


// Every node has one branch = list
:: Tree6 a = Node6 a (Tree6 a) 
           | Leaf6

           
// Tree with different types
:: Tree7 a b = Node7a Int (Tree7 a b) (Tree7 a b)
             | Node7b b (Tree7 a b)
             | Leaf7a b
             | Leaf7b Int


:: BTree a = Bin (BTree a) (BTree a) 
           | Tip a

mapbtree				:: (a -> b) (BTree a) -> BTree b
mapbtree f (Tip x)		= Tip (f x)
mapbtree f (Bin t1 t2)	= Bin (mapbtree f t1) (mapbtree f t2)

foldbtree				:: (a a -> a) (BTree a) -> a
foldbtree f (Tip x)		= x
foldbtree f (Bin t1 t2)	= f (foldbtree f t1) (foldbtree f t2)


aBTree = Bin (Bin (Bin (Tip 1) (Tip 1)) (Bin (Tip 3) (Tip 3))) (Tip 5)

//Start = aBTree
//Start = mapbtree inc aBTree
//Start = foldbtree (+) aBTree // 13


//// Instances

instance + String
where 
     (+) s1 s2 = s1 +++ s2
     
//Start = "Hello" + " world!" // "Hello world!"


instance + (a,b) | + a & + b
where 
     (+) (x1,y1) (x2,y2) = (x1+x2,y1+y2)
 
//Start = (1,2) + (3,4) // (4,6)

/* in StdTuple.dcl
instance == (a,b)   | Eq a & Eq b
instance == (a,b,c) | Eq a & Eq b & Eq c

in StdTuple.icl
instance ==	(a,b) |	Eq a & Eq b
	where
	(==) ::!(a,b) !(a,b) -> Bool	|	Eq a & Eq b
	(==) (x1,y1) (x2,y2) = x1==x2 && y1==y2


instance == (a,b,c)	| Eq a & Eq b & Eq c
	where
	(==) ::!(a,b,c) !(a,b,c) -> Bool	|	Eq a & Eq b & Eq c
	(==) (x1,y1,z1) (x2,y2,z2) = x1==x2 && y1==y2 && z1==z2
*/

//Start = (1,2) == (3,4) // False  == overloading

increment n = n+1

//Start = increment 4

doubleA :: a -> a | +a
doubleA x = x + x

//Start = doubleA 3

//Start = doubleA 3.3

delta :: a a a -> a | *,-,fromInt a
delta a b c = b*b - (fromInt 4)*a*c

//Start = delta 1.0 2.0 1.0 

class Delta a | *,-,fromInt a

delta1 :: a a a -> a | Delta a
delta1 a b c = b*b - (fromInt 4)*a*c

//Start = delta1 1.0 2.0 1.0 


//// Classes

class PlusMinx a
 where 
       (+~)  infixl 6   :: !a   !a      ->      a
       (-~)  infixl 6   :: !a   !a      ->      a
       zerox            :: a

instance PlusMinx Char
 where 
       (+~) :: !Char !Char -> Char
       (+~) x y =  toChar (toInt(x) + toInt(y))
       (-~) x y =  toChar (toInt(x) - toInt(y))
       zerox = toChar 0 

//Start = 'a' +~ 'e' 

//Start :: Char    
//Start = zerox

double1 :: a -> a | PlusMin a
double1 x = x + x


//Start = double1 2 // 4


//// Instances Q type


instance + Q
where 
    (+) x y = mkQ (x.nom*y.den+y.nom*x.den) (x.den*y.den)

// Start = mkQ 2 4 + mkQ 5 6 // (Q 4 3)


instance - Q
where 
    (-) x y = mkQ (x.nom*y.den-y.nom*x.den) (x.den*y.den)

// Start = mkQ 2 4 - mkQ 5 6 // (Q -1 3)


instance fromInt Q
where 
    fromInt i = mkQ i 1
    
//Start :: Q
//Start = fromInt 3 // (Q 4 3)


instance zero Q
where 
    zero = fromInt 0 

//Start :: Q   
//Start = zero // (Q 0 1)

instance one Q
where 
    one = fromInt 1 // 


//Start :: Q   
//Start = one // (Q 1 1)

instance toString Q
where
    toString q
        | xq.den == 1 = toString xq.nom
        | otherwise = toString xq.nom +++"/"+++ toString xq.den
    where xq = simplify q
        
//Start = toString (mkQ 3 4) // "3/4"

instance < Q
where 
    (<) x y = x.nom*y.den < y.nom*x.den
    
//Start = mkQ 1 2 < mkQ 3 4  // True


ls = [toString q \\ q <- [zero, mkQ 1 3 .. mkQ 3 2]]

//Start :: [String]
//Start = ls // ["0","1/3","2/3","1","4/3"]

//overloading can not be solved
//Start = toString zero+zero

/*
Start :: String
Start = toString sum  // "0"
where sum :: Q
      sum = zero + zero */



//// Instances C type

:: C = { re :: Real
       , im :: Real
       } 


mkC r i = { re = r, im = i } 

//Start = mkC 1.0 10.0 // (C 1 10)


instance + C
where 
    (+) x y = mkC (x.re+y.re) (x.im+y.im)

//Start = mkC 2.2 4.1 + mkC 1.5 6.4 // (C 3.7 10.5)

 
instance - C
where 
    (-) x y = mkC (x.re-y.re) (x.im-y.im)

//Start = mkC 2.2 4.1 - mkC 1.5 6.4 // (C 0.7 -2.3)


instance * C
where 
    (*) x y = mkC (x.re*y.re - x.im*y.im) (x.re*y.im + x.im*y.re)

//Start = mkC 2.0 4.0 * mkC 3.0 2.0 // (C -2 16)


// for simplicity only division by a real nr. is defined
instance / C
where 
    (/) x y 
    | y.im == 0.0 = mkC (x.re/y.re) (x.im/y.re)
    = abort "division not defined"

//Start = (mkC 2.0 4.0) / (mkC 2.0 0.0) // (C 1 2)


instance fromReal C
where 
    fromReal r = mkC r 0.0
    
//Start :: C
//Start = fromReal 3.0 // (C 3 0)


instance toReal C
where 
    toReal x
    | x.im == 0.0 = x.re
    = abort "x has imaginary part"
    
//Start = toReal (mkC 3.0 0.0) // 3


instance zero C
where 
    zero = fromReal 0.0

//Start :: C   
//Start = zero // (C 0 0)


instance one C
where 
    one = fromReal 1.0

//Start :: C   
//Start = one // (C 1 0)


instance abs C
where 
    abs x = fromReal (sqrt (x.re*x.re + x.im*x.im))
    
//Start = abs (mkC 3.0 4.0) // (C 5 0)


//conjugate of a complex x+yi is x-yi
instance ~ C
where 
    (~) x = mkC x.re (~x.im)

//Start = ~ (mkC 2.0 3.0) // (C 2 -3)


instance toString C
where
    toString x
        | x.im == 0.0 = toString x.re
        | otherwise = toString x.re +++ "+" +++ toString x.im +++ "i"
        
//Start = toString (mkC 3.0 4.0) // "3+4i"


instance == C
where 
    (==) x y = x.re == y.re && x.im == y.im
    
//Start = mkC 1.0 2.0 == mkC 1.0 2.0  // True

// tests whether the complex number represents a real nr.
isRealC :: C -> Bool    
isRealC x 
| x.im == 0.0 = True 
= False
 
//Start = isRealC (mkC 2.0 0.0) // True


// returns real part
re :: C -> Real
re x = x.re

//Start = re (mkC 1.0 2.0) // 1


// returns imaginary part
im :: C -> Real
im x = x.im

Start = im (mkC 1.0 2.0) // 2

// for Map see separate file
///////////