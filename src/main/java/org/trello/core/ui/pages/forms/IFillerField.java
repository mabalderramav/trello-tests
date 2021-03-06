package org.trello.core.ui.pages.forms;

/**
 * Interface used to set fields on a form.
 */
@FunctionalInterface
public interface IFillerField {

    /**
     * Fills a field with the value given.
     *
     * @param value Field value.
     */
    void fill(String value);
}
