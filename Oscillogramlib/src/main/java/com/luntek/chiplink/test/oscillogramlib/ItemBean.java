package com.luntek.chiplink.test.oscillogramlib;

/**
 * @author lxc
 */
public class ItemBean {
    //数组名称
    String name = "";
    //数据路径
    byte[] array;

    public ItemBean() {
    }

    public ItemBean(String name, byte[] array) {
        this.name = name;
        this.array = array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }
}
