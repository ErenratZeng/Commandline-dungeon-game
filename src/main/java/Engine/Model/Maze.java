package Engine.Model;

import Engine.GameConfig;
import Game.Model.Transition.Exit;

import java.lang.reflect.InvocationTargetException;

public class Maze {
    private MazeElement[][] layout;
    private int sizeRow;
    private int sizeCol;

    public Maze(int[] map_size, GameConfig config) {
        try {
           if (map_size.length < 2) throw new IllegalArgumentException("The map size is malformed");
           sizeRow = map_size[0];
           sizeCol = map_size[1];
           layout = new MazeElement[sizeRow][sizeCol];
           // add all the characters to the layout
           for (GameConfig.ElementConfig c : config.getCharacters()) {
               Class<?> aClass = Class.forName(c.getClassname());
               for (int [] p : c.getPositions()) {
                   if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getClassname());
                   Object elementObject = aClass.getDeclaredConstructor(int.class, int.class).newInstance(p[0], p[1]);
                   if (! (elementObject instanceof Character character)) throw new IllegalArgumentException("The Character class is not appropriate for " + c.getClassname());
                   if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getClassname() + " overlaps it's position with another element");
                   layout[p[0]][p[1]] = (MazeElement) elementObject;
               }
           }
           // add all the items to the layout
            for (GameConfig.ElementConfig c : config.getItems()) {
                Class<?> aClass = Class.forName(c.getClassname());
                for (int [] p : c.getPositions()) {
                    if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getClassname());
                    Object elementObject = aClass.getDeclaredConstructor(int.class, int.class).newInstance(p[0], p[1]);
                    if (! (elementObject instanceof Item)) throw new IllegalArgumentException("The Item class is not appropriate for " + c.getClassname());
                    if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getClassname() + " overlaps it's position with another element");
                    layout[p[0]][p[1]] = (MazeElement) elementObject;
                }
            }
            // add the exit to the layout
            for (GameConfig.ElementConfig c : config.getTransitions()) {
                Class<?> aClass = Class.forName(c.getClassname());
                for (int[] p : c.getPositions()) {
                    if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getClassname());
                    Object elementObject = aClass.getDeclaredConstructor(int.class, int.class).newInstance(p[0], p[1]);
                    if (!(elementObject instanceof Exit)) throw new IllegalArgumentException("The Exit class is not appropriate for " + c.getClassname());
                    if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getClassname() + " overlaps it's position with another element");
                    layout[p[0]][p[1]] = (MazeElement) elementObject;
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error while constructing maze: " + e.getMessage());
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
               InvocationTargetException e) {
            System.err.println("Error while constructing maze: Maze Elements are malformed " + e.getMessage());
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

    public int getRows(){
        return layout.length;
    }

    public int getCols(){
        return layout[0].length;
    }

    public MazeElement[][] getLayout() {
        return layout;
    }
}