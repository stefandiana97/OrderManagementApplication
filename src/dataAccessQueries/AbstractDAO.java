package dataAccessQueries;

import dataAccessConnection.ConnectionDB;
import reflection.ReflectionPrint;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;
//        //this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creeaza o lista de obiecte specifica fiecarui model
     *
     * @param resultSet
     * @return List<T>
     */
    private List<T> createObject(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creeaza o lista de obiecte
     * care va fi transmisa la afisare
     *
     * @return List<Object>
     */
    public List<Object> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return (List<Object>) createObject(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getAll " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

    /**
     * creeaza capul fiecarui tabel in parte
     * care va fi transmis la interfata
     *
     * @return JTable
     */
    public JTable getAllResultSet() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        JTable table = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            table = ReflectionPrint.print_table_null(resultSet);

            return table;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getAll " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return table;
    }

    /**
     * Returneaza urmatorul id care va fi introdus in baza de date
     * specific unui anumit tabel
     *
     * @return int
     */
    public int getLastId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        int id = 0;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (id < resultSet.getInt(1)) {
                    id = resultSet.getInt(1);
                }
            }
            id += 1;
            return id;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getLastId " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return id;
    }

    /**
     * Gaseste pe baza unui id si a unui field obiectul din baza de date corespunzator
     *
     * @param id
     * @param field
     * @return T
     */
    public T findById(int id, String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(field);
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObject(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

    /**
     * Selecteaza din baza de date campurile transmise prin field1 si field2 si a id-ului
     *
     * @param id
     * @param field1
     * @param field2
     * @return List<T>
     */
    public List<T> Select(Object id, String field1, String field2) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelect(field1, field2);
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            return createObject(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Select " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

    /**
     * Creeaza o Stringul unei interogari care apoi va fii parsata
     *
     * @param field1
     * @param field2
     * @return String
     */
    private String createSelect(String field1, String field2) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + field1 + " FROM " + type.getSimpleName());
        sb.append(" WHERE " + field2 + "=?");
        return sb.toString();
    }

    /**
     * Selecteaza din baza de date pe baza id-ului un anumit field
     *
     * @param id
     * @param field
     * @return List<T>
     */
    public List<T> SelectQuery(Object id, String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(field);
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            return createObject(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:SelectQuery " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

    /**
     * Creeaza Stringul corespunzator unei interogari in care avem o conditie
     *
     * @param field
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM " + type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     * Creeaza Stringul unei interogari in care selectam toate campurile dintr-un tabel
     *
     * @return String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Creeaza Stringul unui insert cu campurile ce vor fi introduse
     *
     * @return String
     */
    private String createInsertQuery() {
        StringBuilder sb_insert = new StringBuilder();
        StringBuilder sb_value = new StringBuilder();
        sb_insert.append("INSERT INTO " + type.getSimpleName() + "(");
        sb_value.append(" VALUES(");
        for (Field fields : type.getDeclaredFields()) {
            sb_insert.append(fields.getName() + ",");
            sb_value.append("?,");
        }
        sb_insert.delete(sb_insert.length() - 1, sb_insert.length());
        sb_value.delete(sb_value.length() - 1, sb_value.length());
        sb_insert.append(") ");
        sb_value.append(")");
        return sb_insert.toString() + sb_value.toString();
    }

    /**
     * Creeaza Stringul unui update cu campurile ce vor fi modificate in baza de date
     *
     * @param field
     * @return String
     */
    private String createUpdateQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName() + " SET ");
        for (Field fields : type.getDeclaredFields()) {
            if (!fields.getName().contains(field)) {
                sb.append(fields.getName() + "=?, ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE ");
        sb.append(field + "=?");
        return sb.toString();
    }

    /**
     * Creeaza Stringul unui delete pe baza unei conditii
     *
     * @param field
     * @return String
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     * Se insereaza efectiv obiectele dorite in baza de date
     *
     * @param insert
     */
    public void insertQuery(T insert) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field field : insert.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(insert);
                    statement.setObject(i, value);
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insertQuery " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    /**
     * Se updateaza efectiv obiectele in baza de date
     *
     * @param object
     */
    public void updateQuery(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(type.getDeclaredFields()[0].getName());
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1, idd = 0;
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    if (i == 1) {
                        idd = (int) value;
                    } else if (i != 1) {
                        statement.setObject(i - 1, value);
                    }
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.setObject(i - 1, idd);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:UpdateQuery " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    /**
     * Se sterg efectiv obiectele din baza de date
     *
     * @param object
     */
    public void deleteQuery(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(type.getDeclaredFields()[0].getName());
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(query);
            int id = 0;
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    id = (int) value;
                    break;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:DeleteQuery " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

}
