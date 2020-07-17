package xyz.ddxiong.travel.utils;

import xyz.ddxiong.travel.pojo.User;

import java.util.ResourceBundle;

/**
 * 作用: 解析配置文件,根据配置文件中的类全限定名,创建类对象
 */
public class BeanFactory {
    private static ResourceBundle bundle;
    static {
        // 解析配置文件,得到类的全限定名
        bundle = ResourceBundle.getBundle("beans");
    }

    /**
     * 根据传入的唯一标识,获取类的全限定名,通过反射创建类对象
     * @param id
     * @return
     */
    public static Object getBean(String id){
        Object object = null;
        try {
            // 获取需要创建的类的全限定名
            String className = bundle.getString(id);
            object = Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args) {
        User user = (User)BeanFactory.getBean("User");
        System.out.println(user);
    }
}
