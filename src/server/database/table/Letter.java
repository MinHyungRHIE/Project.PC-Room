package server.database.table;

import java.time.LocalDateTime;

public class Letter {
    public String own;
    public String from;
    public String to;
    public LocalDateTime when;
    public String content;

    @Override
    public String toString() {
        return "Letter{" +
                "own='" + own + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", when=" + when +
                ", content='" + content + '\'' +
                '}';
    }
}
