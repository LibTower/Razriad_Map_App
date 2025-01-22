package com.example.razryadapi

fun table() {
    val filteredAuthors = filterAuthors(authors, commission = true)
    val table = getTable(filteredAuthors)
    for (i in table) {
        when (i) {
            1 -> print("1. x == $i\n")
            2 -> print("2. x == $i\n")
            3 -> print("3. x == $i\n")
            4 -> print("4. x == $i\n")
            5 -> print("5. x == $i\n")
            6 -> print("6. x == $i\n")
            7 -> print("7. x == $i\n")
            8 -> print("8. x == $i\n")
            9 -> print("9. x == $i\n")
            10 -> print("10. x == $i\n")
            11 -> print("11. x == $i\n")
            12 -> print("12. x == $i\n")
            13 -> print("13. x == $i\n")
            14 -> print("14. x == $i\n")
            15 -> print("15. x == $i\n")
            16 -> print("16. x == $i\n")
            17 -> print("17. x == $i\n")
            18 -> print("18. x == $i\n")
            19 -> print("19. x == $i\n")
            20 -> print("20. x == $i\n")
            21 -> print("21. x == $i\n")
            22 -> print("22. x == $i\n")
            23 -> print("23. x == $i\n")
            24 -> print("24. x == $i\n")
            25 -> print("25. x == $i\n")
            26 -> print("26. x == $i\n")
            27 -> print("27. x == $i\n")
            28 -> print("28. x == $i\n")
            29 -> print("29. x == $i\n")
            30 -> print("30. x == $i\n")
            31 -> print("31. x == $i\n")
            32 -> print("32. x == $i\n")
            33 -> print("33. x == $i\n")
        }
    }
}
