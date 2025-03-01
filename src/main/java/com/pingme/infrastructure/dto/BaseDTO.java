package com.pingme.infrastructure.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseDTO<E> {
    
    public static <T extends BaseDTO<E>, E> List<T> fromEntityList(List<E> list, Class<T> dtoClass){

        List<T> result = new ArrayList<T>();

        for(E entity : list){
            try {
                T dto = dtoClass.getDeclaredConstructor().newInstance();
                result.add(dto.fromEntity(entity));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public abstract E toEntity();

    protected abstract <T> T fromEntity(E entity);

}
