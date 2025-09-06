package elector.ElcApp.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollResponseDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private List<OptionResponseDto> options;
}
