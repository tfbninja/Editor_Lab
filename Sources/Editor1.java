
import java.util.Random;

/**
 *
 * @author Tim Barber
 */
public class Editor1 implements Ed {

    private String pre, post;
    private boolean shiftKey;

    /**
     * Constructor - requires initialization for variables
     *
     * @param before The text to have before the cursor
     * @param after  The text to have after the cursor
     */
    public Editor1(String before, String after) {
        pre = before;
        post = after;
        shiftKey = false;
    }

    /**
     *
     * @param val The state of the shift key, either true if held down or false
     *            if not held down
     */
    public void shiftShift(boolean val) {
        shiftKey = val;
    }

    /**
     *
     * @return returns the variable for holding the state of the shift key
     */
    public boolean getShift() {
        return shiftKey;
    }

    /**
     *
     * @return returns the text before the cursor
     */
    public String getBefore() {
        return pre;
    }

    /**
     *
     * @return returns the text after the cursor
     */
    public String getAfter() {
        return post;
    }

    /**
     * clears the text before and after the cursor
     */
    public void clear() {
        pre = "";
        post = "";
    }

    /**
     * Effectively moves the cursor one character to the right, by adding the
     * first char of the post-text to the end of the pre-text, and subtracting
     * one char from the beginning of the post-text.
     *
     * @return Returns the new Editor object with updated pre & post vars
     */
    public Editor1 rightArrow() {
        if (post.length() >= 1) {
            return new Editor1(pre + post.substring(0, 1), post.substring(1));
        } else {
            return new Editor1(pre, post);
        }

    }

    /**
     * Effectively moves the cursor one character to the left, by adding the
     * last char of the pre-text to the beginning of the post-text, and
     * subtracting one char from the end of the pre-text.
     *
     * @return Returns the new Editor object with updated pre & post vars
     */
    public Ed leftArrow() {
        if (pre.length() >= 1) {
            return new Editor1(pre.substring(0, pre.length() - 1), (pre.substring(pre.length() - 1)) + post);
        } else {
            return new Editor1(pre, post);
        }
    }

    /**
     *
     * @return Ed (object)
     */
    public Ed delete() {
        if (post.length() > 0) {
            return new Editor1(pre, post.substring(1));
        } else {
            return new Editor1(pre, post);
        }
    }

    /**
     *
     * @return Ed (object)
     */
    public Ed backspace() {
        if (pre.length() > 0) {
            return new Editor1(pre.substring(0, pre.length() - 1), post);
        } else {
            return new Editor1(pre, post);
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public Ed insertString(String s) {
        // used to be insertChar(), but easier just to accept both single and multiple char strings
        if (!shiftKey) {
            //System.out.println("inserting this string >" + s + "<"); //debug
            pre = pre + s;
        } else {
            //System.out.println("inserting this string >" + s.toUpperCase() + "<"); //debug
            pre = pre + s.toUpperCase();
        }
        return new Editor1(pre, post);

    }

    /**
     *
     * @return
     */
    public Ed homeKey() {
        return new Editor1("", pre + post);
    }

    /**
     *
     * @return
     */
    public Ed endKey() {
        return new Editor1(pre + post, "");
    }

    /**
     *
     * @param amt
     * @return
     */
    public Ed yeetus(int amt) { //easter egg :)
        String b = "";
        Random test = new Random();
        for (int i = 0; i <= amt - 1; i++) {
            b += (char) (test.nextInt(430) + 161);
        }
        return new Editor1(b, "");
    }

    /**
     *
     * @return
     */
    public Ed auto() {
        Random test = new Random();
        return new Editor1(pre + (char) (test.nextInt(430) + 161), post);
    }

    @Override
    public String toString() {
        return pre + "||" + post;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Editor1 eddie = new Editor1("1\n", "2\n3");
        System.out.println(eddie + " right arrow = " + eddie.rightArrow());
        System.out.println(eddie + " left arrow = " + eddie.leftArrow());
    }
}
