import java.util.*;

public class HardwareStore {
    private List<Laptop> laptops;

    public HardwareStore() {
        this.laptops = new ArrayList<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public List<Laptop> filterLaptops(Map<String, Object> filterCriteria) {
        List<Laptop> filteredLaptops = new ArrayList<>(laptops);
        for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            filteredLaptops.removeIf(laptop -> {
                if (key.equals("ОЗУ")) {
                    return laptop.getRam() < (int) value;
                } else if (key.equals("Объем ЖД")) {
                    return laptop.getStorageVolume() < (int) value;
                } else if (key.equals("Операционная система")) {
                    return !laptop.getOperatingSystem().equals(value);
                } else if (key.equals("Цвет")) {
                    return !laptop.getColor().equals(value);
                }
                return false;
            });
        }
        return filteredLaptops;
    }

    public static void main(String[] args) {
        HardwareStore hardwareStore = new HardwareStore();
        // Add some laptops
        hardwareStore.addLaptop(new Laptop(8, 512, "Windows", "Black"));
        hardwareStore.addLaptop(new Laptop(16, 1024, "MacOS", "Silver"));
        hardwareStore.addLaptop(new Laptop(4, 256, "Linux", "Red"));

        // Ask user for filter criteria
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filterCriteria = new HashMap<>();
        System.out.println("Введите число, соответствующее необходимому критерию:");
        System.out.println("1 - ОЗУ ");
        System.out.println("2 - Объем ЖД ");
        System.out.println("3 - Операционная система ");
        System.out.println("4 - Цвет ");

        System.out.print("Введите номер фильтра: ");
        int filterNumber = scanner.nextInt();
        String filterKey;
        Object filterValue;
        switch (filterNumber) {
            case 1:
                filterKey = "ОЗУ";
                System.out.print("Введите минимальный объем оперативной памяти ' 4, 8, 16 ' : ");
                filterValue = scanner.nextInt();
                break;
            case 2:
                filterKey = "Объем ЖД";
                System.out.print("Введите минимальный объем ЖД '256, 512, 1024' : ");
                filterValue = scanner.nextInt();
                break;
            case 3:
                filterKey = "Операционная система";
                System.out.print("Введите желаемую операционную систему 'Windows, MacOs, Linux' ");
                scanner.nextLine(); 
                filterValue = scanner.nextLine();
                break;
            case 4:
                filterKey = "Цвет";
                System.out.print("Введите желаемый цвет 'black, silver, red' ");
                scanner.nextLine(); 
                filterValue = scanner.nextLine();
                break;
            default:
                System.out.println("Неверный номер фильтра.");
                return;
        }
        filterCriteria.put(filterKey, filterValue);

        List<Laptop> filteredLaptops = hardwareStore.filterLaptops(filterCriteria);
        if (filteredLaptops.isEmpty()) {
            System.out.println("Ни один ноутбук не соответствует критериям фильтра.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям фильтра:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println("ОЗУ: " + laptop.getRam() + ", Хранилище: " + laptop.getStorageVolume()
                        + ", ОС: " + laptop.getOperatingSystem() + ", Цвет: " + laptop.getColor());
            }
        }
    }
}
