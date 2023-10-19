package view.GuestView.invoicePanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Set background color for even rows
        if (row % 2 == 0) {
            renderer.setBackground(Color.LIGHT_GRAY);
        } else {
            renderer.setBackground(Color.GRAY);
        }

        // Set foreground color for specific columns
        renderer.setForeground(Color.WHITE);

        return renderer;
    }
}
