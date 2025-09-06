package elector.ElcApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import elector.ElcApp.dto.request.VoteSubmitDto;
import elector.ElcApp.service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitVote(@RequestBody VoteSubmitDto voteDto) {
        boolean success = voteService.submitVote(voteDto);
        if (success) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request nếu không thể submit (ví dụ: đã vote)
        }
    }

    @GetMapping("/hasVoted/{userId}/{pollId}")
    public ResponseEntity<Boolean> hasUserVoted(@PathVariable Integer userId, @PathVariable Integer pollId) {
        boolean hasVoted = voteService.hasUserVoted(userId, pollId);
        return new ResponseEntity<>(hasVoted, HttpStatus.OK);
    }
}