package sanial.netheos.demoapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FaqTagCreation {

    @JsonProperty("QUESTION")
    @NotNull(message = "The question of FAQ is mandatory here.")
    String question;

    @JsonProperty("ANSWER")
    @NotNull(message = "The answer of FAQ is mandatory here.")
    String answer;

    @JsonProperty("TAG_NAME")
    List<String> tagNameList;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tag_name) {
        this.tagNameList = tag_name;
    }
}
