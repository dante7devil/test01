package cn.smbms.dao;


/*单例模式*/
public class Singleton {
    private static Singleton singleton;

    public Singleton() {
    }

    public static Singleton getInstance(){
        /*懒汉式*/
        if (singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
