public class Laptop {
    private int ram;
    private int storageVolume;
    private String operatingSystem;
    private String color;

    public Laptop(int ram, int storageVolume, String operatingSystem, String color) {
        this.ram = ram;
        this.storageVolume = storageVolume;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorageVolume() {
        return storageVolume;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }
}
