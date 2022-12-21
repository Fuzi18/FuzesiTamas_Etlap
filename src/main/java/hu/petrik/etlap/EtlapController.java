package hu.petrik.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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




}