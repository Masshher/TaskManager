package ru.netology.manedger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.task.Epic;
import ru.netology.task.Meeting;
import ru.netology.task.SimpleTask;
import ru.netology.task.Task;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddTasksUnsuitableForRequest () {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Сходить в магазин"));
        todos.add(new Epic(2, new String[]{"Купить картошку", "Купить свеклу", "Купить капусту"}));
        todos.add(new Meeting(3, "Построка дома", "Дом на горе", "01-03-2025"));
        Task[] actual = todos.search("Разобрать почту");
        Task[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void OneTaskFitsOnRequest () {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Сходить в магазин"));
        todos.add(new Epic(2, new String[]{"Купить картошку", "Купить свеклу", "Купить капусту"}));
        todos.add(new Meeting(3, "Построка дома", "Дом на горе", "01-03-2025"));
        Task[] actual = todos.search("Купить свеклу");
        Task[] expected = {new Epic(2, new String[]{"Купить картошку", "Купить свеклу", "Купить капусту"})};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void MultipleTasksSuitableUponRequest () {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Важно! Сходить в магазин"));
        todos.add(new Epic(2, new String[]{"Важно!", "Купить картошку", "Купить свеклу", "Купить капусту"}));
        todos.add(new Meeting(3, "Важно! Построка дома", "Дом на горе", "01-03-2025"));
        Task[] actual = todos.search("Важно!");
        Task[] expected = {new SimpleTask(1, "Важно! Сходить в магазин"), new Epic(2, new String[]{"Купить картошку", "Купить свеклу", "Купить капусту"}), new Meeting(3, "Важно! Построка дома", "Дом на горе", "01-03-2025") };
        Assertions.assertArrayEquals(expected, actual);
    }
}
