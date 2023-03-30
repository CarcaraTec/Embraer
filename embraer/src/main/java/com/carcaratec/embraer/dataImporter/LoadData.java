package com.carcaratec.embraer.dataImporter;

import com.carcaratec.embraer.model.ChassiBoletim;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.poi.ss.usermodel.CellType;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadData {

    public ResponseEntity<ChassiBoletim> getBloco(int point) throws IOException, InterruptedException {
        // inicializa a variável que irá armazenar o conteúdo do arquivo csv
        ChassiBoletim chassi = new ChassiBoletim();

        // caminho para o arquivo csv
        String path = "C:\\Users\\Luara Amaral\\Documents\\GitHub\\Embraer\\embraer\\src\\main\\java\\com\\carcaratec\\embraer\\csv\\Sample_-_Chassis_10000076.csv";

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
                    chassi.setIdChassi(Integer.valueOf(vect[2]));
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

    public int countLine() throws IOException, InterruptedException {
        // caminho para o arquivo csv
        String path = "C:\\Users\\Luara Amaral\\Documents\\GitHub\\Embraer\\embraer\\src\\main\\java\\com\\carcaratec\\embraer\\csv\\Sample_-_Chassis_10000076.csv";

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




    public static void convert(String inputFilePath) throws IOException {
        // Abre o arquivo XLSX
        FileInputStream inputStream = new FileInputStream(inputFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Cria o arquivo CSV de saída no mesmo diretório que o arquivo XLSX
        String outputFilePath = inputFilePath.replace(".xlsx", ".csv");
        FileOutputStream outputStream = new FileOutputStream(outputFilePath);

        // Percorre todas as linhas e células da planilha
        for (Row row : sheet) {
            StringBuilder sb = new StringBuilder();
            for (Cell cell : row) {
                // Verifica o tipo da célula
                if (cell.getCellType() == CellType.NUMERIC) {
                    // Converte o valor numérico em uma string
                    sb.append(String.valueOf(cell.getNumericCellValue()));
                } else {
                    // Adiciona o valor da célula como uma string
                    sb.append(cell.getStringCellValue());
                }
                // Adiciona uma vírgula para separar as células
                sb.append(";");
            }
            // Remove a última vírgula
            sb.deleteCharAt(sb.length() - 1);
            // Adiciona uma quebra de linha
            sb.append("\n");
            // Escreve a linha no arquivo CSV
            outputStream.write(sb.toString().getBytes());
        }

        // Fecha os streams de leitura e escrita
        inputStream.close();
        outputStream.close();
        // Fecha o workbook
        workbook.close();
    }


    public static void main(String[] args) throws IOException {
            String inputFilePath = "C:\\Users\\Luara Amaral\\Documents\\GitHub\\Embraer\\embraer\\src\\main\\java\\com\\carcaratec\\embraer\\csv\\Sample_-_Chassis_10000076.xlsx";
            convert(inputFilePath);
        }

    }


