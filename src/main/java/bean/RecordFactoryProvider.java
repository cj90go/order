package bean;

import config.Configuration;
import config.OrderConfiguration;
import exception.OrderRunTimeException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RecordFactoryProvider {



   private static Configuration defaultConf ;
    static{
        defaultConf = new OrderConfiguration();
    }


    private RecordFactoryProvider() {
    }

    public static RecordFactory getRecordFactory(Configuration conf) {
        if(conf == null){
            conf =defaultConf;
        }

        return (RecordFactory) getFactoryClassInstance(conf.get(OrderConfiguration.DEFT_RECORD_FACTORY_CLASS,OrderConfiguration.DEFT_RECORD_FACTORY_CLASS));
    }

    private static Object getFactoryClassInstance(String factoryClassName) {
        try {
            Class<?> clazz = Class.forName(factoryClassName);
            Method method = clazz.getMethod("get", null);
            method.setAccessible(true);
            return method.invoke(null, null);
        } catch (ClassNotFoundException e) {
            throw new OrderRunTimeException(e);
        } catch (NoSuchMethodException e) {
            throw new OrderRunTimeException(e);
        } catch (InvocationTargetException e) {
            throw new OrderRunTimeException(e);
        } catch (IllegalAccessException e) {
            throw new OrderRunTimeException(e);
        }
    }

}
