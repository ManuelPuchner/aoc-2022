package util;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyUtils {

	private CopyUtils() {}

	public static void copyToClipboard(String str) {
		// check if OS is linux
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			// copy to clipboard
			try {
				ProcessBuilder pb = new ProcessBuilder("xclip", "-selection", "clipboard");
				Process p = pb.start();
				p.getOutputStream().write(str.getBytes());
				p.getOutputStream().flush();
				p.getOutputStream().close();
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			StringSelection selection = new StringSelection(str);
			Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		}
	}
}
