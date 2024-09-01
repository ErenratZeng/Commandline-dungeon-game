package Model;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Maze {
    private String[][] layout;

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
                    layout[i][j] = String.valueOf(row.charAt(j));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[][] getLayout() {
        return layout;
    }
}