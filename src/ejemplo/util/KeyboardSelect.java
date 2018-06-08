/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.javafx.scene.control.behavior.TableCellBehavior;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TablePositionBase;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author plaurent
 */
public class KeyboardSelect implements EventHandler<KeyEvent>{

        private final TableView<?> table2;

        public KeyboardSelect(TableView<?> table) {
            table2 = table;
        }

        @Override
        public void handle(final KeyEvent event) {
            KeyCode code = event.getCode();
            if (event.isShiftDown() && (KeyCode.UP.equals(code) || KeyCode.DOWN.equals(code) || KeyCode.LEFT.equals(code) || KeyCode.RIGHT.equals(code))) {
                int index = this.table2.getFocusModel().getFocusedCell().getRow();
                TableColumn column = this.table2.getFocusModel().getFocusedCell().getTableColumn();
                performSelection(this.table2, column, index);
            }
        }

    protected void performSelection(TableView table2, TableColumn column, int index) {
        final TablePositionBase anchor = TableCellBehavior.getAnchor(table2, table2.getFocusModel().getFocusedCell());
        int columnIndex = table2.getVisibleLeafIndex(column);

        int minRowIndex = Math.min(anchor.getRow(), index);
        int maxRowIndex = Math.max(anchor.getRow(), index);
        TableColumnBase minColumn = anchor.getColumn() < columnIndex ? anchor.getTableColumn() : column;
        TableColumnBase maxColumn = anchor.getColumn() >= columnIndex ? anchor.getTableColumn() : column;

        table2.getSelectionModel().clearSelection();
        final int minColumnIndex = table2.getVisibleLeafIndex((TableColumn) minColumn);
        final int maxColumnIndex = table2.getVisibleLeafIndex((TableColumn) maxColumn);
        for (int _row = minRowIndex; _row <= maxRowIndex; _row++) {
            for (int _col = minColumnIndex; _col <= maxColumnIndex; _col++) {
                table2.getSelectionModel().select(_row, table2.getVisibleLeafColumn(_col));
            }
        }
        table2.getFocusModel().focus(index, column); }
    }

//    protected void performSelection(TableView<DataBaseTable> table, TableColumn<DataBaseTable, String> column, int index) {
//        final TablePositionBase anchor = TableCellBehavior.getAnchor(table, table.getFocusModel().getFocusedCell());
//        int columnIndex = table.getVisibleLeafIndex(column);
//
//        int minRowIndex = Math.min(anchor.getRow(), index);
//        int maxRowIndex = Math.max(anchor.getRow(), index);
//        TableColumnBase minColumn = anchor.getColumn() < columnIndex ? anchor.getTableColumn() : column;
//        TableColumnBase maxColumn = anchor.getColumn() >= columnIndex ? anchor.getTableColumn() : column;
//
//        table.getSelectionModel().clearSelection();
//        final int minColumnIndex = table.getVisibleLeafIndex((TableColumn) minColumn);
//        final int maxColumnIndex = table.getVisibleLeafIndex((TableColumn) maxColumn);
//        for (int _row = minRowIndex; _row <= maxRowIndex; _row++) {
//            for (int _col = minColumnIndex; _col <= maxColumnIndex; _col++) {
//                table.getSelectionModel().select(_row, table.getVisibleLeafColumn(_col));
//            }
//        }
//        table.getFocusModel().focus(index, column);
//    }
