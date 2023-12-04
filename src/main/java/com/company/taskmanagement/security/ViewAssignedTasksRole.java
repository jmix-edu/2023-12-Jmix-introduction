package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.TaskPriority;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.model.RowLevelPredicate;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "view assigned tasks only", code = ViewAssignedTasksRole.CODE)
public interface ViewAssignedTasksRole {
    String CODE = "view-assigned-tasks";

    @JpqlRowLevelPolicy(entityClass = Task.class,
            where = "{E}.assignee.id = :current_user_id")
    void task();


    @PredicateRowLevelPolicy(entityClass = Task.class, actions = RowLevelPolicyAction.CREATE)
    default RowLevelPredicate<Task> taskPredicate() {
        return task -> task.getPriority() != TaskPriority.HIGH;
    }
}