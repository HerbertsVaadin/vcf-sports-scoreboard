package org.vaadin.addons.vcf.sports.scoreboard;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

@CssImport("./styles/sports-scoreboard.css")
public class SportsScoreboard extends Div {

    private static final String WINNER_CLASSNAME = "winner";
    private static final String LOSER_CLASSNAME = "loser";
    private static final String DRAW_CLASSNAME = "draw";

    private final ScoreboardTeamBlock homeTeamBlock = new ScoreboardTeamBlock();
    private final ScoreboardTeamBlock awayTeamBlock = new ScoreboardTeamBlock();
    private int homeScore;
    private int awayScore;
    private boolean invertedScoreMode;

    public SportsScoreboard() {
        addClassName("sports-scoreboard");
        Div teamColumn = new Div();
        teamColumn.add(homeTeamBlock, awayTeamBlock);
        add(teamColumn);
        setHomeScore(homeScore);
        setAwayScore(awayScore);
        setHomeTeamName("Home");
        setAwayTeamName("Away");
        setWidth("200px");
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(int score) {
        homeScore = score;
        homeTeamBlock.setScore(score);
        refreshLeader();
    }

    public void setAwayScore(int score) {
        awayScore = score;
        awayTeamBlock.setScore(score);
        refreshLeader();
    }

    public void setLeadIndicator(Icon leadIndicator) {
        homeTeamBlock.setLeadIndicator(leadIndicator);
        awayTeamBlock.setLeadIndicator(leadIndicator);
    }

    private void refreshLeader() {
        if (isAwayWinning()) {
            homeTeamBlock.setLeading(invertedScoreMode);
            awayTeamBlock.setLeading(!invertedScoreMode);
        } else if (isHomeWinning()) {
            homeTeamBlock.setLeading(!invertedScoreMode);
            awayTeamBlock.setLeading(invertedScoreMode);
        } else {
            homeTeamBlock.setLeading(false);
            awayTeamBlock.setLeading(false);
        }
    }

    public boolean isHomeWinning() {
        return (awayScore > homeScore && invertedScoreMode) || (awayScore < homeScore && !invertedScoreMode);
    }

    public boolean isAwayWinning() {
        return (awayScore < homeScore && invertedScoreMode) || (awayScore > homeScore && !invertedScoreMode);
    }

    public void setHomeTeamName(String name) {
        homeTeamBlock.setTeamName(name);
    }

    public void setAwayTeamName(String name) {
        awayTeamBlock.setTeamName(name);
    }

    public void setHomeTeamInfo(String info) {
        homeTeamBlock.setTeamInfo(info);
        setClassName("with-team-info", StringUtils.isNotEmpty(info));
    }

    public void setAwayTeamInfo(String info) {
        awayTeamBlock.setTeamInfo(info);
        setClassName("with-team-info", StringUtils.isNotEmpty(info));
    }

    public void setHomeTeamLogo(Component logo) {
        homeTeamBlock.setLogo(logo);
    }

    public void setAwayTeamLogo(Component logo) {
        awayTeamBlock.setLogo(logo);
    }

    public void determineWinner() {
        if (isHomeWinning()) {
            setWinner(homeTeamBlock);
            setLoser(awayTeamBlock);
            clearDraw();
        } else if (isAwayWinning()) {
            setWinner(awayTeamBlock);
            setLoser(homeTeamBlock);
            clearDraw();
        } else {
            setDraw();
            clearWinner();
        }
    }

    private void setWinner(ScoreboardTeamBlock teamBlock) {
        teamBlock.addClassName(WINNER_CLASSNAME);
        teamBlock.removeClassName(LOSER_CLASSNAME);
    }

    private void setLoser(ScoreboardTeamBlock teamBlock) {
        teamBlock.removeClassName(WINNER_CLASSNAME);
        teamBlock.addClassName(LOSER_CLASSNAME);
    }

    private void setDraw() {
        awayTeamBlock.addClassName(DRAW_CLASSNAME);
        homeTeamBlock.addClassName(DRAW_CLASSNAME);
    }

    private void clearDraw() {
        awayTeamBlock.removeClassName(DRAW_CLASSNAME);
        homeTeamBlock.removeClassName(DRAW_CLASSNAME);
    }

    private void clearWinner() {
        awayTeamBlock.removeClassName(WINNER_CLASSNAME);
        homeTeamBlock.removeClassName(WINNER_CLASSNAME);
        awayTeamBlock.removeClassName(LOSER_CLASSNAME);
        homeTeamBlock.removeClassName(LOSER_CLASSNAME);
    }

    public void finished() {
        determineWinner();
        addClassName("finished");
    }

    public void notFinished() {
        removeClassName("finished");
        clearWinner();
        clearDraw();
    }

    public void setInvertedScoreMode(boolean invertedScoreMode) {
        this.invertedScoreMode = invertedScoreMode;
    }
}
