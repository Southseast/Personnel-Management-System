package cc.southseast.view.ui.manage.admin.tableview;

import cc.southseast.controller.function.delete.ToDeleteRel;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.command.update.RelUpdatePanel;
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

import static cc.southseast.controller.base.ToGetData.getRelCacheData;
import static cc.southseast.controller.base.ToGetData.REL_CACHE_DATA;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/5 9:33 PM
 * @Version 1.0
 * 职工部门关系表
 */
public class RelTableView extends HBox {

    private TableView tableView = new TableView();
    private TableColumn checkBoxColumn = new TableColumn<Employee, Boolean>();
    private TableColumn num = new TableColumn<Employee, Integer>(NUM_NAME);
    private TableColumn empNo = new TableColumn<Employee, Long>(EMP_NO_NAME);
    private TableColumn empName = new TableColumn<Employee, String>(EMP_NAME_NAME);
    private TableColumn depNo = new TableColumn<Employee, String>(DEP_NO_NAME);
    private TableColumn depName = new TableColumn<Employee, String>(DEP_NAME_NAME);
    private TableColumn empLevel = new TableColumn<Employee, Date>(EMP_LEVEL_NAME);
    private TableColumn empPost = new TableColumn<Employee, String>(EMP_POST_NAME);
    private TableColumn empWages = new TableColumn<Employee, String>(EMP_WAGES_NAME);
    private TableColumn commandbar = new TableColumn<>(COMMAND_BAR_NAME);
    private Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> cellCheckBox;
    private Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> cellCommandBar;

    public RelTableView() {

        // 勾选框
        cellCheckBox = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> param) {
                CheckBox<Employee, Boolean> cell = new CheckBox<>();
                JFXCheckBox jfxCheckBox = (JFXCheckBox) cell.getGraphic();
                jfxCheckBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Employee employee = REL_CACHE_DATA.get(cell.getIndex());
                        if (employee.getCheck()) {
                            employee.setCheck(false);
                        } else {
                            employee.setCheck(true);
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
            TableCell<Employee, String> cell = new TableCell<Employee, String>() {
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

        empNo.setCellValueFactory(new PropertyValueFactory<Employee, Long>(EMP_NO_BEAN_ID));

        empName.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_NAME_BEAN_ID));

        depNo.setCellValueFactory(new PropertyValueFactory<Employee, String>(DEP_NO_BEAN_ID));

        depName.setCellValueFactory(new PropertyValueFactory<Employee, String>(DEP_NAME_BEAN_ID));

        empPost.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_POST_BEAN_ID));

        empLevel.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_LEVEL_BEAN_ID));

        // 计算工资
        empWages.setCellFactory((col) -> {
            TableCell<Employee, Date> cell = new TableCell<Employee, Date>() {
                @Override
                public void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Employee employee = getTableView().getItems().get(getIndex());
                        long wages = 4000 + employee.getEmpLevel() * 500;
                        this.setText(String.valueOf(wages));
                    }
                }
            };
            return cell;
        });

        // 删除修改操作
        cellCommandBar = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> param) {
                CommandBar<Employee, Boolean> cell = new CommandBar<>();
                cell.getUserEdit().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new RelUpdatePanel(REL_CACHE_DATA.get(cell.getIndex()), tableView);
                    }
                });
                cell.getUserDelete().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new ToDeleteRel(REL_CACHE_DATA.get(cell.getIndex()).getEmpNo(), tableView);
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

        REL_CACHE_DATA = getRelCacheData();

        tableView.setItems(REL_CACHE_DATA);
        tableView.setMinSize(884, 680);
        tableView.styleProperty();
        tableView.getColumns().setAll(checkBoxColumn, num, empNo, empName, depNo, depName,
                empPost, empLevel, empWages, commandbar);

        this.getChildren().addAll(tableView);
    }
}
