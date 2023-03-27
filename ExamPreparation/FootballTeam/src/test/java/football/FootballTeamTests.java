package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FootballTeamTests {

    private static final int VACANT_POSITIONS = 12;
    private static final String PLAYER_NAME = "Mark";
     private static final String TEAM_NAME = "Mark's team";
    private Footballer footballer;
    private FootballTeam footballTeam;

    @Before
    public void setUp() {
        this.footballer = new Footballer(PLAYER_NAME);
        this.footballTeam = new FootballTeam(TEAM_NAME, VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatingTeamWithNoPositions() {
        new FootballTeam("test_name", -1);
    }

    @Test
    public void testCreatingTeamWithActualPositions_ShouldSetCorrectPositionsCount(){
        Assert.assertEquals(VACANT_POSITIONS, footballTeam.getVacantPositions());
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithNullName_ShouldFail() {
        new FootballTeam(null, 1);
    }
    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithEmptyName_ShouldFail() {
        new FootballTeam("   ", 1);
    }
    @Test
    public void testCreatingTeamWithName_ShouldCreateTheTeam() {
        Assert.assertEquals(TEAM_NAME, footballTeam.getName());
    }

    @Test
    public void testAddPlayer_ShouldIncreaseTeamMembersCount() {
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerShouldFailWhenTeamIsFull() {
        FootballTeam footballTeam1 = new FootballTeam(TEAM_NAME, 0);
        footballTeam1.addFootballer(footballer);
    }

    @Test
    public void testRemoveFootballer_ShouldReduceTeamCount() {
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1, footballTeam.getCount());

        footballTeam.removeFootballer(footballer.getName());
        Assert.assertEquals(0, footballTeam.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballer_ShouldFailWhenNoSuchPlayer(){
        footballTeam.addFootballer(footballer);
        footballTeam.removeFootballer("not_added");
    }

    @Test
    public void testFootballerForSale_ShouldDeactivatePlayer() {
        footballTeam.addFootballer(footballer);

        footballTeam.footballerForSale(footballer.getName());
        Assert.assertFalse(footballer.isActive());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSale_ShouldFailIfPlayerIsMissing(){
        footballTeam.addFootballer(footballer);
        footballTeam.removeFootballer("not_added");
    }
}
