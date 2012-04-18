
package visao;

import Mapas.DeathbringsRise;
import Mapas.LowerCitadel;
import Mapas.LowerCitadel2;
import Mapas.RampartSkulls;
import javaPlay.GameEngine;


    public class Begin {
public static void main(String args[]) {
    
    
    GameEngine.getInstance().addGameStateController( 1 , new LowerCitadel());
    GameEngine.getInstance().addGameStateController( 2 , new LowerCitadel2());
    GameEngine.getInstance().addGameStateController( 3 , new DeathbringsRise());
    GameEngine.getInstance().setStartingGameStateController( 3 );
    GameEngine.getInstance().setFramesPerSecond(60);
    GameEngine.getInstance().run();

}

    }
