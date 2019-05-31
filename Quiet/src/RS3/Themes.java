package RS3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 
 * @author Jacob
 *
 */

public class Themes extends JFrame implements ActionListener {
	private JButton btnMotif = new JButton("Motif");
	private JButton btnSystem = new JButton("System");
	private JButton btnNimbus = new JButton("Nimbus");
	private JButton btnStandard = new JButton("Standard");
	private JPanel pnlBtn = new JPanel();

	public Themes () {
		JFrame frame = new JFrame("Themes");

		pnlBtn.setLayout(new GridLayout(2, 2));
		pnlBtn.add(btnMotif);
		pnlBtn.add(btnSystem);
		pnlBtn.add(btnNimbus);
		pnlBtn.add(btnStandard);
		frame.add(pnlBtn);

		btnMotif.addActionListener(this);
		btnSystem.addActionListener(this);
		btnNimbus.addActionListener(this);
		btnStandard.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(50, 100);
	}

	public void Standard() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			JOptionPane.showMessageDialog(null, "Theme changed.");
		} catch (Exception e) {
			System.out.println("Look and feel not set.");
		}
	}
	
	public void Motif() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			JOptionPane.showMessageDialog(null, "Theme changed.");
		} catch (Exception e) {
			System.out.println("Look and feel not set.");
		}
	}

	public void System() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JOptionPane.showMessageDialog(null, "Theme changed.");
		} catch (Exception e) {
			System.out.println("Look and feel not set.");
		}
	}
	
	public void Nimbus() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			JOptionPane.showMessageDialog(null, "Theme changed.");
		} catch (Exception e) {
			System.out.println("Look and feel not set.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStandard) {
			Standard();
		} else if (e.getSource() == btnMotif) {
			Motif();
		} else if (e.getSource() == btnSystem) {
			System();
		} else if (e.getSource() == btnNimbus) {
			Nimbus();
		}
	}
}