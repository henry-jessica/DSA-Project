package disruptorqueue;

public class Event<T> {
    private volatile T data;

    public void set(T data) {
        this.data = data;
    }

    public T get() {
        return data;
    }
}
