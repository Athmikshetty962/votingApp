package com.VotingApplication.controller;

import java.util.List;

import com.VotingApplication.entity.Candidate;
import com.VotingApplication.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/entercandidate")
    public Candidate enterCandidate(@RequestParam String name) {
        return candidateService.enterCandidate(name);
    }

    @PostMapping("/castvote")
    public ResponseEntity<String> castVote(@RequestParam String name) {
        if (isValidCandidate(name)) {
            int voteCount = candidateService.castVote(name);
            return ResponseEntity.ok("Vote counted. New vote count: " + voteCount);
        } else {
            return ResponseEntity.badRequest().body("Invalid candidate name.");
        }
    }


    @GetMapping("/countvote")
    public ResponseEntity<String> countVote(@RequestParam String name) {
        if (isValidCandidate(name)) {
            int voteCount = candidateService.countVote(name);
            return ResponseEntity.ok("Vote count for " + name + ": " + voteCount);
        } else {
            return ResponseEntity.badRequest().body("Invalid candidate name.");
        }
    }

    private boolean isValidCandidate(String name) {
        return candidateService.doesCandidateExist(name);
    }
    @GetMapping("/listvote")
    public List<Candidate> listVote() {
        return candidateService.listVote();
    }

    @GetMapping("/getwinner")
    public String getWinner() {
        return candidateService.getWinner();
    }
}

