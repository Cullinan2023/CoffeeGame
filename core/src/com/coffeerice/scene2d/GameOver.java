package com.coffeerice.scene2d;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.coffeerice.screens.MainMenuScreen;
import com.coffeerice.Assets;
import com.coffeerice.Settings;
import com.coffeerice.screens.Screens;

public class GameOver extends Group {

    Screens screen;

    public GameOver(final Screens screen, boolean didWin, int time, long score) {
        this.screen = screen;
        setSize(420, 450);
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setPosition(Screens.SCREEN_WIDTH / 2f - getWidth() / 2f, 260);
        setScale(.5f);

        Image background = new Image(Assets.backgroundRatings);
        background.setSize(getWidth(), getHeight());
        addActor(background);

        Image imgGameStateText = new Image(Assets.gameOver);
        if(didWin) 
            imgGameStateText = new Image(Assets.gameClear);
        imgGameStateText.setAlign(Align.center);
        imgGameStateText.setPosition(getWidth() / 2f - imgGameStateText.getWidth() / 2f, 365);
        addActor(imgGameStateText);

        final Table scoreTable = new Table();
        scoreTable.setSize(getWidth(), 180);
        scoreTable.setY(170);
        scoreTable.padLeft(15).padRight(15);

        // ACTUAL TIME
        Label lbTime = new Label(Assets.language.get("time"), Assets.labelStyleChico);
        lbTime.setAlignment(Align.left);

        Label lblNumTime = new Label(time + Assets.language.get("secondAbbreviation"), Assets.labelStyleChico);
        lblNumTime.setAlignment(Align.right);

        // ACTUAL SCORE
        Label lbScore = new Label(Assets.language.get("score"), Assets.labelStyleChico);
        lbScore.setAlignment(Align.left);

        Label lbNumScore = new Label(score + "", Assets.labelStyleChico);
        lbNumScore.setAlignment(Align.right);

        // BEST MOVES
        Label lbBestScore = new Label(Assets.language.get("bestScore"), Assets.labelStyleChico);
        lbBestScore.setAlignment(Align.left);

        Label lbBestNumScore = new Label(Settings.bestScore + "", Assets.labelStyleChico);
        lbBestNumScore.setAlignment(Align.right);

        scoreTable.add(lbTime).left();
        scoreTable.add(lblNumTime).right().expand();

        scoreTable.row();
        scoreTable.add(lbScore).left();
        scoreTable.add(lbNumScore).right().expand();

        scoreTable.row();
        scoreTable.add(lbBestScore).left();
        scoreTable.add(lbBestNumScore).right().expand();
        

        final Image imgMainMenu = new Image(Assets.mainMenu);
        imgMainMenu.setPosition(getWidth() / 2f - imgMainMenu.getWidth() / 2f, 30);
        screen.addEffectPress(imgMainMenu);
        imgMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.changeScreenWithFadeOut(MainMenuScreen.class, screen.game);
            }
        });

        addAction(Actions.sequence(Actions.scaleTo(1, 1, .2f), Actions.run(new Runnable() {

            @Override
            public void run() {
                addActor(scoreTable);
                addActor(imgMainMenu);
            }
        })));

    }
}
