/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.application.service;

import biz.cogitare.driveranddelaydetails.application.exceptions.FileReaderException;
import biz.cogitare.driveranddelaydetails.infrastructure.application.FileReaderServiceImplementation;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class FileReaderServiceTest {

    private final FileReaderService fileReaderService = new FileReaderServiceImplementation();
    private final String resourceURI = "DriverAndDelayDetailsTest.txt";
    private final String header = "TrainId|Station|ActualDepartureTime|DriverName|DepartureLatenessInSeconds";
    private List<String[]> fileContents;

    @Before
    public void setup() throws FileReaderException {
        fileContents = fileReaderService.readFile(resourceURI, header);
    }

    @Test
    public void TestRecordsCount() throws FileReaderException {
        assertThat(fileContents.size()).isEqualTo(30);
    }

    @Test
    public void TestEachElementCount() {
        assertThat(fileContents.get(0).length).isEqualTo(5);
    }
}
