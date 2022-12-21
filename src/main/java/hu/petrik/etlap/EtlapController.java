package hu.petrik.etlap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EtlapController {
    @FXML
    private TableView<Etel> etlapTable;
    @FXML
    private Button torlesButton;
    @FXML
    private TableColumn<Etel, String> nameCol;
    @FXML
    private ListView<String> listview;
    @FXML
    private Button ujEtelFelveteleButton;
    @FXML
    private TableColumn<Etel, String> categoryCol;
    @FXML
    private TableColumn<Etel, Integer> priceCol;
    private EtlapDB db;

    private Etel getSelectedFood() {
        int selectedIndex = etlapTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert(Alert.AlertType.WARNING, "A táblázatból előbb válasszon ki egy ételt!", "");
            return null;
        }
        return etlapTable.getSelectionModel().getSelectedItem();
    }

    private Object alert(Alert.AlertType warning, String s, String s1) {
        return null;
    }

    private Etel getSelectedFoodNullNotMatter() {
        int selectedIndex = etlapTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return null;
        }
        return etlapTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void torlesClick(ActionEvent actionEvent) {
        Etel selected = getSelectedFood();
        if (selected == null) return;
        Optional<ButtonType> optionalButtonType = (Optional<ButtonType>) alert(Alert.AlertType.CONFIRMATION, "Biztos, hogy törölni szeretné a kiválasztott ételt?", "");
        if (optionalButtonType.isEmpty() || !(optionalButtonType.get().equals(ButtonType.OK)) && !(optionalButtonType.get().equals(ButtonType.YES))) {
            return;
        }
        try {
            if (db.deleteFood(selected.getId())) {
                alert(Alert.AlertType.WARNING, "Sikeres törlés", "");
                listview.getItems().clear();
            } else {
                alert(Alert.AlertType.WARNING, "Sikertelen törlés", "");
            }
            readFood();
        } catch (SQLException e) {
            sqlAlert(e);
        }
    }
    private void readFood() throws SQLException {
        List<Etel> dogs = db.readFood();
        etlapTable.getItems().clear();
        etlapTable.getItems().addAll(dogs);
    }



    private void sqlAlert(SQLException e) {
        alert(Alert.AlertType.ERROR, "Hiba történt az adatbázis kapcsolat kialakításakor", e.getMessage());
    }





}