package berlin.gd;

public class Spiral {

    final static int dim = 20;
    int x = 8;
    int y = 8;
    int size = 1;
    int number = 2;
    int[][] matrix = new int[dim][dim];

    public static void main(String[] args) {
        Spiral spiral = new Spiral();
        spiral.draw();
    }

    public void draw() {
        matrix[y][x] = 1;
        for (int count = 1; count <= 9; count++) {
            drawEdge(size - 1, -1, 0);
            drawEdge(size, 0, -1);
            drawEdge(size, +1, 0);
            drawEdge(size + 1, 0, +1);
            size += 2;
        }
        printMatrix();
        System.out.println();
    }

    private void drawEdge(int to, int dy, int dx) {
        for (int i = 1; i < to; i++) {
            number++;
            matrix[y += dy][x += dx] = (isPrime(number) ? number : 0);
        }
    }

    private void printMatrix() {
        for (int y = 0; y < dim; y++) {
            for (int x = 0; x < dim; x++) {
                if (matrix[y][x] > 0)
                    System.out.print(" X");
                else
                    System.out.print(" .");
            }
            System.out.println();
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
