package service;

import model.DirectoryPathProvider;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FinderDirectory {

    private final DirectoryPathProvider directoryPathProvider;

    // Mapeamento de extensões de arquivos para suas respectivas pastas
    private final Map<String, String> extensionToFolder = new HashMap<>();

    public FinderDirectory(DirectoryPathProvider directoryPathProvider) {
        this.directoryPathProvider = directoryPathProvider;
        initializeExtensionsMap();
    }

    // Inicializa o mapeamento de extensões para as pastas correspondentes
    private void initializeExtensionsMap() {
        extensionToFolder.put("pdf", "PDFs");
        extensionToFolder.put("pptx", "Apresentacoes");
        extensionToFolder.put("xls", "Excels");
        extensionToFolder.put("xlsx", "Excels");
        extensionToFolder.put("docx", "Documentos");
        extensionToFolder.put("doc", "Documentos");
        extensionToFolder.put("m4a", "Musicas");
        extensionToFolder.put("jpg", "Fotos");
        extensionToFolder.put("jpeg", "Fotos");
        extensionToFolder.put("png", "Fotos");
        extensionToFolder.put("gif", "Fotos");

    }

    // Método para iniciar a navegação no diretório
    public void find() {
        String directoryPath = directoryPathProvider.getDirectoryPath();
        Path directory = Paths.get(directoryPath);

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    try {
                        String fileExtension = getFileExtension(file);
                        if (extensionToFolder.containsKey(fileExtension)) {
                            String folderName = extensionToFolder.get(fileExtension);
                            moveFileToFolder(file, folderName);
                        } else {
                            System.out.println("Extensão desconhecida: " + fileExtension + " para o arquivo: " + file.getFileName());
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao processar o arquivo: " + file.getFileName());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {

                    if (!dir.equals(directory)) {
                        System.out.println("Ignorando diretório: " + dir.getFileName());
                        return FileVisitResult.SKIP_SUBTREE; // Não entra nos subdiretórios
                    }
                    return FileVisitResult.CONTINUE; // Continua no diretório raiz
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para pegar a extensão do arquivo
    private String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        int lastDotIndex = fileName.lastIndexOf('.');

        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1).toLowerCase(); // Retorna a extensão em minúsculas
        }
        return "Sem extensão";
    }

    // Método para mover o arquivo para a pasta correspondente
    private void moveFileToFolder(Path file, String folderName) {
        try {
            // Criar o diretório se ele não existir
            Path targetDirectory = Paths.get(directoryPathProvider.getDirectoryPath(), folderName);
            if (Files.notExists(targetDirectory)) {
                Files.createDirectory(targetDirectory);
                System.out.println("Diretório '" + folderName + "' criado.");
            }

            // Mover o arquivo para o diretório correspondente
            Path targetPath = targetDirectory.resolve(file.getFileName());
            Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo movido: " + file.getFileName() + " para " + targetDirectory);
        } catch (IOException e) {
            System.out.println("Erro ao mover o arquivo: " + file.getFileName() + " para " + folderName + ": " + e.getMessage());
        }
    }

}
