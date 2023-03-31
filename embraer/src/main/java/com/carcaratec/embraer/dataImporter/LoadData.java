package com.carcaratec.embraer.dataImporter;

import com.carcaratec.embraer.model.ChassiBoletim;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadData {

    public ResponseEntity<ChassiBoletim> getBloco(int point, String path) throws IOException, InterruptedException {
        // inicializa a variável que irá armazenar o conteúdo do arquivo csv
        ChassiBoletim chassi = new ChassiBoletim();

        // caminho para o arquivo csv

        // cria uma lista para armazenar os valores do csv
        List<String> list = new ArrayList<>();

        // tenta ler o arquivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // lê a primeira linha do csv (que deve conter os títulos das colunas)
            String line = br.readLine();

            // lê a próxima linha do csv
            line = br.readLine();

            int param = 1;
            // enquanto houver uma próxima linha
            while (line != null) {
                // divide a linha atual pelo separador ";"
                String[] vect = line.split(";");
                if(param==1){
                    chassi.setIdChassi(Integer.valueOf(vect[2].replace("E7","").replaceAll("\\.","")));
                }

                if(point==param){
                    chassi.setIdBoletim(vect[0]);
                    chassi.setStatus(vect[1]);
                }


                line = br.readLine();
                param++;
            }
        } catch (IOException e) {
            // em caso de erro, exibe a mensagem de erro
            System.out.println("Error: " + e.getMessage());
        }

        // verifica se o conteúdo foi encontrado

        // se foi, retorna o conteúdo como resposta
        return ResponseEntity.ok(chassi);

    }

    public int countLine(String path) throws IOException, InterruptedException {
        // caminho para o arquivo csv

        // cria uma lista para armazenar os valores do csv
        List<String> list = new ArrayList<>();
        int param = -1;


        // tenta ler o arquivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // lê a primeira linha do csv (que deve conter os títulos das colunas)
            String line = br.readLine();

            // lê a próxima linha do csv
            line = br.readLine();

            // enquanto houver uma próxima linha
            while (line != null) {
                // divide a linha atual pelo separador ";"
                param++;
                line = br.readLine();

            }
        } catch (IOException e) {
            // em caso de erro, exibe a mensagem de erro
            System.out.println("Error: " + e.getMessage());
        }

        // verifica se o conteúdo foi encontrado

        // se foi, retorna o conteúdo como resposta
        return param;

    }




    public void convert(String inputFilePath, String outputFilePath, char delimiter) throws IOException {
        // Abre o arquivo XLSX
        try (FileInputStream inputStream = new FileInputStream(inputFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            // Obtém a primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Cria o arquivo CSV de saída
            try (PrintWriter writer = new PrintWriter(outputFilePath, StandardCharsets.UTF_8)) {

                // Percorre todas as linhas da planilha
                for (Row row : sheet) {
                    // Percorre todas as células da linha
                    int lastCellNum = row.getLastCellNum();
                    if (lastCellNum < 0) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < lastCellNum; i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        if (cell == null) {
                            sb.append(delimiter);
                            continue;
                        }
                        // Verifica o tipo da célula
                        switch (cell.getCellType()) {
                            case STRING:
                                sb.append(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    sb.append(new SimpleDateFormat("dd/MM/yyyy").format(date));
                                } else {
                                    sb.append(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                sb.append(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                // Avalia a fórmula e obtém o valor resultante
                                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                                CellValue cellValue = evaluator.evaluate(cell);
                                switch (cellValue.getCellType()) {
                                    case STRING:
                                        sb.append(cellValue.getStringValue());
                                        break;
                                    case NUMERIC:
                                        sb.append(cellValue.getNumberValue());
                                        break;
                                    case BOOLEAN:
                                        sb.append(cellValue.getBooleanValue());
                                        break;
                                    case ERROR:
                                        sb.append(cellValue.getErrorValue());
                                        break;
                                    default:
                                        sb.append(delimiter);
                                }
                                break;
                            default:
                                sb.append(delimiter);
                        }
                        sb.append(delimiter);
                    }
                    // Remove o último separador
                    sb.setLength(sb.length() - 1);
                    // Escreve a linha no arquivo CSV
                    writer.println(sb.toString());
                }
            }
        }
    }


    public String encodar(String path) {
     try {
         String decodedUrl = URLDecoder.decode(path, "UTF-8");
        String url = decodedUrl;
        System.out.println(url);
        return url;
    } catch (Exception e) {
        e.printStackTrace();
    }
     return "400";
}
}




