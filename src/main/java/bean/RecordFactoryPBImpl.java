package bean;

import exception.OrderRunTimeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RecordFactoryPBImpl implements RecordFactory {


    private static final String PB_IMPL_PACKAGE_SUFFIX = "impl.pb";
    private static final String PB_IMPL_CLASS_SUFFIX = "PBImpl";

    private static final RecordFactoryPBImpl self = new RecordFactoryPBImpl();
    private ConcurrentMap<Class<?>, Constructor<?>> cache = new ConcurrentHashMap<Class<?>, Constructor<?>>();

    private RecordFactoryPBImpl() {
    }

    public static RecordFactory get() {
        return self;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T newRecordInstance(Class<T> clazz) {

        Constructor<?> constructor = cache.get(clazz);
        if (constructor == null) {

            try {
                Class<?> pbClazz = Class.forName("bean.RecordFactoryPBImpl");
                constructor = pbClazz.getConstructor();
                constructor.setAccessible(true);
                cache.putIfAbsent(clazz, constructor);
            } catch (Exception e) {
                throw new OrderRunTimeException("Could not find 0 argument constructor", e);
            }
        }
        try {
            Object retObject = constructor.newInstance();
            return (T) retObject;
        } catch (InvocationTargetException e) {
            throw new OrderRunTimeException(e);
        } catch (IllegalAccessException e) {
            throw new OrderRunTimeException(e);
        } catch (InstantiationException e) {
            throw new OrderRunTimeException(e);
        }
    }

    private String getPBImplClassName(Class<?> clazz) {
        String srcPackagePart = getPackageName(clazz);
        String srcClassName = getClassName(clazz);
        String destPackagePart = srcPackagePart + "." + PB_IMPL_PACKAGE_SUFFIX;
        String destClassPart = srcClassName + PB_IMPL_CLASS_SUFFIX;
        return destPackagePart + "." + destClassPart;
    }

    private String getClassName(Class<?> clazz) {
        String fqName = clazz.getName();
        return (fqName.substring(fqName.lastIndexOf(".") + 1, fqName.length()));
    }

    private String getPackageName(Class<?> clazz) {
        return clazz.getPackage().getName();
    }


}
