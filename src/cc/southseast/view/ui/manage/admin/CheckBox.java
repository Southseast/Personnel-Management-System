package cc.southseast.view.ui.manage.admin;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.TableCell;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 10:50 AM
 * @Version 1.0
 * 多选框
 */
public class CheckBox<S, T> extends TableCell<S, T> {

    private JFXCheckBox jfxCheckBox = new JFXCheckBox();

    public CheckBox() {
        setGraphic(jfxCheckBox);
    }

    @Override
    protected void updateItem(T item, boolean empty) {

        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // 初始化为不选中
            jfxCheckBox.setSelected(false);
            setGraphic(jfxCheckBox);
        }
    }
}
