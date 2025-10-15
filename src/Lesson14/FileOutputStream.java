void main() {
    String text = "Привет мир"; // строка для записи
    try (FileOutputStream fos = new FileOutputStream("hello.txt"))
    {
        // перевод строки в байты
        byte[] buffer = text.getBytes();
        fos.write(buffer, 0, buffer.length);
        IO.println("Файл записан");
    }
    catch (IOException ex) {
        IO.println(ex.getMessage());
    }
}