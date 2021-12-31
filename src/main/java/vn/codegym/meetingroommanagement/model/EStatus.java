package vn.codegym.meetingroommanagement.model;

public enum EStatus {
    FIXING("Đang sửa"),
    AVAILABLE("Sẵn sàng"),
    USING("Đang sử dụng");

    public final String label;

    private EStatus(String label){
        this.label = label;
    }
}