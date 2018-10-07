
import java.util.Random;

public class Editor1 implements Ed {

    private String pre, post;

    public Editor1(String before, String after) {
        pre = before;
        post = after;
    }

    public String toString() {
        return pre + "||" + post;
    }

    public String getBefore() {
        return pre;
    }

    public String getAfter() {
        return post;
    }

    public Editor1 rightArrow() {
        if (post.length() >= 1) {
            return new Editor1(pre + post.substring(0, 1), post.substring(1));
        } else {
            return new Editor1(pre, post);
        }

    }

    public Ed leftArrow() {
        if (pre.length() >= 1) {
            return new Editor1(pre.substring(0, pre.length() - 1), (pre.substring(pre.length() - 1)) + post);
        } else {
            return new Editor1(pre, post);
        }
    }

    public Ed delete() {
        if (post.length() > 0) {
            return new Editor1(pre, post.substring(1));
        } else {
            return new Editor1(pre, post);
        }
    }

    public Ed backspace() {
        if (pre.length() > 0) {
            return new Editor1(pre.substring(0, pre.length() - 1), post);
        } else {
            return new Editor1(pre, post);
        }
    }

    public Ed insertString(char c) {
        return new Editor1(pre + c, post);

    }

    public Ed homeKey() {
        return new Editor1("", pre + post);
    }

    public Ed endKey() {
        return new Editor1(pre + post, "");
    }

    public Ed yeetus(int amt) { //easter egg :)
        String b = "";
        Random test = new Random();
        for (int i = 0; i <= amt - 1; i++) {
            b += (char) (test.nextInt(430) + 161);
        }
        return new Editor1(b, "");
    }

    public Ed auto() {
        Random test = new Random();
        return new Editor1(pre + (char) (test.nextInt(430) + 161), post);
    }

    public static void main(String[] args) {
        Editor1 eddie = new Editor1("big", "dog");
        System.out.println(eddie + " right arrow = " + eddie.rightArrow());
        System.out.println(eddie + " left arrow = " + eddie.leftArrow());
    }
}
