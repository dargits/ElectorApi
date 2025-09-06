package elector.ElcApp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elector.ElcApp.dto.request.VoteSubmitDto;
import elector.ElcApp.model.Option;
import elector.ElcApp.model.Poll;
import elector.ElcApp.model.User;
import elector.ElcApp.model.Vote;
import elector.ElcApp.repository.OptionRepository;
import elector.ElcApp.repository.PollRepository;
import elector.ElcApp.repository.UserRepository;
import elector.ElcApp.repository.VoteRepository;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private OptionRepository optionRepository;

    public boolean submitVote(VoteSubmitDto voteDto) {
        // Kiểm tra xem người dùng đã vote cho poll này chưa
        if (voteRepository.existsByUserIdAndPollId(voteDto.getUserId(), voteDto.getPollId())) {
            return false;
        }

        Optional<User> userOptional = userRepository.findById(voteDto.getUserId());
        Optional<Poll> pollOptional = pollRepository.findById(voteDto.getPollId());
        Optional<Option> optionOptional = optionRepository.findById(voteDto.getOptionId());

        if (userOptional.isPresent() && pollOptional.isPresent() && optionOptional.isPresent()) {
            Vote vote = new Vote();
            vote.setUser(userOptional.get());
            vote.setPoll(pollOptional.get());
            vote.setOption(optionOptional.get());

            voteRepository.save(vote);
            return true;
        }
        return false;
    }

    public boolean hasUserVoted(Integer userId, Integer pollId) {
        return voteRepository.existsByUserIdAndPollId(userId, pollId);
    }
}