package business;


import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	private ListView<String> list;

	public void initialize() {
		LibraryMember lm= this.getMember();
		memberName.setFill(Color.GREEN);
		memberName.setText("Member "+lm.getFirstName()+" "+lm.getLastName()+" Added");
		list.getItems().add(list.getItems().size(),"First Name:  "+lm.getFirstName());

		list.getItems().add(list.getItems().size(),"First Name:  "+lm.getFirstName());
		list.getItems().add(list.getItems().size(),"Last Name:  "+lm.getLastName());
		list.getItems().add(list.getItems().size(),"Telephone:  "+lm.getTelephone());
		list.getItems().add(list.getItems().size(),"Address");
		list.getItems().add(list.getItems().size(),"Street:  "+lm.getAddress().getStreet());
		list.getItems().add(list.getItems().size(),"City:  "+lm.getAddress().getCity());
		list.getItems().add(list.getItems().size(),"State:  "+lm.getAddress().getState());
		list.getItems().add(list.getItems().size(),"Zip:  "+lm.getAddress().getZip());
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
