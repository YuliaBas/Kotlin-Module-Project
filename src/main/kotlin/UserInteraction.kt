import java.util.InputMismatchException
import java.util.Scanner


object UserInteraction {
    val scanner = Scanner(System.`in`)
    private val MENU_TEMPLATES_TITLE = listOf("Список архивов:", "Список заметок:", "Просмотр заметки: ")
    private val MENU_TEMPLATES_FIRST_COMMAND = listOf("Создать архив", "Создать заметку", "Вернуться к списку заметок")
    private val MENU_TEMPLATES_LAST_COMMAND = listOf("Выход", "Вернуться к списку архивов", "")

    fun showGreeting() {
        println("————————————————————————————————————————————————————")
        println("Программа \"Заметки\" запущена!")
    }
    fun <T: Printable> showCurrentMenu(level: Int, source: MutableList<T>) {
        println("————————————————————————————————————————————————————")
        println(MENU_TEMPLATES_TITLE[level])
        println("0: ${MENU_TEMPLATES_FIRST_COMMAND[level]}")
        source.forEachIndexed { index, item ->          //вывод пунктов меню открыть архив / открыть заметку
            println("${index + 1}: ${item.getTitleForMenu()}")
        }
        //вывод команды для возврата/выхода из программы для меню выбора архива и меню выбора заметки
        if (level < 2) println("${source.size + 1}: ${MENU_TEMPLATES_LAST_COMMAND[level]}")
        println("——> Введите цифру, соответствующую пункту в меню")
        //на экране просмотра заметки дополнительно выводится текст заметки
        if (level == 2) println("\nТекст заметки: \n${allArchives[NoteAppState.currentArchive].notes[NoteAppState.currentNote].text}")
        println("————————————————————————————————————————————————————")
    }
    fun getCommandFromUser(menuSourceSize: Int): Int{
        var command = -1
        try {
            command = scanner.nextInt()
            if (command > menuSourceSize + 1) {
                command = -1
                println("Ошибка! Введенная команда выходит за границы меню. Пожалуйста, повторите ввод")
            }
        } catch (e: InputMismatchException) {
            println("Ошибка! Ожидалась ЦИФРА. Пожалуйста, повторите ввод")
        }
        scanner.nextLine()  //для корректного распознавания следующего ввода
        return command
    }

    fun getStringFromUser(message: String) : String{
        var string: String
        while (true){
            println(message)
            string = scanner.nextLine()
            if (string.isBlank()) {
                println("Ошибка! Ожидались СИМВОЛЫ. Пожалуйста, повторите ввод")
                continue
            } else {
                return string
            }
        }
    }

}