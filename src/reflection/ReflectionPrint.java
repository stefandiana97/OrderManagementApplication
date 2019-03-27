package reflection;

import javax.swing.*;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ReflectionPrint {

    /**
     * se preia capul de tabel din baza de date
     *
     * @param object
     * @return
     */
    public static Vector<String> print_tablename(Object object) {
        Vector<String> tablename = new Vector<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                tablename.add(field.getName());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return tablename;
    }

    /**
     * se preiau datele din baza de date
     *
     * @param object
     * @return
     */
    public static Vector<String> print_data(Object object) {
        Vector<String> data = new Vector<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                data.add(value + "");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    /**
     * se formeaza un JTable cu datele din baza de date
     *
     * @param list
     * @return
     */
    public static JTable print_table(List<Object> list) {

        JTable jTable;
        try {

            Vector<String> tablename;
            tablename = print_tablename(list.get(0));
            Vector<Vector<String>> data = new Vector<>();
            for (Object object : list) {
                data.add(print_data(object));
            }
            jTable = new JTable(data, tablename);
            jTable.setDefaultEditor(Object.class, null);
            return jTable;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    /**
     * se creeaza capul de tabel in cazul in care nu avem nici o data in baza de date
     * pentru tabelul respectiv
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static JTable print_table_null(ResultSet resultSet) throws SQLException {

        ResultSetMetaData rs = resultSet.getMetaData();
        JTable jTable;
        try {

            Vector<String> tablename = new Vector<>();
            ;
            int nr = rs.getColumnCount();
            for (int i = 1; i <= nr; i++) {
                tablename.add(rs.getColumnName(i));
            }
            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= nr; i++) {
                    vector.add(resultSet.getObject(i));
                }
                data.add(vector);
            }
            jTable = new JTable(data, tablename);
            jTable.setDefaultEditor(Object.class, null);
            return jTable;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

}
