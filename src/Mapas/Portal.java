package Mapas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Sprite;

public class Portal extends GameObject {

  private Color cor;
  private int vermelhoEVerde = 230;
  Sprite sprite;

  public Portal(int x, int y){
    this.x = x;
    this.y = y;
    this.largura = 100;
    this.altura = 100;
    try {            
            this.sprite = new Sprite("resources/Portal2.png", 1 ,100,100);                        
        } catch (Exception ex) {
           System.out.println("Imagem não encontrada: " + ex.getMessage());
       }
  }

  public void step(long timeElapsed) {
      
      
      
      
  }

  public void draw(Graphics g) {
    
    this.sprite.draw(g, this.x, this.y);
   
  }

}
