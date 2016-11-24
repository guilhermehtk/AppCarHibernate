package relatorios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.jdesktop.swingx.JXBusyLabel;

public abstract class GlassPaneWorker extends SwingWorker<Object, Object> {

    private class WaitingGlassPane extends LockingGlassPane implements PropertyChangeListener {

        private JXBusyLabel busyLabel;

        private Component previousGlassPane;

        private JInternalFrame frame;

        WaitingGlassPane(JInternalFrame frame, String message) {
            super(new Color(255, 255, 255, 180));

            busyLabel = new JXBusyLabel();

            this.frame = frame;

            previousGlassPane = frame.getGlassPane();
            setLayout(new BorderLayout());
            JPanel messagePanel
                    = new JPanel(new FlowLayout(FlowLayout.LEADING));
            messagePanel.setOpaque(false);
            JLabel messageLabel = new JLabel(message);
            messageLabel.setOpaque(false);
            messagePanel.add(messageLabel);
            add(messagePanel, BorderLayout.NORTH);
            JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            centerPanel.add(busyLabel);
            add(centerPanel, BorderLayout.CENTER);
            final JButton cancelButton = new JButton("Cancelar");
            cancelButton.setOpaque(false);
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cancel(true);
                }
            });
            addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    cancelButton.requestFocusInWindow();
                }
            });
            JPanel southPanel = new JPanel();
            southPanel.setOpaque(false);
            southPanel.add(cancelButton);
            cancelButton.setCursor(Cursor.getDefaultCursor());
            add(southPanel, BorderLayout.SOUTH);
        }

        public void propertyChange(PropertyChangeEvent evt) {
            if ("state".equals(evt.getPropertyName())) {
                if (StateValue.DONE.equals(evt.getNewValue())) {
                    setVisible(false);
                    frame.setGlassPane(previousGlassPane);
                    busyLabel.setBusy(false);
                } else if (StateValue.STARTED.equals(evt.getNewValue())) {
                    busyLabel.setBusy(true);
                    frame.setGlassPane(this);
                    setVisible(true);
                }
            }
        }

    }

    public void execute(
            final JInternalFrame frame,
            final String message) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final WaitingGlassPane glassPane = new WaitingGlassPane(frame, message);
                addPropertyChangeListener(glassPane);
            }
        });
        super.execute();
    }

}
