package model;

public enum Message {

    LINE("----------------------------------------------"),
    ERROR_INPUT("Неправильный ввод. Повторите попытку!"),
    EMPTY_LIST("Список пуст"),
    SUCCESSFUL_OPERATION("Успешная операция"),
    ERROR_OPERATION("Ошибка!"),
    NAME("Введите имя:"),
    ID("Введите ID:");

    private final String message;

     Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
