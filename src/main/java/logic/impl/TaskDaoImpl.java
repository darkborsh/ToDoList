package logic.impl;

import logic.TaskDao;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TaskDaoImpl implements TaskDao {
    private final List<Task> storage = new ArrayList<>();
    private long counter = 0;

    @Override
    public void save(Task task) {
        counter++;
        task.setId(counter);
        storage.add(task);
    }

    @Override
    public void delete(long id) {
        int low = 0;
        int high = storage.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (storage.get(mid).getId() < id) {
                low = mid + 1;
            } else if (storage.get(mid).getId() > id) {
                high = mid - 1;
            } else if (storage.get(mid).getId() == id) {
                storage.remove(mid);
                return;
            }
        }
    }

    @Override
    public Stream<Task> find(String substring, boolean excludeCompleted) {
        Stream<Task> result = storage.stream();
        if (excludeCompleted) {
            result = result.filter(t -> !t.isCompleted());
        }
        if (substring != null && !substring.isEmpty()) {
            result = result.filter(t -> t.getDescription().contains(substring));
        }
        return result;
    }

    @Override
    public Optional<Task> get(long id) {
        return storage.stream()
                .filter(t -> t.getId() == id)
                .findAny();
    }
}
