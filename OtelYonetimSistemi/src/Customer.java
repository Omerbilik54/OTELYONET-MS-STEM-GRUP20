public class Customer {


        private int customerId;
        private String name;
        private String surname;
        private String phone;

        public Customer(int customerId, String name, String surname, String phone) {
            this.customerId = customerId;
            this.name = name;
            this.surname = surname;
            this.phone = phone;
        }

        // Getter - Setter
        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getPhone() {
            return phone;
        }

        // TXT formatı
        public String toFileString() {
            return customerId + "," + name + "," + surname + "," + phone;
        }

        @Override
        public String toString() {
            return "[Müşteri: " + customerId + "] "
                    + name + " " + surname
                    + " | Tel: " + phone;
        }
    }


