

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Integer id) {
        boolean deleted = pollService.deletePoll(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/lock")
    public ResponseEntity<Void> lockPoll(@PathVariable Integer id) {
        boolean locked = pollService.lockPoll(id);
        if (locked) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<PollResponseDto>> getAllPolls() {
        List<PollResponseDto> polls = pollService.getAllPolls();
        return new ResponseEntity<>(polls, HttpStatus.OK);
    }
    @PutMapping("/{id}/unlock")
    public ResponseEntity<Void> unlockPoll(@PathVariable Integer id) {
        boolean unlocked = pollService.unlockPoll(id);
        if (unlocked) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}