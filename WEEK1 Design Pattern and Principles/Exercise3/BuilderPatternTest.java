public class BuilderPatternTest {
    public static void main(String[] args) {

        Computer basicComputer = new Computer.Builder("Intel i5", "8GB").build();
        System.out.println("Basic Computer: " + basicComputer);


        Computer gamingComputer = new Computer.Builder("AMD Ryzen 7", "16GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .setSSD(true)
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
