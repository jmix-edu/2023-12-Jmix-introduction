package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.UiMinimalRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Manager role", code = ProjectManager.CODE, scope = "UI")
public interface ProjectManager extends UiMinimalRole {
    String CODE = "manager-role";

    @MenuPolicy(menuIds = "tm_Project.browse")
    @ScreenPolicy(screenIds = {"tm_Project.browse", "tm_Project.edit", "tm_Task.browse", "tm_User.browse"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.ALL)
    void project();

    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = User.class, attributes = {"username", "firstName", "lastName"}, action = EntityAttributePolicyAction.VIEW)
    void user();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.READ)
    void task();
}