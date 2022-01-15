module examples1_solved

import StdEnv 


////////////// EXAMPLES with SOLUTIONS


// 1. Define a function maxi with two arguments that delivers the maximum of the two.
maxi :: Int Int -> Int 
maxi x y
| x > y = x
= y

//Start = maxi 45 3 // 45



// 2. Check if an integer is even - in two ways. To divide integer use /, for remainder use rem.
even1 :: Int -> Bool
even1 x 
| (x / 2) * 2 == x = True
= False

//Start = even1 6 // True


even2 :: Int -> Bool
even2 x
| x rem 2 == 0 = True
= False

//Start = even2 5 // False


even21 :: Int -> Bool
even21 x = x rem 2 == 0 

//Start = even21 5 // False



// 3. Compute the product of the elements of a list
product :: [Int] -> Int
product [] = 1
product [h:t] = h * product t

//Start = product [1..5] // 120



// 4. Keep the head of every sublist e.g. [[1, 2, 3], [3, 4], [5, 7, 8, 9]] -> [1, 3, 5]
heads :: [[Int]] -> [Int]
heads [] = []
heads [x : xs] = [ hd x : heads xs]

//Start = heads [[1, 2, 3], [3, 4], [5, 7, 8, 9]]



// 5. Rewrite flatten with ++
lc :: [[Int]] -> [Int]
lc [] = []
lc [x : xs] = x ++ lc xs

//Start = lc [[1, 2, 3], [3, 4], [5, 7, 8, 9]]



// 6. Test if a list is symmetrical
sim :: [Int] -> Bool
sim x  = (x == reverse x)

//Start = sim [1, 2, 1]
//Start = sim [1, 2, 3, 2, 1]
//Start = sim [1..5]



// 7. rewrite flatten using foldr 
// (for the following list [[1,2], [3, 4, 5], [6, 7]] => [1,2,3,4,5,6,7])

//Start = foldr (++) [] [[1,2], [3, 4, 5], [6, 7]]



// 8. using map extract only the first elements of the sublists in 
// [[1,2], [3, 4, 5], [6, 7]] => [1,3,6]

//Start = map hd [[1,2], [3, 4, 5], [6, 7]]



// 9. Add 3 to every element of a list
f1 :: [Int] -> [Int]
f1 list = map ((+) 3) list

//Start = f1 [1,5,3,1,6]  // [4,8,6,4,9]  



// 10. compute the double of the positive elements of a list [1, 2, -2, 3, -4] -> [2, 4, 6]
// hints: first filter it then use map 
double :: Int -> Int
double x = 2*x

f2 :: [Int] -> [Int]
f2 list = map double (filter ((<)0) list)

//Start = f2 [1, 2, -2, 3, -4] // [2, 4, 6]



// 11. Insert 0 at the beginning of each sublist
// [[1,2], [3,4,5], [6,7]] -> [[0,1,2], [0,3,4,5], [0,6,7]]
f6 :: [[Int]] -> [[Int]]
f6 lists = map ((++) [0]) lists

//Start =  f6 [[1,2], [3,4,5], [6,7]]



// 12. Generate a list with 10 times of 13 : [13,13,13,13,13,13,13,13,13,13]

//Start = [13 \\ x<-[1..10]]



// 13. Generate the following list [(1,1),(1,2),(2,1),(2,2)]

//Start = [(x,y) \\ x<-[1..2], y<-[1..2]]



// 14. Generate the following list [(1,3),(1,2),(1,1),(2,3),(2,2),(2,1),(3,3),(3,2),(3,1)]

//Start = [(x,y) \\ x<-[1..3], y<-[3,2,1]]



// 15. Generate the list [(1,5),(2,6),(3,7),(4,8),(5,9),(6,10)]

//Start = [(x,y) \\ x <- [1..] & y <- [5..10]]


/////////////////// More exercises


// 16. Triple a number.
triple :: Int -> Int
//triple x = 3*x
triple x = (*)3 x

//Start = triple 3



// 17. Check if a number is odd.
isoddnr :: Int -> Bool
isoddnr x = (x rem 2 == 1)

//Start = isoddnr 6
//Start = isoddnr 7



// 18. Check if a number is the sum of two other given numbers.
issum :: Int Int Int -> Bool
issum a b c = (a == b+c) || (b == a+c) || (c == a+b)

//Start = issum 10 6 3
//Start = issum 10 6 4



// 19. Add 100 to a number.
add100 :: Int -> Int
add100 x = x + 100

