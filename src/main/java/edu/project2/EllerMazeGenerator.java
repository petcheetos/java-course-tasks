package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EllerMazeGenerator implements Generator {


    @Override
    public Maze generate(int height, int width) {
        Random random = new Random();
        List<Cell.Type> possibleTypes = new ArrayList<>();
        possibleTypes.add(Cell.Type.WALL);
        possibleTypes.add(Cell.Type.PASSAGE);

        Cell[][] grid = new Cell[height][width];
        for (int j= 0; j < width; j++) {
            grid[0][j] = new Cell(0, j, Cell.Type.ENTRANCE);
        }
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, possibleTypes.get(random.nextInt(possibleTypes.size())));
            }
        }


        return new Maze(height, width, grid);
    }

}
