package bean;

public interface RecordFactory {
    public <T> T newRecordInstance(Class<T> clazz);
}
