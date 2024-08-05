public class Computer {

    private final String CPU;
    private final String RAM;
    private final String storage;
    private final String GPU;
    private final boolean isSSD;


    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.isSSD = builder.isSSD;
    }


    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getStorage() {
        return storage;
    }

    public String getGPU() {
        return GPU;
    }

    public boolean isSSD() {
        return isSSD;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage + ", GPU=" + GPU + ", SSD=" + isSSD + "]";
    }


    public static class Builder {

        private final String CPU;
        private final String RAM;


        private String storage = "500GB HDD";
        private String GPU = "Integrated";
        private boolean isSSD = false;


        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }


        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setSSD(boolean isSSD) {
            this.isSSD = isSSD;
            return this;
        }


        public Computer build() {
            return new Computer(this);
        }
    }
}
