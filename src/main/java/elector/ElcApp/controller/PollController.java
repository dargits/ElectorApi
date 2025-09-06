package elector.ElcApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import elector.ElcApp.dto.request.PollCreateDto;
import elector.ElcApp.dto.response.PollResponseDto;
import elector.ElcApp.dto.response.PollResultDto;
import elector.ElcApp.service.PollService;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<PollResponseDto> createPoll(@RequestBody PollCreateDto pollDto) {
        // Có thể thêm logic kiểm tra userId có phải là admin ở đây hoặc trong service
        PollResponseDto newPoll = pollService.createPoll(pollDto);
        return new ResponseEntity<>(newPoll, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PollResponseDto>> getActivePolls() {
        List<PollResponseDto> polls = pollService.getActivePolls();
        return new ResponseEntity<>(polls, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PollResponseDto> getPollDetails(@PathVariable Integer id) {
        PollResponseDto poll = pollService.getPollDetails(id);
        if (poll != null) {
            return new ResponseEntity<>(poll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<PollResultDto> getPollResults(@PathVariable Integer id) {
        PollResultDto results = pollService.getPollResults(id);
        if (results != null) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}