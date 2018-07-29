package services;

public abstract class ApiResponse<T> {
    public void success(T value){};
    public void fail(String msg){};
}
