import java.awt.event.KeyEvent;
import java.io.File;
import edu.illinois.cs.cs125.lib.zen.Zen;

/**
 * this does the thing.
 *
 * @author gjenn
 *
 */
public class Main {
    /**
     * this is the thing that is done.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        // I'll keep it 1080p for now I guess
        Zen.create(1920, 1080, "stretch");
        // I don't know why this is here but I'll leave it for now
        // ClassLoader classloader = SpriteMoveFlipBuffer.class.getClassLoader();
        Sprite[] xLasers = new Sprite[0];
        Sprite[] tLasers = new Sprite[0];
        Sprite[] asteroids = new Sprite[0];
        Sprite xWing = new Sprite(new File(ClassLoader.getSystemResource("xWingL.png").getFile()),
                100, 100, 128, 128);
        xWing.setDirection("left");
        Sprite tieFighter = new Sprite(
                new File(ClassLoader.getSystemResource("tieFighterL.png").getFile()), 1700, 800,
                128, 128);
        tieFighter.setDirection("left");
        File background = new File(ClassLoader.getSystemResource("space.png").getFile());
        // P1 = X-Wing, P2 = Tie Fighter
        int p1hp = 200;
        int p2hp = 200;
        int p1CoolDown = 10;
        int p2CoolDown = 10;
        boolean playing = true;
        int newGameTimer = 300;
        while (Zen.isRunning()) {
            if (playing) {
                if (xWing.getY() >= 0 && Zen.isVirtualKeyPressed(KeyEvent.VK_UP)) {
                    xWing.moveY(-10);
                    if (xWing.touches(tieFighter)) {
                        xWing.moveY(10);
                    }
                    xWing.setImage(new File(ClassLoader.getSystemResource("xWingU.png").getFile()));
                    xWing.setDirection("up");
                }
                if (xWing.getY() <= Zen.getZenHeight() - xWing.getHeight()
                        && Zen.isVirtualKeyPressed(KeyEvent.VK_DOWN)) {
                    xWing.moveY(10);
                    if (xWing.touches(tieFighter)) {
                        xWing.moveY(-10);
                    }
                    xWing.setImage(new File(ClassLoader.getSystemResource("xWingD.png").getFile()));
                    xWing.setDirection("down");
                }
                if (xWing.getX() >= 0 && Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT)) {
                    xWing.moveX(-10);
                    if (xWing.touches(tieFighter)) {
                        xWing.moveX(+10);
                    }
                    xWing.setImage(new File(ClassLoader.getSystemResource("xWingL.png").getFile()));
                    xWing.setDirection("left");
                }
                if (xWing.getX() <= Zen.getZenWidth() - xWing.getWidth()
                        && Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT)) {
                    xWing.moveX(10);
                    if (xWing.touches(tieFighter)) {
                        xWing.moveX(-10);
                    }
                    xWing.setImage(new File(ClassLoader.getSystemResource("xWingR.png").getFile()));
                    xWing.setDirection("right");
                }
                if (Zen.isVirtualKeyPressed(KeyEvent.VK_M)) {
                    if (p1CoolDown <= 0) {
                        xLasers = addLaserX(xLasers, xWing);
                        p1CoolDown = 10;
                    }
                }
                if (tieFighter.getY() >= 0 && Zen.isVirtualKeyPressed(KeyEvent.VK_W)) {
                    tieFighter.moveY(-10);
                    if (xWing.touches(tieFighter)) {
                        tieFighter.moveY(10);
                    }
                    tieFighter.setImage(
                            new File(ClassLoader.getSystemResource("tieFighterU.png").getFile()));
                    tieFighter.setDirection("up");
                }
                if (tieFighter.getY() <= Zen.getZenHeight() - tieFighter.getHeight()
                        && Zen.isVirtualKeyPressed(KeyEvent.VK_S)) {
                    tieFighter.moveY(10);
                    if (xWing.touches(tieFighter)) {
                        tieFighter.moveY(-10);
                    }
                    tieFighter.setImage(
                            new File(ClassLoader.getSystemResource("tieFighterD.png").getFile()));
                    tieFighter.setDirection("down");
                }
                if (tieFighter.getX() >= 0 && Zen.isVirtualKeyPressed(KeyEvent.VK_A)) {
                    tieFighter.moveX(-10);
                    if (xWing.touches(tieFighter)) {
                        tieFighter.moveX(10);
                    }
                    tieFighter.setImage(
                            new File(ClassLoader.getSystemResource("tieFighterL.png").getFile()));
                    tieFighter.setDirection("left");
                }
                if (tieFighter.getX() <= Zen.getZenWidth() - tieFighter.getWidth()
                        && Zen.isVirtualKeyPressed(KeyEvent.VK_D)) {
                    tieFighter.moveX(10);
                    if (xWing.touches(tieFighter)) {
                        tieFighter.moveX(-10);
                    }
                    tieFighter.setImage(
                            new File(ClassLoader.getSystemResource("tieFighterR.png").getFile()));
                    tieFighter.setDirection("right");
                }
                if (Zen.isVirtualKeyPressed(KeyEvent.VK_R)) {
                    if (p2CoolDown <= 0) {
                        tLasers = addLaserT(tLasers, tieFighter);
                        p2CoolDown = 10;
                    }
                }
                if (Math.random() < 0.025) {
                    asteroids = addAsteroid(asteroids);
                }
                p1CoolDown--;
                p2CoolDown--;
                // move all non-player objects according to their velocity
                for (int i = 0; i < xLasers.length; i++) {
                    xLasers[i].moveX(xLasers[i].getXVelocity());
                    xLasers[i].moveY(xLasers[i].getYVelocity());
                }
                for (int i = 0; i < tLasers.length; i++) {
                    tLasers[i].moveX(tLasers[i].getXVelocity());
                    tLasers[i].moveY(tLasers[i].getYVelocity());
                }
                for (int i = 0; i < asteroids.length; i++) {
                    asteroids[i].moveX(asteroids[i].getXVelocity());
                    asteroids[i].moveY(asteroids[i].getYVelocity());
                }
                // draw all images on the screen.
                Zen.drawImage(background.getAbsolutePath(), 0, 0);
                for (int i = 0; i < xLasers.length; i++) {
                    Zen.drawImage(xLasers[i].getImage().getAbsolutePath(), xLasers[i].getX(),
                            xLasers[i].getY());
                }
                for (int i = 0; i < tLasers.length; i++) {
                    Zen.drawImage(tLasers[i].getImage().getAbsolutePath(), tLasers[i].getX(),
                            tLasers[i].getY());
                }
                for (int i = 0; i < asteroids.length; i++) {
                    Zen.drawImage(asteroids[i].getImage().getAbsolutePath(), asteroids[i].getX(),
                            asteroids[i].getY());
                }
                Zen.drawImage(xWing.getImage().getAbsolutePath(), xWing.getX(), xWing.getY());
                // The order changes which sprite is on top
                // whatever is coded as printing last is on top
                Zen.drawImage(tieFighter.getImage().getAbsolutePath(), tieFighter.getX(),
                        tieFighter.getY());
                for (int i = 0; i < tLasers.length; i++) {
                    if (tLasers[i].touches(xWing)) {
                        tLasers = removeLaser(tLasers, i);
                        i--;
                        p1hp = p1hp - 3;
                    } else if (tLasers[i].getX() < -170 || tLasers[i].getY() < -170
                            || tLasers[i].getX() > 2000 || tLasers[i].getY() > 1100) {
                        tLasers = removeLaser(tLasers, i);
                        i--;
                    }
                }
                for (int i = 0; i < xLasers.length; i++) {
                    if (xLasers[i].touches(tieFighter)) {
                        xLasers = removeLaser(xLasers, i);
                        i--;
                        p2hp = p2hp - 3;
                    } else if (xLasers[i].getX() < -170 || xLasers[i].getY() < -170
                            || xLasers[i].getX() > 2000 || xLasers[i].getY() > 1100) {
                        xLasers = removeLaser(xLasers, i);
                        i--;
                    }
                }
                for (int i = 0; i < asteroids.length; i++) {
                    if (asteroids[i].getX() < -170 || asteroids[i].getY() < -170
                            || asteroids[i].getX() > 2000 || asteroids[i].getY() > 1100) {
                        asteroids = removeAsteroid(asteroids, i);
                        i--;
                    }
                }
                for (int i = 0; i < asteroids.length; i++) {
                    if (asteroids[i].touches(xWing)) {
                        p1hp--;
                    }
                    if (asteroids[i].touches(tieFighter)) {
                        p2hp--;
                    }
                }
                Zen.setFont("Times", 40);
                Zen.setColor(255, 0, 0);
                Zen.drawText("Health: " + p1hp + "/200", 50, 1000);
                Zen.setColor(0, 255, 0);
                Zen.drawText("Health: " + p2hp + "/200", 1600, 1000);
                Zen.flipBuffer();
                Zen.sleep(8);
                if (p1hp <= 0 || p2hp <= 0) {
                    playing = false;
                }
            } else {
                Zen.drawImage(background.getAbsolutePath(), 0, 0);
                Zen.setFont("Times", 80);
                Zen.setColor(255, 255, 255);
                if (p1hp <= 0) {
                    Zen.drawText("Imperial Victory!", 800, 500);
                } else {
                    Zen.drawText("Rebel Victory!", 700, 500);
                }
                newGameTimer--;
                if (newGameTimer < 0) {
                    playing = true;
                    p1hp = 200;
                    p2hp = 200;
                    newGameTimer = 300;
                    for (int i = 0; i < asteroids.length; i++) {
                        asteroids = removeAsteroid(asteroids, i);
                        i--;
                    }
                    xWing.setX(100);
                    xWing.setY(100);
                    tieFighter.setX(1700);
                    tieFighter.setY(800);
                }
                Zen.flipBuffer();
                Zen.sleep(8);
            }
        }
    }
    /**
     * adds a tie fighter laser.
     *
     * @param input the array of lasers.
     * @param player the player firing
     * @return the new array of lasers
     */
    public static Sprite[] addLaserT(final Sprite[] input, final Sprite player) {
        int bulletSpeed = 30;
        Sprite[] result = new Sprite[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i];
        }
        if (player.getDirection() == "left" || player.getDirection() == "right") {
            result[input.length] = new Sprite(
                    new File(ClassLoader.getSystemResource("greenLaserLR.png").getFile()),
                    player.getX() + 57, player.getY() + 57, 63, 13);
            if (player.getDirection() == "left") {
                result[input.length].setXVelocity(-bulletSpeed);
            }
            if (player.getDirection() == "right") {
                result[input.length].setXVelocity(bulletSpeed);
            }
        }
        if (player.getDirection() == "up" || player.getDirection() == "down") {
            result[input.length] = new Sprite(
                    new File(ClassLoader.getSystemResource("greenLaserUD.png").getFile()),
                    player.getX() + 57, player.getY() + 57, 13, 63);
            if (player.getDirection() == "up") {
                result[input.length].setYVelocity(-bulletSpeed);
            }
            if (player.getDirection() == "down") {
                result[input.length].setYVelocity(bulletSpeed);
            }
        }
        return result;
    }
    /**
     * a method to add a new laser to the array or lasers.
     *
     * @param input the starting array
     * @param player the player shooting
     * @return the new array of lasers
     */
    public static Sprite[] addLaserX(final Sprite[] input, final Sprite player) {
        int bulletSpeed = 30;
        Sprite[] result = new Sprite[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i];
        }
        if (player.getDirection() == "left" || player.getDirection() == "right") {
            result[input.length] = new Sprite(
                    new File(ClassLoader.getSystemResource("redLaserLR.png").getFile()),
                    player.getX() + 57, player.getY() + 57, 63, 13);
            if (player.getDirection() == "left") {
                result[input.length].setXVelocity(-bulletSpeed);
            }
            if (player.getDirection() == "right") {
                result[input.length].setXVelocity(bulletSpeed);
            }
        }
        if (player.getDirection() == "up" || player.getDirection() == "down") {
            result[input.length] = new Sprite(
                    new File(ClassLoader.getSystemResource("redLaserUD.png").getFile()),
                    player.getX() + 57, player.getY() + 57, 13, 63);
            if (player.getDirection() == "up") {
                result[input.length].setYVelocity(-bulletSpeed);
            }
            if (player.getDirection() == "down") {
                result[input.length].setYVelocity(bulletSpeed);
            }
        }
        return result;
    }
    /**
     * removes a laser from the set of laser sprites.
     *
     * @param input the set of lasers
     * @param index the number of the laser to be removed
     * @return the new array of lasers
     */
    public static Sprite[] removeLaser(final Sprite[] input, final int index) {
        Sprite[] result = new Sprite[input.length - 1];
        for (int i = 0; i < index; i++) {
            result[i] = input[i];
        }
        for (int i = index + 1; i < input.length; i++) {
            result[i - 1] = input[i];
        }
        return result;
    }
    /**
     * generates an asteroid at a random point on the edge of the screen.
     * @param input the existing array of asteroids
     * @return the array with the new asteroid
     */
    public static Sprite[] addAsteroid(final Sprite[] input) {
        Sprite[] result = new Sprite[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i];
        }
        if (Math.random() < 0.5) {
            if (Math.random() < 0.5) {
                result[input.length] = new Sprite(new File(ClassLoader.getSystemResource("asteroid"
                        + ".png").getFile()), (int) (Math.random() * 1680 + 120), -168, 168, 168);
                result[input.length].setXVelocity((int) (Math.random() * 7 - 3));
                result[input.length].setYVelocity(7);
            } else {
                result[input.length] = new Sprite(new File(ClassLoader.getSystemResource("asteroid."
                        + "png").getFile()), (int) (Math.random() * 1680 + 120), 1080, 168, 168);
                result[input.length].setXVelocity((int) (Math.random() * 7 - 3));
                result[input.length].setYVelocity(-7);
            }
        } else {
            if (Math.random() < 0.5) {
                result[input.length] = new Sprite(new File(ClassLoader.getSystemResource("asteroid"
                        + ".png").getFile()), -168, (int) (Math.random() * 920 + 80), 168, 168);
                result[input.length].setXVelocity(7);
                result[input.length].setYVelocity((int) (Math.random() * 7 - 3));
            } else {
            result[input.length] = new Sprite(new File(ClassLoader.getSystemResource("asteroid"
                    + ".png").getFile()), 1920, (int) (Math.random() * 920 + 80), 168, 168);
            result[input.length].setXVelocity(-7);
            result[input.length].setYVelocity((int) (Math.random() * 7 - 3));
            }
        }
        return result;
    }
    /**
     * removes the asteroid at index.
     * @param input the array of asteroids
     * @param index the index of the asteroid in question
     * @return the new array of asteroids
     */
    public static Sprite[] removeAsteroid(final Sprite[] input, final int index) {
        Sprite[] result = new Sprite[input.length - 1];
        for (int i = 0; i < index; i++) {
            result[i] = input[i];
        }
        for (int i = index + 1; i < input.length; i++) {
            result[i - 1] = input[i];
        }
        return result;
    }
}
