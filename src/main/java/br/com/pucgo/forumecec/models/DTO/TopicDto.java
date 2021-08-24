package br.com.pucgo.forumecec.models.DTO;

import br.com.pucgo.forumecec.models.Topic;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicDto {
    private Long id;
    private String message;
    private String title;
    private LocalDateTime creationDate;


    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.message = topic.getMessage();
        this.title = topic.getTitle();
        this.creationDate = topic.getCreationDate();
    }

    public static Page<TopicDto> convert(Page<Topic> topics) {
        return topics.map(TopicDto::new);
    }
}
