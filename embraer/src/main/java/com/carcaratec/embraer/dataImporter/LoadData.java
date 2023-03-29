package com.carcaratec.embraer.dataImporter;

import com.carcaratec.embraer.model.ChassiBoletim;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


}
