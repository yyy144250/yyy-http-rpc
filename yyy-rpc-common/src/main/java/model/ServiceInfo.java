package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {

//    类名
    private String clazz;

// 方法名
    private String method;

//    参数列表
    private String[] parameterType;

//    返回值
    private String returnType;


    /*
    * 根据传入的类和方法获得ServiceInfo实例,作为map的key
    * */
    public static ServiceInfo getKey(Class clazz, Method method){
        ServiceInfo serviceInfo=new ServiceInfo();
        serviceInfo.setClazz(clazz.getName());
        serviceInfo.setMethod(method.getName());
        serviceInfo.setReturnType(method.getReturnType().getName());
        Class[] parameter=method.getParameterTypes();
        String[] parameterType=new String[parameter.length];
        for(int i=0;i<parameter.length;i++){
            parameterType[i]=parameter[i].getName();
        }
        serviceInfo.setParameterType(parameterType);

        return serviceInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInfo that = (ServiceInfo) o;
        return Objects.equals(clazz, that.clazz) && Objects.equals(method, that.method) && Arrays.equals(parameterType, that.parameterType) && Objects.equals(returnType, that.returnType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(clazz, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterType);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", parameterType=" + Arrays.toString(parameterType) +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
