import java.io.File;

/**
 * A class of sprite objects to be manipulated.
 *
 * @author gjenn
 *
 */
public class Sprite {
    /**
     * this is what it looks like.
     */
    private File image;
    /**
     * teh position.
     */
    private int xPos = 0;
    /**
     * teh position.
     */
    private int yPos = 0;
    /**
     * sprite width.
     */
    private int width = 0;
    /**
     * sprite height.
     */
    private int height = 0;
    /**
     * speed in x direction.
     */
    private int xVelocity = 0;
    /**
     * speed in y direction.
     */
    private int yVelocity = 0;
    /**
     * the direction the object is facing.
     */
    private String direction = "left";
    /**
     * default constructor.
     */
    public Sprite() {
    }
    /**
     * constructs the sprite.
     *
     * @param inputImage the image
     * @param inputX the x
     * @param inputY the y
     * @param inputWidth the size
     * @param inputHeight the other size
     */
    public Sprite(final File inputImage, final int inputX, final int inputY, final int inputWidth,
            final int inputHeight) {
        this.xPos = inputX;
        this.yPos = inputY;
        this.image = inputImage;
        this.width = inputWidth;
        this.height = inputHeight;
    }
    /**
     * this gets the image file.
     *
     * @return the file
     */
    public File getImage() {
        return this.image;
    }
    /**
     * gets the x.
     *
     * @return x
     */
    public int getX() {
        return this.xPos;
    }
    /**
     * gets the y.
     *
     * @return y
     */
    public int getY() {
        return this.yPos;
    }
    /**
     * gets width.
     *
     * @return width
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * gets the height.
     *
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * changes x.
     *
     * @param change movement in x direction.
     */
    public void moveX(final int change) {
        this.xPos += change;
    }
    /**
     * changes y.
     *
     * @param change movement in y direction
     */
    public void moveY(final int change) {
        this.yPos += change;
    }
    /**
     * sets x to something.
     *
     * @param pos the x
     */
    public void setX(final int pos) {
        this.xPos = pos;
    }
    /**
     * changes y to something.
     *
     * @param pos the new pos
     */
    public void setY(final int pos) {
        this.yPos = pos;
    }
    /**
     * changes the sprite.
     *
     * @param inputImage the new image
     */
    public void setImage(final File inputImage) {
        this.image = inputImage;
    }
    /**
     * changes width.
     *
     * @param inputWidth new width
     */
    public void setWidth(final int inputWidth) {
        this.width = inputWidth;
    }
    /**
     * changes the height.
     *
     * @param inputHeight new height
     */
    public void setHeight(final int inputHeight) {
        this.height = inputHeight;
    }
    /**
     * sets the direction the image is facing.
     *
     * @param input desired direction
     */
    public void setDirection(final String input) {
        this.direction = input;
    }
    /**
     * gets the direction of a sprite.
     *
     * @return the direction as a string
     */
    public String getDirection() {
        return this.direction;
    }
    /**
     * sets the x velocity.
     *
     * @param input the velocity
     */
    public void setXVelocity(final int input) {
        this.xVelocity = input;
    }
    /**
     * gets the xVelocity.
     *
     * @return the velocity.
     */
    public int getXVelocity() {
        return this.xVelocity;
    }
    /**
     * sets the y velocity.
     *
     * @param input the velocity
     */
    public void setYVelocity(final int input) {
        this.yVelocity = input;
    }
    /**
     * gets the xVelocity.
     *
     * @return the velocity.
     */
    public int getYVelocity() {
        return this.yVelocity;
    }
    /**
     * tells if they touch.
     *
     * @param sprite the other sprite
     * @return true if they touch
     */
    public boolean touches(final Sprite sprite) {
        if (sprite.getX() >= this.getX() - sprite.getWidth()
                && sprite.getX() <= this.getX() + this.getWidth()) {
            if (sprite.getY() >= this.getY() - sprite.getHeight()
                    && sprite.getY() <= this.getY() + this.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
