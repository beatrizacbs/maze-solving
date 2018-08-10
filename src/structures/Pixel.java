package structures;

import structures.enums.PixelType;

public class Pixel {

    private int x;
    private int y;
    private PixelType pixelType;

    public Pixel(int x, int y, PixelType pixelType) {
        this.x = x;
        this.y = y;
        this.pixelType = pixelType;

    }

    public PixelType getPixelType() {
        return pixelType;
    }

    public void setPixelType(PixelType pixelType) {
        this.pixelType = pixelType;
    }

}
