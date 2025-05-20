package katas.gameoflife_tdd;

public class GridParser {

    /*
    public Grid createGrid(String[] strings) throws Exception {
        if(xLengthDiffer(strings)) {
            throw new Exception("different lines have different length.");
        }
        if(strings.length==0) {
            throw new Exception("height must be >0.");
        }

        int width = strings[0].length();
        int height = strings.length;

        Grid grid = new Grid(width, height);

        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell();
                char c = strings[y].charAt(x);
                if(c=='*') {
                    cell.setAlive(true);
                }
                grid.putCellAt(cell,x,y);
            }
        }

        grid.computeCellsNeighbourCounts();

        return grid;
    }

    private boolean xLengthDiffer(String[] strings) {
        if(strings.length==0) {
            return false;
        } else {
            int len = strings[0].length();
            for(int i=1; i<strings.length; i++) {
                if(strings[i].length()!=len) {
                    return true;
                }
            }
        }
        return false;
    }

     */
}
