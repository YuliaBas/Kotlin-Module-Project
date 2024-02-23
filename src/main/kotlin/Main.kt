import UserInteraction.getCommandFromUser
import UserInteraction.scanner
import UserInteraction.showCurrentMenu
import UserInteraction.showGreeting

object NoteAppState {
    var menuLevel = 0          //уровень глубины меню
    var currentArchive = -1
    var currentNote = -1
}

val allArchives = mutableListOf<Archive>()   //все архивы заметок
fun main() {
    showGreeting()
    while (NoteAppState.menuLevel >= 0) {
        val source =
            when (NoteAppState.menuLevel) {     //список для пунктов меню для команд открыть архив / открыть заметку
                0 -> allArchives
                1 -> allArchives[NoteAppState.currentArchive].notes
                else -> mutableListOf<Nothing>()
            }
        showCurrentMenu(NoteAppState.menuLevel, source)  //вывод меню в консоль
        val command = getCommandFromUser(source.size)             //чтение пользовательского ввода
        performAction(NoteAppState.menuLevel, command, source)    //выполнение выбранного действия
    }
}

fun performAction(menuLevel: Int, command: Int, source: MutableList<out Printable>) {
    when (menuLevel) {
        0 -> when (command) {
            0 -> createNewArchive()
            in 1..source.size -> {   //выбран существующий архив
                NoteAppState.menuLevel++   //переходим на следующий уровень вложенности меню
                NoteAppState.currentArchive = command - 1   //индекс выбранного пользователем архива
            }
            source.size + 1 -> toPreviousLevel()
        }

        1 -> when (command) {
            0 -> createNewNote(allArchives[NoteAppState.currentArchive])
            in 1..source.size -> {   //выбрана существующая заметка
                NoteAppState.menuLevel++
                NoteAppState.currentNote = command - 1  //индекс выбранной пользователем заметки
            }
            source.size + 1 -> toPreviousLevel()
        }

        2 -> when (command) {
            0 -> toPreviousLevel()
        }
    }
}

fun toPreviousLevel() {     //переходим на предыдущий уровень вложенности меню
    NoteAppState.menuLevel--
    if (NoteAppState.menuLevel < 0) {  //выход из программы
        println("Работа программы завершена")
        scanner.close()
        allArchives.clear()
    }
}


