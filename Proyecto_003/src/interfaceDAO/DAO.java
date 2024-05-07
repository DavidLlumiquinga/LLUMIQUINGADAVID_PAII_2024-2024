package interfaceDAO;

import java.util.List;

public interface DAO<T> {
	public T get(int id);

	public List<T> getAll();

	public void create(T t);

	public void update(T t);

	public void delete(T t);
}
