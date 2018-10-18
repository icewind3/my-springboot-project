package org.icewind.elasticsearchdemo.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Ye Jianyu
 * @date 2018/8/7
 */
public class BeanUtils {

    public  static  <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null){
            return null;
        }
        T object = null;
        try {
            object = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(object, map.get(property.getName()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
