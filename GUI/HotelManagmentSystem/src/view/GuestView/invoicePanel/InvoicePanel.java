package view.GuestView.invoicePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Connector;
import Model.Users;


class TableInfo {
	String[] colNames ;
	String[][] data;
}

public class InvoicePanel implements ActionListener {
	JPanel parentPanel;
	JButton refreshBtn;
	JTable invoiceTable;
	DefaultTableModel model;
	JScrollPane scrollInvoicePane;
	Connector con;
	ResultSet rs;
	int guestId;
	
	public InvoicePanel(JPanel p, int guset_id) {
		con = new Connector(Users.getRoot());
		
		parentPanel = p;
		guestId = guset_id;
		// scroll pane
		scrollInvoicePane = new JScrollPane(this.renderTable());
		scrollInvoicePane.setPreferredSize(new Dimension(850,700));
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setPreferredSize(new Dimension(100,40));
		refreshBtn.setOpaque(true);
		refreshBtn.setBackground(Color.black);
		refreshBtn.setFont(new Font("monospace", Font.ITALIC, 15));
		refreshBtn.addActionListener(this);
		
		
		p.add(refreshBtn, BorderLayout.NORTH);
		p.add(scrollInvoicePane, BorderLayout.CENTER);
		
	}
	
	public JTable renderTable() {
		TableInfo tableInfo = this.getInvoice(guestId);
		
		model = new DefaultTableModel(tableInfo.data , tableInfo.colNames ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non editable
            }
        };
        
		invoiceTable = new JTable(model);
		
		// table styling
		JTableHeader tableHeader = invoiceTable.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 20));
		tableHeader.setBackground(Color.GRAY);
		tableHeader.setForeground(Color.ORANGE);
		invoiceTable.setFont(new Font("Arial", Font.TYPE1_FONT, 15));
		
		CustomTableCellRenderer customRenderer = new CustomTableCellRenderer();
		invoiceTable.setDefaultRenderer(Object.class, customRenderer);
		
		return invoiceTable;
	}
	
	public TableInfo getInvoice(int guest_id) {
		// fetch invoice filter by user_id
		String query = "{CALL get_generated_invoice(?)}";
		
		ArrayList<String> colNames = 
				new ArrayList<>(Arrays.asList("invoice_id","guest_id", "invoice_total","invoice_date"));
		int colSize = colNames.size();
		ArrayList<String[]> data = new ArrayList<>();
		
		Object[] param = {guest_id};
		rs = con.getProcedureCallResult(query, param);
		
		try {
			while(rs.next()) {
				String[] rowValues = new String[colSize];
				
				int count = 0;
				for(String col: colNames) {
					if(col.equals("invoice_date")) {						
						rowValues[count] = rs.getString("invoice_date");
					} else {
						Integer val = rs.getInt(col);
						rowValues[count] = val.toString();
					}
					count++;
				}
				
				data.add(rowValues);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		TableInfo tableInfo = new TableInfo();
		// convert back to String
		tableInfo.colNames = colNames.toArray(new String[(colNames.size())]);
		tableInfo.data = data.toArray(new String[(data.size())][(colNames.size())]);
		
		return tableInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Refresh and add new row
		
		int prevNumRows = model.getRowCount();
		int afterNumRows = 0;
		
		TableInfo tableInfo = this.getInvoice(3);
		
		DefaultTableModel tempModel = new DefaultTableModel(tableInfo.data , tableInfo.colNames ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non editable
            }
        };
        
        afterNumRows = tempModel.getRowCount();
        
        if(afterNumRows != prevNumRows) {
        	int diff = Math.abs(afterNumRows - prevNumRows);
        	for(int i = 0; i < diff ; i++) {
        		int currentRow = prevNumRows + i;
        		model.addRow(tableInfo.data[currentRow]);
        	}
        }
        
		
//		model.addRow(newRow);
		model.fireTableDataChanged();
		
	}
	
	
}
