package com.company.taskmanagement.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "TM_PROJECT", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_TM_PROJECT_UNQ_NAME", columnNames = {"NAME"})
})
@Entity(name = "tm_Project")
public class Project {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "DEFAULT_PROJECT", nullable = false)
    @NotNull
    private Boolean defaultProject = false;

    @Column(name = "DEFAULT_TASK_PRIORITY")
    private String defaultTaskPriority;

    public TaskPriority getDefaultTaskPriority() {
        return defaultTaskPriority == null ? null : TaskPriority.fromId(defaultTaskPriority);
    }

    public void setDefaultTaskPriority(TaskPriority defaultTaskPriority) {
        this.defaultTaskPriority = defaultTaskPriority == null ? null : defaultTaskPriority.getId();
    }

    public Boolean getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(Boolean defaultProject) {
        this.defaultProject = defaultProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstruct() {
        setDefaultTaskPriority(TaskPriority.MEDIUM);
    }

//    @InstanceName
//    @DependsOnProperties({"description", "name"})
//    public String getInstanceName(MetadataTools metadataTools) {
//        return String.format("%s %s",
//                metadataTools.format(description),
//                metadataTools.format(name));
//    }
}