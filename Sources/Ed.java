
public interface Ed {

    String getBefore();

    String getAfter();

    void clear();
    Ed rightArrow();
    Ed leftArrow();
    Ed delete();
    Ed backspace();
    Ed insertString(String s);
    Ed homeKey();
    Ed endKey();
    Ed yeetus(int amt);
    Ed auto();
    void shiftShift(boolean val);
    boolean getShift();
    String toString();
}
