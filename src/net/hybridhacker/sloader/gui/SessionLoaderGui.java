package net.hybridhacker.sloader.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.hybridhacker.sloader.loader.AltInjector;
import net.hybridhacker.sloader.utils.UUIDResolver;

/**
 * Loaders GUI
 * @author Flaflo
 *
 */
public final class SessionLoaderGui {
	
	private JFrame frmAltloader;
	private JTextField textField;

	/**
	 * Program Entry point
	 * @param args the command line args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionLoaderGui window = new SessionLoaderGui();
					window.frmAltloader.setVisible(true);
					UIManager.LookAndFeelInfo[] arrayOfLookAndFeelInfo;
					int j = (arrayOfLookAndFeelInfo = UIManager.getInstalledLookAndFeels()).length;
					for (int i = 0; i < j; i++) {
						UIManager.LookAndFeelInfo info = arrayOfLookAndFeelInfo[i];
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructs the Session GUI
	 */
	public SessionLoaderGui() {
		try {
			initialize();
		} catch (SAXException | IOException | ParserConfigurationException | RuntimeException e) {
			JOptionPane.showMessageDialog(null, "An error occured\n" + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Initializes the GUI Components
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws RuntimeException
	 */
	private void initialize() throws SAXException, IOException, ParserConfigurationException, RuntimeException {
		final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getClass().getResourceAsStream("/language.xml"));
		
		doc.getDocumentElement().normalize();

		final NodeList langNodes = doc.getElementsByTagName("language");
		Element found = null;

		for (int i = 0; i < langNodes.getLength(); i++)
			if (langNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element element = (Element) langNodes.item(i);
				
				if (element.getAttribute("name").equalsIgnoreCase(doc.getDocumentElement().getAttribute("current"))) {
					found = element;
					
					break;
				}
			}
	
		if (found == null)
			throw new RuntimeException("Could not find current language");
		
		final Element lang = found;
		
		this.frmAltloader = new JFrame();
		this.frmAltloader.setResizable(false);
		this.frmAltloader.setTitle(getLangText(lang, "title"));
		this.frmAltloader.setBounds(100, 100, 510, 139);
		this.frmAltloader.setDefaultCloseOperation(3);
		this.frmAltloader.getContentPane().setLayout(null);

		JLabel lblEnterToken = new JLabel(getLangText(lang, "token"));
		lblEnterToken.setBounds(10, 12, 244, 16);
		this.frmAltloader.getContentPane().add(lblEnterToken);

		JButton btnNewButton = new JButton(getLangText(lang, "load"));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (SessionLoaderGui.this.textField.getText().length() == 0)
					JOptionPane.showMessageDialog(null, getLangText(lang, "error1"));
				else {
					final String[] infos = SessionLoaderGui.this.textField.getText().split(":");
					
					if (infos.length == 4)
						try {
							AltInjector.inject(infos[0], UUIDResolver.addUUIDDashes(infos[1]), infos[2], infos[3]);
							JOptionPane.showMessageDialog(null, String.format(getLangText(lang, "success"), infos[0]));
						} catch (IOException | ParseException ex) {
							JOptionPane.showMessageDialog(null, String.format(getLangText(lang, "error3"), ex.getMessage()));
						}
					else
						JOptionPane.showMessageDialog(null, getLangText(lang, "error2"));
				}
			}
		});
		btnNewButton.setBounds(408, 79, 90, 28);
		this.frmAltloader.getContentPane().add(btnNewButton);

		this.textField = new JTextField();
		this.textField.setBounds(10, 39, 488, 28);
		this.frmAltloader.getContentPane().add(this.textField);
		this.textField.setColumns(10);

		final JLabel lblWatermark = new JLabel(getLangText(lang, "footer"));
		lblWatermark.setBounds(10, 84, 148, 16);
		this.frmAltloader.getContentPane().add(lblWatermark);

		final JLabel lblformatNameuuidtokentoken = new JLabel(getLangText(lang, "hint"));
		lblformatNameuuidtokentoken.setBounds(308, 12, 190, 16);
		this.frmAltloader.getContentPane().add(lblformatNameuuidtokentoken);
	}
	
	/**
	 * Returns the language text from an xml element by a tag
	 * @param lang the xml element
	 * @param tag the xml tag
	 * @return the language text
	 */
	private static String getLangText(Element lang, String tag) {
		return lang.getElementsByTagName(tag).item(0).getTextContent();
	}

}