package com.javatrap2020.footballmanager.controller;

import com.javatrap2020.footballmanager.controller.model.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootballmanagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @LocalServerPort
    private int port;

    @Before
    public void initialize() {
        Team team = new Team();
        team.setName("Manchester United");
        team.setTeamIdentifier("MAN");
        restTemplate.postForEntity(getRootUrl(), team, Team.class);

        Team team1 = new Team();
        team1.setName("FC Porto");
        team1.setTeamIdentifier("FCP");
        restTemplate.postForEntity(getRootUrl(), team1, Team.class);

        Team team2 = new Team();
        team2.setName("Chelsea");
        team2.setTeamIdentifier("FC Chelsea");
        restTemplate.postForEntity(getRootUrl(), team2, Team.class);

    }

    private String getRootUrl() {
        return "http://localhost:" + port + "/team/";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateNewTeam() {
        Team team = new Team();
        team.setName("Manchester City");
        team.setTeamIdentifier("MC");
        ResponseEntity<Team> postResponse = restTemplate.postForEntity(getRootUrl(), team, Team.class);
        System.out.println(postResponse.getStatusCode() + ": " + postResponse.getBody().toString());
        assertEquals("Manchester City", postResponse.getBody().getName());
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    }

    @Test
    public void testGetTeamByTeamIdentifier() {
        Team team = restTemplate.getForObject(getRootUrl() + "MAN", Team.class);
        System.out.println(team.toString());
        assertEquals(null, team.getName());
    }


    @Test
    public void testGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "all",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getStatusCode() + ": " + response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteTeam() {
        String teamIdentifier = "FC Chelsea";
        Team team = restTemplate.getForObject(getRootUrl() + teamIdentifier, Team.class);
        assertNotNull(team);
        System.out.println("Before delete: " + team.getTeamIdentifier());
        restTemplate.delete(getRootUrl() + teamIdentifier);
        try {
            team = restTemplate.getForObject(getRootUrl() + teamIdentifier, Team.class);
            System.out.println("After delete: " + team.getTeamIdentifier());
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testUpdateTeamData() {
        String teamIdentifier = "FCP";
        Team team = restTemplate.getForObject(getRootUrl() + teamIdentifier, Team.class);
        assertNotNull(team);
        System.out.println("Before update: " + team.toString());
        team.setName("New FCP");
        restTemplate.patchForObject(getRootUrl() + teamIdentifier,  team, Team.class);
        Team updatedTeam = restTemplate.getForObject(getRootUrl() + teamIdentifier, Team.class);
        Assert.assertEquals(null, updatedTeam.getName());
        System.out.println("After update: " + updatedTeam.toString());
    }

}
