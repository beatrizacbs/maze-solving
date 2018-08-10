package util;

import org.opencv.core.Mat;
import structures.Node;
import structures.Pixel;
import structures.enums.PixelType;

/**
 * Crated in order to transform the image on a data structure, for better processing;
 */
public class ImageRender {

    private Mat matrix;
    private Pixel[][] pixelsStruture;

    public ImageRender(Mat matrix)
    {
        this.matrix = matrix;
        this.pixelsStruture = new Pixel[matrix.height()][matrix.width()];
    }

    public void initialize()
    {
        System.out.println("Initializing image rendering...");
        for(int i = 0; i < pixelsStruture.length; i++)
        {
            for (int j = 0; j < pixelsStruture[i].length; j++)
            {
                double[] currentPixel = matrix.get(i, j);
                double color = currentPixel[0];
                Pixel createdPixel = new Pixel(i, j, verifyPixelType(color, getNeighboorsColor(i, j)));
                pixelsStruture[i][j] = createdPixel;
            }

        }
        System.out.println("Image rendering finished.");
    }

    private double[] getNeighboorsColor(int i, int j)
    {
        double[] retorno = new double[4];

        try
        {
            double[] top = matrix.get(i+1, j);
            double colorTop = top[0];
            retorno[0] = colorTop;
        }
        catch (NullPointerException e)
        {
            retorno[0] = 0;
        }

        try
        {
            double[] bottom = matrix.get(i-1, j);
            double colorBottom = bottom[0];
            retorno[1] = colorBottom;
        }
        catch (NullPointerException e)
        {
            retorno[1] = 0;
        }

        try
        {
            double[] left = matrix.get(i, j-1);
            double colorLeft = left[0];
            retorno[2] = colorLeft;
        }
        catch (NullPointerException e)
        {
            retorno[2] = 0;
        }

        try
        {
            double[] right = matrix.get(i, j+1);
            double colorRight = right[0];
            retorno[3] = colorRight;
        }
        catch (NullPointerException e)
        {
            retorno[3] = 0;
        }


        return retorno;


    }

    private PixelType verifyPixelType(double color, double[] neighboorsColor)
    {
        PixelType tipo;
        if(color == 0)
        {
            tipo = PixelType.WALL;
        }
        else
        {
            boolean cimaParede = neighboorsColor[0] == 0;
            boolean baixoParede = neighboorsColor[1] == 0;
            boolean esquerdaParede = neighboorsColor[2] == 0;
            boolean direitaParede = neighboorsColor[3] == 0;
            //Se o for um beco (so pode andar na horizontal ou so pode andar na vertical)
            if ((cimaParede && baixoParede) || (esquerdaParede && direitaParede))
            {
                //Se for um beco na horizontal
                if(cimaParede && baixoParede)
                {
                    //Se for um beco sem saída
                    if(direitaParede || esquerdaParede)
                    {
                        tipo = PixelType.NODE;
                    }
                    else //Se for o meio do beco
                    {
                        tipo = PixelType.PAHT;
                    }
                }
                else //Se for um beco na vertical
                {
                    //Se for um beco sem saída
                    if(cimaParede || baixoParede)
                    {
                        tipo = PixelType.NODE;
                    }
                    else // se for o meio do beco
                    {
                        tipo = PixelType.PAHT;
                    }
                }

            }
            else
            {

                tipo = PixelType.NODE;

            }
        }

        return tipo;

    }

    public Pixel[][] getPixelsStruture()
    {
        return this.pixelsStruture;
    }

}
