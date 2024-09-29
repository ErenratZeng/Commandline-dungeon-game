package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;
import Game.Model.State.PlayerHealth;

/**
 * Represents a Non-Player Character (NPC) in the game.
 * NPCs are stationary characters that can heal the player when interacted with.
 *
 * Written by Xiaotian Cheng.
 */
public class NPC extends Character {

    /** The amount of health the NPC restores to the player. */
    public static final int HEAL_AMOUNT = 10;

    /** The maximum health of the player. */
    static final int MAX_PLAYER_HEALTH = 100;

    /**
     * Constructs a new NPC at the specified position.
     *
     * @param x The x-coordinate of the NPC's position.
     * @param y The y-coordinate of the NPC's position.
     */
    public NPC(int x, int y) {
        super(x, y, 'N', "NPC");
    }

    /**
     * NPCs are stationary and cannot move.
     *
     * @param direction The direction of movement (ignored for NPCs).
     * @return The current position of the NPC as an array of two integers [x, y].
     */
    @Override
    public int[] moveCharacter(Direction direction) {
        System.out.println("NPCs much prefer to remain stationary, thank you very much.");
        return new int[]{this.x, this.y};
    }

    /**
     * Handles the interaction between the player and the NPC.
     * The NPC heals the player when interacted with.
     *
     * @param engine The game engine, providing access to game state.
     * @return Always returns true, indicating the interaction was successful.
     */
    public boolean onInteract(Engine engine) {
        PlayerHealth playerHealth =  engine.getState(PlayerHealth.class);
        System.out.println("You've met a friendly NPC! He is welling to heal you with 10 health");

        int currentHealth = playerHealth.getValue();
        int newHealth = Math.min(currentHealth + HEAL_AMOUNT, MAX_PLAYER_HEALTH);

        int actualHealAmount = newHealth - currentHealth;
        playerHealth.increaseBy(HEAL_AMOUNT);

        if (newHealth == MAX_PLAYER_HEALTH) {
            System.out.println("Maximum health reached!");
        }

        System.out.println("The NPC healed you for " + actualHealAmount + " HP!");
        System.out.println("Your current HP is: " + newHealth);

        return true;
    }
}