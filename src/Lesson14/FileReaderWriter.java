void main() {
    String s = "";
    try (FileReader reader = new FileReader("in.txt"))
    {
        s = reader.readAllAsString();
        IO.print(s);
    }
    catch (IOException ex) {
        IO.println(ex.getMessage());
    }
    try (var writer = new FileWriter("out.txt"))
    {
        s = s.toUpperCase();
        writer.write(s);
        IO.println("Записан файл out.txt");
    }
    catch (IOException ex) {
        IO.println(ex.getMessage());
    }
}