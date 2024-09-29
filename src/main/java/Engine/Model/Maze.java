package Engine.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static java.lang.System.exit;

public class Maze {
    private MazeElement[][] layout;
    private int sizeRow;
    private int sizeCol;

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

    public void setElementAt (int X, int Y, MazeElement mazeElement) {
        this.layout[Y][X] = mazeElement;
    }

    public MazeElement getElement(int X, int Y) {
        return this.layout[Y][X];
    }
    public int getRows(){
        return layout.length;
    }

    public int getCols(){
        return layout[0].length;
    }
    }