import processing.core.PApplet;

public class Utility {

    // solid color
    public static void solidFill(PApplet starbucks, int startX, int startY, int width, int height, int color) {
        starbucks.noFill();

        int endX = startX + width;
        int endY = startY + height;

        for (int i = startY; i <= endY; i++) {
            float inter = PApplet.map(i, startY, endY, 0, 1);
            int c = starbucks.lerpColor(color, color, inter);
            starbucks.stroke(c);

            starbucks.line(startX, i, endX, i);
        }
    }

    // linear gradient: vertical
    public static void setVerticalGradient(PApplet starbucks, int startX, int startY, int width, int height, int startColor, int endColor) {
        starbucks.noFill();

        int endX = startX + width;
        int endY = startY + height;

        for (int i = startY; i <= endY; i++) {
            float inter = PApplet.map(i, startY, endY, 0, 1);
            int c = starbucks.lerpColor(startColor, endColor, inter);
            starbucks.stroke(c);

            starbucks.line(startX, i, endX, i);
        }
    }
    
    // linear gradient: vertical with 1 stop
    public static void setVerticalGradient(PApplet starbucks, int startX, int startY, int width, int height, int stop, int startColor, int midColor, int endColor) {
        starbucks.noFill();

        int endX = startX + width;
        int endY = startY + height;

        for (int i = startY; i <= stop; i++) {
            float inter = PApplet.map(i, startY, stop, 0, 1);
            int c = starbucks.lerpColor(startColor, midColor, inter);
            starbucks.stroke(c);

            starbucks.line(startX, i, endX, i);
        }

        for (int i = stop; i <= endY; i++) {
            float inter = PApplet.map(i, stop, endY, 0, 1);
            int c = starbucks.lerpColor(midColor, endColor, inter);
            starbucks.stroke(c);

            starbucks.line(startX, i, endX, i);
        }
    }
}
