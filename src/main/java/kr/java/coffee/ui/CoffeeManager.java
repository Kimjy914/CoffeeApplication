package kr.java.coffee.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.java.coffee.service.ProductService;
import kr.java.coffee.service.SaleService;
import kr.java.coffee.ui.content.ProductTablePanel;
import kr.java.coffee.ui.content.SaleDetailTable;
import kr.java.coffee.ui.content.SaleTablePanel;

public class CoffeeManager extends JFrame implements ActionListener, Observer{

	private JPanel contentPane;
	private ProductTablePanel pdtTable;
	private SaleTablePanel saleTable;
	private SaleDetailTable salePriceRankTable;
	private SaleDetailTable marginPriceRankTable;

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
        
        createMenu();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel topTable = new JPanel();
        contentPane.add(topTable);
        topTable.setLayout(new GridLayout(0, 2, 0, 0));
        
        pdtTable = new ProductTablePanel();
        pdtTable.loadData(ProductService.getInstance().selectProductAll());
        topTable.add(pdtTable);
        
        saleTable = new  SaleTablePanel();
        saleTable.loadData(SaleService.getInstance().selectSaleByAll());
        topTable.add(saleTable);
        
        salePriceRankTable = new  SaleDetailTable(true);
        Map<String, Boolean> map = new HashMap<>();
        map.put("isSalePrice", true);
        salePriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));
        contentPane.add(salePriceRankTable);
        
        marginPriceRankTable = new SaleDetailTable(false);
        map.put("isSalePrice", false);
        marginPriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));
        contentPane.add(marginPriceRankTable);
    }

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("프로그램");
        menuBar.add(mnNewMenu);
        
        JMenuItem menuItem = new JMenuItem("종료");
        menuItem.addActionListener(this);
        mnNewMenu.add(menuItem);
        
        JMenu mnNewMenu_1 = new JMenu("제품");
        menuBar.add(mnNewMenu_1);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("제품 등록");
        mntmNewMenuItem.addActionListener(this);
        mnNewMenu_1.add(mntmNewMenuItem);
        
        JMenu menu = new JMenu("판매현황");
        menuBar.add(menu);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("판매현황 등록");
        mntmNewMenuItem_1.addActionListener(this);
        menu.add(mntmNewMenuItem_1);
	}	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("종료")) {
			System.exit(0);
		}
		if(e.getActionCommand().equals("제품 등록")) {
			RegProductUI regProductUI = RegProductUI.getInstance();
			regProductUI.clearValue();
			regProductUI.setTable(pdtTable);
			regProductUI.setVisible(true);
		}
		if(e.getActionCommand().equals("판매현황 등록")) {
			RegSaleUI regSaleUI = RegSaleUI.getInstance();
			regSaleUI.clearValue();
			regSaleUI.setTable(saleTable);
			regSaleUI.getProductLoad();
			regSaleUI.setVisible(true);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	      Map<String, Boolean> map = new HashMap<>();
	      map.put("isSalePrice", true);
	      salePriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));

	      map.put("isSalePrice", false);
	      marginPriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));

	}
}
