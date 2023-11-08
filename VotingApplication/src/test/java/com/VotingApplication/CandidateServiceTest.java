package com.VotingApplication;

import com.VotingApplication.entity.Candidate;
import com.VotingApplication.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CandidateServiceTest {

    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        ConcurrentHashMap<String, Candidate> candidates = new ConcurrentHashMap<>();
        candidates.put("validCandidate", new Candidate("validCandidate", 0));
        candidateService = new CandidateService(candidates);
    }


    @Test
    public void testEnterCandidate() {
        Candidate candidate = candidateService.enterCandidate("newCandidate");
        assertNotNull(candidate);
        assertEquals("newCandidate", candidate.getName());
        assertEquals(0, candidate.getVoteCount());
    }

    @Test
    public void testCastVoteValidCandidate() {
        int voteCount = candidateService.castVote("validCandidate");
        assertEquals(1, voteCount);
    }

    @Test
    public void testCastVoteInvalidCandidate() {
        int voteCount = candidateService.castVote("invalidCandidate");
        assertEquals(-1, voteCount);
    }

    @Test
    public void testCountVoteValidCandidate() {
        int voteCount = candidateService.countVote("validCandidate");
        assertEquals(0, voteCount);
    }

    @Test
    public void testCountVoteInvalidCandidate() {
        int voteCount = candidateService.countVote("invalidCandidate");
        assertEquals(-1, voteCount);
    }

    @Test
    public void testListVote() {
        CandidateService candidateService = new CandidateService(new ConcurrentHashMap<>());
        assertTrue(candidateService.listVote().isEmpty());
    }


    @Test
    public void testGetWinner() {
        when(candidateService.getWinner()).thenReturn("WinnerName");
        String winner = candidateService.getWinner();
        assertEquals("WinnerName", winner);
    }

    @Test
    public void testGetWinnerNoWinner() {
        when(candidateService.getWinner()).thenReturn("No winner yet");
        String winner = candidateService.getWinner();
        assertEquals("No winner yet", winner);
    }
}
