package vintage.mods.companion.items;

public class LazyEntry<T> {

    private final Supplier<T> factory;
    private T instance;

    public LazyEntry(Supplier<T> factory) {
        this.factory = factory;
    }

    public T get() {
        if (this.instance == null) {
            this.instance = this.factory.get();
        }
        return this.instance;
    }

    public interface Supplier<T> {
        T get();
    }
}
