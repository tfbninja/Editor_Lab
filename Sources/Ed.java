public interface Ed
{
   String getBefore();
   String getAfter();
   Ed rightArrow();
   Ed leftArrow();
   Ed delete();
   Ed backspace();
   Ed insertString(char c);  // Originally insert(char c), which is fine if you teach the char type
   Ed homeKey();
   Ed endKey();
   Ed yeetus(int amt);
   Ed auto();
   String toString();
}