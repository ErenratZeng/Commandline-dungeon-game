package Engine.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static java.lang.System.exit;

/**
 * Represents a maze that consists of a 2D grid of {@link MazeElement} objects. The maze is constructed based on
 * a design layout provided in the form of a character array, with each character representing a different element.
 * The maze can be rendered to show its layout and can be modified during gameplay.
 *
 * @author Christo Antony
 */
public class Maze {
    private MazeElement[][] layout;
    private int sizeRow;
    private int sizeCol;

    /**
     * Constructs a maze from a design layout, mapping symbols in the layout to specific {@link MazeElement} classes.
     *
     * @param maze_design the 2D character array representing the maze layout.
     * @param elementMap  a map of characters to class names, where each character in the maze corresponds to a specific element class.
     * @param emptySymbol the character representing an empty space in the maze.
     * @throws IllegalArgumentException if the maze design is invalid (e.g., too small, unknown symbols, or overlapping elements).
     */
    public Maze(char [][] maze_design, HashMap<java.lang.Character, String> elementMap, java.lang.Character emptySymbol) {
        try {
            if (maze_design.length == 0 || maze_design[0].length == 0) throw new IllegalArgumentException("The maze has a size smaller than 1");
            this.sizeRow = maze_design.length;
            this.sizeCol = maze_design[0].length;
            this.layout = new MazeElement[sizeRow][sizeCol];
            for (int i=0; i<sizeRow; i++) {
                for (int j=0; j<sizeCol; j++) {
                    java.lang.Character symbol = maze_design[i][j];
                    if (symbol == emptySymbol) continue;
                    if (! elementMap.containsKey(symbol)) throw new IllegalArgumentException("Unknown Symbol %s found in maze design ".formatted(symbol));
                    String classname = elementMap.get(symbol);
                    Class<?> aClass = Class.forName(classname);
                    Object elementObject = aClass.getDeclaredConstructor(int.class, int.class).newInstance(j, i);
                    if (layout[i][j] != null) throw new IllegalArgumentException("The position of %s overlaps it's position with another element".formatted(classname));
                    layout[i][j] = (MazeElement) elementObject;
                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error while constructing maze: " + e.getMessage());
            exit(-1);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
               InvocationTargetException e) {
            System.err.println("Error while constructing maze: Maze Elements are malformed " + e.getMessage());
            exit(-1);
        }
    }

    /**
     * Renders the maze as a string, where each element is represented by its symbol, and empty spaces are shown as '.'.
     * Each row is separated by '|' characters.
     *
     * @return a string representing the current state of the maze.
     */
    public String renderMaze() {
        char empty_space =  '.';
        char separator = '|';
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.sizeRow; i++) {
            out.append(separator);
            for (int j=0; j <this.sizeCol; j++) {
                MazeElement element = this.layout[i][j];
                if (element == null) out.append(empty_space);
                else out.append(element.symbol);
                out.append(separator);
            }
            out.append('\n');
        }
        return out.toString();
    }

    /**
     * Sets the element at the specified (X, Y) position in the maze.
     *
     * @param X           the x-coordinate where the element should be placed.
     * @param Y           the y-coordinate where the element should be placed.
     * @param mazeElement the element to place in the maze.
     */
    public void setElementAt (int X, int Y, MazeElement mazeElement) {
        this.layout[Y][X] = mazeElement;
    }

    /**
     * Retrieves the element at the specified (X, Y) position in the maze.
     *
     * @param X the x-coordinate of the element to retrieve.
     * @param Y the y-coordinate of the element to retrieve.
     * @return the {@link MazeElement} at the specified position, or null if no element is present.
     */
    public MazeElement getElement(int X, int Y) {
        return this.layout[Y][X];
    }

    /**
     * Gets the number of rows in the maze.
     *
     * @return the number of rows in the maze.
     */
    public int getRows(){
        return layout.length;
    }

    /**
     * Gets the number of columns in the maze.
     *
     * @return the number of columns in the maze.
     */
    public int getCols(){
        return layout[0].length;
    }
}