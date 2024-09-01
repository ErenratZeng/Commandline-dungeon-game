package Model;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private String[][] layout;
    private int playerX, playerY;
    private List<int[]> enemies;
    private int exitX, exitY;
    private int[][] playerPosition;
    private int[][] enemyPosition;
    private int[][] itemPosition;

    public Maze() {
        this.enemies = new ArrayList<>();
    }

    public void load_map(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            reader.close();

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONArray mapArray = jsonObject.getJSONArray("map");

            int rows = mapArray.length();
            int cols = 0;

            if (rows > 0) {
                cols = mapArray.getString(0).length();
            }

            layout = new String[rows][cols];

            for (int i = 0; i < rows; i++) {
                String row = mapArray.getString(i);

                if (row.length() != cols) {
                    throw new IllegalArgumentException("Map rows have inconsistent lengths");
                }

                for (int j = 0; j < cols; j++) {
                    char tile = row.charAt(j);
                    layout[i][j] = String.valueOf(tile);
                    switch (tile) {
                        case 'p':
                            playerX = i;
                            playerY = j;
                            break;
                        case 'm':
                            enemies.add(new int[]{i, j});
                            break;
                        case 'e':
                            exitX = i;
                            exitY = j;
                            break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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

    public String[][] getLayout() {
        return layout;
    }
}