package com.example.syoui.imagetab.launch_others;

/**
 * Created by xuzhongwei on 2018/01/21.
 */
class LaunchItem{
    private boolean isLaunchble = false;
    private String packageName;
    private String className;

    public boolean isLaunchble() {
        return isLaunchble;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public LaunchItem(boolean isLaunchble, String packageName, String className) {
        this.isLaunchble = isLaunchble;
        this.packageName = packageName;
        this.className = className;
    }

}
