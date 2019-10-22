package bean;

public class Records {
    // The default record factory
    private static final RecordFactory factory =
            RecordFactoryProvider.getRecordFactory(null);

    public static <T> T newRecord(Class<T> cls) {
        return factory.newRecordInstance(cls);
    }
}