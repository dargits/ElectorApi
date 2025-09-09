
package elector.ElcApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elector.ElcApp.dto.request.PollCreateDto;
import elector.ElcApp.dto.response.OptionResponseDto;
import elector.ElcApp.dto.response.OptionResultDto;
import elector.ElcApp.dto.response.PollResponseDto;
import elector.ElcApp.dto.response.PollResultDto;
import elector.ElcApp.model.Option;
import elector.ElcApp.model.Poll;
import elector.ElcApp.repository.OptionRepository;
import elector.ElcApp.repository.PollRepository;
import elector.ElcApp.repository.VoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private VoteRepository voteRepository;

    public PollResponseDto createPoll(PollCreateDto pollDto) {
        // Lưu Poll Entity
        Poll poll = new Poll();
        poll.setTitle(pollDto.getTitle());
        poll.setDescription(pollDto.getDescription());
        poll.setUserId(pollDto.getUserId());
        Poll savedPoll = pollRepository.save(poll);

        // Lưu Options Entity và thiết lập mối quan hệ
        List<Option> options = pollDto.getOptionTexts().stream()
                .map(text -> {
                    Option option = new Option();
                    option.setOptionText(text);
                    option.setPoll(savedPoll);
                    return option;
                })
                .collect(Collectors.toList());

        optionRepository.saveAll(options);
        savedPoll.setOptions(options);

        // Chuyển đổi sang DTO để trả về
        return mapToPollResponseDto(savedPoll);
    }

    public List<PollResponseDto> getActivePolls() {
    List<Poll> activePolls = pollRepository.findByIsActive(1);
    return activePolls.stream()
        .map(this::mapToPollResponseDto)
        .collect(Collectors.toList());
    }

    public PollResponseDto getPollDetails(Integer pollId) {
        return pollRepository.findById(pollId)
                .map(this::mapToPollResponseDto)
                .orElse(null);
    }

    public PollResultDto getPollResults(Integer pollId) {
        Poll poll = pollRepository.findById(pollId).orElse(null);
        if (poll == null) {
            return null;
        }

        PollResultDto resultDto = new PollResultDto();
        resultDto.setPollId(poll.getId());
        resultDto.setPollTitle(poll.getTitle());

        List<OptionResultDto> results = poll.getOptions().stream()
                .map(option -> {
                    OptionResultDto optionResult = new OptionResultDto();
                    optionResult.setOptionId(option.getId());
                    optionResult.setOptionText(option.getOptionText());
                    optionResult.setVoteCount(voteRepository.countByOptionId(option.getId()));
                    return optionResult;
                })
                .collect(Collectors.toList());

        resultDto.setResults(results);
        return resultDto;
    }
    
    private PollResponseDto mapToPollResponseDto(Poll poll) {
        List<OptionResponseDto> optionDtos = poll.getOptions().stream()
                .map(option -> {
                    OptionResponseDto optionDto = new OptionResponseDto();
                    optionDto.setId(option.getId());
                    optionDto.setOptionText(option.getOptionText());
                    return optionDto;
                })
                .collect(Collectors.toList());

        PollResponseDto responseDto = new PollResponseDto();
        responseDto.setId(poll.getId());
        responseDto.setTitle(poll.getTitle());
        responseDto.setDescription(poll.getDescription());
        responseDto.setCreatedAt(poll.getCreatedAt());
    responseDto.setIsActive(poll.getIsActive());
        responseDto.setOptions(optionDtos);

        return responseDto;
    }
    public boolean deletePoll(Integer pollId) {
        if (pollRepository.existsById(pollId)) {
            pollRepository.deleteById(pollId);
            return true;
        }
        return false;
    }

    public boolean lockPoll(Integer pollId) {
        return pollRepository.findById(pollId).map(poll -> {
            poll.setIsActive(0);
            pollRepository.save(poll);
            return true;
        }).orElse(false);
    }
    public List<PollResponseDto> getAllPolls() {
        return pollRepository.findAll().stream()
            .map(this::mapToPollResponseDto)
            .collect(Collectors.toList());
    }
    public boolean unlockPoll(Integer pollId) {
        return pollRepository.findById(pollId).map(poll -> {
            poll.setIsActive(1);
            pollRepository.save(poll);
            return true;
        }).orElse(false);
    }
}