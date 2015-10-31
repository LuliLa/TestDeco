package ru.luli.db.schema.lobjects;


import android.database.Cursor;
import ru.luli.db.schema.annotates.TableColumn;
import ru.luli.db.schema.interfaces.IDbTable;
import ru.luli.decompiler.utils.DBUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class LDbTable<T> implements IDbTable {

    protected List<T> fetch(Class beanClass, String whereCause) {
        List<T> result = new ArrayList<T>();
        Cursor c = null;
        try {
            c = DBUtils.execQuery(getTableName(), whereCause);
            result.addAll(fillObject(beanClass, c));
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (c!=null) {
                c.close();
            }
        }
        return result;
    }

    private List<T> fillObject(Class beanClass, Cursor dbCursor) throws IllegalAccessException, InstantiationException {
        List<T> listObjects = new ArrayList<T>();
        Field[] fields = beanClass.getDeclaredFields();
        while(dbCursor.moveToNext()) {
            T bean = (T) beanClass.newInstance();
            for (Field field : fields) {
                TableColumn annot = field.getAnnotation(TableColumn.class);
                if (annot!=null) {
                    String columnName = annot.name();
                    int columnIndex = -1;
                    if ((columnIndex = dbCursor.getColumnIndex(columnName)) >= 0) {
                        field.setAccessible(true);
                        setValueObjectWithType(field.getType(), dbCursor, columnIndex, field, bean);
                    }
                }
            }
            listObjects.add(bean);
        }
        return listObjects;
    }

    private void setValueObjectWithType(Class typeClass, Cursor c, int columnIndex, Field f, T bean) throws IllegalAccessException {
        if (int.class==typeClass) {
            f.setInt(bean, c.getInt(columnIndex));
        } else if (double.class==typeClass) {
            f.setDouble(bean, c.getDouble(columnIndex));
        } else if (String.class==typeClass) {
            f.set(bean, c.getString(columnIndex));
        } else if (boolean.class==typeClass) {
            f.setBoolean(bean, c.getInt(columnIndex)==1);
        }
    }
}
