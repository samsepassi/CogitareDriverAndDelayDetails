/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.application.service;

import biz.cogitare.driveranddelaydetails.application.exceptions.FileReaderException;
import java.util.List;

/**
 *
 * @author Sam
 */
public interface FileReaderService {
    /**
     * Read a file and returns a list containing individuals lines.
     * @param resourceURI The address of a file inside the resources folder.
     * @param header - Header information to be avoided during parsing the file
     * contents.
     * @return - file contents as a list
     * @throws biz.cogitare.driveranddelaydetails.application.exceptions.FileReaderException
     */
    public List<String[]> readFile(final String resourceURI, final String header) throws FileReaderException;
}
