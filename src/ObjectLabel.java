import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class ObjectLabel {

    private String label;
    private float confidence;

    public ObjectLabel(String label, float confidence) {
    this.label = label;
    this.confidence = confidence;
    }

    public String getLabel() {return label; }
    public void setLabel(String description) {
        this.label = description;
    }
    public float getConfidence() {return confidence; }

    public void setConfidence(float score) {
        this.confidence = score;

    }
    public String toString() {
        return String.format(
                "%s{label='%s', confidence=%.2f}",
                this.getClass().getSimpleName(),
                this.label,
                this.confidence
        );
    }
}


