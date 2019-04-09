package com.base.base.utilcode.util;

import java.io.*;
import java.util.List;

public class ListUtils {

    /**
     * 描述：list集合深拷贝
     *
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @author songfayuan
     * 2018年7月22日下午2:35:23
     */
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteout);
            out.writeObject(src);
            ByteArrayInputStream bytein = new ByteArrayInputStream(byteout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bytein);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
