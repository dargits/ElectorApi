package elector.ElcApp.dto.request;


import lombok.Data;
import java.util.List;

@Data
public class PollCreateDto {
    private String title;
    private String description;
    private Integer userId;
    private List<String> optionTexts;
}