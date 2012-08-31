package org.imageposter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class VerifyingUI extends JPanel {
	private JLabel lblNewLabel;
	private JTextField txtVerificationCode;
	private JButton btnAuthenticate;
	private JWebBrowser webBrowser;

	/**
	 * Create the panel.
	 */
	public VerifyingUI() {
		this.setLayout(new MigLayout("", "[][grow][]", "[][grow]"));
		this.add(this.getLblNewLabel(), "cell 0 0,alignx leading");
		this.add(this.getTxtVerificationCode(), "cell 1 0,growx");
		this.add(this.getBtnAuthenticate(), "cell 2 0");
		this.add(this.getWebBrowser_1(), "cell 0 1 3 1,grow");

	}

	public JLabel getLblNewLabel() {
		if (this.lblNewLabel == null) {
			this.lblNewLabel = new JLabel("Verification Code");
		}
		return this.lblNewLabel;
	}
	public JTextField getTxtVerificationCode() {
		if (this.txtVerificationCode == null) {
			this.txtVerificationCode = new JTextField();
			this.txtVerificationCode.setColumns(10);
		}
		return this.txtVerificationCode;
	}
	public JButton getBtnAuthenticate() {
		if (this.btnAuthenticate == null) {
			this.btnAuthenticate = new JButton("Authenticate");
		}
		return this.btnAuthenticate;
	}
	public JWebBrowser getWebBrowser_1() {
		if (this.webBrowser == null) {
			this.webBrowser = new JWebBrowser();
		}
		return this.webBrowser;
	}
}
