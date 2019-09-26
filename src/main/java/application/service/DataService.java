package application.service;

import application.model.Data;

import java.util.List;
import java.util.Optional;

public interface DataService {
    void addData(Data data);

    Optional<Data> findDataById(long id);

    List<Data> getAllData();
}
