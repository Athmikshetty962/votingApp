package com.VotingApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.VotingApplication.entity.Candidate;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private ConcurrentHashMap<String, Candidate> candidates = new ConcurrentHashMap<>();



    public CandidateService(ConcurrentHashMap<String, Candidate> candidates) {
        this.candidates = candidates;
    }

    public Candidate enterCandidate(String name) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setVoteCount(0);
        candidates.put(name, candidate);
        return candidate;
    }

    public int castVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate != null) {
            candidate.setVoteCount(candidate.getVoteCount() + 1);
            return candidate.getVoteCount();
        }
        return -1; //
    }

    public int countVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate != null) {
            return candidate.getVoteCount();
        }
        return -1;
    }

    public List<Candidate> listVote() {
        return new ArrayList<>(candidates.values());
    }

    public String getWinner() {
        Candidate winner = candidates.values().stream()
                .max((c1, c2) -> Integer.compare(c1.getVoteCount(), c2.getVoteCount()))
                .orElse(null);
        return (winner != null) ? winner.getName() : "No winner yet";
    }
    public boolean doesCandidateExist(String name) {
        // Check if the candidate with the given name exists in your data store (e.g., candidates map).
        return candidates.containsKey(name);
    }


}
