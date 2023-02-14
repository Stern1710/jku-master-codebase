package fp

fun main() {

    // mapping ------------------------------------------------------------------------------------

    //    map - map elements and create result collections
    val names = persons.map { "${it.firstName} ${it.lastName}" }
    println("names = $names")

    //    mapTo - map elements and add them to mutable collection
    val ages = mutableListOf<Int>()
    TODO()
    println("ages = $ages")

    //    mapIndexed - map together with position
    val ordered = persons.sortedByDescending { it.age }.mapIndexed { index, person ->  "${index + 1} : ${person.lastName}" }
    println("ordered = $ordered")

    //    zip - combine elements of two collections to collection of Pairs
    val pairs = TODO()
    println("pairs = $pairs")

    //    associate - creates a map from collection of keys and value
    val map1 = TODO()
    println("map1 = $map1")

    val map2 = TODO()
    println("map2 = $map2")

    val map3 =  TODO()
    println("map3 = $map3")

    //    joinToString - join elements of collection to a string
    val s = persons.joinToString ("; ", prefix = "{", postfix = "}") { "${it.lastName} , ${it.firstName}"  }
    println("joined = $s")

    //    flatMap - maps each element to collection and then flattens then resulting collections
    val years1 = TODO()
    println("flatMap = ${years1}")

    //    flatten - flatten a collection of collections
    val years2 = TODO()
    println("flatten = ${years2}")


    // filtering ----------------------------------------------------------------------------------

    // filter, filterNot - filter elements fulfilling predicate
    val adults = persons.filterNot { it.age < 18 }
    println("adults = $adults")

    // filterIndexed - filter elements based on value and index
    val filteredIndex = persons.filterIndexed { idx, pers -> idx % 2 == 0 }
    println("filteredIndex = $filteredIndex")

    // Quantifiers --------------------------------------------------------------------------------

    // all - check if all fulfill predicate
    val allAdults = persons.all { it.age >= 18 }
    println("allAdults = $allAdults")

    // any - check if any element fulfills predicate
    val anyMinor = persons.any { it.age < 18 }
    println("anyMinor = $anyMinor")

    // none - check if none of the elements fulfills predicate
    val noneMinor = persons.none { it.age < 18 }
    println("noneMinor = $noneMinor")

    // Find an element ----------------------------------------------------------------------------

    // first, last - first, last element fulfilling criteria, throw exception if none found
    val anna = persons.find { it.lastName == "Anna" }
    println("anna = $anna")

    // firstOrNull, lastOrNull	- first, last element fulfilling criteria, returns null if none found
    val lastSenior = persons.lastOrNull() { it.age >= 60 }
    //println("lastSenior = ${lastSenior?.lastName?:"NN"}")

    // indexOfFirst, indexOfLast - position of first, last element fulfilling criteria, -1 if none found
    val lastSeniorIdx = persons.indexOfLast { it.age >= 60 }
    println("lastSeniorIdx = $lastSeniorIdx")

    // Sorting ------------------------------------------------------------------------------------

    // sortedBy, sortedByDescening - sort elements and returns sorted collection based on Comparator
    val sortedPersons = persons.sortedByDescending { it.age }
    println("sortedPersons = $sortedPersons")
    // compareBy - use compareBy for creating Comparator based on property

    // sortWith, sort elements in mutable collection based on Comparator
    val mPersons = persons.toMutableList()
    mPersons
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
    val ageGroupsNames : Map<Int, String> = TODO()
    println("ageGroupsNames = $ageGroupsNames")

    // Partition -----------------------------------------------------------------------------------

    // partition - partitions the elements based on predicate
    val juniorSeniorPart = persons.partition { it.age < 30 }
    println("juniorSeniorPart = $juniorSeniorPart")

}