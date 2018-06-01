package kr.java.coffee.ui;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import kr.java.coffee.service.ProductService;
import kr.java.coffee.service.SaleService;
import kr.java.coffee.ui.content.ProductTablePanel;
import kr.java.coffee.ui.content.SaleDetailTable;
import kr.java.coffee.ui.content.SaleTablePanel;

public class CoffeeManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public CoffeeManager() {
		initComponent();
	}

	private void initComponent() {
        setTitle("프랜차이즈 커피전문점 관리 ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel topTable = new JPanel();
        contentPane.add(topTable);
        topTable.setLayout(new GridLayout(0, 2, 0, 0));
        
        ProductTablePanel pdtTable = new ProductTablePanel();
        pdtTable.loadData(ProductService.getInstance().selectProductAll());
        topTable.add(pdtTable);
        
        SaleTablePanel saleTable = new  SaleTablePanel();
        saleTable.loadData(SaleService.getInstance().selectSaleByAll());
        topTable.add(saleTable);
        
        SaleDetailTable salePriceRankTable = new  SaleDetailTable(true);
        Map<String, Boolean> map = new HashMap<>();
        map.put("isSalePrice", true);
        salePriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));
        contentPane.add(salePriceRankTable);
        
        SaleDetailTable marginPriceRankTable = new SaleDetailTable(false);
        map.put("isSalePrice", false);
        marginPriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));
        contentPane.add(marginPriceRankTable);
    }	
}
