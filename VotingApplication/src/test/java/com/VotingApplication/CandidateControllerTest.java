package com.VotingApplication;

import com.VotingApplication.controller.CandidateController;
import com.VotingApplication.entity.Candidate;
import com.VotingApplication.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CandidateControllerTest {

    @InjectMocks
    private CandidateController candidateController;

    @Mock
    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnterCandidate() {
        Candidate newCandidate = new Candidate("newCandidate", 0);
        when(candidateService.enterCandidate("newCandidate")).thenReturn(newCandidate);
        Candidate response = candidateController.enterCandidate("newCandidate");
        assertEquals(newCandidate, response);
    }

    @Test
    public void testCastVoteValidCandidate() {
        when(candidateService.castVote("validCandidate")).thenReturn(1);
        ResponseEntity<String> response = candidateController.castVote("validCandidate");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Vote counted. New vote count: 1", response.getBody());
    }

    @Test
    public void testCastVoteInvalidCandidate() {
        when(candidateService.castVote("invalidCandidate")).thenReturn(-1);
        ResponseEntity<String> response = candidateController.castVote("invalidCandidate");
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid candidate name.", response.getBody());
    }

    @Test
    public void testCountVoteValidCandidate() {
        when(candidateService.countVote("validCandidate")).thenReturn(5);
        ResponseEntity<String> response = candidateController.countVote("validCandidate");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Vote count for validCandidate: 5", response.getBody());
    }

    @Test
    public void testCountVoteInvalidCandidate() {
        when(candidateService.countVote("invalidCandidate")).thenReturn(-1);
        ResponseEntity<String> response = candidateController.countVote("invalidCandidate");
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid candidate name.", response.getBody());
    }

    @Test
    public void testListVote() {
        List<Candidate> candidateList = new ArrayList<>();
        when(candidateService.listVote()).thenReturn(candidateList);
        List<Candidate> response = candidateController.listVote();
        assertEquals(candidateList, response);
    }

    @Test
    public void testGetWinner() {
        when(candidateService.getWinner()).thenReturn("WinnerName");
        String winner = candidateController.getWinner(); // Changed the type to String
        assertEquals("WinnerName", winner);
    }


    @Test
    public void testGetWinnerNoWinner() {
        when(candidateService.getWinner()).thenReturn("No winner yet");
        String winner = candidateController.getWinner(); // Changed the type to String
        assertEquals("No winner yet", winner);
    }

}
