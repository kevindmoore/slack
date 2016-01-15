package com.mastertechsoftware.slack.sql;

import java.util.List;

/**
 * Helper Class that does the regular CRUD operations
 */
public class DatabaseHelper<T extends ReflectTableInterface> {
    protected String databaseName;
    protected Class tableClass;

    public DatabaseHelper(String databaseName, Class tableClass) {
        this.databaseName = databaseName;
        this.tableClass = tableClass;
    }

    public void deleteDatabase() {
        DatabaseManager.getInstance().deleteDatabase(databaseName);
    }

    public T get(int id) {
        return (T) DatabaseManager.getInstance().getItem(databaseName, tableClass, id);
    }

    public List<T> getAll() {
        return  (List<T>)DatabaseManager.getInstance().getAllItems(databaseName, tableClass);
    }

    public long add(T table) {
        return  DatabaseManager.getInstance().addItem(databaseName, tableClass, table);
    }

    public void update(T table) {
        DatabaseManager.getInstance().updateItem(databaseName, tableClass, table);
    }

    public void removeAll() {
        DatabaseManager.getInstance().removeAllItems(databaseName, tableClass);
    }

    public void delete(T table) {
        DatabaseManager.getInstance().deleteItem(databaseName, tableClass, table.getId());

    }
}
