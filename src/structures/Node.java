package structures;

import structures.enums.VizinhoDirection;

import java.util.List;

public class Node implements Comparable<Node>{

    private int x;
    private int y;
    private double heuristica;
    private int pesoAtual;
    private VizinhoDirection vizinhoProibido;

    /*
    * 0 - cima
    * 1 - baixo
    * 2 - direita
    * 3 - esquerda
    */
    private Node[] vizinhos;

    public Node(int x, int y)
    {
       this.x = x;
       this.y = y;
       this.vizinhos = new Node[4];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(double heuristica) {
        this.heuristica = heuristica;
    }

    public int getPesoAtual() {
        return pesoAtual;
    }

    public void setPesoAtual(int pesoAtual) {
        this.pesoAtual = pesoAtual + new Double(heuristica).intValue();
    }

    public void setVizinho(VizinhoDirection direction, Node node)
    {
        switch (direction)
        {
            case CIMA:
                this.vizinhos[0] = node;
                break;
            case BAIXO:
                this.vizinhos[1] = node;
                break;
            case DIREITA:
                this.vizinhos[2] = node;
                break;
            case ESQUERDA:
                this.vizinhos[3] = node;
                break;
        }
    }

    public Node getVizinho(VizinhoDirection direction)
    {
        Node retorno = null;
        switch (direction)
        {
            case CIMA:
                retorno = this.vizinhos[0];
                break;
            case BAIXO:
                retorno = this.vizinhos[1];
                break;
            case DIREITA:
                retorno = this.vizinhos[2];
                break;
            case ESQUERDA:
                retorno = this.vizinhos[3];
                break;
        }

        return retorno;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")        ";
    }

    @Override
    public int compareTo(Node o) {
        int retorno = 0;
        if (this.getPesoAtual() > o.getPesoAtual())
        {
            retorno = 1;
        }
        else if (this.getPesoAtual() < o.getPesoAtual())
        {
            retorno = -1;
        }

        return retorno;
    }

    public Node getMenorVizinho()
    {
        Node retorno = null;
        for(int i = 0; i < vizinhos.length; i++)
        {
            if(vizinhos[i] != null)
            {
                if(retorno == null)
                {
                    retorno = vizinhos[i];
                }
                else
                {
                    if(vizinhos[i].getPesoAtual() < retorno.getPesoAtual())
                    {
                        retorno = vizinhos[i];
                    }
                }

            }
        }
        return retorno;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node)
        {
            Node no = (Node) obj;
            boolean retorno = (no.getX() == this.getX()) && (no.getY() == this.getY());
            return retorno;

        }

        return false;
    }
}
