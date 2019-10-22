package lock;

import config.Configuration;

public class LockFactoryProvider {

    private static Configuration defaultConf;

    static {
        defaultConf = new Configuration();
    }

    private RecordFactoryProvider() {
    }

    public static LockFactory getRecordFactory(Configuration conf) {
        if (conf == null) {
            //Assuming the default configuration has the correct factories set.
            //Users can specify a particular factory by providing a configuration.
            conf = defaultConf;
        }
        String   recordFactoryClassName = conf.get(
                YarnConfiguration.IPC_RECORD_FACTORY_CLASS,
                YarnConfiguration.DEFAULT_IPC_RECORD_FACTORY_CLASS);
        return (RecordFactory) getFactoryClassInstance(recordFactoryClassName);
    }

    private static Object getFactoryClassInstance(String factoryClassName) {
        try {
            Class<?> clazz = Class.forName(factoryClassName);
            Method method = clazz.getMethod("get", null);
            method.setAccessible(true);
            return method.invoke(null, null);
        } catch (ClassNotFoundException e) {
            throw new YarnRuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new YarnRuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new YarnRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new YarnRuntimeException(e);
        }
    }


}
