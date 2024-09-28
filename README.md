# COMP2120 Assignment 3 - Dungeons Project

#### Team Members
- Qiutong Zeng (u7724723)
- Christo Antony (u7701030)
- Xiaotian  Cheng (u7769920)
- Charles Dino (u7140320)
***

## Overview
The Dungeons project focuses on a simple text-based, rogue-like adventure game dependent on Gradle and JSON to generate an experience wherein the player navigates a series of mazes, collecting items, interacting with NPCs and battling enemies in order to reach the exit.
The premise follows an adventure into a forgotten temple in search of treasure, and the game features interactive entities and obstacles marked with special characters, three levels of varying sizes, and the capacity to view the map overview and the player's health and inventory.

## Installation & Setup
Before running the game, ensure you have one of the following installed:

1. **[Docker](https://www.docker.com/products/docker-desktop/)** (if you want to run the game via Docker).
2. **[JDK17](https://www.oracle.com/au/java/technologies/downloads/#java17)** (for running the game via an IDE).

### Cloning the Repository

First, clone the repository from Git:

```bash
git clone <https://gitlab.cecs.anu.edu.au/u7724723/comp2120-fri10_a3_c.git>
cd <your repository-directory>
```
### **Option 1: Running the Game via Docker**
    
**Step 1:** Build the Docker image.

Once you have Docker installed, keep the docker running in the background and use the terminal's change directory command (cd) to the project repository-directory, allowing you to build the game image using the following command:

```bash
docker build -t game .
```
**Step 2:** Run the Docker container.

After building the image, you can run the game in a Docker container by typing the following command in the terminal:

```bash
docker run -it game
```
This will start the game in the container, and you can begin playing directly from the Docker environment.

### **Option 2**: Running the Game via an IDE (IntelliJ IDEA)
**Step 1:**
Open IntelliJ IDEA. Click on File -> Open and select the directory where the game was cloned.

**Step 2:**
Once the project is opened, IntelliJ IDEA will automatically detect the `build.gradle` file and start syncing the project. Wait for the sync to complete before proceeding.

**Step 3:**
In the project directory, navigate to the `src/main/java/Game` folder. 
Locate the `Game` class.
Right-click on it and select Run 'Game.main()'.
The game will launch in the console, and you can start playing from the IDE.

## Project Licence
This project is licensed under Apache License 2.0. See [LICENSE](https://gitlab.cecs.anu.edu.au/u7724723/comp2120-fri10_a3_c/-/blob/main/LICENSE) file in the root directory for more information.