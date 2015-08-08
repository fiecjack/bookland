package org.landal.bookland.model;

public interface Identifiable<T> {

    /**
     * @return The primary key, or ID, of this entity
     */
    T getId();

}
