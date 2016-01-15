package com.mastertechsoftware.slack.sql.upgrade;

import com.mastertechsoftware.slack.sql.ClassField;
/**
 * Interface for mapping a class T with a Column
 */
public interface UpgradeDataMaper<T> {
    void write(UpgradeHolder upgradeHolder, ClassField column, T data);
}
