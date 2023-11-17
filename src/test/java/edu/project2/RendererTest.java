package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RendererTest {

    @Test
    @DisplayName("Testing renderer")
    void testRenderWithoutWay() {
        Maze maze = new Maze(5, 5);

        maze.setCellTypeAt(2 - 1, 2 - 1, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(2 - 1, 3 - 1, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(2 - 1, 4 - 1, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(3 - 1, 4 - 1, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(4 - 1, 4 - 1, Maze.Cell.Type.PASSAGE);

        String result = Renderer.render(maze).trim();

        String expected = """
            ⬛⬛⬛⬛⬛
            ⬛◻️◻️◻️⬛
            ⬛⬛⬛◻️⬛
            ⬛⬛⬛◻️⬛
            ⬛⬛⬛⬛⬛""";
        assertEquals(expected, result);
    }

    @Test
    void testRendererWithWay() {
        Maze maze = new Maze(5, 5);

        maze.setCellTypeAt(2 - 1, 2 - 1, Maze.Cell.Type.WAY);
        maze.setCellTypeAt(2 - 1, 3 - 1, Maze.Cell.Type.WAY);
        maze.setCellTypeAt(2 - 1, 4 - 1, Maze.Cell.Type.WAY);
        maze.setCellTypeAt(3 - 1, 4 - 1, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(4 - 1, 4 - 1, Maze.Cell.Type.PASSAGE);

        List<Maze.Coordinate> path = new ArrayList<>();
        path.add(new Maze.Coordinate(1, 1));
        path.add(new Maze.Coordinate(1, 2));
        path.add(new Maze.Coordinate(1, 3));

        String result = Renderer.render(maze, path).trim();

        String expected = """
            ⬛⬛⬛⬛⬛
            ⬛🔺🔺🔺⬛
            ⬛⬛⬛◻️⬛
            ⬛⬛⬛◻️⬛
            ⬛⬛⬛⬛⬛""";
        assertEquals(expected, result);
    }
}
