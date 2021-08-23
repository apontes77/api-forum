package br.com.pucgo.forumecec.repositories;

import br.com.pucgo.forumecec.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String nameCourse);
}
