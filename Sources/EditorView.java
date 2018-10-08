
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author wkranz
 */
public class EditorView extends Application {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static Canvas canvas;

    public static Ed editor;

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, EditorView.WIDTH, EditorView.HEIGHT);
        gc.setFont(new Font("Arial", 24));
        gc.setFill(Color.BLACK);
        gc.fillText(editor.getBefore() + "\u25c4\u25ba" + editor.getAfter(), 30, 30);
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        //editor = new Editor1(">", "< Knockoff notepad");
        editor = new Editor1(">", "< Knockoff notepad");
        //EditorControl.edControl(scene);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    editor = editor.leftArrow();
                } else if (event.getCode() == KeyCode.RIGHT) {

                    editor = editor.rightArrow();
                } else if (event.getCode() == KeyCode.DELETE) {
                    editor = editor.delete();
                } else if (event.getCode() == KeyCode.UP) { // do nothing
                } else if (event.getCode() == KeyCode.DOWN) { // do nothing
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    editor = editor.backspace();
                } else if (event.getCode() == KeyCode.HOME) {
                    editor = editor.homeKey();
                } else if (event.getCode() == KeyCode.END) {
                    editor = editor.endKey();
                } else if (event.getCode() == KeyCode.SHIFT) {
                    //System.out.println(event.isShiftDown()); //debug
                    if (event.isShiftDown()) {
                        editor.shiftShift(true);
                    } else {
                        editor.shiftShift(false);
                    }
                    //editor.shiftShift(true); // if shift is pressed, tell editor
                } else if (event.getCode() == KeyCode.SEMICOLON) {
                    if (event.isShiftDown()) {
                        editor.insertString(":");
                    } else {
                        editor.insertString(";");
                    }
                } else if (event.getCode() == KeyCode.OPEN_BRACKET) {
                    if (event.isShiftDown()) {
                        editor.insertString("{");
                    } else {
                        editor.insertString("[");
                    }
                } else if (event.getCode() == KeyCode.CLOSE_BRACKET) {
                    if (event.isShiftDown()) {
                        editor.insertString("}");
                    } else {
                        editor.insertString("]");
                    }
                } else if (event.getCode() == KeyCode.SLASH) {
                    if (event.isShiftDown()) {
                        editor.shiftShift(true);
                        editor = editor.insertString("?");
                    } else {
                        editor.shiftShift(false);
                        editor = editor.insertString("/");
                    }
                    if (editor.getBefore().toLowerCase().equals("is that legal?")) {
                        editor.clear();
                        editor = editor.insertString("I will make it legal.");
                    }
                } else if (event.getCode().isDigitKey()) {
                    if (!event.isShiftDown()) {
                        editor.insertString(String.valueOf(event.getCode().toString().charAt(5)));
                        //event.getCode() returns "DIGIT0" - "DIGIT9", so this grabs the 5th char, which is the actual number

                    } else {
                        String digitSymbols = ")!@#$%^&*("; // String of shift equivalents for numbers 0-9
                        int num = event.getCode().toString().charAt(5); // get ascii value of the number that was pressed
                        //System.out.println(num); //debug
                        char symbol = digitSymbols.charAt(num - 48); // find the shift equivalent of the ascii value - 48
                        editor.insertString(String.valueOf(symbol)); // insert it
                    }
                } else if (event.getCode() == KeyCode.ENTER) {
                    editor = editor.auto();
                } else if (event.getCode() == KeyCode.SPACE) {
                    editor.insertString(" ");
                    //System.out.println("Space was pressed."); //debug
                } else if (event.getCode() == KeyCode.PERIOD) {
                    if (event.isShiftDown()) {
                        editor.insertString(">");
                    } else {
                        editor.insertString(".");
                    }
                } else if (event.getCode() == KeyCode.COMMA) {
                    if (event.isShiftDown()) {
                        editor.insertString("<");
                    } else {
                        editor.insertString(",");
                    }
                } else if (event.getCode() == KeyCode.BACK_SLASH) {
                    if (event.isShiftDown()) {
                        editor.insertString("|");
                    } else {
                        editor.insertString("\\");
                    }
                } else if (event.getCode() == KeyCode.QUOTE) {
                    if (event.isShiftDown()) {
                        editor.insertString("\"");
                    } else {
                        editor.insertString("\'");
                    }
                } else if (event.getCode() == KeyCode.TAB) {
                    editor.insertString("    ");
                } else {
                    if (event.isShiftDown()) {
                        editor.shiftShift(true);
                        editor = editor.insertString((String.valueOf(event.getCode().toString().toLowerCase().charAt(0)))); // insert letter
                    } else {
                        editor.shiftShift(false);
                        editor = editor.insertString((String.valueOf(event.getCode().toString().toLowerCase().charAt(0)))); // insert letter
                    }
                    if (editor.getBefore().trim().toLowerCase().equals("yeet")) { // if the text before the cursor is "yeet",
                        editor = editor.yeetus(editor.getAfter().length()); // perform this operation xD
                    }
                    if (editor.getBefore().trim().toLowerCase().equals("bigyeet")) { // if the text before the cursor is "bigyeet",
                        for (int i = 0; i <= 99; i++) {
                            editor = editor.auto(); // do this 100 times xD

                        }
                    }
                    if (editor.getBefore().toLowerCase().equals("i am the senate")) {
                        editor.clear();
                        editor = editor.insertString("Not Yet.");
                    }
                }
                draw();
            }
        });
        draw();

        primaryStage.setTitle(
                "EDITOR!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
