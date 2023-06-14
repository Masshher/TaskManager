package ru.netology.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {


    @Test
    public void searchInTheTitleOfSimpleTaskPositive() {
        SimpleTask task = new SimpleTask(1, "Оплатить счета");
        String query = "счета";
        boolean expected = true;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchInTheTitleOfSimpleTaskNegative() {
        SimpleTask task = new SimpleTask(1, "Сходить в магазин");
        String query = "зоопарк";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void subtaskSearchPositive () {
        Epic epic = new Epic(1, new String[]{"Подзадача1", "Подзадача2", "Подзадача3"});
        String query = "Подзадача4";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void subtaskSearchIsNegative() {
        Epic epic = new Epic(1, new String[]{"Подзадача1", "Подзадача2", "Подзадача3"});
        String query = "Подзадача1";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void searchInTopicOfTheTaskForTheMeetingPositive() {
        Meeting meeting = new Meeting(1, "topic", "project", "13-06-2023");
        String query = "topic";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchInProjectOfTheTaskForTheMeetingPositive() {
        Meeting meeting = new Meeting(1, "topic", "project", "13-06-2023");
        String query = "project";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchInTaskByMeetingNegative() {
        Meeting meeting = new Meeting(1, "topic", "project", "13-06-2023");
        String query = "13-06-2023";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }
}
