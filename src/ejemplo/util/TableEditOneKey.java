package util;

import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class TableEditOneKey {

    final static KeyCombination KEYSCTRLC = new KeyCodeCombination(
            KeyCode.C, KeyCombination.CONTROL_ANY);
    final static KeyCombination KEYSCTRLV = new KeyCodeCombination(
            KeyCode.V, KeyCombination.CONTROL_ANY);
    final static KeyCombination KEYSCTRLX = new KeyCodeCombination(
            KeyCode.X, KeyCombination.CONTROL_ANY);

    public static void installEventFilter(TableView<?> table) {

        // install copy/paste keyboard handler
        table.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
//                  event.consume(); // don't consume the event or else the values won't be updated;
                return;
            }
            if (table.getEditingCell() == null) {
                if (KEYSCTRLC.match(event)) {
                    System.out.println("copiar");
                } else if (KEYSCTRLV.match(event)) {
                    System.out.println("pegar");
                } else if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {

                    TablePosition focusedCellPosition = table.getFocusModel().getFocusedCell();
                    table.edit(focusedCellPosition.getRow(), focusedCellPosition.getTableColumn());

                }
            } else {
            }
        });

        table.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {

                // move focus & selection
                // we need to clear the current selection first or else the selection would be added to the current selection since we are in multi selection mode
                TablePosition pos = table.getFocusModel().getFocusedCell();
                TableColumnBase column = table.getFocusModel().getFocusedCell().getTableColumn();
                int size = table.getColumns().size();
//                System.out.println(size);

                if (pos.getRow() == -1) {
                    table.getSelectionModel().select(0);
                } // add new row when we are at the last row
                else if (pos.getRow() == table.getItems().size() - 1) {
                    if (pos.getColumn() < table.getColumns().size() - 1) {
                        table.getFocusModel().focusRightCell();
                        TableColumnBase next = table.getFocusModel().getFocusedCell().getTableColumn();
                        table.getSelectionModel().clearAndSelect(0, next);
                    } else {
                        for (int i = 0; i < table.getColumns().size() - 1; i++) {
                            table.getFocusModel().focusLeftCell();
                        }
                        table.getSelectionModel().selectFirst();
                    }
                } // select next row, but same column as the current selection
                else if (pos.getRow() < table.getItems().size() - 1) {
//                    table.getSelectionModel().clearAndSelect(pos.getRow() + 1, pos.getTableColumn());
                    table.getSelectionModel().clearAndSelect(pos.getRow() + 1, column);
                }

            }
        });

    }

}
