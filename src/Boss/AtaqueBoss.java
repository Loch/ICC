package Boss;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javaPlay.GameObject;
import javaPlay.Sprite;



public class AtaqueBoss extends ObjetoComMovimento {

    boolean desativado;
    Direcao direcao;
    int velocidade;
    Sprite sprite;
    int numero;
    
    public AtaqueBoss (int x, int y, Modelo.Direcao direcao){
      this.desativado = false;
        this.x = x;
        this.y = y;
        //this.direcao = direcao;
        this.velocidade = 5;
        
        Random gerador = new Random();
        this.numero = gerador.nextInt(7)+1 ;
        System.out.println(this.numero);
        
        
         try {
            this.sprite = new Sprite("resources/boss/fogo.png", 8 , 100 ,100);
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }
        
        
        
    }

  

  

    public void step(long timeElapsed) {
        if(this.desativado){
            return;
        }
         
        
        switch(this.numero){
            case 1:
                this.moveDireita( this.velocidade );
                this.sprite.setCurrAnimFrame(1);
                break;
            case 2:
                this.moveEsquerda( this.velocidade );
                this.sprite.setCurrAnimFrame(2);
                break;
            case 3:
                this.moveCima( this.velocidade );
                this.sprite.setCurrAnimFrame(4);
                break;
            case 4:
                this.moveBaixo( this.velocidade );
                this.sprite.setCurrAnimFrame(3);
                break;
            case 5:
                this.moveDireitaCima( this.velocidade );
                this.sprite.setCurrAnimFrame(7);
                break;
            case 6:
               this.moveDireitaBaixo( this.velocidade );
               this.sprite.setCurrAnimFrame(5);
               break;
           case 7:
                this.moveEsquerdaCima( this.velocidade );
                this.sprite.setCurrAnimFrame(8);
              break;
            case 8:
                this.moveEsquerdaBaixo( this.velocidade );
                this.sprite.setCurrAnimFrame(6);
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(this.desativado){
            return;
        }
       this.sprite.draw(g, this.x, this.y);
    }

    public boolean temColisao(Rectangle retangulo){
        if(this.desativado){
            return false;
        }
        
        if(retangulo.contains(x, y)){
            this.desativado = true;
            return true;            
        } else {
            return false;
        }
    }

}
