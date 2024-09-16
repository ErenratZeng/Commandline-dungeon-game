package Engine.Model;

import Engine.GameConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Maze {
    private MazeElement[][] layout;
    private int sizeRow;
    private int sizeCol;
    private int playerX, playerY;
    private List<int[]> enemies;
    private int exitX, exitY;
    private int[][] playerPosition;
    private int[][] enemyPosition;
    private int[][] itemPosition;

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
                   if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getName());
                   Object elementObject = aClass.getDeclaredConstructor(int.class, int.class, char.class).newInstance(p[0], p[1], c.getSymbol());
                   if (! (elementObject instanceof Character)) throw new IllegalArgumentException("The Character class is not appropriate for " + c.getName());
                   if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getName() + " overlaps it's position with another element");
                   layout[p[0]][p[1]] = (MazeElement) elementObject;
               }
           }
           // add all the items to the layout
            for (GameConfig.ElementConfig c : config.getItems()) {
                Class<?> aClass = Class.forName(c.getClassname());
                for (int [] p : c.getPositions()) {
                    if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getName());
                    Object elementObject = aClass.getDeclaredConstructor(int.class, int.class, char.class).newInstance(p[0], p[1], c.getSymbol());
                    if (! (elementObject instanceof Item)) throw new IllegalArgumentException("The Item class is not appropriate for " + c.getName());
                    if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getName() + " overlaps it's position with another element");
                    layout[p[0]][p[1]] = (MazeElement) elementObject;
                }
            }
            // add the exit to the layout
            for (GameConfig.ElementConfig c : config.getExit()) {
                Class<?> aClass = Class.forName(c.getClassname());
                for (int[] p : c.getPositions()) {
                    if (p.length < 2) throw new IllegalArgumentException("Positions malformed for character " + c.getName());
                    Object elementObject = aClass.getDeclaredConstructor(int.class, int.class, char.class).newInstance(p[0], p[1], c.getSymbol());
                    if (!(elementObject instanceof Exit)) throw new IllegalArgumentException("The Exit class is not appropriate for " + c.getName());
                    if (layout[p[0]][p[1]] != null) throw new IllegalArgumentException("The position of " + c.getName() + " overlaps it's position with another element");
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
        char empty_space =  ' ';
        char separator = '|';
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.sizeRow; i++) {
            out.append('|');
            for (int j=0; j <this.sizeCol; j++) {
                MazeElement element = this.layout[i][j];
                if (element == null) out.append('.');
                else out.append(element.symbol);
                out.append('|');
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

    public int[] getPlayerPosition() {
        //TODO
        return null;
    }

    public List<int[]> getEnemiesPositions() {
        //TODO
        return null;
    }

    public int[] getExitPosition() {
        //TODO
        return null;
    }

    public String getTile(int x, int y){
        //TODO
        return null;
    }

    public MazeElement[][] getLayout() {
        return layout;
    }
}