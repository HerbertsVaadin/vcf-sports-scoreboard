package org.vaadin.addons.vcf.sports.scoreboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ScoreboardTeamBlock extends Div {

    private final Div logoContainer = new Div();
    private final Div teamNameAndInfoContainer = new Div();
    private final Div teamNameContainer = new Div();
    private final Div teamInfoContainer = new Div();
    private final Div scoreContainer = new Div();
    private final Div leadIndicatorContainer = new Div();
    private Icon leadIndicator = VaadinIcon.CARET_RIGHT.create();

    public ScoreboardTeamBlock() {
        addClassName("scoreboard-team-block");
        teamNameAndInfoContainer.addClassName("team-name-and-info");
        teamNameAndInfoContainer.add(teamNameContainer, teamInfoContainer);
        teamNameContainer.addClassName("team-name");
        teamInfoContainer.addClassName("team-info");
        logoContainer.addClassName("logo");
        scoreContainer.addClassName("score");
        leadIndicatorContainer.addClassName("lead-indicator-container");
        leadIndicatorContainer.add(leadIndicator);

        add(logoContainer, teamNameAndInfoContainer, leadIndicatorContainer, scoreContainer);
    }

    public void setTeamName(String name) {
        teamNameContainer.setText(name);
    }

    public void setTeamInfo(String info) {
        teamInfoContainer.setText(info);
    }

    public void setScore(int score) {
        scoreContainer.setText(String.valueOf(score));
    }

    public void setLeading(boolean leading) {
        if (leading) {
            addClassName("leading");
            leadIndicatorContainer.setVisible(true);
        } else {
            removeClassName("leading");
            leadIndicatorContainer.setVisible(false);
        }
    }

    public void setLeadIndicator(Icon leadIndicator) {
        this.leadIndicator = leadIndicator;
        leadIndicatorContainer.removeAll();
        leadIndicatorContainer.add(leadIndicator);
    }

    public void setLogo(Component component) {
        logoContainer.removeAll();
        logoContainer.add(component);
    }
}
