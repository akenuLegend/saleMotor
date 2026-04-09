package utils;

public interface DAOInterface<T> {
    public int insert(T t);
    public int delete(T t);

}