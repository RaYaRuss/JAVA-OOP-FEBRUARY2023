package Polymorphism_Exercise.word;

public class PasteTransform implements TextTransform {
    private Clipboard clipboard;

    public PasteTransform(Clipboard clipboard) {

        this.clipboard = clipboard;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String clipboard = this.clipboard.getCurrent();
        text.replace(startIndex, endIndex, clipboard);
    }
}
