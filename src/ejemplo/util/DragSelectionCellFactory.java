/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.javafx.scene.control.behavior.TableCellBehavior;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TablePositionBase;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 *
 * @author plaurent
 * @param <S>
 * @param <T>
 */
public class DragSelectionCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    private final Callback<TableColumn<S, T>, TableCell<S, T>> factory;

    public DragSelectionCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> factory) {
        this.factory = factory;
    }

    @Override
    public TableCell<S, T> call(final TableColumn<S, T> col) {
        TableCell<S, T> cell = factory.call(col);
        cell.setOnDragDetected(new DragDetectedEventHandler(cell));
        cell.setOnMouseDragEntered(new DragEnteredEventHandler(cell));
        return cell;
    }

    public class DragSelectionCell extends TableCell<S, T> {

        public DragSelectionCell() {

            setOnDragDetected(new DragDetectedEventHandler(this));
            setOnMouseDragEntered(new DragEnteredEventHandler(this));
        }

        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                setText(item.toString());
            }
        }

    }

    public class DragDetectedEventHandler implements EventHandler<MouseEvent> {

        TableCell<S, T> tableCell;

        public DragDetectedEventHandler(TableCell<S, T> tableCell) {
            this.tableCell = tableCell;
//            this.tableCell.getTableView().getSelectionModel().clearSelection();

        }

        @Override
        public void handle(final MouseEvent event) {
            tableCell.startFullDrag();
        }
    }

    public class DragEnteredEventHandler implements EventHandler<MouseDragEvent> {

        TableCell<S, T> tableCell;

        public DragEnteredEventHandler(TableCell<S, T> tableCell) {
            this.tableCell = tableCell;
        }

        @Override
        public void handle(final MouseDragEvent event) {
            tableCell.getTableView().getSelectionModel().clearSelection();
            performSelection(tableCell.getTableView(), tableCell.getTableColumn(), tableCell.getIndex());
        }
    }

    protected void performSelection(TableView<S> table, TableColumn<S, T> column, int index) {
        table.getSelectionModel().clearSelection();
        final TablePositionBase anchor = TableCellBehavior.getAnchor(table, table.getFocusModel().getFocusedCell());
        int columnIndex = table.getVisibleLeafIndex(column);

        int minRowIndex = Math.min(anchor.getRow(), index);
        int maxRowIndex = Math.max(anchor.getRow(), index);
        TableColumnBase minColumn = anchor.getColumn() < columnIndex ? anchor.getTableColumn() : column;
        TableColumnBase maxColumn = anchor.getColumn() >= columnIndex ? anchor.getTableColumn() : column;

        table.getSelectionModel().clearSelection();
        final int minColumnIndex = table.getVisibleLeafIndex((TableColumn) minColumn);
        final int maxColumnIndex = table.getVisibleLeafIndex((TableColumn) maxColumn);
        for (int _row = minRowIndex; _row <= maxRowIndex; _row++) {
            for (int _col = minColumnIndex; _col <= maxColumnIndex; _col++) {
                table.getSelectionModel().select(_row, table.getVisibleLeafColumn(_col));
            }
        }
        table.getFocusModel().focus(index, column);
    }

}
