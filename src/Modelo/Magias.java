package Modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Sprite;

public class Magias extends ObjetoComMovimento {

    boolean desativado;
    Direcao direcao;
    int velocidade;
    Sprite sprite;

    public Magias(int x, int y, Direcao direcao) {
        this.desativado = false;
        this.x = x;
        this.y = y;
        this.direcao = direcao;
        this.velocidade = 10;
        try {
            this.sprite = new Sprite("resources/flechas.png", 8, 20, 30);
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: BIXA " + ex.getMessage());
        }



    }

    public void step(long timeElapsed) {
        if (this.desativado) {
            return;
        }
        switch (this.direcao) {
            case DIREITA:
                this.moveDireita(this.velocidade);
                this.sprite.setCurrAnimFrame(1);
                break;
            case ESQUERDA:
                this.moveEsquerda(this.velocidade);
                this.sprite.setCurrAnimFrame(5);
                break;
            case CIMA:
                this.moveCima(this.velocidade);
                this.sprite.setCurrAnimFrame(3);
                break;
            case BAIXO:
                this.moveBaixo(this.velocidade);
                this.sprite.setCurrAnimFrame(8);
                break;
            case DIREITA_CIMA:
                this.moveDireitaCima(this.velocidade);
                this.sprite.setCurrAnimFrame(2);
                break;
            case DIREITA_BAIXO:
                this.moveDireitaBaixo(this.velocidade);
                this.sprite.setCurrAnimFrame(7);
                break;
            case ESQUERDA_CIMA:
                this.moveEsquerdaCima(this.velocidade);
                this.sprite.setCurrAnimFrame(4);
                break;
            case ESQUERDA_BAIXO:
                this.moveEsquerdaBaixo(this.velocidade);
                this.sprite.setCurrAnimFrame(6);
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (this.desativado) {
            return;
        }
        this.sprite.draw(g, this.x, this.y);
    }

    public boolean temColisao(Rectangle retangulo) {
        if (this.desativado) {
            return false;
        }

        if (retangulo.contains(x, y)) {
            this.desativado = true;
            return true;
        } else {
            return false;
        }
    }
}
