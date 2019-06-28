package cc.southseast.view.ui.manage.admin;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 9:20 AM
 * @Version 1.0
 * 添加编辑
 */
public class CommandBar<S, T> extends TableCell<S, T> {

    private HBox commandBar = new HBox();

    private JFXButton userEdit = new JFXButton(EDIT_BUTTON_NAME);

    private JFXButton userDelete = new JFXButton(DELETE_BUTTON_NAME);

    public CommandBar() {

        // 引入样式文件
        this.getStylesheets().add(COMMAND_BAR_CSS_ROUTE);

        userEdit.setId(EDIT_BUTTON_CSS_ID);

        userDelete.setId(DELETE_BUTTON_CSS_ID);

        commandBar.setSpacing(10);
        commandBar.getChildren().addAll(userEdit, userDelete);
        setGraphic(commandBar);
    }

    @Override
    public void updateItem(T item, boolean empty) {

        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(commandBar);
            setText(null);
        }
    }

    public JFXButton getUserEdit() {
        return userEdit;
    }

    public JFXButton getUserDelete() {
        return userDelete;
    }
}