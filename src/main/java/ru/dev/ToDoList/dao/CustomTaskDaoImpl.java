package ru.dev.ToDoList.dao;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@RequiredArgsConstructor
public class CustomTaskDaoImpl implements CustomTaskDao {
    private final EntityManager entityManager;

    public Task findByIdAndUserId(long id, long userId) {
        StringBuilder jpql = new StringBuilder("from Task t where t.user.id = ")
                .append(userId)
                .append(" and t.id = ")
                .append(id);
        TypedQuery<Task> typedQuery = entityManager.createQuery(jpql.toString(), Task.class);
        return typedQuery.getSingleResult();
    }

    public List<Task> find(long userId, String substring, boolean includeCompleted)  {
        StringBuilder jpql = new StringBuilder("from Task t ");
        List<String> conditions = new ArrayList<>();

        if (Strings.isNotBlank(substring)) {
            conditions.add("t.description like :desc");
        }

        if (!includeCompleted) {
            conditions.add("t.isCompleted = false");
        }

        jpql.append("where t.user.id = ").append(userId);

        if (!conditions.isEmpty()) {
            jpql.append(" and ").append(String.join(" and ", conditions));
        }

        TypedQuery<Task> typedQuery = entityManager.createQuery(jpql.toString(), Task.class);

        if (Strings.isNotBlank(substring)) {
            typedQuery.setParameter("desc","%"+substring+"%");
        }

        return typedQuery.getResultList();
    }

    public Task build(String description, User user) {
        Task t = new Task();
        t.setDescription(description);
        t.setUser(user);
        return t;
    }

    public boolean update(long taskId, long userId, Consumer<Task> command) {
        Optional<Task> task = Optional.ofNullable(this.findByIdAndUserId(taskId, userId));
        if (task.isPresent()) {
            var updatedTask = task.get();
            command.accept(updatedTask);
            entityManager.merge(updatedTask);
            return true;
        }
        return false;
    }
}
