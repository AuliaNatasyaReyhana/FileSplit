/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filesplit;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class FileProcessor {
    private Queue<String> fileChunks;

    public FileProcessor() {
        this.fileChunks = new LinkedList<>();
    }

    // Method untuk membaca file teks
    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileChunks.add(line);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }

    // Method untuk memotong file sesuai jumlah bagian yang diminta pengguna
    public void splitFile(int numberOfChunks) {
        int chunkSize = fileChunks.size() / numberOfChunks;
        int remainder = fileChunks.size() % numberOfChunks;

        Queue<String> currentChunk = new LinkedList<>();
        int counter = 0;
        int chunkCounter = 1;

        while (!fileChunks.isEmpty()) {
            currentChunk.add(fileChunks.poll());
            counter++;

            if (counter == chunkSize + (remainder > 0 ? 1 : 0)) {
                System.out.println("Bagian " + chunkCounter + ":");
                printChunk(currentChunk);
                currentChunk.clear();
                counter = 0;
                chunkCounter++;
                remainder--;
            }
        }
    }

    // Method untuk menampilkan isi tiap potongan file
    private void printChunk(Queue<String> chunk) {
        for (String line : chunk) {
            System.out.println(line);
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file teks yang akan dibaca: ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah bagian yang diinginkan: ");
        int numberOfChunks = scanner.nextInt();

        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(fileName);
        fileProcessor.splitFile(numberOfChunks);

        scanner.close();
    }
}

