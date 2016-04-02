package pl.radekdab.todo;

import java.io.IOException;
import java.util.List;

public interface TaskSaver {
    void save(List<Task> tasks);
    List<Task> restore();
}