package com.shoesclick.service.notification.loader;

import com.shoesclick.service.notification.exception.ResourceException;
import com.shoesclick.service.notification.utils.FileUtils;
import org.springframework.boot.ApplicationRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractImportFileLoader implements ApplicationRunner {

    protected void importFile(String fileName) {
        try (BufferedReader fileBuffer = FileUtils.getBufferReader(fileName)) {
            String linha;
            while ((linha = fileBuffer.readLine()) != null) {
                createElementItem(linha);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler a linha do arquivo", e);
        }
    }

    private void createElementItem(String linha) {
        try {
            createItem(linha);
        }catch (ResourceException resourceException){
            System.err.println("Erro ao criar o item: "+ resourceException.toString());
        }
    }

    protected abstract void createItem(String linha);
}
