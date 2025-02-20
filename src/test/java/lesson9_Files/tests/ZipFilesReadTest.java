package lesson9_Files.tests;

import com.codeborne.pdftest.PDF;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFilesReadTest {
    private final ClassLoader cl = ZipFilesReadTest.class.getClassLoader();


    @Test
    void zipFilePDFReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("example.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("examplepdf.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals("Пример pdf \n", pdf.text);
                }
            }
        }
    }

    @Test
    void zipCSVFileReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("example.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("necessaryFieldsOnlyRegistrationPageUsingCsvFileSourceAnnotationTest.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(3, data.size());
                    Assertions.assertArrayEquals(new String[]{"Test", " Testov", " Male", " 1234567899", " 05", " June", " 2000"}, data.get(0));
                    Assertions.assertArrayEquals(new String[]{"Ivan", " Ivanov", " Other", " 1234567800", " 07", " July", " 2006"}, data.get(1));
                    Assertions.assertArrayEquals(new String[]{"Testie", " Testovna", " Female", " 1225567899", " 25", " August", " 2021"}, data.get(2));
                }
            }
        }
    }

    @Test
    void zipFileXLSXReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("example.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("import_empl_xlsx.xlsx")) {
                    XLS xls = new XLS(zis);
                    double actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(9).getNumericCellValue();
                    String  actualValueString = Double.toString(actualValue);
                    Assertions.assertTrue(actualValueString.contains("131"));
                }
            }
        }
    }
}



