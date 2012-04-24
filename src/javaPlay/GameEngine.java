/*
 * GameEngine
 */

package javaPlay;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.*;

/**
 * @author VisionLab/PUC-Rio
 */
public class GameEngine 
{
    private GameCanvas canvas;
    private Mouse mouse;
    private Keyboard keyboard;

    //kaleu: N�mero de frames por segundo do jogo.
	private int framesPerSecond;
    private long lastTime;
    private long timeElapsed;

    private boolean engineRunning;
    private Hashtable gameStateControllers;
    private GameStateController currGameState;
    private GameStateController nextGameState;
    private static GameEngine instance;

    private GameEngine()
    {
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphDevice = graphEnv.getDefaultScreenDevice();
        GraphicsConfiguration graphicConf = graphDevice.getDefaultConfiguration();

        canvas = new GameCanvas(graphicConf);

        mouse = new Mouse();
        keyboard = new Keyboard();

        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        canvas.addKeyListener(keyboard);

        lastTime = System.currentTimeMillis();
        timeElapsed = System.currentTimeMillis();
        engineRunning = true;
        gameStateControllers = new Hashtable();
        currGameState = null;
        nextGameState = null;
        instance = null;

		this.framesPerSecond = 10;
    }

    public static GameEngine getInstance()
    {
        if(instance == null)
        {
            instance = new GameEngine();
        }
        return instance;
    }

    public GameCanvas getGameCanvas()
    {
        return canvas;
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public Keyboard getKeyboard()
    {
        return keyboard;
    }

    public void addGameStateController(int id, GameStateController gs)
    {
        gameStateControllers.put(id, gs);

        gs.load();
    }

    public void removeGameStateController(int id)
    {
        GameStateController gs = (GameStateController)gameStateControllers.get(id);

        gs.unload();
    }

    public void setStartingGameStateController(int id)
    {
        currGameState = (GameStateController)gameStateControllers.get(id);
    }

    public void setNextGameStateController(int id)
    {
        nextGameState = (GameStateController)gameStateControllers.get(id);
    }

    public void requestShutdown()
    {
        engineRunning = false;
    }

    public void run()
    {
        if(currGameState == null)
        {
            return;
        }

        currGameState.start();

        long currentTime;
        while(engineRunning == true)
        {            
            currentTime = System.currentTimeMillis();

			// Verifica se o tempo necess�rio para o pr�ximo frame j� passou.
			if(!this.timeElapsedToFrame(currentTime)){                
				continue;
				//Pula para pr�xima itera��o.
			}            


            currGameState.step(currentTime - lastTime);            
            this.lastTime = currentTime;
            
            currGameState.draw(canvas.getGameGraphics());

            canvas.swapBuffers();

            if(nextGameState != null)
            {
                currGameState.stop();
                nextGameState.start();

                currGameState = nextGameState;
                nextGameState = null;
            }			
        }

        canvas.dispose();
    }

	public void setFramesPerSecond(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}


	private boolean timeElapsedToFrame(long currentTime){
		if((currentTime - this.timeElapsed) > (1000/this.framesPerSecond)){
            this.timeElapsed = currentTime;
			return true;
		}        
		return false;
	}
}
