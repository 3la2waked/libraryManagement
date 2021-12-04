package business;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutRecordController {

	@FXML
	private Text message;
	@FXML
	private TextField memberId;

	public void initialize() {

	}
	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}

	public void search(){
		DataAccess da = new DataAccessFacade();

		LibraryMember lm = da.readMemberMap().get(memberId.getText());

		List<CheckoutRecordEntry> cri= lm.getCheckoutRecord().getCheckoutRecordEntries();
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("---------------------------------------------------------------");
		System.out.println("Checkout Date | Due Date | Isbn | Title | Copies | Is Available");
		for(CheckoutRecordEntry c: cri) {
			System.out.println(c.getCheckoutDate()+" | "+c.getDueDate()+" | "+c.getBookCopy().getBook().getIsbn()+" | "+c.getBookCopy().getBook().getTitle()+" | "+c.getBookCopy().getCopyNum()+" | "+c.getBookCopy().getBook().isAvailable());
		}

		if(cri.size()==0){
			System.out.println("No Checkout Record Found");
		}


	}


	
	
}
