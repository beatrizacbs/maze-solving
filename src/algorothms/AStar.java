package algorothms;

import structures.Node;
import structures.enums.VizinhoDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

    private List<Node> listaCaminhoPercorrido;
    private Node startNode;
    private Node endNode;
    private Node noAtual;
    private PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

    public AStar(Node startNode, Node endNode)
    {
        this.startNode = startNode;
        this.endNode = endNode;
        this.listaCaminhoPercorrido = new ArrayList<>();
        init();
    }

    public void init()
    {
        this.noAtual = this.startNode;
        this.listaCaminhoPercorrido.add(noAtual);
        System.out.println("Começou a rodar o while");
        while (!(this.noAtual).equals(this.endNode))
        {
            System.out.println("X: " + noAtual.getX() + " Y: " + noAtual.getY());
            calcularPesos();
            if(priorityQueue.peek() != null)
            {
                noAtual = priorityQueue.poll();
            }
            else
            {
                break;
            }


        }
    }

    private void backTracking() {

    }

    private void calcularPesos() {
        Node[] vizinhos = new Node[]{
                noAtual.getVizinho(VizinhoDirection.CIMA),
                noAtual.getVizinho(VizinhoDirection.BAIXO),
                noAtual.getVizinho(VizinhoDirection.DIREITA),
                noAtual.getVizinho(VizinhoDirection.ESQUERDA)
        };

        for (Node vizinho :
                vizinhos) {
            if (vizinho != null)
            {
                double fator1 = Math.pow(noAtual.getX() - vizinho.getX(), 2);
                double fator2 = Math.pow(noAtual.getY() - vizinho.getY(), 2);
                double distancia = Math.sqrt(fator1 + fator2) + vizinho.getDistanciaPercorrida();
                vizinho.setDistanciaPercorrida(new Double(distancia).intValue());
                if(!this.listaCaminhoPercorrido.contains(vizinho) && !vizinho.equals(noAtual))
                {
                    vizinho.setPesoAtual(new Double(distancia).intValue());
                    priorityQueue.add(vizinho);
                    listaCaminhoPercorrido.add(vizinho);
                }
            }
        }
        
    }

}
