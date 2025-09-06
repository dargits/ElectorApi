package elector.ElcApp.dto.request;

import lombok.Data;

@Data
public class VoteSubmitDto {
    private Integer pollId;
    private Integer optionId;
    private Integer userId;
}