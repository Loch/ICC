package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javaPlay.GameObject;
import javaPlay.Scene;
import javaPlay.TileInfo;

public class CenarioComColisaoCima {

    private Scene scene;
    private ArrayList<GameObject> objetos;

    public CenarioComColisaoCima(String sceneFile) throws FileNotFoundException {
        this.scene = new Scene();

        try {
            this.scene.loadFromFile(sceneFile);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        this.objetos = new ArrayList<GameObject>();
    }

    public void adicionaObjeto(GameObject obj) {
        this.objetos.add(obj);
    }

    public void step(long timeElapsed) {
        for (GameObject obj : this.objetos) {
            this.verificaColisao(obj);
        }
    }

    public void draw(Graphics g) {
        this.scene.draw(g);     
        //this.drawDebug(g);
    }

    public boolean temColisaoComTile(GameObject obj, int idTile){
        ArrayList<TileInfo> tiles = this.scene.getTilesFromRect(obj.getPontoMin(), obj.getPontoMax());
        for (TileInfo tile : tiles) {
            if ((tile.id == idTile) && obj.temColisao(tile.getRetangle())) {
                return true;
            }
        }
        return false;
    }

    private void drawDebug(Graphics g){
        for (GameObject obj : this.objetos) {
            Point objMin = new Point(obj.getX(), obj.getY());
            Point objMax = new Point(obj.getX() + obj.getLargura() - 1, obj.getY() + obj.getAltura() - 1);

            ArrayList<TileInfo> tiles = this.scene.getTilesFromRect(objMin, objMax);


            for (TileInfo tile : tiles) {
                if ((tile.id > 0) && obj.temColisao(tile.getRetangle())) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawRect(tile.min.x, tile.min.y, 32, 32);
            }
        }
    }

    private void verificaColisao(GameObject obj) {
        //Considera que n�o existe bloco abaixo
        

        ArrayList<TileInfo> tiles = this.scene.getTilesFromRect(obj.getPontoMin(), obj.getPontoMax());
        DirecaoColisao direcao = null;

        for(TileInfo tile : tiles){
            if ( (tile.id > 0) && obj.temColisao(tile.getRetangle())) {            
                direcao = this.pegaDirecaoColisao(obj, tile);
                this.realizaColisao(obj, tile, direcao);                
            }
        }                
    }

    private DirecaoColisao pegaDirecaoColisao(GameObject obj, TileInfo tile) {
        /**
         * Para calcular  de ond evem a dire��o
         * verifica-se quais o ponto do Tile mais pr�ximo do ponto central do objeto.
         */

        //Considera inicialmente que a colis�o foi de cima para baixo
        DirecaoColisao direcao = DirecaoColisao.CIMA_PARA_BAIXO;
        double distancia = this.calculaDistancia(tile.getTopPoint(), obj.getPontoCentral());
        double distanciaTemp;

        distanciaTemp = this.calculaDistancia(tile.getBottomPoint(), obj.getPontoCentral());
        if(distanciaTemp < distancia ){
            direcao = DirecaoColisao.BAIXO_PARA_CIMA;
            distancia = distanciaTemp;
        }

        distanciaTemp = this.calculaDistancia(tile.getLeftPoint(), obj.getPontoCentral());
        if(distanciaTemp < distancia ){
            direcao = DirecaoColisao.ESQUERDA_PARA_DIREITA;
            distancia = distanciaTemp;
        }

        distanciaTemp = this.calculaDistancia( tile.getRightPoint(), obj.getPontoCentral() );
        if(distanciaTemp < distancia ){
            direcao = DirecaoColisao.DIREITA_PARA_ESQUERDA;
            distancia = distanciaTemp;
        }
      
        return direcao;
    }

    private double calculaDistancia(Point ponto1, Point ponto2){
        int dx = ponto1.x - ponto2.x;
        int dy = ponto1.y - ponto2.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    private void realizaColisao(GameObject obj, TileInfo tile, DirecaoColisao direcao) {
        switch (direcao) {
            case CIMA_PARA_BAIXO:
                obj.setY(tile.min.y - obj.getAltura());               
                break;             
            case BAIXO_PARA_CIMA:
                obj.setY(tile.max.y);               
                break;
            case ESQUERDA_PARA_DIREITA:                
                obj.setX(tile.min.x - obj.getLargura());
                break;
            case DIREITA_PARA_ESQUERDA:
                obj.setX(tile.max.x);
                break;
        }
    }
}
