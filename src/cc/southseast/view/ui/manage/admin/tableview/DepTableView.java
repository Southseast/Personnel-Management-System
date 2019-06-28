package cc.southseast.view.ui.manage.admin.tableview;

import cc.southseast.controller.function.delete.ToDeleteDep;
import cc.southseast.model.dao.Department;
import cc.southseast.view.ui.command.update.DepUpdatePanel;
import cc.southseast.view.ui.manage.admin.CheckBox;
import cc.southseast.view.ui.manage.admin.CommandBar;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Date;

import static cc.southseast.controller.base.ToGetData.DEP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getDepCacheData;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/5 9:33 PM
 * @Version 1.0
 * 部门表
 */
public class DepTableView extends HBox {

    private TableView tableView = new TableView();
    private TableColumn checkBoxColumn = new TableColumn<Department, Boolean>();
    private TableColumn num = new TableColumn<Department, Integer>(NUM_NAME);
    private TableColumn depNo = new TableColumn<Department, Long>(DEP_NO_NAME);
    private TableColumn depName = new TableColumn<Department, String>(DEP_NAME_NAME);
    private TableColumn depAddress = new TableColumn<Department, String>(DEP_ADDRESS_NAME);
    private TableColumn depPhone = new TableColumn<Department, String>(DEP_POHONE_NAME);
    private TableColumn commandbar = new TableColumn<>(COMMAND_BAR_NAME);
    private Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>> cellCheckBox;
    private Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>> cellCommandBar;

    public DepTableView() {

        // 勾选框
        cellCheckBox = new Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>>() {
            public TableCell<Department, Boolean> call(TableColumn<Department, Boolean> param) {
                CheckBox<Department, Boolean> cell = new CheckBox<>();
                JFXCheckBox jfxCheckBox = (JFXCheckBox) cell.getGraphic();
                jfxCheckBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Department department = DEP_CACHE_DATA.get(cell.getIndex());
                        if (department.getCheck()) {
                            department.setCheck(false);
                        } else {
                            department.setCheck(true);
                        }
                    }
                });
                return cell;
            }
        };
        checkBoxColumn.setCellFactory(cellCheckBox);
        checkBoxColumn.setMaxWidth(25);

        // 序号框
        num.setCellFactory((col) -> {
            TableCell<Department, String> cell = new TableCell<Department, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });

        depNo.setCellValueFactory(new PropertyValueFactory<Department, Long>(DEP_NO_BEAN_ID));

        depName.setCellValueFactory(new PropertyValueFactory<Department, String>(DEP_NAME_BEAN_ID));

        depAddress.setCellValueFactory(new PropertyValueFactory<Department, String>(DEP_ADDRESS_BEAN_ID));

        depPhone.setCellValueFactory(new PropertyValueFactory<Department, Date>(DEP_PHONE_BEAN_ID));

        // 删除修改操作
        cellCommandBar = new Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>>() {
            public TableCell<Department, Boolean> call(TableColumn<Department, Boolean> param) {
                CommandBar<Department, Boolean> cell = new CommandBar<>();
                cell.getUserEdit().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new DepUpdatePanel(DEP_CACHE_DATA.get(cell.getIndex()), tableView);
                    }
                });
                cell.getUserDelete().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new ToDeleteDep(DEP_CACHE_DATA.get(cell.getIndex()).getDepNo(), tableView);
                    }
                });
                return cell;
            }
        };
        commandbar.setCellFactory(cellCommandBar);
        commandbar.setMinWidth(110);

        tableView.setEditable(true);
        tableView.setId(TABLEVIEW_CSS_ID);
        tableView.getItems().clear();

        DEP_CACHE_DATA = getDepCacheData();

        tableView.setItems(DEP_CACHE_DATA);
        tableView.setMinSize(884, 680);
        tableView.styleProperty();
        tableView.getColumns().setAll(checkBoxColumn, num, depNo, depName, depAddress, depPhone, commandbar);

        this.getChildren().addAll(tableView);
    }
}
