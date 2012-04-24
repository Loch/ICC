
package visao;

import Mapas.*;
import javaPlay.GameEngine;


    public class Begin {
        
        
       
public static void main(String args[]) {
  
    
    GameEngine.getInstance().addGameStateController( 1 , new LowerCitadel());
    GameEngine.getInstance().addGameStateController( 2 , new LowerCitadel2());
    GameEngine.getInstance().addGameStateController( 3 , new DeathbringsRise());
    GameEngine.getInstance().addGameStateController( 4 , new UpperReaches());
     GameEngine.getInstance().addGameStateController( 5 , new UpperReaches2());
     GameEngine.getInstance().addGameStateController( 6 , new UpperReaches3());
     GameEngine.getInstance().addGameStateController( 7 , new RoyalQuarters());
    GameEngine.getInstance().addGameStateController( 8 , new RoyalQuarters2());
    GameEngine.getInstance().setStartingGameStateController( 1 );
    GameEngine.getInstance().setFramesPerSecond(60);
    GameEngine.getInstance().run();

}

    }
