package Polymorphism_Exercise.word;

public class CutTransform implements TextTransform {
    private Clipboard clipboard;
    public CutTransform(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String cutResult = text.substring(startIndex, endIndex);
        this.clipboard.setCurrent(cutResult);
        text.replace(startIndex, endIndex, "");
    }
}
