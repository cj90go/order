package config;

public class OrderConfiguration extends Configuration{
    public static final String DEFT_RECORD_FACTORY_CLASS = "";

    @Override
    public String get(String key, String dlftKey) {
        return "bean.RecordFactoryDfltImpl";
    }
}
