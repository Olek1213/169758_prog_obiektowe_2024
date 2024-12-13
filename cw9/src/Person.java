public record Person(String firstName,String lastName,int age,Address adress) {
    public Person(String firstName, String lastName, int age, Address adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(age<0) {
            this.age = 0;
        }
        else {
            this.age = age;
        }
        this.adress = adress;
    }
}
