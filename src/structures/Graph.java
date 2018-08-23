package structures;

import structures.enums.PixelType;
import structures.enums.TipoHeuristica;
import structures.enums.VizinhoDirection;

public class Graph {

    private static Graph instance;
    private Pixel[][] pixelMatrix;
    private Node startNode;
    private Node endNode;

    public static Graph getInstance(Pixel[][] pixelMatrix)
    {
        if(instance == null)
        {
            instance = new Graph(pixelMatrix);
        }
        return instance;
    }

    private Graph(Pixel[][] pixelMatrix)
    {
        this.pixelMatrix = pixelMatrix;
        init();
    }

    private void init()
    {
        System.out.println("Initializing graph build...");
        findEndNode();
        findStartNode();
        generateNodes();

    }

    private void findEndNode() {
        System.out.println("Finding end node...");
        int position = 0;
        for (int i = 0; i < pixelMatrix.length; i++)
        {
            if(pixelMatrix[pixelMatrix.length - 1][i].getPixelType() == PixelType.NODE)
            {
                position = i;
                endNode = new Node(pixelMatrix.length - 1, i);
                break;
            }
        }
        this.endNode.setHeuristica(0);
        System.out.println("End node found at position: " + endNode.toString());
    }

    private void generateNodes() {
        System.out.println("Generating graph...");
        Node[] nosAcima = new Node[pixelMatrix[0].length];
        nosAcima[startNode.getY()] = startNode;
        Node anterior = null;
        for(int i = 1; i < pixelMatrix.length; i++)
        {
            anterior = null;
            for (int j = 1; j < pixelMatrix[i].length; j++)
            {
                Pixel pixelAtual = pixelMatrix[i][j];
                switch (pixelAtual.getPixelType())
                {
                    case PAHT:
                       break;
                    case NODE:
                        Node aux = new Node(i, j);
                        aux.setHeuristica(calcularHeuristica(aux, TipoHeuristica.EUCLIDIANA));
                        System.out.println("No encontrado: " + aux.toString());
                        if(nosAcima[j] != null)
                        {
                           System.out.println("Ligou com no: " + nosAcima[j].toString() + VizinhoDirection.CIMA);
                           nosAcima[j].setVizinho(VizinhoDirection.BAIXO, aux);
                           aux.setVizinho(VizinhoDirection.CIMA, nosAcima[j]);
                        }

                        if(anterior != null)
                        {
                           System.out.println("Ligou com no: " + anterior.toString() + VizinhoDirection.ESQUERDA);
                           aux.setVizinho(VizinhoDirection.ESQUERDA, anterior);
                           anterior.setVizinho(VizinhoDirection.DIREITA, aux);
                        }
                        else
                        {
                            System.out.println("No anterior: null" );
                        }

                        anterior = aux;
                        nosAcima[j] = aux;
                        break;
                        case WALL:
                            anterior = null;
                            nosAcima[j] = null;
                            break;
                        case NULL:
                            break;
                   }

           }
        }
        System.out.println("Graph generation finished.");

    }

    private void findStartNode() {

        System.out.println("Finding start node...");
        Pixel[] linhaZero = pixelMatrix[0];
        int position = 0;
        for (int i = 0; i < linhaZero.length; i++)
        {
            if(pixelMatrix[0][i].getPixelType() == PixelType.NODE)
            {
                position = i;
                startNode = new Node(0, i);
                break;
            }
        }
        this.startNode.setHeuristica(calcularHeuristica(this.startNode, TipoHeuristica.EUCLIDIANA));
        System.out.println("Start node found at position: " + position);

    }

    private double calcularHeuristica(Node node, TipoHeuristica tipoHeuristica)
    {
        double retorno = 0.0;
        switch (tipoHeuristica)
        {
            case EUCLIDIANA:
                double fator1 = Math.pow(node.getX() - this.endNode.getX(), 2);
                double fator2 = Math.pow(node.getY() - this.endNode.getY(), 2);
                retorno = Math.sqrt(fator1 + fator2);
                break;
            case MANHATTAN:
                double fatorA = Math.abs(node.getX() - this.endNode.getX());
                double fatorB = Math.abs(node.getY() - this.endNode.getY());
                retorno = fatorA + fatorB;
                break;
            case DIAGONAL:
                break;
        }

        return retorno;
    }

    public Node getStartNode()
    {
        return this.startNode;
    }

    public Node getEndNode()
    {
        return this.endNode;
    }


}
