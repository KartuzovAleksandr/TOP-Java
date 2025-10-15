void main() {
    try (FileInputStream fin = new FileInputStream("in.txt"))
    {
        byte[] buffer = fin.readAllBytes();
        IO.println("Файл прочитан:");
        IO.println(new String(buffer, StandardCharsets.UTF_8));
    }
    catch (IOException ex) {
        IO.println(ex.getMessage());
    }
}