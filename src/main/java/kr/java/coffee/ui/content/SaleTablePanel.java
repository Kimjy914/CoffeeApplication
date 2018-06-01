package kr.java.coffee.ui.content;

import javax.swing.SwingConstants;

import kr.java.swinglibrary.component.AbstractTablePanel;

public class SaleTablePanel extends AbstractTablePanel {

	public SaleTablePanel() {
		super("판매 현황");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setAlignWith() {
		// TODO Auto-generated method stub
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 3);
        tableSetWidth(100, 200, 100, 100);
	}

	@Override
	public void setColumnNames() {
		// TODO Auto-generated method stub
		colNames = new String[] { "번호", "제품코드", "판매수량", "마진율" };
	}
}