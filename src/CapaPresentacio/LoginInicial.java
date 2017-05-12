package CapaPresentacio;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import CapaAplicacio.LoginControler;

public class LoginInicial {

	public static void main(String[] args) {
		boolean logat = false;
		while (!logat) {
			JLabel label_login = new JLabel("Usuari:");
			JTextField login = new JTextField();

			JLabel label_password = new JLabel("Password:");
			JPasswordField password = new JPasswordField();

			Object[] array = { label_login, login, label_password, password };

			int res = JOptionPane.showConfirmDialog(null, array, "Login BBDD",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (res == JOptionPane.OK_OPTION) {

				try {
					new LoginControler().Login(login.getText().trim(),
							new String(password.getPassword()));
					logat = true;
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(new JFrame(),
									"Usuari i/o contrasenya incorrecte.\nTorna a provar.");
				}

			} else
				System.exit(0);

		}
		
		new PantallaJocDaus();
	}
}
