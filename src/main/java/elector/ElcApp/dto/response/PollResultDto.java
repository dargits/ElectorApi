package elector.ElcApp.dto.response;
import lombok.Data;
import java.util.List;

@Data
public class PollResultDto {
    private Integer pollId;
    private String pollTitle;
    private List<OptionResultDto> results;
}