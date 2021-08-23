package br.com.pucgo.forumecec.models.DTO;

import br.com.pucgo.forumecec.models.Topic;
import br.com.pucgo.forumecec.repositories.TopicRepository;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class TopicFormUpdate {

    @NotEmpty @NotNull @Length(min = 5)
    private String title;
    @NotEmpty @NotNull @Length(min = 10)
    private String message;

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getById(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }
}
