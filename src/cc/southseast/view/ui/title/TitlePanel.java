package cc.southseast.view.ui.title;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/9 9:07 AM
 * @Version 1.0
 */
public class TitlePanel extends HBox {

    private Label titleLable = new Label(TITLE_NAME);

    private Label littleTitleLabe = new Label();

    private HBox littleTitleBox = new HBox();

    public TitlePanel(String littleTitle) {

        // 引入样式文件
        this.getStylesheets().add(TITLE_PANEL_CSS_ROUTE);

        titleLable.setId(TITLE_CSS_ID);

        littleTitleLabe.setId(LITTLE_TITLE_CSS_ID);
        littleTitleLabe.setText(littleTitle);
        littleTitleBox.setAlignment(Pos.BOTTOM_RIGHT);
        littleTitleBox.getChildren().addAll(littleTitleLabe);

        this.getChildren().addAll(titleLable, littleTitleBox);
    }
}
