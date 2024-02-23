import UserInteraction.getStringFromUser
interface Printable {
    fun getTitleForMenu():String
}

class Note (val title: String, val text: String) : Printable {
    override fun getTitleForMenu(): String {
        return title
    }
}
fun createNewNote(archive: Archive) {
    val title = getStringFromUser("Введите название новой заметки:")
    val text = getStringFromUser("Введите текст заметки:")
    val newNote = Note(title, text)
    println("Заметка \"$title\" успешно добавлена в архив")   //проверка на дубликат имени?
    archive.notes.add(newNote)
}