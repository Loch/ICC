
package visao;

import Mapas.DeathbringsRise;
import Mapas.LowerCitadel;
import Mapas.LowerCitadel2;
import Mapas.RampartSkulls;
import Mapas.UpperReaches;
import javaPlay.GameEngine;


    public class Begin {
public static void main(String args[]) {
    
    
    GameEngine.getInstance().addGameStateController( 1 , new LowerCitadel());
    GameEngine.getInstance().addGameStateController( 2 , new LowerCitadel2());
    GameEngine.getInstance().addGameStateController( 3 , new DeathbringsRise());
    GameEngine.getInstance().addGameStateController( 4 , new UpperReaches());
    GameEngine.getInstance().setStartingGameStateController( 1 );
    GameEngine.getInstance().setFramesPerSecond(60);
    GameEngine.getInstance().run();

}

    }
