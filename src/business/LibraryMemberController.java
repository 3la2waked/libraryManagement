package business;


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

public class LibraryMemberController {

	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField phone;
	@FXML
	private TextField street;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField zip;

	@FXML
	private Text fnameLabel;
	@FXML
	private Text lnameLabel;
	@FXML
	private Text phoneLabel;
	@FXML
	private Text streetLabel;
	@FXML
	private Text cityLabel;
	@FXML
	private Text stateLabel;
	@FXML
	private Text zipLabel;

	@FXML
	private Text message;

	@FXML
	private Text memberName;
	@FXML
	private Text memberDetails;

	@FXML
	private Button show;

	public void save(){


		if(isValid()){
			DataAccess da = new DataAccessFacade();
			List<String> retval = new ArrayList<>();
			retval.addAll(da.readMemberMap().keySet());
			Collections.sort(retval);
			System.out.println(retval.toString());
			int lastId = Integer.parseInt(retval.get(retval.size()-1));
			lastId++;

			Address address = new Address(street.getText(),city.getText(),state.getText(),zip.getText());
			LibraryMember member = new LibraryMember(lastId+"",fname.getText(),lname.getText(),phone.getText(),address);
			DataAccessFacade daf = new DataAccessFacade();
			daf.saveNewMember(member);
			message.setFill(Color.GREEN);
			message.setText("New Member Added Successfully");
			clean();
			this.view(member);
		}else {
			message.setFill(Color.RED);
			message.setText("Fields With Red Color are Required");
		}



	}
	public void clean(){
		message.setText("");
		fname.setText("");
		lname.setText("");
		phone.setText("");
		street.setText("");
		state.setText("");
		city.setText("");
		zip.setText("");
	}
	private boolean isValid(){
		boolean valid = true;


		if(fname.getText().isEmpty()){
			fnameLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			fnameLabel.setFill(Color.BLACK);
		}

		if(lname.getText().isEmpty()){
			lnameLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			lnameLabel.setFill(Color.BLACK);
		}

		if(phone.getText().isEmpty()){
			phoneLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			phoneLabel.setFill(Color.BLACK);
		}

		if(street.getText().isEmpty()){
			streetLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			streetLabel.setFill(Color.BLACK);
		}

		if(city.getText().isEmpty()){
			cityLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			cityLabel.setFill(Color.BLACK);
		}

		if(state.getText().isEmpty()){
			stateLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			stateLabel.setFill(Color.BLACK);
		}

		if(zip.getText().isEmpty()){
			zipLabel.setFill(Color.FIREBRICK);
			valid = false;
		}else{
			zipLabel.setFill(Color.BLACK);
		}

		return valid;
	}

	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}

	public void view(LibraryMember lm){
		Start.hideAllWindows();
		ViewMemberWindow.INSTANCE.init();
		ViewMemberWindow.INSTANCE.show();
	}
}
