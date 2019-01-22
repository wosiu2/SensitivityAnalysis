/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.BOQ;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Włosek
 */
public class Summary {

    public int generate() {
        String filePath = "D:/file.html";

        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("<html>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<table>");
            bw.newLine();
            bw.write("<tr>");

            bw.newLine();
            bw.write("<td>sdssds</td>");
            bw.newLine();
            bw.write("<td>sdsds</td>");

            bw.newLine();
            bw.write("</tr>");
            bw.newLine();
            bw.write("</table>");
            bw.newLine();
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd podczas zapisywania pliku");
            System.exit(1);
        }

        return 0;

    }

    public static void main(String[] args) {

        Summary s = new Summary();

        s.generate();
    }
}
