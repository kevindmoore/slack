package com.mastertechsoftware.slack.sql.upgrade;

import com.mastertechsoftware.slack.log.Logger;
import com.mastertechsoftware.slack.sql.AbstractDataMapper;
import com.mastertechsoftware.slack.sql.AbstractTable;
import com.mastertechsoftware.slack.sql.ClassField;
import com.mastertechsoftware.slack.sql.Column;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
/**
 * Table used to hold old data from the database
 */
public class UpgradeTable extends AbstractTable<UpgradeHolder> {
    protected Mapper mapper = new Mapper();
    protected List<? extends UpgradeHolder> allEntries;
    protected List<ClassField> fields = new ArrayList<ClassField>();

    public List<? extends UpgradeHolder> getAllEntries() {
        return allEntries;
    }

    public void setAllEntries(List<? extends UpgradeHolder> allEntries) {
        this.allEntries = allEntries;
    }

    public List<ClassField> getFields() {
        return fields;
    }

    public void setFields(List<ClassField> fields) {
        this.fields = fields;
    }

    public void addField(String fieldName, Column.COLUMN_TYPE type, int column) {
        addField(new ClassField(fieldName, type, column));
    }

    public void addField(String fieldName, Column.COLUMN_TYPE type) {
        addField(new ClassField(fieldName, type));
    }

    public void addField(ClassField classField) {
        fields.add(classField);
    }


    public Mapper getMapper() {
        return mapper;
    }

    /**
     * Mapper class. Get all fields and use the column to get it's type
     */
    public class Mapper extends AbstractDataMapper<UpgradeHolder> {

        @Override
        public void write(ContentValues cv, Column column, UpgradeHolder upgradeHolder) {
            if (fields.size() <= column.getColumnPosition()) {
                Logger.error(this, "Field at position " + column.getColumnPosition() + " does not exist");
                return;
            }
            ClassField field = fields.get(column.getColumnPosition());

            ClassField holderField = upgradeHolder.getField(column.getColumnPosition());
            if (holderField == null) {
                upgradeHolder.addField(field.getFieldName(), field.getType(), column.getColumnPosition());
                holderField = upgradeHolder.getField(column.getColumnPosition());
            }

            // Need to skip ID
            if (column.getName().equalsIgnoreCase(ID)) {
                return;
            }
            switch (column.getType()) {
                case TEXT:
                    cv.put(column.getName(), holderField.getValue());
                    break;
                case INTEGER:
                    cv.put(column.getName(), Integer.valueOf(holderField.getValue()));
                    break;
                case FLOAT:
                    cv.put(column.getName(), Float.valueOf(holderField.getValue()));
                    break;
                case BOOLEAN:
                    cv.put(column.getName(), Boolean.valueOf(holderField.getValue()));
                    break;
                case LONG:
                    cv.put(column.getName(), Long.valueOf(holderField.getValue()));
                    break;
                case DOUBLE:
                    cv.put(column.getName(), Double.valueOf(holderField.getValue()));
                    break;
            }
        }

        @Override
        public void read(Cursor cursor, Column column, UpgradeHolder upgradeHolder) {
            int columnIndex = getColumnIndex(cursor, column.getName());
            if (columnIndex == -1) {
                Logger.error(this, "Mapper.read: Column " + column.getName() + " does not exist in cursor");
                return;
            }
            if (fields.size() <= column.getColumnPosition()) {
                Logger.error(this, "Field at position " + column.getColumnPosition() + " does not exist");
                return;
            }
            ClassField field = fields.get(columnIndex);
            ClassField holderField = upgradeHolder.getField(columnIndex);
            if (holderField == null) {
                upgradeHolder.addField(field.getFieldName(), field.getType(), columnIndex);
                holderField = upgradeHolder.getField(column.getColumnPosition());
            }

            holderField.setValue(cursor.getString(columnIndex));
        }
    }
}
