package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ") ?: listOf(null, null)
        val firstName = parts.firstOrNull()
        val lastName = parts.lastOrNull()

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInit = toUpperInitial(firstName)
        val lastInit = toUpperInitial(lastName)
        return when {
            firstInit.isNullOrEmpty() && lastInit.isNullOrEmpty() -> null
            !firstInit.isNullOrEmpty() && !lastInit.isNullOrEmpty() -> firstInit + lastInit
            !firstInit.isNullOrEmpty() -> firstInit
            !lastInit.isNullOrEmpty() -> lastInit
            else -> null
        }
    }

    fun transliteration(payload: String, divider: String = " ") : String {
        val parts = payload.split(divider)
        val firstPart = parts.first()
        val lastPart = parts.last()

        fun transliterate(string: String): String{
            val abcCyr: Array<Char>  =   arrayOf(' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','-', '_')
            val abcLat: Array<String>  = arrayOf(" ","a","b","v","g","d","e","e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u","f","h","c","ch","sh","sh", "","i", "","e","yu","ya","A","B","V","G","D","E","E","Zh","Z","I","I","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sh", "","I", "","E","Yu","Ya","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","-", "_")

            return string.map { abcCyr.indexOf(it) }
                .joinToString("") { abcLat[it] }
        }
        return transliterate(firstPart) + divider + transliterate(lastPart)
    }

    private fun toUpperInitial(value: String?): String? {
        return value?.trim()?.take(1)?.toUpperCase()
        }
    }