package com.coffeerice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;

public class Assets {

    public static I18NBundle language;

    public static BitmapFont smallFont;
    public static BitmapFont bigFont;

    public static AtlasRegion background;
    public static AtlasRegion backgroundBoard;
    public static AtlasRegion puzzleSolved;

    public static AtlasRegion title;
    public static AtlasRegion play;
    public static AtlasRegion help;
    public static AtlasRegion kTeam;
    
    public static AtlasRegion pause;
    public static AtlasRegion resume;
    public static AtlasRegion mainMenu;
    
    public static AtlasRegion helpText1;
    public static AtlasRegion helpText2;
    
    public static AtlasRegion gameOver;
    public static AtlasRegion gameClear;
    
    public static NinePatchDrawable black;
    public static AtlasRegion backgroundRatings;

    public static TextureRegionDrawable btAtras;

    public static AtlasRegion pieceEmpty;
    public static AtlasRegion piece3;
    public static AtlasRegion piece9;
    public static AtlasRegion piece27;
    public static AtlasRegion piece81;
    public static AtlasRegion piece243;
    public static AtlasRegion piece729;
    public static AtlasRegion piece2187;
    public static AtlasRegion piece6561;
    public static AtlasRegion piece19683;
    public static AtlasRegion piece59049;
    public static AtlasRegion piece177147;
    public static AtlasRegion bombItemPiece;
    public static AtlasRegion x3ItemPiece;

    public static LabelStyle labelStyleSmallFont;
    public static LabelStyle labelStyleBigFont;
    public static ButtonStyle styleButtonMusic;
    public static ButtonStyle styleButtonPause;
    public static ButtonStyle styleButtonSFX;

    static TextureAtlas atlas;

    private static Music music2;

    static Sound move1;
    static Sound move2;

    public static void loadFont() {
        smallFont = new BitmapFont(Gdx.files.internal("data/font25.fnt"),
                atlas.findRegion("font25"));

        bigFont = new BitmapFont(Gdx.files.internal("data/font45.fnt"),
                atlas.findRegion("font45"));
    }

    private static void loadStyle() {
        labelStyleSmallFont = new LabelStyle(smallFont, Color.WHITE);
        labelStyleBigFont = new LabelStyle(bigFont, Color.WHITE);

		/* Button */
        TextureRegionDrawable btMusicOn = new TextureRegionDrawable(
                atlas.findRegion("btMusic"));
        TextureRegionDrawable btMusicOff = new TextureRegionDrawable(
                atlas.findRegion("btSinMusic"));
        styleButtonMusic = new ButtonStyle(btMusicOn, null, btMusicOff);

		/* Button Sound */
        TextureRegionDrawable buttonSFXOn = new TextureRegionDrawable(
                atlas.findRegion("btSFX"));
        TextureRegionDrawable buttonSFXOff = new TextureRegionDrawable(
                atlas.findRegion("btSinSFX"));
        styleButtonSFX = new ButtonStyle(buttonSFXOn, null, buttonSFXOff);

		/* ImageButton Pause */
        TextureRegionDrawable btPauseUp = new TextureRegionDrawable(
                atlas.findRegion("btPause"));
        TextureRegionDrawable btPauseDown = new TextureRegionDrawable(
                atlas.findRegion("btPauseDown"));
        styleButtonPause = new ButtonStyle(btPauseUp, btPauseDown, null);

    }

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("data/atlasMap.txt"));

        loadFont();
        loadStyle();

        if (MathUtils.randomBoolean())
            background = atlas.findRegion("background");
        else
            background = atlas.findRegion("background2");
        backgroundBoard = atlas.findRegion("backgroundRatings");

        title = atlas.findRegion("title");
        play = atlas.findRegion("play");
        help = atlas.findRegion("help");
        kTeam = atlas.findRegion("kTeam");

        gameOver = atlas.findRegion("gameOver");
        gameClear = atlas.findRegion("gameClear");
        
        pause = atlas.findRegion("pause");
        resume = atlas.findRegion("resume");
        mainMenu = atlas.findRegion("mainMenu");
        
        helpText1 = atlas.findRegion("helpText1");
        helpText2 = atlas.findRegion("helpText2");
        
        black = new NinePatchDrawable(new NinePatch(
                atlas.findRegion("black"), 1, 1, 0, 0));
        backgroundRatings = atlas.findRegion("backgroundRatings");

        puzzleSolved = atlas.findRegion("puzzleSolved");

        pieceEmpty = atlas.findRegion("pieceEmpty");

        piece3 = atlas.findRegion("piece3");
        piece9 = atlas.findRegion("piece9");
        piece27 = atlas.findRegion("piece27");
        piece81 = atlas.findRegion("piece81");
        piece243 = atlas.findRegion("piece243");
        piece729 = atlas.findRegion("piece729");
        piece2187 = atlas.findRegion("piece2187");
        piece6561 = atlas.findRegion("piece6561");
        piece19683 = atlas.findRegion("piece19683");
        piece59049 = atlas.findRegion("piece59049");
        piece177147 = atlas.findRegion("piece177147");
        bombItemPiece = atlas.findRegion("bombItemPiece");
        x3ItemPiece = atlas.findRegion("x3ItemPiece");

        btAtras = new TextureRegionDrawable(atlas.findRegion("btAtlas2"));

        move1 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/move1.mp3"));
        move2 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/move2.mp3"));

        music2 = Gdx.audio.newMusic(Gdx.files
                .internal("data/Sounds/music2.mp3"));

        Settings.load();

        music2.setVolume(0.4f);

        playMusic();

        language = I18NBundle.createBundle(Gdx.files.internal("strings/strings"));
    }

    public static void playMusic() {
        if (Settings.isMusicOn) 
        {
            music2.play();
            music2.setLooping(true);
        }
    }

    public static void pauseMusic() {
        music2.stop();
    }

    public static void playSoundMove() {
        if (Settings.isSoundOn) move1.play(.3f);
    }
    
    public static void playSoundMerge() {
        if (Settings.isSoundOn) move2.play(.3f);
    }
}
