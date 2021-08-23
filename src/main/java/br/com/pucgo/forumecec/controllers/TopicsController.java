package br.com.pucgo.forumecec.controllers;

import br.com.pucgo.forumecec.models.Course;
import br.com.pucgo.forumecec.models.DTO.TopicDto;
import br.com.pucgo.forumecec.models.DTO.TopicDtoDetails;
import br.com.pucgo.forumecec.models.Topic;
import br.com.pucgo.forumecec.models.form.TopicForm;
import br.com.pucgo.forumecec.repositories.CourseRepository;
import br.com.pucgo.forumecec.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/topics")
    public List<TopicDto> list(String courseName) {
        List<Topic> topics;
        if(courseName == null) {
            topics = topicRepository.findAll();
        }
       else {
            topics = topicRepository.findByCourse_Name(courseName);
        }
        return TopicDto.convert(topics);
    }

    @PostMapping("/topics")
    public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm topicForm,
                                             UriComponentsBuilder uriBuilder) {
        Topic topic = topicForm.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/topics/{id}")
    public TopicDtoDetails detail(@PathVariable("id") Long id) {
        Topic topic = topicRepository.getById(id);
        return new TopicDtoDetails(topic);
    }
}
