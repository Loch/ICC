
package visão;

import Mapas.LowerCitadel;
import javaPlay.GameEngine;


    public class Begin {
public static void main(String args[]) {
    
    
    GameEngine.getInstance().addGameStateController( 100 , new LowerCitadel());
    GameEngine.getInstance().setStartingGameStateController( 100 );
    GameEngine.getInstance().setFramesPerSecond(60);
    GameEngine.getInstance().run();

}

    }
