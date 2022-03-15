package org.vaadin.addons.vcf.sports.scoreboard;


import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends VerticalLayout {

    public AddonView() {
        SportsScoreboard scoreboard = new SportsScoreboard();
        scoreboard.setId("scoreboard");
        HorizontalLayout actionButtons = new HorizontalLayout();
        var scoreAway = new Button();
        var scoreHome = new Button();
        var endGame = new Button();
        var reset = new Button();
        var random = new Random();
        scoreAway.addClickListener(clickEvent -> {
            var scoreToAdd = random.nextInt(2) + 1;
            scoreboard.setAwayScore(scoreboard.getAwayScore() + scoreToAdd);
        });
        scoreAway.setIcon(VaadinIcon.PLUS.create());
        scoreAway.setText("Away");

        scoreHome.addClickListener(clickEvent -> {
            var scoreToAdd = random.nextInt(2) + 1;
            scoreboard.setHomeScore(scoreboard.getHomeScore() + scoreToAdd);
        });
        scoreHome.setIcon(VaadinIcon.PLUS.create());
        scoreHome.setText("Home");

        reset.addClickListener(clickEvent -> {
           scoreboard.setHomeScore(0);
           scoreboard.setAwayScore(0);
           scoreboard.notFinished();
        });
        reset.setIcon(VaadinIcon.TRASH.create());

        endGame.setIcon(VaadinIcon.FLAG_CHECKERED.create());
        endGame.setText("Finish");
        endGame.addClickListener(clickEvent -> {
           scoreboard.finished();
        });
        actionButtons.add(scoreHome, scoreAway, endGame, reset);

        TextField awayName = new TextField("Away name");
        awayName.addValueChangeListener(event -> {
            var name = StringUtils.isEmpty(event.getValue()) ? "Away" : event.getValue();
            scoreboard.setAwayTeamName(name);
        });
        awayName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField homeName = new TextField("Home name");
        homeName.addValueChangeListener(event -> {
            var name = StringUtils.isEmpty(event.getValue()) ? "Home" : event.getValue();
            scoreboard.setHomeTeamName(name);
        });
        homeName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField awayInfo = new TextField("Away info");
        awayInfo.addValueChangeListener(event -> {
            var name = StringUtils.isEmpty(event.getValue()) ? "" : event.getValue();
            scoreboard.setAwayTeamInfo(name);
        });
        awayInfo.setValueChangeMode(ValueChangeMode.EAGER);

        TextField homeInfo = new TextField("Home info");
        homeInfo.addValueChangeListener(event -> {
            var name = StringUtils.isEmpty(event.getValue()) ? "" : event.getValue();
            scoreboard.setHomeTeamInfo(name);
        });
        homeInfo.setValueChangeMode(ValueChangeMode.EAGER);


        var inputFields = new VerticalLayout();
        inputFields.add(awayName, homeName, awayInfo, homeInfo);

        add(scoreboard, actionButtons, inputFields);
    }
}
