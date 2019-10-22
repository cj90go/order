package lock;

public interface LockFactory {
    public <T> T newRecordInstance(Class<T> clazz);
}
