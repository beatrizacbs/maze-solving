import algorothms.AStar;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import structures.Graph;
import structures.Node;
import util.ImageRender;

public class MazeSolving {

    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        System.out.println(Core.VERSION);
        Imgcodecs imgcodecs = new Imgcodecs();
        String file = "C:\\Users\\Marnie\\Desktop\\braid200.png";
        Mat matrix = imgcodecs.imread(file);
        //Graph graph = new Graph(matrix);
        ImageRender imageRender = new ImageRender(matrix);
        imageRender.initialize();
        Graph graph = Graph.getInstance(imageRender.getPixelsStruture());
        Node currentNode = graph.getStartNode();
        AStar aStar = new AStar(graph.getStartNode(), graph.getEndNode());
        //Essa parte de saida da imagem vai ficar sem funcionar por enquanto até eu descobrir um jeito de pegar somente o
        // melhor caminho sem o backtrack. Por enquanto o algoritmo ta mapeando também os passos com backtrack apenas pulando para
        // o próximo nó da fila de prioridade.
        /*while((currentNode.getY() != graph.getEndNode().getY()) && (currentNode.getX() != graph.getEndNode().getX()))
        {

            double[] cor = matrix.get(currentNode.getX(), currentNode.getY());

            matrix.put(currentNode.getX(), currentNode.getY(), cor);
            currentNode = graph.getEndNode();


        }*/

        imgcodecs.imwrite("Output.jpg", matrix);//Writes image back to the file system using values of the modified matrix


    }
}
