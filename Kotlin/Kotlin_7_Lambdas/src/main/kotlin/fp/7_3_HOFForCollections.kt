package fp

fun main() {

    // mapping ------------------------------------------------------------------------------------

    //    map - map elements and create result collections
    val names = TODO()
    println("names = $names")

    //    mapTo - map elements and add them to mutable collection
    val ages = mutableListOf<Int>()

    println("ages = $ages")

    //    mapIndexed - map together with position
    val ordered = TODO()
    println("ordered = $ordered")

    //    zip - combine elements of two collections to collection of Pairs
    val pairs = TODO()
    println("pairs = $pairs")

    //    associate - creates a map from collection of keys and value
    val map1 =  TODO()
    println("map1 = $map1")

    val map2 = TODO()
    println("map2 = $map2")

    val map3: Map<String, Person> =  TODO()
    println("map3 = $map3")

    val map4  = TODO()
    println("map4 = $map4")

    //    joinToString - join elements of collection to a string
    println("joined = TODO()")

    //    flatMap - maps each element to collection and then flattens then resulting collections
    println("flatMap = ${TODO()}")

    //    flatten - flatten a collection of collections
    println("flatten = ${TODO()}")


    // filtering ----------------------------------------------------------------------------------

    // filter, filterNot - filter elements fulfilling predicate
    val adults = TODO()
    println("adults = $adults")

    // filterIndexed - filter elements based on value and index
    val filteredIndex = TODO()
    println("filteredIndex = $filteredIndex")

    // Quantifiers --------------------------------------------------------------------------------

    // all - check if all fulfill predicate
    val allAdults = TODO()
    println("allAdults = $allAdults")

    // any - check if any element fulfills predicate
    val anyMinor = TODO()
    println("anyMinor = $anyMinor")

    // none - check if none of the elements fulfills predicate
    val noneMinor = TODO()
    println("noneMinor = $noneMinor")

    // Find an element ----------------------------------------------------------------------------

    // first, last - first, last element fulfilling criteria, throw exception if none found
    val anna = TODO()
    println("anna = $anna")

    // firstOrNull, lastOrNull	- first, last element fulfilling criteria, returns null if none found
    val lastSenior = TODO()
    //println("lastSenior = ${lastSenior?.lastName?:"NN"}")

    // indexOfFirst, indexOfLast - position of first, last element fulfilling criteria, -1 if none found
    val lastSeniorIdx = TODO()
    println("lastSeniorIdx = $lastSeniorIdx")

    // Sorting ------------------------------------------------------------------------------------

    // sortedBy, sortedByDescening - sort elements and returns sorted collection based on Comparator
    val sortedPersons = TODO()
    println("sortedPersons = $sortedPersons")
    // compareBy - use compareBy for creating Comparator based on property

    // sort, sortDescening - sort elements in mutable collection based on Comparator
    val mPersons = persons.toMutableList()
    TODO()
    println("mutable sorted = $mPersons")

    // Reductions ---------------------------------------------------------------------------------

    // fold - computes a result from the elements starting with a initial value and an accumulator function
    val agesSum = TODO()
    println("agesSum = $agesSum")

    // reduce - computes a result from the elements starting with a first element and an accumulator function
    val agesSum2 = TODO()
    println("agesSum2 = $agesSum2")

    // Grouping -----------------------------------------------------------------------------------

    // groupBy	Group elements based on criteria
    val ageGroups = TODO()
    println("agesGroups = $ageGroups")

    // groupingBy - creates Grouping which allow further with processing elements in each group
    // - eachCount	counts the elements in group
    val ageCounts = TODO()
    println("agesCounts = $ageCounts")

    // - fold and reduce - compute result
    val ageSums = TODO()
    println("ageSums = $ageSums")

    // - aggregate - allows flexible processing of groups
    val ageGroupsNames = TODO()
    println("ageGroupsNames = $ageGroupsNames")

    // Partition -----------------------------------------------------------------------------------

    // partition - partitions the elements based on predicate
    val juniorSeniorPart = TODO()
    println("juniorSeniorPart = $juniorSeniorPart")

}