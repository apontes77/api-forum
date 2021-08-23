package br.com.pucgo.forumecec.controllers;

import br.com.pucgo.forumecec.models.Course;
import br.com.pucgo.forumecec.models.DTO.TopicDto;
import br.com.pucgo.forumecec.models.DTO.TopicDtoDetails;
import br.com.pucgo.forumecec.models.DTO.TopicFormUpdate;
import br.com.pucgo.forumecec.models.Topic;
import br.com.pucgo.forumecec.models.form.TopicForm;
import br.com.pucgo.forumecec.repositories.CourseRepository;
import br.com.pucgo.forumecec.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm topicForm,
                                             UriComponentsBuilder uriBuilder) {
        Topic topic = topicForm.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDtoDetails> detail(@PathVariable("id") Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if(topic.isPresent()) {
            return ResponseEntity.ok(new TopicDtoDetails(topic.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/topics/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id,
                                           @RequestBody @Valid TopicFormUpdate topicForm) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isPresent()) {
            Topic topic = topicForm.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/topics/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
