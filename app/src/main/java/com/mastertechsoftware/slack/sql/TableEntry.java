package com.mastertechsoftware.slack.sql;

/**
 * Interface for subclasses to override to define a table.
 * Used in AbstractDatabaseHelper
 */
public interface TableEntry {
    Table getTable();
    String getName();
}
