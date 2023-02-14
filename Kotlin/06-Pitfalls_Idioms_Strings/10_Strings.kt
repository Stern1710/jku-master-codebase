/*
(Nearly all) String extension methods:

all
any
chunked
contains (operator, in keyword)
count (with predicate)
drop
dropWhile
dropLast
dropLastWhile
elementAtOrElse
elementAtOrNull
endsWith (with extended paramters)
filter
filterIndexed
filterNot
find (finds first matching)
findAnyOf
findLast
findLastAnyOf
first (with or without predicate)
firstOrNull (general advice: check the documentation what happens in unsuccessful situations: null or exception)
(flatMap)
fold
foldIndexed
foldRight
foldRightIndexed
forEach
forEachIndexed
format (uses this string as a format, with varargs parameter)
getOrElse
getOrNull
(groupBy)
ifBlank
ifEmpty
indexOf (with extended parameters)
indexOfAny
indexOfFirst (with predicate)
indexOfLast (with predicate)
isEmpty
isNotBlank
isNotEmpty
isNullOrBlank
isNullOrEmpty
iterator (operator, for-loop)
last (with or without predicate)
lastOrNull
lastIndexOf
lastIndexOfAny
lines
lowercase
map
mapIndexed
mapIndexedNotNull
mapNotNull
matches (regex - boolean result)
maxBy
maxByOrNull
maxOf
maxOfOrNull
maxOfWith
maxOfWithOrNull
maxWithOrNull
(all the same with min)
none (opposite of all)
onEach (like forEach, but returns the String afterwards, can be uses as an "intermediate forEach")
onEachIndexed
orEmpty
padEnd
padStart
(partition)
prependIndent
random
randomOrNull
rangeTo (operator, ..)
reader
reduce
reduceIndexed
reduceIndexedOrNull
reduceOrNull
(same with reduceRight)
removePrefix
removeRange
removeSuffix
removeSurrounding
replace (regex-based)
replaceAfter (after given delimeter)
replaceAfterLast
replaceBefore
replaceBeforeLast
replaceFirst (regex-based)
replaceFirstChar
replaceIndent
replaceIndentByMargin
replaceRange
reversed
runningFold
runningFoldIndexed
runningReduce
runningReduceIndexed
scan
scanIndexed
single (with or without predicate)
singleOrNull
slice
split (with delimiter(s) or regex)
startsWith (with extended parameters)
substring (based on range)
substringAfter
substringAfterLast
substringBefore
substringBeforeLast
sumOf
take
takeLast
takeLastWhile
takeWhile { it <= 'z'}
to<Number/Collection/Array><OrNull>
toRegex
trim (with predicate)
trimEnd (with predicate)
trimIndent
trimMargin
trimStart
uppercase
windowed
withIndex
zip
zipWithNext
*/


fun main() {
    // chunked
    val myString = "Kotlin is nice"
    val myStringChunked = myString.chunked(3) { part ->
        part[0]
    }
    println(myStringChunked)

    // dropLastWhile
    val droppedFromBehind = myString.dropLastWhile { !it.isWhitespace()  }
    println(droppedFromBehind)
}