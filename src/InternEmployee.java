class InternEmployee extends Employee {

    private double stipend;

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }

    @Override
    public double calculateSalary() {
        return stipend + 2000; // performance bonus
    }
}