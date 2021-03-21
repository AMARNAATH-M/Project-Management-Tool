package io.agileintellligence.fullstack.repositories;

import io.agileintellligence.fullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);
    Project findByProjectIdentifier(String projectId);
}
