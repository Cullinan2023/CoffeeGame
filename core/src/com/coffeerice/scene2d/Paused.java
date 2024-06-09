package com.coffeerice.scene2d;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.coffeerice.Assets;
import com.coffeerice.game.GameScreen;
import com.coffeerice.screens.MainMenuScreen;
import com.coffeerice.screens.Screens;

public class Paused extends Group {

    Screens screen;

    public Paused(final Screens screen) {
        this.screen = screen;
        setSize(420, 300);
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setPosition(Screens.SCREEN_WIDTH / 2f - getWidth() / 2f, 260);
        setScale(.5f);

        Image background = new Image(Assets.fondoPuntuaciones);
        background.setSize(getWidth(), getHeight());
        addActor(background);
        
        Image imgPause = new Image(Assets.pause);
        imgPause.setAlign(Align.center);
        imgPause.setPosition(getWidth() / 2f - imgPause.getWidth() / 2f, 230);
        addActor(imgPause);
        
        final Image imgResume = new Image(Assets.resume);
        imgResume.setPosition(getWidth() / 2f - imgResume.getWidth() / 2f, 155);
        screen.addEfectoPress(imgResume);
        imgResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen oGame = (GameScreen) screen;
                remove();
                oGame.setRunning();
            }
        });
        
        final Image imgMainMenu = new Image(Assets.mainMenu);
        // imgMainMenu.setAlign(Align.center);
        imgMainMenu.setPosition(getWidth() / 2f - imgMainMenu.getWidth() / 2f, 65);
        screen.addEfectoPress(imgMainMenu);
        imgMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.changeScreenWithFadeOut(MainMenuScreen.class,
                        screen.game);

            }
        });
        
//        final Label lbMainMenu = new Label(Assets.idiomas.get("menu"), Assets.labelStyleChico);
//        lbMainMenu.setWrap(true);
//        lbMainMenu.setAlignment(Align.center);
//        lbMainMenu
//                .setPosition(getWidth() / 2f - lbMainMenu.getWidth() / 2f, 65);
//        screen.addEfectoPress(lbMainMenu);
//        lbMainMenu.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                screen.changeScreenWithFadeOut(MainMenuScreen.class,
//                        screen.game);
//
//            }
//        });

        addAction(Actions.sequence(Actions.scaleTo(1, 1, .2f),
                Actions.run(new Runnable() {

                    @Override
                    public void run() {
                        addActor(imgMainMenu);
                        addActor(imgResume);

                    }
                })));

    }
}
