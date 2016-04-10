package pl.radekdab.todo;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean done;
    private String text;

    public Task() {
        done = false;
        text = "";
    }

    public Task(boolean done, String text) {
        this.done = done;
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
