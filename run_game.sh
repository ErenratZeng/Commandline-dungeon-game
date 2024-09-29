#!/bin/bash

# Step 1: Build the project using Gradle with shadowJar
echo "Building the project with Gradle..."
./gradlew shadowJar

# Check if the build was successful
if [ $? -ne 0 ]; then
  echo "Gradle build failed."
  exit 1
fi

# Step 2: Run the main Game class with predefined inputs and store the output to a file
echo "Running the Game..."
java -jar build/libs/comp2120-fri10_a3_c-all.jar < script/sample_input.txt > script/game_output.txt

# Check if the game ran successfully
if [ $? -ne 0 ]; then
  echo "Game execution failed."
  exit 1
fi

# Step 3: Ensure consistent line endings
echo "Ensuring consistent line endings..."
dos2unix script/game_output.txt
dos2unix script/expected_output.txt

# Step 4: Compare the actual output with the expected output, ignoring whitespace differences
echo "Comparing output with expected result..."
if diff -w script/game_output.txt script/expected_output.txt > /dev/null; then
  echo "Test passed: Output matches expected result."
else
  echo "Test failed: Output does not match expected result."
  echo "Differences:"
  diff -w script/game_output.txt script/expected_output.txt
  exit 1
fi

echo "Game executed successfully. Test passed."
