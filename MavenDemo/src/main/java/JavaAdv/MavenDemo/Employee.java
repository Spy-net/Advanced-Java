package JavaAdv.MavenDemo;

public class Employee {
    
    private int E_id;
    private String E_Name;
    private int SALARY;
    private String Address;
    
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getE_id() {
        return E_id;
    }

    public void setE_id(int e_id) {
        this.E_id = e_id;
    }

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        this.E_Name = e_Name;
    }

    public int getSALARY() {
        return SALARY;
    }

    public void setSALARY(int sALARY) {
        this.SALARY = sALARY;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }   
    
    @Override
    public String toString() {
        return "Employee [E_id=" + E_id + ", E_Name=" + E_Name + ", SALARY=" + SALARY + ", Address=" + Address + "]";
    }

}
