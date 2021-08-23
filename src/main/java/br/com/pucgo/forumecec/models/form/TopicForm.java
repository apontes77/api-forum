package br.com.pucgo.forumecec.models.form;

import br.com.pucgo.forumecec.models.Course;
import br.com.pucgo.forumecec.models.Topic;
import br.com.pucgo.forumecec.repositories.CourseRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;
    @NotNull
    @NotEmpty
    private String nameCourse;

    public Topic convert(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(nameCourse);
        return new Topic(title, message, course);
    }
}
