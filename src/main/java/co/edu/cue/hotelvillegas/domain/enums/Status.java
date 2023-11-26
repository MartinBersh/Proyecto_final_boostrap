package co.edu.cue.hotelvillegas.domain.enums;

public enum Status {
    AVAILABLE("Available"){
        @Override
        public String getStatus() {
            return "Room is available ";
        }
    },
    RESERVED("Reserved"){
        @Override
        public String getStatus() {
            return "Room is reserved";
        }
    },
    OCCUPIED("Occupied"){
        @Override
        public String getStatus() {
            return "Room is occupied";
        }
    };


    private final String value ;
    Status(String value) {
        this.value = value;
    }

    public abstract String getStatus();
}
