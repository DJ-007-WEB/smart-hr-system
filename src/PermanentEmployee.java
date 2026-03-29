class PermanentEmployee extends Employee {

    private double salary;

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary + (salary * 0.1); // 10% bonus
    }
}