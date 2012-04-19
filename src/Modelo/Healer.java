package Modelo;

import Modelo.Keys;
import Modelo.Magias;
import Modelo.ObjetoComMovimento;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Sprite;

public class Healer extends ObjetoComMovimento {

    Sprite sprite;
    int vida;
    int mana;
    //Só pode lançar um tiro após o outro com um intervalo de 10 frames.
    int controleTiros = 0;
    int framesContoleTiros = 10;

    public Healer() {
        try {
            this.sprite = new Sprite("resources/healer/healersprite3.png", 8, 45, 75);
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }


        this.x = 90;
        this.y = 150;
        this.vida = 39856;
        this.mana = 43547;
        this.largura = 45;
        this.altura = 75;
    }

    public void step(long timeElapsed) {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();
        if (this.estaMorto()) {
            if (teclado.keyDown(Keys.I)) {
                this.vida += 29856;
            }
        }


        this.controleTiros++;

        if (teclado.keyDown(Keys.J) && teclado.keyDown(Keys.I)) {
            this.sprite.setCurrAnimFrame(8);
            this.moveEsquerdaCima(5);

        } else if (teclado.keyDown(Keys.J) && teclado.keyDown(Keys.K)) {
            this.sprite.setCurrAnimFrame(6);
            this.moveEsquerdaBaixo(5);

        } else if (teclado.keyDown(Keys.L) && teclado.keyDown(Keys.I)) {
            this.sprite.setCurrAnimFrame(2);
            this.moveDireitaCima(5);

        } else if (teclado.keyDown(Keys.L) && teclado.keyDown(Keys.K)) {
            this.sprite.setCurrAnimFrame(4);
            this.moveDireitaBaixo(5);

        } else if (teclado.keyDown(Keys.L)) {
            this.sprite.setCurrAnimFrame(3);
            this.moveDireita(5);

        } else if (teclado.keyDown(Keys.J)) {
            this.sprite.setCurrAnimFrame(7);
            this.moveEsquerda(5);

        } else if (teclado.keyDown(Keys.I)) {
            this.sprite.setCurrAnimFrame(1);
            this.moveCima(5);

        } else if (teclado.keyDown(Keys.K)) {
            this.sprite.setCurrAnimFrame(5);
            this.moveBaixo(5);

        }

    }

    public void draw(Graphics g) {
        if (this.estaMorto()) {
            g.setColor(Color.red);
            g.drawString("O Mage morreu", this.x + 5, this.y - 15);
            g.drawString("Pressione I para continuar", this.x + 5, this.y);
            return;
        }
        g.setColor(Color.white);
        g.drawString("Vida Healer:" + this.vida, 19, 75);
        g.drawString("Mana Healer:" + this.mana, 19, 90);
        this.sprite.draw(g, this.x, this.y);
    }

    public void perdeVida(int numPontos) {
        this.vida -= numPontos;
    }

    public void heal(int numPontos) {
        this.vida += numPontos;
    }

    public boolean estaMorto() {
        return (this.vida <= 0);
    }

    public boolean podeAtirar() {
        return (this.controleTiros > this.framesContoleTiros);
    }

    public void mana() {
        this.mana -= 716;
    }

    public void gmana() {
        if (mana > 44958) {
            return;
        } else {
            this.mana += 4;
            
        }
    }

    public void gvida() {
        if (vida > 39958) {
            return;
        } else {
         
          this.vida += 1;
        }
    }

    public void rmana() {

        if (mana < 1000) {
            controleTiros = -45;
        }



    }

    public Heal getMagia() {
        //O tiro pode sair de qualquer um dos oito lados.
        //E o x e y inicial do tiro podem sempre ser diferentes

        int xTiro = this.x;
        int yTiro = this.y;
        int tamanhoNave = 70;
        int metadeNave = tamanhoNave / 2;

        switch (this.direcao) {
            case DIREITA:
                xTiro += tamanhoNave;
                yTiro += metadeNave;
                break;
            case ESQUERDA:
                yTiro += metadeNave;
                break;
            case CIMA:
                xTiro += metadeNave;
                break;
            case BAIXO:
                xTiro += metadeNave;
                yTiro += tamanhoNave;
                break;
            case DIREITA_CIMA:
                xTiro += tamanhoNave;
                break;
            case DIREITA_BAIXO:
                xTiro += tamanhoNave;
                yTiro += tamanhoNave;
                break;
            case ESQUERDA_CIMA:
                break;
            case ESQUERDA_BAIXO:
                yTiro += tamanhoNave;
                break;
        }

        this.controleTiros = 0;
        return new Heal(xTiro, yTiro, this.direcao);
    }

    //public boolean temColisao(NaveInimiga nave){
    //     return this.getRetangulo().intersects( nave.getRetangulo() );
    // }
    public Rectangle getRetangulo() {
        return new Rectangle(this.x + 4, this.y + 4, 45, 75);
    }
}
