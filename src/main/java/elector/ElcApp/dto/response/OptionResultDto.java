package elector.ElcApp.dto.response;

import lombok.Data;

@Data
public class OptionResultDto {
    private Integer optionId;
    private String optionText;
    private Long voteCount;
}