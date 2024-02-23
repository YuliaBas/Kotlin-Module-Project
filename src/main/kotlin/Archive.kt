import UserInteraction.getStringFromUser

class Archive (val title: String, val notes: MutableList<Note> = mutableListOf<Note>()) : Printable {
    override fun getTitleForMenu(): String {
        return title
    }
}

fun createNewArchive()  {
    val newArchive = Archive(getStringFromUser("Введите название нового архива:"))
    println("Архив \"${newArchive.title}\" создан успешно")   //проверка на дубликат имени?
    allArchives.add(newArchive)
}