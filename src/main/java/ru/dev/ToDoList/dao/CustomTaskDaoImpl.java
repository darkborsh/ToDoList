package ru.dev.ToDoList.dao;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import ru.dev.ToDoList.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomTaskDaoImpl implements CustomTaskDao {
    private final EntityManager entityManager;

    public List<Task> find (long userId, String substring, boolean includeCompleted)  {
        StringBuilder jpql = new StringBuilder("from Task t ");
        List<String> conditions = new ArrayList<>();

        if (Strings.isNotBlank(substring)) {
            conditions.add("t.taskDescription like :desc");
        }

        if (!includeCompleted) {
            conditions.add("t.done = false");
        }

        jpql.append("where t.user.id = ").append(userId);

        if (!conditions.isEmpty()) {
            jpql.append(String.join(" and ", conditions));
        }

        TypedQuery<Task> typedQuery = entityManager.createQuery(jpql.toString(), Task.class);

        if (Strings.isNotBlank(substring)) {
            typedQuery.setParameter("desc","%"+substring+"%");
        }

        return typedQuery.getResultList();
    }
}
