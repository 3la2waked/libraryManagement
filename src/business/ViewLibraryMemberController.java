package business;


import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;
import ui.ViewMemberWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewLibraryMemberController {

	@FXML
	private Text memberName;
	@FXML
	private Text memberDetails;

	@FXML
	private Button show;

	public void initialize() {
		LibraryMember lm= this.getMember();
		memberName.setFill(Color.GREEN);
		memberName.setText("Member "+lm.getFirstName()+" "+lm.getLastName()+" Added");
	}
	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}


	public LibraryMember getMember(){
		DataAccess da = new DataAccessFacade();

		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		Collections.sort(retval);
		return da.readMemberMap().get(retval.get(retval.size()-1));
	}

	
	
}
