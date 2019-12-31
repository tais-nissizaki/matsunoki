/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.control.produtos;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Taís
 */
public class JPEGImageFileFilter extends FileFilter implements java.io.FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.getName().toLowerCase().endsWith(".jpeg")) {
            return true;
        }
        if (file.getName().toLowerCase().endsWith(".jpg")) {
            return true;
        }
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "JPEG files";
    }

}
