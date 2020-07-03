package com.ml.datasource;

public class DataSourceHolder {

    // ThreadLocal每个线程都独有的保存其线程所属的变量值
    private static ThreadLocal<String> holder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDatabaseSource(String dbType) {
        System.out.println("切换到{"+dbType+"}数据源");
        holder.set(dbType);
    }

    // 获取数据源名
    public static String getDatabaseSource() {
        return holder.get();
    }

    // 清除数据源名
    public static void clearDatabaseSource() {
        holder.remove();
    }

}