package school;

import java.io.Serializable;

public record ScoreItem(String name) implements Serializable {
    @Override
    public String toString() {
        return "分数项名:" + name;
    }
}