//Start = add100 5



// 20. Check if a number is multiple of 10.
ismult10 :: Int -> Bool
ismult10 x = x rem 10 == 0

//Start = ismult10 20
//Start = ismult10 202



// 21. Add the numbers from 1..N in a recursive function.
addn :: Int -> Int
addn 0 = 0
addn n = n + addn (n-1)

//Start = addn 5

addn2 :: Int -> Int
addn2 n = sum [1..n] 

//Start = addn2 5



// 22. Compute the cube of a number
cube :: Int -> Int
//cube x = x*x*x
cube x = x^3  // caret

//Start = cube 4 
 

 
// 23. Delete an element n from a list
del :: Int [Int] -> [Int]
del _  []   =  []
del n  [h:t]   
|  n==h     = del n t 
= [h : del n t]

//Start = del 5 [1, 5, 6, 7, 5, 8, 5] // [1, 6, 7, 8]



// 24. sumsq n returns 1*1 + 2*2 + ... + n*n - with a pattern for 0
sumsq :: Int -> Int
sumsq 0 = 0
sumsq n 
| n < 0 = abort "n should be positive"
| n > 0 = sumsq (n-1) + n*n

//Start = sumsq 3 // 14
//Start = sumsq -3
//Start = sumsq 0



// 25. exists x xs checks whether x exists as an element in the xs list (note: or is ||, and is &&)
exists :: Int [Int] -> Bool
//exists x list = isMember x list

exists x [] = False
//exists x [h:t]
//| x == h = True
//= exists x t 
exists x [h:t] = (x == h) || exists x t 

//Start = exists 3 [1, 2, 1, 1, 2, 3, 2, 1, 3] // True



// 26. Write the function duplicates which checks if there are duplicates in the list xs
duplicates :: [Int] -> Bool
duplicates [] = False
duplicates [x:xs] 
| exists x xs = True
= duplicates xs

//Start = duplicates [1, 2, 1, 1, 2, 3, 2, 1, 3] // True
//Start = duplicates [1..10] // False



// 27. removeDuplicates l returns the list l with all duplicate elements removed
removeDuplicates :: [Int] -> [Int]
removeDuplicates [] = []
removeDuplicates [x:xs]
| exists x xs = [ x :  removeDuplicates (del x xs)]
= [ x : removeDuplicates xs ]

//Start = removeDuplicates [1, 2, 1, 2, 3, 1, 2, 4, 2, 3] // [1, 2, 3, 4]



// 28. Check if the numbers of a list are multiple of 10.
ff :: Int -> Bool []
ff x = x rem 10 == 0

ismult10list :: [Int] -> [Bool]
//ismult10list list = map ff list
ismult10list list = map (\ x = x rem 10 == 0) list

//Start = ismult10list [1..20]



// 29. Compute 1*1 + 2*2 + ... + n*n  for n positive using map and foldr.
sqr :: Int -> Int
sqr x = x*x

sumsqr :: Int -> Int
//sumsqr n = foldr (+) 0 (map sqr [1..n])  
sumsqr n = foldr (+) 0 (map (\ x = x*x) [1..n])

//Start = sumsqr 5 // 55



// 30. Reverse every sublist of a list
revsub :: [[Int]] ->  [[Int]]
revsub lists = map reverse lists

//Start = revsub [[1,2,3],[5,6],[],[7,8,9,10]]

revsubAll :: [[Int]] ->  [[Int]]
revsubAll lists = reverse (map reverse lists)

//Start = revsubAll [[1,2,3],[5,6],[],[7,8,9,10]]



// 31. generate the list [1,2,2,3,3,3,4,4,4,4,...,10,..,10]
l31 :: [Int]
//l31 = [ y \\ y <-[1..10], x <-[1..y]]
l31 = [ snd (x,y) \\ y <-[1..10], x <-[1..y]]

//Start = l31



// 32. generate the list [[1],[2,2],[3,3,3],[4,4,4,4],...,[10,..,10]]
l32 :: [[Int]]
l32 = [ [y \\ x<-[1..y]] \\ y <- [1..10]]

//Start = l32

  
  
// 33. generate 6 pythagoras numbers : [(3,4,5),(6,8,10),(5,12,13),(9,12,15),(8,15,17),(12,16,20)]
l33 :: [(Int, Int, Int)]
l33 = take 6 [(a,b,c) \\ c <- [1..], b<-[1..c], a<-[1..b] | a*a + b*b == c*c]

//Start = l33

////////////////

