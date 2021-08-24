package br.com.pucgo.forumecec.repositories;

import br.com.pucgo.forumecec.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByCourse_Name(String courseName, Pageable pagination);
}
