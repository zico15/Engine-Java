package com.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

public class MultipleSelectionModelNone extends MultipleSelectionModel {

    private ObservableList<Integer> list = FXCollections.observableArrayList();
    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return list;
    }

    @Override
    public ObservableList getSelectedItems() {
        return list;
    }

    @Override
    public void selectIndices(int i, int... ints) {

    }

    @Override
    public void selectAll() {

    }

    @Override
    public void clearAndSelect(int i) {

    }

    @Override
    public void select(int i) {

    }

    @Override
    public void select(Object o) {

    }

    @Override
    public void clearSelection(int i) {

    }

    @Override
    public void clearSelection() {

    }

    @Override
    public boolean isSelected(int i) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void selectPrevious() {

    }

    @Override
    public void selectNext() {

    }

    @Override
    public void selectFirst() {

    }

    @Override
    public void selectLast() {

    }
}
