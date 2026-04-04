package services;

public class MotorImportService {
  
     private List<MotorbikeInstance> kho = new ArrayList<>();

    public void addMotorbike(MotorbikeInstance xe) {

    }

    public void showKho() {
        for (MotorbikeInstance m : kho) {
            System.out.println(m);
        }
    }

    public void sellMotorbike(String vin, Customer c) {
        for (MotorbikeInstance m : kho) {
            if (m.getVin().equals(vin) && m.getStatus() == Status.IN_STOCK) {
                m.setStatus(Status.SOLD);
                System.out.println("Ban thanh cong!");
                return;
            }
        }
    }

    public void addMaintenance(String vin, String note) {
        System.out.println("Bao hanh: " + vin + " - " + note);
    }
}