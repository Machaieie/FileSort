
# Organizador de Arquivos por Extensão

Este projeto é um utilitário desenvolvido em Java para organizar automaticamente arquivos de um diretório com base em suas extensões. O programa agrupa arquivos em subdiretórios específicos, como `PDFs`, `Documentos`, e `Fotos`, e ignora subdiretórios existentes no diretório raiz. Ele foi projetado para funcionar de forma simples e eficiente, sem modificar a estrutura de pastas existente.

## Funcionalidades

- Organiza arquivos com as extensões especificadas em pastas específicas.
- Cria pastas automaticamente com base nas extensões (ex: `PDFs`, `Documentos`, `Fotos`).
- Ignora subdiretórios e organiza apenas os arquivos no nível raiz do diretório especificado.
- Funciona com várias extensões de arquivos e pode ser facilmente expandido para suportar mais tipos.

## Estrutura do Projeto

O projeto está estruturado em dois pacotes principais:

- **model**
  - `DirectoryPathProvider`: Classe responsável por fornecer o caminho do diretório a ser organizado.
  
- **service**
  - `FinderDirectory`: Classe principal que percorre o diretório e organiza os arquivos conforme suas extensões.

## Requisitos

- Java 8 ou superior
- Maven (se for utilizar para gerenciar dependências)

## Como usar

1. Clone o repositório para sua máquina local:

   ```bash
   git clone https://github.com/Machaieie/FileSort.git
   ```

2. Compile o projeto (caso esteja utilizando Maven):

   ```bash
   mvn clean install
   ```

3. Execute a aplicação fornecendo o caminho do diretório que deseja organizar.

   Exemplo de uso em código:

   ```java
   public class Main {
       public static void main(String[] args) {
           String directoryPath = "caminho/do/diretorio";
           DirectoryPathProvider directoryProvider = new DirectoryPathProvider(directoryPath);
           FinderDirectory finderDirectory = new FinderDirectory(directoryProvider);
           finderDirectory.find();
       }
   }
   ```

4. A aplicação criará as pastas para organizar os arquivos no diretório fornecido:
   - PDFs: Todos os arquivos com a extensão `.pdf`.
   - Documentos: Todos os arquivos com a extensão `.docx`.
   - Fotos: Todos os arquivos com as extensões de imagem como `.jpg`, `.jpeg`, `.png`, e `.gif`.
   
5. Qualquer subdiretório no diretório raiz será ignorado.

## Extensões Suportadas

Atualmente, as seguintes extensões são suportadas:
- `.pdf` (colocado na pasta `PDFs`)
- `.docx` (colocado na pasta `Documentos`)
- `.jpg`, `.jpeg`, `.png`, `.gif` (colocados na pasta `Fotos`)

Você pode adicionar mais extensões ao mapa de extensões no código para suportar mais tipos de arquivos.

## Contribuição

Se você deseja contribuir com o projeto, siga os seguintes passos:

1. Faça um Fork do projeto.
2. Crie um branch para sua feature (`git checkout -b feature/sua-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Faça o push para o branch (`git push origin feature/sua-feature`).
5. Abra um Pull Request.

Autor
`Edwin Machaieie`
