package com.hrm.dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public int them(T object);

    public boolean xoa(T object);

    public boolean capnhat(T object);

    public ArrayList<T> selectAll();

    public T selectByID(int id);

}