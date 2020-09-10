package cn.smbms.tools;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigeManager {
    private static ConfigeManager configeManager;
    private static Properties properties;

    private ConfigeManager(){
        properties=new Properties();
        String configFile = "database.properties";
        InputStream is= BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class InitInstance{
        private static ConfigeManager instance = new ConfigeManager();
    }

    /*饿汉式*/
    public static ConfigeManager getInstance(){
        configeManager=InitInstance.instance;
        return configeManager;

    }


    /*懒汉式*/
 /*   public static  synchronized ConfigeManager getInstance(){
        if (configeManager==null){
            configeManager=new ConfigeManager();
        }
        return configeManager;
    }*/

    public String getValue(String key){
        return properties.getProperty(key);
    }
}
