package util;


import static java.lang.Double.NaN;
import javafx.event.Event;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class EditCellNumberDouble<S, T> extends TableCell<S, T> {

    // Text field for editing
    // TODO: allow this to be a plugable control.
    private final TextField textField = new TextField();

    // Converter for converting the text in the text field to the user type, and vice-versa:
    private final StringConverter<T> converter;

    public EditCellNumberDouble(StringConverter<T> converter) {
        this.converter = converter;

        itemProperty().addListener((obx, oldItem, newItem) -> {
            if (newItem == null) {
                setText(null);
            } else {
                setText(converter.toString(newItem));
            }
        });
        setGraphic(textField);
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        textField.setOnAction(evt -> {
            commitEdit(this.converter.fromString(textField.getText()));
        });
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                commitEdit(this.converter.fromString(textField.getText()));
            }
        });
        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (null != event.getCode()) {
                switch (event.getCode()) {
                    case ESCAPE:
                        textField.setText(converter.toString(getItem()));
                        cancelEdit();
                        event.consume();
                        break;
                    case RIGHT:
                        getTableView().getSelectionModel().selectRightCell();
                        event.consume();
                        break;
                    case LEFT:
                        getTableView().getSelectionModel().selectLeftCell();
                        event.consume();
                        break;
                    case UP:
                        getTableView().getSelectionModel().selectAboveCell();
                        event.consume();
                        break;
                    case DOWN:
                        getTableView().getSelectionModel().selectBelowCell();
                        event.consume();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static final StringConverter<Number> NUMBER = new StringConverter<Number>() {
        @Override
        public String toString(java.lang.Number object) {
            return object.toString();
        }

        @Override
        public java.lang.Number fromString(String string) {
            if (checkIsDouble(string)) {

                if (string == null) {
                    return NaN;
                }
                string = string.trim();
                if (string.length() < 1) {
                    return NaN;
                }
                return Double.valueOf(string);
            } else {
                return NaN;
            }
        }

        private boolean checkIsDouble(String str) {
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }

    };

    /**
     * Convenience method for creating an EditCell for a String value.
     *
     * @param <S>
     * @return
     */
    public static <S> EditCellNumberDouble<S, Number> createStringEditCell() {
        return new EditCellNumberDouble<>(NUMBER);
    }
    // set the text of the text field and display the graphic
    @Override
    public void startEdit() {
        super.startEdit();
        textField.setText(converter.toString(getItem()));
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.requestFocus();
    }

    // revert to text display
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    // commits the edit. Update property if possible and revert to text display
    @Override
    public void commitEdit(T item) {

        // This block is necessary to support commit on losing focus, because the baked-in mechanism
        // sets our editing state to false before we can intercept the loss of focus.
        // The default commitEdit(...) method simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<S> table = getTableView();
            if (table != null) {
                TableColumn<S, T> column = getTableColumn();
                CellEditEvent<S, T> event = new CellEditEvent<>(table,
                        new TablePosition<>(table, getIndex(), column),
                        TableColumn.editCommitEvent(), item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);

        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

}
